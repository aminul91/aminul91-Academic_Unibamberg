package de.uniba.rz.queue;

import java.util.ArrayList;
import java.util.List;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.NamingException;

import de.uniba.rz.entities.Ticket;

public class QueueReceiver  {

	private ConnectionFactory connFactory;
	private Destination destination;
	private JMSContext jmsContext;
	private JMSConsumer jmsConsumer;
	private List<Ticket> tickets = new ArrayList<>();
//	private boolean active;

	public QueueReceiver(Context ctx, String connFactoryName, String queueName)
			throws NamingException {

		// Look up the connection factory object in the JNDI context provided
		connFactory = (ConnectionFactory) ctx.lookup(connFactoryName);
		
		// Look up the Destination in the JNDI context
		destination = (Destination) ctx.lookup(queueName);
		
		jmsContext = connFactory.createContext();
		
		jmsConsumer = jmsContext.createConsumer(destination);
	}

//	public void startServer() {
//		
//		this.active = true;
//		
//		try (JMSContext jmsContext = connFactory.createContext()) {
//			
//			JMSConsumer jmsConsumer = jmsContext.createConsumer(destination);
//			
//			while(active) {
//				Ticket ticket = jmsConsumer.receiveBody(Ticket.class, 5000);
//				
//				if(ticket != null) {
//					System.out.println(ticket);
//					tickets.add(ticket);
//				}
//			}
//			
//			jmsConsumer.close();
//			jmsContext.close();
//		}
//	}
	
//	public void stopServer() {
//		this.active = false;
//	}
	
	public List<Ticket> receiveAllTickets() {
		// Create JMSContext
		//return tickets;
		
		boolean active = true;
		while(active) {
			Ticket ticket = jmsConsumer.receiveBody(Ticket.class, 1000);
			
			if(ticket != null) {
				System.out.println(ticket);
				tickets.add(ticket);
			}
			else {
				active = false;
			}
		}
		return tickets;
	}
	
//	@Override
//	public void run() {
//		startServer();
//	}
	
	public void freeResources() {
		jmsConsumer.close();
		jmsContext.close();
	}

}

