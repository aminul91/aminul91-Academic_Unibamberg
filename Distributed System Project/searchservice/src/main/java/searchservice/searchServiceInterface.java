package searchservice;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import de.uniba.rz.entities.Ticket;

import de.uniba.rz.entities.Type;


@WebService
public interface searchServiceInterface {

	 
	 @WebMethod
	 public List<Ticket> getTickets();
	
	 @WebMethod
	 public List<Ticket> getTicketsByName(@WebParam(name = "name")String name); 
	
	 @WebMethod
	 public List<Ticket> getTicketsByNameAndType(@WebParam(name = "name") String name, @WebParam(name = "type") Type type); 
	
	
}
