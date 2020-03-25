package de.uniba.rz.app.udpimplementation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.uniba.rz.app.TicketManagementBackend;
import de.uniba.rz.entities.MessageBody;
import de.uniba.rz.entities.MessageDTO;
import de.uniba.rz.entities.MessageType;
//import de.uniba.rz.app.implementation.udp.UDPSender;
import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Status;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.TicketException;
import de.uniba.rz.entities.Type;


public class TicketManagementBackendUdp implements TicketManagementBackend {
	
	private PacketSender sender;
	private PacketReceiver receiver;
    private AtomicInteger nextId;
    private DatagramSocket socket;
    private Map<Integer, Ticket> tickets = new HashMap<>();

    public TicketManagementBackendUdp() throws TicketException {
        nextId = new AtomicInteger(1);
        try {
            this.socket = new DatagramSocket();
            this.socket.setSoTimeout(5000);
        } catch (SocketException e) {
            throw new TicketException("Socket not found!");
        }
        sender = new PacketSender(this.socket);
        receiver = new PacketReceiver(this.socket);
    }


	@Override
	public void triggerShutdown() {
		// TODO Auto-generated method stub
		socket.close();
	}

	@Override
	public Ticket createNewTicket(String reporter, String topic, String description, Type type, Priority priority)
			throws TicketException {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket(nextId.getAndIncrement(), reporter, topic, description, type, priority, Status.NEW);
		sendUDPPacketToServer(MessageType.SAVE, ticket, 0);
		return ticket;
	}
	
	public void sendUDPPacketToServer(int messageType, Ticket ticket, int optionalTicketId) {
		
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(6400);
		ObjectOutputStream objectOutputStream = null;
		
		MessageDTO messageDto = new MessageDTO();
		MessageBody messageBody = new MessageBody();
		try {
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			messageDto.setMessageType(messageType);
			if(messageType == MessageType.SAVE)
			{
				messageBody.setSingleTicket(ticket);
				this.tickets.put(ticket.getId(), ticket);
			}
			else if(messageType == MessageType.RETRIEVE_ONE) {
				messageDto.setOptionalTicketId(optionalTicketId);
			}
			else if(messageType == MessageType.UPDATE) {
				messageDto.setOptionalTicketId(optionalTicketId);
				messageBody.setSingleTicket(ticket);
				this.tickets.put(ticket.getId(), ticket);
			}
			messageDto.setMessageBody(messageBody);
			objectOutputStream.writeObject(messageDto);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final byte[] data = byteArrayOutputStream.toByteArray();
		DatagramPacket packet = null;
		try {
			packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5556);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sender.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Ticket> getAllTickets() throws TicketException {
		
		List<Ticket> ticketList;
//		this.tickets.forEach((key, value) -> ticketList.add(value));
		sendUDPPacketToServer(MessageType.RETRIEVE_ALL, null, 0);
		ticketList = receiver.receive();
		if(ticketList == null) {
			ticketList = new ArrayList<>();
		}
		else {
			ticketList.forEach(ticket -> this.tickets.put(ticket.getId(), ticket));
		}
		return ticketList;
	}

	@Override
	public Ticket getTicketById(int id) throws TicketException {

//		return new Ticket(1, "aa", "bb", "cc", Type.BUG, Priority.CRITICAL);//this.tickets.get(id);
//		Ticket singleTicket = null;
		sendUDPPacketToServer(MessageType.RETRIEVE_ONE, null, id);
		List<Ticket> receivedTicket = receiver.receive();
		if(!receivedTicket.isEmpty()) {
			this.tickets.put(receivedTicket.get(0).getId(), receivedTicket.get(0));
		}
		return receivedTicket.get(0);
	}

	@Override
	public Ticket acceptTicket(int id) throws TicketException {
		
		Ticket ticket = this.tickets.get(id);
		if(ticket.getStatus() != Status.NEW) {
			throw new TicketException("Can no accept ticket as it is in status: "+ticket.getStatus());
		}
		ticket.setStatus(Status.ACCEPTED);
		this.tickets.put(ticket.getId(), ticket);//public void sendUDPPacketToServer(int messageType, Ticket ticket, int optionalTicketId)
		sendUDPPacketToServer(MessageType.UPDATE, ticket, ticket.getId());
		
		return (Ticket) ticket.clone();
	}

	@Override
	public Ticket rejectTicket(int id) throws TicketException {
		
		Ticket ticket = this.tickets.get(id);
		if(ticket.getStatus() != Status.NEW) {
			throw new TicketException("Can no reject ticket as it is in status: "+ticket.getStatus());
		}
		ticket.setStatus(Status.REJECTED);
		this.tickets.put(ticket.getId(), ticket);
		sendUDPPacketToServer(MessageType.UPDATE, ticket, ticket.getId());
		
		return (Ticket) ticket.clone();
	}

	@Override
	public Ticket closeTicket(int id) throws TicketException {
		
		Ticket ticket = this.tickets.get(id);
		if(ticket.getStatus() != Status.ACCEPTED) {
			throw new TicketException("Can no close ticket as it is in status: "+ticket.getStatus());
		}
		ticket.setStatus(Status.CLOSED);
		this.tickets.put(ticket.getId(), ticket);
		sendUDPPacketToServer(MessageType.UPDATE, ticket, ticket.getId());
		
		return (Ticket) ticket.clone();
	}
	
    private List<Ticket> packetCombined() throws TicketException {
        boolean gotResponse = false;

        List<Ticket> ticketList = new ArrayList<>();
        ArrayList<Integer> packetList = new ArrayList<>();
        while (!gotResponse) {
            byte[] receiveData = new byte[600];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                this.socket.receive(receivePacket);
            } catch (SocketTimeoutException e) {
                throw new TicketException("");
            } catch (IOException e) {
            	throw new TicketException("");
            }

//
//            int packetNumber = MessageDTO.getPacketNumber();
//            int totalPacketNumber = MessageDTO.getTotalPacketNumber();
//
//            if (!packetList.contains(packetNumber)) {
//                packetList.add(packetNumber);
//                ticketList.addAll(MessageDTO.getTickets());
//            }
//            if (packetList.size() == totalPacketNumber) {
//                gotResponse = true;
//            }
        }
        Collections.sort(ticketList, new Comparator<Ticket>() {
            public int compare(Ticket ticket1, Ticket ticket2) {
                return Float.compare(ticket1.getId(), ticket2.getId());
            }
        });
        return ticketList;
    }

}
