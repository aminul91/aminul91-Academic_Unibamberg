package de.uniba.rz.jms;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.uniba.rz.entities.Ticket;
import de.uniba.rz.queue.QueueSender;

public class SenderJMS {

	QueueSender sender;
	
	Context ctx;

	public SenderJMS(String localStoragePath, String sender, String ctx) {
		this.setupJMSConfigs(localStoragePath, sender, ctx);
	}
	
	public void setupJMSConfigs(String localStoragePath, String connFactoryName, String queueName) {
		
		Hashtable<String, String> contextParams = new Hashtable<>();
		
		contextParams.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		
		// Configuration specific
		contextParams.put(Context.PROVIDER_URL, localStoragePath);
		
		try {
			ctx = new InitialContext(contextParams);
			
			sender = new QueueSender(ctx, connFactoryName, queueName);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveTicket(Ticket ticket) {
		sender.saveTicket(ticket);
	}
	
	public void freeResources() {
		try {
			ctx.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
