package de.uniba.rz.backendUdp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import de.uniba.rz.backend.RemoteAccess;
import de.uniba.rz.backend.TicketStore;
import de.uniba.rz.backend.UnknownTicketException;
import de.uniba.rz.entities.MessageBody;
import de.uniba.rz.entities.MessageDTO;
import de.uniba.rz.entities.MessageType;
import de.uniba.rz.entities.Ticket;

public class RemoteAccessUdp implements RemoteAccess {
//	private int serverPort;
	private boolean isRunning = false;
//	PacketSender Sender;
	TicketStore ticketStore;
	DatagramSocket serverSocket;

	public RemoteAccessUdp(int serverPort) {
//		this.serverPort = serverPort;
		 this.serverSocket = null;
		try {
			serverSocket = new DatagramSocket(serverPort);
//			Sender = new PacketSender(serverPort, serverSocket);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (isRunning) {
			requestProcessing();
		}
	}

	@Override
	public void prepareStartup(TicketStore ticketStore) {
		this.ticketStore = ticketStore;
		isRunning = true;

		requestProcessing();
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
        isRunning = false;
//        this.udpSender = null;
        this.ticketStore = null;
	}
	
	private void requestProcessing() {
		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

		try {
			serverSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final byte[] data = receivePacket.getData();
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Ticket ticket = null;
		MessageDTO messageDto = null;
		try {
			messageDto = (MessageDTO) is.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int action = messageDto.getMessageType();
		
		switch(action) {
			case MessageType.SAVE:
				Ticket ticket = messageDto.getMessageBody().getSingleTicket();
				this.ticketStore.storeNewTicket(ticket.getReporter(), ticket.getTopic(), ticket.getDescription(), ticket.getType(), ticket.getPriority());
				break;
				
			case MessageType.RETRIEVE_ONE:
				List<Ticket> ticketList = this.ticketStore.getAllTickets();
				List<Ticket> sendingTicket = new ArrayList<>();
				Ticket retrieveTicket = null;
				for (Ticket ticketFromList : ticketList) {
					if (ticketFromList.getId() == messageDto.getOptionalTicketId()) {
						retrieveTicket = ticketFromList;
						sendingTicket.add(retrieveTicket);
					}
				}				
				sendDataToClient(receivePacket.getAddress(), receivePacket.getPort(), serverSocket, sendingTicket);				
				break;
				
			case MessageType.RETRIEVE_ALL:
//	             List<Ticket> tickets = this.ticketStore.getAllTickets();
//	                
//	                int packetNumber = tickets.size() / 2;
//	                if (tickets.isEmpty()) {
//	                    packetNumber++;
//	                }
//
//			for (int i = 0, j = 1; j <= packetNumber; i += 2, j++) {                  
//	                    int limit = i + 2;
//	                    if (limit > tickets.size()) {
//	                    	limit = tickets.size();
//	                    }
//	                    List<Ticket> modifiedList = tickets.subList(i, limit);
//	                    sendDataToClient(receivePacket.getAddress(), receivePacket.getPort(), serverSocket, modifiedList);				
//	                }			
//					break;
				sendDataToClient(receivePacket.getAddress(), receivePacket.getPort(), serverSocket, this.ticketStore.getAllTickets());				
				break;
				
			case MessageType.UPDATE:
				try {
					this.ticketStore.updateTicketStatus(messageDto.getOptionalTicketId(), messageDto.getMessageBody().getSingleTicket().getStatus());
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownTicketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				sendDataToClient(receivePacket.getAddress(), receivePacket.getPort(), serverSocket, ticketListInStore);	
				break;
		}
	}
	
	public void sendDataToClient(InetAddress address, int port, DatagramSocket sender, List<Ticket> ticketList) {
        
		MessageDTO messageDto = new MessageDTO();
		if(ticketList.size() == 1)
		{
			messageDto.setMessageType(MessageType.SEND_ONE);
			messageDto.setMessageBody((new MessageBody(ticketList.get(0))));
		}
		else if(ticketList.size() > 1) {
			messageDto.setMessageType(MessageType.SEND_ALL);
			messageDto.setMessageBody((new MessageBody(ticketList)));
		}
		
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(6400);
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			objectOutputStream.writeObject(messageDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final byte[] data = byteArrayOutputStream.toByteArray();
		DatagramPacket packet = null;
		packet = new DatagramPacket(data, data.length, address, port);
		try {
			sender.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
