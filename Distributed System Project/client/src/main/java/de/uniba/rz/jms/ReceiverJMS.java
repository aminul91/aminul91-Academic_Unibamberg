package de.uniba.rz.jms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.uniba.rz.entities.Ticket;
import de.uniba.rz.queue.QueueReceiver;

public class ReceiverJMS {

	QueueReceiver receiver;
	
	Context ctx;

	public ReceiverJMS(String localStoragePath, String sender, String ctx) {
		this.setupJMSConfigs(localStoragePath, sender, ctx);
	}
	
	public void setupJMSConfigs(String localStoragePath, String connFactoryName, String queueName) {
		
		Hashtable<String, String> contextParams = new Hashtable<>();
		
		contextParams.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		
		// Configuration specific
		contextParams.put(Context.PROVIDER_URL, localStoragePath);
		
		try {
			ctx = new InitialContext(contextParams);
			
			receiver = new QueueReceiver(ctx, connFactoryName, queueName);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Ticket> receiveAllTickets() {
		List<Ticket> tickets = new ArrayList<>();
		//receiver.start();
		tickets = receiver.receiveAllTickets();
		//receiver.stopServer();
		return tickets;
	}
	
	public void freeResources() {
		try {
			receiver.freeResources();
			ctx.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
