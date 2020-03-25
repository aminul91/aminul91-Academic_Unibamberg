package searchservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;




import de.uniba.rz.backendwebservice.TicketService;
import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Status;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.Type;
import java.util.logging.Logger;

@WebService(endpointInterface = "searchservice.searchServiceInterface")
public class searchServiceImplementation implements searchServiceInterface {
	

 private final static Logger logger = Logger.getLogger(searchServiceImplementation.class.getName());

	
  @Override	
  public List<Ticket> getTickets() {   	
	URL location = null;
	try {
		location = new URL("http://localhost:9999/server/TicketService?wsdl");
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
	return tickets;
}
	@Override
	public List<Ticket> getTicketsByName(String name) {
		// TODO Auto-generated method stub
	    
		HashMap<Integer, Ticket> temporaryTicketList= new HashMap<Integer,Ticket>();
		HashMap<Integer, Ticket> finalTicketList= new HashMap<Integer,Ticket>();
		List<Ticket> tickets= new ArrayList<Ticket>();
		List<Ticket> sortedTickets= new ArrayList<Ticket>();
		List<Ticket> sorted= new ArrayList<Ticket>();
		List sortedList= new ArrayList<Integer>();
		tickets=getTickets();
		
		StringBuilder newString = new StringBuilder();
		newString.append("Result");
		int count=0;
		for(Ticket ticket:tickets) {
			
			if(ticket.getReporter().equalsIgnoreCase(name)||ticket.getTopic().equalsIgnoreCase(name)||ticket.getDescription().equalsIgnoreCase(name)) {
				
				Ticket newTicket = new Ticket();
				newTicket.setReporter(ticket.getReporter());
				newTicket.setTopic(ticket.getTopic());
				newTicket.setDescription(ticket.getDescription());
				newTicket.setType(Type.values()[ticket.getType().ordinal()]);
				newTicket.setPriority(Priority.values()[ticket.getPriority().ordinal()]);
				newTicket.setStatus(Status.values()[ticket.getStatus().ordinal()]);
				
				sortedTickets.add(newTicket);
				temporaryTicketList.put(newTicket.getPriority().ordinal(),newTicket);
				
			}

				
			}
		
		    int max=0;
		    List<Integer> priority_values=new ArrayList<Integer>();
		    priority_values.add(sortedTickets.get(0).getPriority().ordinal());
		   
		    for(int i=1;i<sortedTickets.size();i++) {
		    	
		    	priority_values.add(sortedTickets.get(i).getPriority().ordinal());
		    	
		       
		    }
		    logger.info("1111111111111111111\t"+ priority_values.toString());
		    Collections.sort(priority_values);
		    logger.info("222222222222222222222\t"+ priority_values.toString());
			
		    for(int i=0; i<priority_values.size();i++) {
		    	
		   for(Map.Entry<Integer,Ticket> element:temporaryTicketList.entrySet()) {
		    		
		    		if(element.getKey()==priority_values.get(i)) {
		    			sorted.add(element.getValue());
		    		}
		    		
		    	}
		    	
		    }
		    logger.info("3333333333333333\t"+ sorted.toString());
		    return sorted;
		
	}

	@Override
	public List<Ticket> getTicketsByNameAndType(String name, Type type) {
		// TODO Auto-generated method stub
		
		List<Ticket> ticketsByName=new ArrayList<Ticket>();
		List<Ticket> ticketsByNameOrType=new ArrayList<Ticket>();
		ticketsByName= getTicketsByName(name);
		
		for(Ticket singleTicket : ticketsByName) {
			if(singleTicket.getType().toString()== type.toString()) {
				ticketsByNameOrType.add(singleTicket);
			}
			
		}
		
		return ticketsByNameOrType;
		
		
		
	}

	
	
}
