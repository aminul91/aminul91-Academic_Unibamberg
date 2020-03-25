package de.uniba.rz.backendWebService;

import javax.jws.WebService;

import de.uniba.rz.backend.TicketStore;
import de.uniba.rz.backend.UnknownTicketException;
import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Status;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.Type;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService
public class TicketService {
	TicketStore ticketStore;

	public TicketService(TicketStore ticketStore) {
		this.ticketStore = ticketStore;
	}

	@WebMethod
	public Ticket createNewTicket(@WebParam(name = "reporter") String reporter, @WebParam(name = "topic") String topic,
			@WebParam(name = "description") String description, @WebParam(name = "typeOrdinal") int typeOrdinal,
			@WebParam(name = "priorityOrdinal") int priorityOrdinal) throws IllegalArgumentException {
		if (reporter == null || topic == null || description == null) {
			throw new IllegalArgumentException("Fields must not be null!");
		}

		return this.ticketStore.storeNewTicket(reporter, topic, description, Type.values()[typeOrdinal], Priority.values()[priorityOrdinal]);
	}
	
	@WebMethod
	public List<Ticket> getAllTicket(){
		
		return this.ticketStore.getAllTickets();
	}

}