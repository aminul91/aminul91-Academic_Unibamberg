package de.uniba.rz.app.udpimplementation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import de.uniba.rz.entities.TicketException;

public class PacketSender {
	private DatagramSocket socket;

	public PacketSender(DatagramSocket serverSocket) {
		this.socket = serverSocket;
	}

	public PacketSender() throws TicketException {

		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void send(DatagramPacket packet) throws IOException {
		socket.send(packet);
	}
}
