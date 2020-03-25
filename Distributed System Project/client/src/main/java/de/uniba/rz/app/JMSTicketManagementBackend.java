package de.uniba.rz.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Status;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.TicketException;
import de.uniba.rz.entities.Type;
//import de.uniba.rz.jms.ReceiverJMS;
import de.uniba.rz.jms.SenderJMS;

public class JMSTicketManagementBackend implements TicketManagementBackend {

	//HashMap<Integer, Ticket> localTicketStore = new HashMap<>();
	
	private SenderJMS senderJMS;
	
//	private ReceiverJMS receiverJMS; 
	
	private List<Ticket> tickets = new ArrayList<>();

	AtomicInteger nextId;

	public JMSTicketManagementBackend(String localStoragePath, String sender, String ctx) {
		nextId = new AtomicInteger(1);
		senderJMS = new SenderJMS(localStoragePath, sender, ctx);
//		receiverJMS = new ReceiverJMS(localStoragePath, sender, ctx);
	}

	
	
	@Override
	public void triggerShutdown() {
		// local implementation is in memory only - no need to close connections
		// and free resources
		senderJMS.freeResources();
//		receiverJMS.freeResources();
	}

	@Override
	public Ticket createNewTicket(String reporter, String topic, String description, Type type, Priority priority) {
		Ticket newTicket = new Ticket(nextId.getAndIncrement(), reporter, topic, description, type, priority);
		//localTicketStore.put(newTicket.getId(), newTicket);
		tickets.add(newTicket);
		senderJMS.saveTicket(newTicket);

		return newTicket;
	}

	@Override
	public List<Ticket> getAllTickets() throws TicketException {
		
//		tickets =  receiverJMS.receiveAllTickets();
		return tickets;
	}

	@Override
	public Ticket getTicketById(int id) throws TicketException {
		
		for(Ticket ticket : tickets) {
			if(ticket.getId() == id) {
				return ticket;
			}
		}
		return null;
	}

//	private Ticket getTicketByIdInteral(int id) throws TicketException {
//		if (!localTicketStore.containsKey(id)) {
//			throw new TicketException("Ticket ID is unknown");
//		}
//
//		return localTicketStore.get(id);
//	}

	@Override
	public Ticket acceptTicket(int id) throws TicketException {

		Ticket ticketToModify = getTicketById(id);
		if (ticketToModify.getStatus() != Status.NEW) {
			throw new TicketException(
					"Can not accept Ticket as it is currently in status " + ticketToModify.getStatus());
		}

		ticketToModify.setStatus(Status.ACCEPTED);
		return (Ticket) ticketToModify.clone();
	}

	@Override
	public Ticket rejectTicket(int id) throws TicketException {

		Ticket ticketToModify = getTicketById(id);
		if (ticketToModify.getStatus() != Status.NEW) {
			throw new TicketException(
					"Can not reject Ticket as it is currently in status " + ticketToModify.getStatus());
		}

		ticketToModify.setStatus(Status.REJECTED);
		return (Ticket) ticketToModify.clone();
	}

	@Override
	public Ticket closeTicket(int id) throws TicketException {

		Ticket ticketToModify = getTicketById(id);
		if (ticketToModify.getStatus() != Status.ACCEPTED) {
			throw new TicketException(
					"Can not close Ticket as it is currently in status " + ticketToModify.getStatus());
		}

		ticketToModify.setStatus(Status.CLOSED);
		return (Ticket) ticketToModify.clone();
	}

}
