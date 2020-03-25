package de.uniba.rz.backendWebService;

import de.uniba.rz.backend.RemoteAccess;
import de.uniba.rz.backend.TicketStore;
import javax.xml.ws.Endpoint;

public class RemoteAccessWebService implements RemoteAccess {
//	private int serverPort;
	private boolean isRunning = false;
//	PacketSender Sender;
	TicketStore ticketStore;

	Endpoint endpoint = null;

	public RemoteAccessWebService() {
////		this.serverPort = serverPort;
//		this.serverSocket = null;
//		try {
//			serverSocket = new DatagramSocket(serverPort);
////			Sender = new PacketSender(serverPort, serverSocket);
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (isRunning) {
//			requestProcessing();
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
		this.endpoint.stop();
		// TODO Auto-generated method stub
		isRunning = false;
//        this.udpSender = null;
		this.ticketStore = null;
	}

	private void requestProcessing() {
		String url = "http://localhost:9999/server/TicketService";
		System.out.println("Starting Web Service Endpoint...");
		this.endpoint = Endpoint.publish(url, new TicketService(this.ticketStore));
		System.out.println("Endpoint available at " + url);
	}



}
