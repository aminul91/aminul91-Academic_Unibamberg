package de.uniba.rz.backendUdp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PacketSender {
	private int serverPort;
    private static DatagramSocket serversocket;

    public PacketSender(int serverPort, DatagramSocket serverSocket) {
        this.serversocket = serverSocket;
        this.serverPort = serverPort;
    }

    public static void send(DatagramPacket packet) throws IOException {
    	serversocket.send(packet);
    }
}
