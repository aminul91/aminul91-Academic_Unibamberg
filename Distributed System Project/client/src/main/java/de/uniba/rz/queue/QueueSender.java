package de.uniba.rz.queue;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.NamingException;

import de.uniba.rz.entities.Ticket;

public class QueueSender {

	ConnectionFactory connFactory;
	Destination destination;

	public QueueSender(Context ctx, String connFactoryName, String queueName)
			throws NamingException {

		// Look up the connection factory object in the JNDI context provided
		connFactory = (ConnectionFactory) ctx.lookup(connFactoryName);
		
		// Look up the Destination in the JNDI context
		destination = (Destination) ctx.lookup(queueName);
	}

	public void saveTicket(Ticket ticket) {
		// Create JMSContext
		try (JMSContext jmsContext = connFactory.createContext()) {

			// Create a Producer and use it to send a TextMessage
			jmsContext.createProducer().send(destination, ticket);
			
			jmsContext.close();
		}
	}
}

