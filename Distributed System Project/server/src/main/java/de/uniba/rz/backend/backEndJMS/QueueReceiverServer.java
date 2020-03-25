package de.uniba.rz.backend.backEndJMS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.NamingException;
import de.uniba.rz.entities.Ticket;

public class QueueReceiverServer extends Thread {
	
	private ConnectionFactory connFactory;
	private Destination destination;

	private boolean active;
	
	
	private JMSContext jmsContext;
	private JMSConsumer jmsConsumer;
	final Logger logging = Logger.getLogger(QueueReceiverServer.class.getName());

	
	public QueueReceiverServer(Context ctx, String connFactoryName, String queueName)
			throws NamingException {
		
		// Look up the connection factory object in the JNDI context provided
		connFactory = (ConnectionFactory) ctx.lookup(connFactoryName);

		// Look up the Destination in the JNDI context
		destination = (Destination) ctx.lookup(queueName);
		
        jmsContext = connFactory.createContext();
		
		jmsConsumer = jmsContext.createConsumer(destination);
	  
	}

	
	
	
	
	public void start() {
		// TODO Auto-generated method stub
		     System.out.println("\t [RECEIVER]: Start waiting for Tickets");
		    active = true;
	     	List<Ticket> ticketColl;
			ticketColl=receiveAllTickets();
			logging.info("msg"+ ticketColl.toString());
			if (ticketColl.isEmpty()) {
					String message="No ticket Found in the Queue";
					System.out.println("\t [RECEIVER]: >Received: " + message);
				}
				else {
					for(Ticket singleTicket: ticketColl) {
				System.out.println("\t [RECEIVER]: >Received: " + "\n" + "Ticket Info from the queue"+ "id:"+ singleTicket.getId()+ "Infor"+ singleTicket.toString());
					}					
				}
			
		
		//super.start();
	
  
	}
	public List<Ticket> receiveAllTickets() {
		// TODO Auto-generated method stub
	
		    List<Ticket> tickets = new ArrayList<>();
			Ticket ticket = jmsConsumer.receiveBody(Ticket.class, 1000);
			
			if(ticket != null) {
				tickets.add(ticket);
			}
			else {
				active = false;
			}
			
			return tickets;
	}
	
	public void shutdownJMS() {
		
		jmsConsumer.close();
		jmsContext.close();
		
	}
	
	
	
	

}
