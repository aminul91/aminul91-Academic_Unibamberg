package de.uniba.rz.entities;

import java.io.Serializable;
import java.util.List;

public class MessageBody implements Serializable{

	private static final long serialVersionUID = -56757783499460508L;
	
	private List<Ticket> tickets;
	private Ticket singleTicket;

	public MessageBody() {
	}

	public MessageBody(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public MessageBody(Ticket ticket) {
		this.singleTicket = ticket;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket getSingleTicket() {
		return singleTicket;
	}

	public void setSingleTicket(Ticket singleTicket) {
		this.singleTicket = singleTicket;
	}

	@Override
	public String toString() {
		return "MessageBody [tickets=" + tickets + ", singleTicket=" + singleTicket + "]";
	}

}
