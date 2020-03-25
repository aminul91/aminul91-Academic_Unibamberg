package de.uniba.rz.backendgrpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.uniba.rz.backend.RemoteAccess;
import de.uniba.rz.backend.SimpleTicketStore;
import de.uniba.rz.backend.TicketStore;
import de.uniba.rz.backend.UnknownTicketException;
import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.Type;
import de.uniba.rz.io.rpc.ProtoMessageBody;
import de.uniba.rz.io.rpc.ProtoMessageDTO;
import de.uniba.rz.io.rpc.ProtoMessageType;
import de.uniba.rz.io.rpc.TicketList;
import de.uniba.rz.io.rpc.TicketRequest;
import de.uniba.rz.io.rpc.TicketResponse;
import de.uniba.rz.io.rpc.TicketServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class RemoteAccessGrpc implements RemoteAccess {

	private boolean isRunning = false;
    private final int port;
    private final Server server;

    /**
     * During the construction of our server, the implemented service must be specified. There is also a possibility to
     * expose more than one service.
     */
    public RemoteAccessGrpc() {
        this.port = 5556;
        this.server = ServerBuilder.forPort(this.port).addService(new CreateTicketServiceImpl()).build();
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRunning) {
			requestProcessing();
		}
	}

	private void requestProcessing() {

        System.out.println("starting server from the multi-threaded system");
        try {
			this.server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			this.blockUntilShutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void prepareStartup(TicketStore ticketStore) {
		isRunning = true;

		requestProcessing();
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
        if (server != null) {
            isRunning = false;
            server.shutdown();
        }
	}

    /**
     * Blocking method until the shutdown hock terminates the server.
     *
     * @throws InterruptedException
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Custom class for the implementation of the base service, which is an abstract class. The method must be
     * overridden since the default implementation has no implemented logic.
     */
    static class CreateTicketServiceImpl extends TicketServiceGrpc.TicketServiceImplBase{

    	private TicketStore ticketStore = new SimpleTicketStore();

        @Override
        public void createTicketService(ProtoMessageDTO message, StreamObserver<ProtoMessageDTO> responseObserver) {
        	
        	TicketRequest request = message.getMessageBody().getTicket();
        	System.out.println("NEW TICKET ID: "+request.getId());

            Type type = Type.valueOf(request.getType().name());
            Priority priority = Priority.valueOf(request.getPriority().name());
            
//            ProtoMessageDTO.Builder messageDto = ProtoMessageDTO.newBuilder();
//    		ProtoMessageBody.Builder messageBody = ProtoMessageBody.newBuilder(); 
    		ProtoMessageType messageType = message.getMessageType();
    		
            
    		if(messageType == ProtoMessageType.SAVE)
    		{
    			ticketStore.storeNewTicket(request.getReporter(), request.getTopic(), request.getDescription(), type, priority);
    			
    			List<Ticket> ticketList = ticketStore.getAllTickets();
    			
    			List<TicketRequest> ticketRequestList = new ArrayList<TicketRequest>();
    			
    			for(Ticket ticket : ticketList) {
    				TicketRequest rq = TicketRequest.newBuilder().setId(ticket.getId())
						.setReporter(ticket.getReporter())
						.setTopic(ticket.getTopic())
						.setDescription(ticket.getDescription())
						.setTypeValue(ticket.getType().ordinal())
						.setPriorityValue(ticket.getPriority().ordinal())
						.setStatusValue(ticket.getStatus().ordinal()).build();
	    				
	    			ticketRequestList.add(rq);
    			}
    			
    			ProtoMessageDTO.Builder messageDto = ProtoMessageDTO.newBuilder();
        		ProtoMessageBody.Builder messageBody = ProtoMessageBody.newBuilder(); 
        		
    			messageDto.setMessageType(ProtoMessageType.SEND_ALL);
    			messageBody.setTicketList(TicketList.newBuilder().addAllTickets(ticketRequestList));    			
    			messageDto.setMessageBody(messageBody);
    			responseObserver.onNext(messageDto.build());
    		}
    		else if(messageType == ProtoMessageType.RETRIEVE_ONE) {
    			System.out.println("calling retrieve one");
    			List<Ticket> ticketList = ticketStore.getAllTickets();
    			int optionalId = message.getOptionalTicketId();
    			System.out.println("in get one: "+ticketList.get(0));
    			List<TicketRequest> ticketRequestList = new ArrayList<TicketRequest>();
    			
    			for(Ticket ticket : ticketList) {
    				if(ticket.getId() == optionalId) {
    					TicketRequest ticketRequest = TicketRequest.newBuilder().setId(ticket.getId())
    							.setReporter(ticket.getReporter())
    							.setTopic(ticket.getTopic())
    							.setDescription(ticket.getDescription())
    							.setTypeValue(ticket.getType().ordinal())
    							.setPriorityValue(ticket.getPriority().ordinal())
    							.setStatusValue(ticket.getStatus().ordinal()).build();
    					
    					ticketRequestList.add(ticketRequest);
    					break;
    				}
    			}
    			
    			ProtoMessageDTO.Builder messageDto = ProtoMessageDTO.newBuilder();
        		ProtoMessageBody.Builder messageBody = ProtoMessageBody.newBuilder(); 
        		
    			messageDto.setMessageType(ProtoMessageType.SEND_ONE);
    			messageBody.setTicketList(TicketList.newBuilder().addAllTickets(ticketRequestList));    			
    			messageDto.setMessageBody(messageBody);
    			responseObserver.onNext(messageDto.build());
    		}
    		else if(messageType == ProtoMessageType.UPDATE) {
    			System.out.println("In update");
    			System.out.println("optional ticket id: "+message.getOptionalTicketId());
    			System.out.println("new status: "+message.getMessageBody().getTicket().getStatus().toString());
    			try {
					this.ticketStore.updateTicketStatus(message.getOptionalTicketId(), 
							de.uniba.rz.entities.Status.valueOf(message.getMessageBody().getTicket().getStatus().toString()));
				} catch (IllegalStateException | UnknownTicketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			List<Ticket> ticketList = ticketStore.getAllTickets();
    			System.out.println("Size of ticket store: "+ticketList.size());
    			System.out.println("updated ticket: "+ticketList.get(0).getStatus());
    			
    			List<TicketRequest> ticketRequestList = new ArrayList<TicketRequest>();
    			
    			for(Ticket ticket : ticketList) {
    				System.out.println("status in update: "+ticket.getStatus().toString());
    				TicketRequest rq = TicketRequest.newBuilder().setId(ticket.getId())
						.setReporter(ticket.getReporter())
						.setTopic(ticket.getTopic())
						.setDescription(ticket.getDescription())
						.setTypeValue(ticket.getType().ordinal())
						.setPriorityValue(ticket.getPriority().ordinal())
						.setStatusValue(ticket.getStatus().ordinal()).build();
	    				
	    			ticketRequestList.add(rq);
    			}
    			
    			ProtoMessageDTO.Builder messageDto = ProtoMessageDTO.newBuilder();
        		ProtoMessageBody.Builder messageBody = ProtoMessageBody.newBuilder(); 
        		
    			messageDto.setMessageType(ProtoMessageType.SEND_ALL);
    			messageBody.setTicketList(TicketList.newBuilder().addAllTickets(ticketRequestList));    			
    			messageDto.setMessageBody(messageBody);
    			responseObserver.onNext(messageDto.build());
    		}
    		else {
    			
    			List<Ticket> ticketList = ticketStore.getAllTickets();
    			
    			List<TicketRequest> ticketRequestList = new ArrayList<TicketRequest>();
    			
    			for(Ticket ticket : ticketList) {
    				TicketRequest rq = TicketRequest.newBuilder().setId(ticket.getId())
						.setReporter(ticket.getReporter())
						.setTopic(ticket.getTopic())
						.setDescription(ticket.getDescription())
						.setTypeValue(ticket.getType().ordinal())
						.setPriorityValue(ticket.getPriority().ordinal())
						.setStatusValue(ticket.getStatus().ordinal()).build();
	    				
	    			ticketRequestList.add(rq);
    			}
    			
    			ProtoMessageDTO.Builder messageDto = ProtoMessageDTO.newBuilder();
        		ProtoMessageBody.Builder messageBody = ProtoMessageBody.newBuilder(); 
        		
    			messageDto.setMessageType(ProtoMessageType.SEND_ALL);
    			messageBody.setTicketList(TicketList.newBuilder().addAllTickets(ticketRequestList));    			
    			messageDto.setMessageBody(messageBody);
    			responseObserver.onNext(messageDto.build());
    		}
            
//    		responseObserver.onNext(messageDto.build());
            responseObserver.onCompleted();
        }	

        
    }
}
