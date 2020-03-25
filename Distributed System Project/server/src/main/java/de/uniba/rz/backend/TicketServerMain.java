package de.uniba.rz.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;


import de.uniba.rz.backend.backEndJMS.RemoteAccessJMS;
import de.uniba.rz.backendUdp.RemoteAccessUdp;
import de.uniba.rz.backendWebService.RemoteAccessWebService;
import de.uniba.rz.backendgrpc.RemoteAccessGrpc;


public class TicketServerMain {

	public static void main(String[] args) throws IOException, NamingException {
		TicketStore simpleTestStore = new SimpleTicketStore();

		List<RemoteAccess> remoteAccessImplementations = getAvailableRemoteAccessImplementations(args);

		// Starting remote access implementations:
		for (RemoteAccess implementation : remoteAccessImplementations) {
			implementation.prepareStartup(simpleTestStore);
			new Thread(implementation).start();
		}

		try (BufferedReader shutdownReader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Press enter to shutdown system.");
			shutdownReader.readLine();
			System.out.println("Shutting down...");
	
			// Shuttung down all remote access implementations
			for (RemoteAccess implementation : remoteAccessImplementations) {
				implementation.shutdown();
			}
			System.out.println("completed. Bye!");
		}
	}

	private static List<RemoteAccess> getAvailableRemoteAccessImplementations(String[] args) {
        List<RemoteAccess> implementations = new ArrayList<RemoteAccess>();
        RemoteAccessUdp remoteAccessUDPImpl;
        RemoteAccessJMS remoteAccessJMS;
        RemoteAccessGrpc remoteAccessGrpc;
        RemoteAccessWebService remoteAccessWS;

        switch (args[0]) {
            case "UDP":
                remoteAccessUDPImpl = new RemoteAccessUdp(5556);
                implementations.add(remoteAccessUDPImpl);
                System.out.println("UDP at port " + 5556);
                break;
            case "JMS":
                // todo: implement JMS
            	if(args.length < 4)
            		System.out.println("Not enough arguments to run JMS");
					
            	String sender = args[1];
				String ctx = args[2];
				String localStoragePath = args[3];
				remoteAccessJMS= new RemoteAccessJMS(localStoragePath, sender, ctx);
				System.out.println("JMS Server Started");
				implementations.add(remoteAccessJMS);
				break;
            case "GRPC":
            	remoteAccessGrpc = new RemoteAccessGrpc();
                implementations.add(remoteAccessGrpc);
                break;
            case "WS":
            	remoteAccessWS = new RemoteAccessWebService();
                implementations.add(remoteAccessWS);
                break;
            	
            default:
                System.out.println("Unknown backend type. Using local backend implementation.");
        }

        return implementations;
	}
}
