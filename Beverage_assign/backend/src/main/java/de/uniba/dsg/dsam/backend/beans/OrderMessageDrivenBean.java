package de.uniba.dsg.dsam.backend.beans;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.persistence.OrderJMSQueueManagement;

/**
 * 
 * @author Mohammed Mehedi Hasan
 *
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class OrderMessageDrivenBean implements MessageListener {
	private static final Logger logger = Logger.getLogger(OrderMessageDrivenBean.class.getName());
	
	@EJB
	private OrderJMSQueueManagement<CustomerOrder> orderJMSQueueManagement;

	@EJB
	private BeverageManagement<?, Beverage> beverageManagement;
	
	/**
	 * Default constructor
	 */
    public OrderMessageDrivenBean() {
    	
    }
    
    /**
     * Implements validateData functionality.
     * Verifies acceptance criteria of data as customer order DTO.
     * 
     * @param dataObject the expected customer order DTO.
     * @return true in case the data is validated as customer order or false otherwise.
     */
    private boolean validateData(Object dataObject) {
    	try {
    		if(!(dataObject instanceof CustomerOrder)) {
				throw new Exception("Unexpected message object for customer order validation: " + dataObject);
			}
    		
    		CustomerOrder customerOrderDTO = (CustomerOrder) dataObject;
    		
    		if(customerOrderDTO.getIssueDate() == null || customerOrderDTO.getIssueDate().toString().isEmpty()) {
    			throw new Exception("Invalid customer order: " + customerOrderDTO);
    		}
    		
    		return true;
    		
    	} catch(Exception ex) {
    		logger.warning(ex.getMessage());
    		
    		return false;
    	}
    }
    
    /**
     * @see MessageListener#onMessage(Message) documentation.
     */
    public void onMessage(Message message) {
    	if(message == null) {
    		logger.warning("Recieved null message from order JMS queue.");
    		return;
    	}
    	
    	if(message instanceof ObjectMessage) {
    		try {
				Object messageObject = ((ObjectMessage) message).getObject();
				
				if(validateData(messageObject)) {
					CustomerOrder customerOrder = orderJMSQueueManagement.create((CustomerOrder) messageObject);
					if(customerOrder != null) {
						Beverage beverage = ((CustomerOrder) messageObject).getOrderItems();
						beverageManagement.update(beverage);
					}
				}
			} catch (JMSException jmsEx) {
				logger.severe("Error in JMS message accessing: " + jmsEx);
			}
    	}
    	else {
    		logger.warning("Received message of unexpected JMS message type: " + message);
    	}
    }
}
