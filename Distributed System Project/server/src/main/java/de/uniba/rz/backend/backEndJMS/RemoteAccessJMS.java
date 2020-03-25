package de.uniba.rz.backend.backEndJMS;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.uniba.rz.backend.RemoteAccess;
import de.uniba.rz.backend.TicketStore;
import de.uniba.rz.entities.Status;
import de.uniba.rz.entities.Ticket;

public class RemoteAccessJMS implements RemoteAccess {

	
	
    QueueReceiverServer receiver;
    QueueSenderServer ticketObjectRequester;
	Context ctx;
	TicketStore ticketStore;
	List<Ticket> ticketObjects= new ArrayList<>();
	public boolean running;
	final Logger logging = Logger.getLogger(RemoteAccessJMS.class.getName());

	public RemoteAccessJMS(String localStoragePath, String sender, String ctx) {
		this.setupJMSConfigs(localStoragePath, sender, ctx);
	}
	
	public void setupJMSConfigs(String localStoragePath, String connFactoryName, String queueName) {
		
		Hashtable<String, String> contextParams = new Hashtable<>();
		
		contextParams.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		
		// Configuration specific
		contextParams.put(Context.PROVIDER_URL, localStoragePath);
		
		try {
			ctx = new InitialContext(contextParams);
			
			receiver = new QueueReceiverServer(ctx, connFactoryName, queueName);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<Ticket> ticketObjects;
	    while(running) {
	    	    
				ticketObjects=receiver.receiveAllTickets();
				///logging.info("msg"+ ticketObjects.toString());
				if(!ticketObjects.isEmpty()) {
					
					for(Ticket ticket: ticketObjects) {
						
						
							System.out.println("All tickets in the thread" + ticket.toString());
							this.ticketStore.storeNewTicket(ticket.getReporter(), ticket.getTopic(), ticket.getDescription(), ticket.getType(),
									ticket.getPriority());
						}
						
					 
					}
			
				
				else {
					System.out.println(" No tickets in the thread");
					
						
					
				}
					
				
				//Ticket ticket=ticketObjects.get(0);
				//ticketStore.storeNewTicket(ticket.getReporter(), ticket.getTopic(), ticket.getDescription(), ticket.getType(),
				//		ticket.getPriority());
				//System.out.println("ticket 1" + ticketStore.getAllTickets());
				
			}
		
		//receiver.run();

	}

	@Override
	public void prepareStartup(TicketStore ticketStore) {
		// TODO Auto-generated method stub
        running=true;
		this.ticketStore=ticketStore;
		receiver.start();
		ticketObjects=receiver.receiveAllTickets();
		
		if(!ticketObjects.isEmpty()) {
			for(Ticket ticket: ticketObjects) {
				
					
					this.ticketStore.storeNewTicket(ticket.getReporter(), ticket.getTopic(), ticket.getDescription(), ticket.getType(),
							ticket.getPriority());
				}
				
			 
			}
	
		
		else {
			System.out.println(" No tickets in the thread");
			
		}
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

		running=false;
		
	}

}

