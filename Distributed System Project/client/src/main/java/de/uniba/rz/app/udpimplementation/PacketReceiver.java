package de.uniba.rz.app.udpimplementation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import de.uniba.rz.entities.MessageDTO;
import de.uniba.rz.entities.MessageType;
import de.uniba.rz.entities.Ticket;

public class PacketReceiver {
	
	private DatagramSocket datagramSocket;
	
	public PacketReceiver(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
	}
	
	public PacketReceiver() {
		try {
			datagramSocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Ticket> receive() {
		
		List<Ticket> tickets = new ArrayList<>();
		
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		
		try {
			this.datagramSocket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ArrayList<Ticket>();
		} 
		System.out.println("Received packet size in client: "+data.length);
		
		data = packet.getData();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		ObjectInputStream objectStream = null;
		MessageDTO message = new MessageDTO();
		
		try {
			objectStream = new ObjectInputStream(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			message = (MessageDTO) objectStream.readObject();
//			System.out.println(tickets.get(0));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException exception) {
			return new ArrayList<Ticket>();
		}
		
		if(message != null) {
			if(message.getMessageType() == MessageType.SEND_ALL)
				tickets = message.getMessageBody().getTickets();
			else if(message.getMessageType() == MessageType.SEND_ONE)
				tickets.add(message.getMessageBody().getSingleTicket());
		}
		return tickets;
	}
}
