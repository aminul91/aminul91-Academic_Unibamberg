package de.uniba.rz.backend;

import java.util.List;

import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Status;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.Type;

public class TicketStoreImplementationviaJMS implements TicketStore {
	
	

	@Override
	public Ticket storeNewTicket(String reporter, String topic, String description, Type type, Priority priority) {
		// TODO Auto-generated method stub
		
		System.out.println("Creating new Ticket from Reporter: " + reporter + " with the topic \"" + topic + "\"");
		int nextTicketId = 0;
		Ticket newTicket = new Ticket(nextTicketId++, reporter, topic, description, type, priority);
		
		return newTicket;
	}

	@Override
	public void updateTicketStatus(int ticketId, Status newStatus)
			throws UnknownTicketException, IllegalStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		return null;
	}

}
