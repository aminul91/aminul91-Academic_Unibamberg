package de.uniba.rz.app.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import de.uniba.rz.app.TicketManagementBackend;
import de.uniba.rz.backendwebservice.TicketService;
import searchservice.SearchServiceInterface;

import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Status;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.TicketException;
import de.uniba.rz.entities.Type;

public class TicketManagementBackendWS implements TicketManagementBackend {
	private static final String wsdlLocation = "http://localhost:9999/server/TicketService?wsdl";
	private static final String searchServiceLocation = "http://localhost:8080/searchService?wsdl";
	List<Ticket> ticketList;

	@Override
	public void triggerShutdown() {
		// TODO Auto-generated method stub

	}

	public TicketManagementBackendWS() {
		// TODO Auto-generated constructor stub
		this.ticketList = new ArrayList<Ticket>();
	}

	@Override
	public Ticket createNewTicket(String reporter, String topic, String description, Type type, Priority priority)
			throws TicketException {
		// TODO Auto-generated method stub
		URL location = null;
		try {
			location = new URL(wsdlLocation);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String namespace = "http://backendWebService.rz.uniba.de/";
		QName serviceName = new QName(namespace, "TicketServiceService");
		Service service = Service.create(location, serviceName);
		QName portName = new QName(namespace, "TicketServicePort");

		TicketService proxy = service.getPort(portName, TicketService.class);

		de.uniba.rz.backendwebservice.Ticket createdTicket = proxy.createNewTicket(reporter, topic, description,
				type.ordinal(), priority.ordinal());

		Ticket newTicket = new Ticket();
		newTicket.setReporter(createdTicket.getReporter());
		newTicket.setTopic(createdTicket.getTopic());
		newTicket.setDescription(createdTicket.getDescription());
		newTicket.setType(Type.values()[createdTicket.getType().ordinal()]);
		newTicket.setPriority(Priority.values()[createdTicket.getPriority().ordinal()]);
		newTicket.setStatus(Status.values()[createdTicket.getStatus().ordinal()]);

		this.ticketList.add(newTicket);

		System.out.println("Accessing Web service...");
		System.out.println("Getting tickets from " + createdTicket);

		return newTicket;
	}

	@Override
	public List<Ticket> getAllTickets() throws TicketException {
		// TODO Auto-generated method stub
		
		URL location = null;
		try {
			location = new URL(wsdlLocation);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String namespace = "http://backendWebService.rz.uniba.de/";
		QName serviceName = new QName(namespace, "TicketServiceService");
		Service service = Service.create(location, serviceName);
		QName portName = new QName(namespace, "TicketServicePort");

		TicketService proxy = service.getPort(portName, TicketService.class);

		List<de.uniba.rz.backendwebservice.Ticket> ticketList = proxy.getAllTicket();
		List<Ticket> tickets= new ArrayList<Ticket>();
		for(de.uniba.rz.backendwebservice.Ticket ticket: ticketList)
		{
			Ticket newTicket = new Ticket();
			newTicket.setReporter(ticket.getReporter());
			newTicket.setTopic(ticket.getTopic());
			newTicket.setDescription(ticket.getDescription());
			newTicket.setType(Type.values()[ticket.getType().ordinal()]);
			newTicket.setPriority(Priority.values()[ticket.getPriority().ordinal()]);
			newTicket.setStatus(Status.values()[ticket.getStatus().ordinal()]);

			tickets.add(newTicket);
	
			
		}
		
		System.out.println("Ticket List...");
		System.out.println("Getting tickets from " + tickets.toString());
		return tickets;
	}
	
	@Override
	public List<Ticket> getTicketsByName(String name){
		
		List<Ticket> ticektListByName=new ArrayList<Ticket>();
		URL location = null;
		try {
			location = new URL(searchServiceLocation);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String namespace = "http://searchservice/";
		QName serviceName = new QName(namespace, "searchServiceImplementationService");
		Service service = Service.create(location, serviceName);
		QName portName = new QName(namespace, "searchServiceImplementationPort");

		SearchServiceInterface newService=service.getPort(portName,SearchServiceInterface.class);

		List<searchservice.Ticket> t =newService.getTicketsByName(name);
		for(searchservice.Ticket one:t) {
			Ticket newTicket = new Ticket();
			newTicket.setReporter(one.getReporter());
			newTicket.setTopic(one.getTopic());
			newTicket.setDescription(one.getDescription());
			newTicket.setType(Type.values()[one.getType().ordinal()]);
			newTicket.setPriority(Priority.values()[one.getPriority().ordinal()]);
			newTicket.setStatus(Status.values()[one.getStatus().ordinal()]);
			ticektListByName.add(newTicket);
		}
		
		System.out.println("Getting tickets from " + ticektListByName.toString());
		return ticektListByName;
		
	}
	
	
	@Override
	public List<Ticket> getTicketsByNameAndType(String name, Type type){
		
		List<Ticket> ticektListByNameAndType=new ArrayList<Ticket>();
		URL location = null;
		try {
			location = new URL(searchServiceLocation);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String namespace = "http://searchservice/";
		QName serviceName = new QName(namespace, "searchServiceImplementationService");
		Service service = Service.create(location, serviceName);
		QName portName = new QName(namespace, "searchServiceImplementationPort");

		SearchServiceInterface newService=service.getPort(portName,SearchServiceInterface.class);
		
		     
		List<searchservice.Ticket> t =newService.getTicketsByNameAndType(name, searchservice.Type.values()[type.ordinal()]);
		for(searchservice.Ticket one:t) {
			Ticket newTicket = new Ticket();
			newTicket.setReporter(one.getReporter());
			newTicket.setTopic(one.getTopic());
			newTicket.setDescription(one.getDescription());
			newTicket.setType(Type.values()[one.getType().ordinal()]);
			newTicket.setPriority(Priority.values()[one.getPriority().ordinal()]);
			newTicket.setStatus(Status.values()[one.getStatus().ordinal()]);
			ticektListByNameAndType.add(newTicket);
		}
		
		System.out.println("Getting tickets from type and name " + ticektListByNameAndType.toString());
		return ticektListByNameAndType;
		
	}
    
	@Override
	public Ticket getTicketById(int id) throws TicketException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket acceptTicket(int id) throws TicketException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket rejectTicket(int id) throws TicketException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket closeTicket(int id) throws TicketException {
		// TODO Auto-generated method stub
		return null;
	}

}
