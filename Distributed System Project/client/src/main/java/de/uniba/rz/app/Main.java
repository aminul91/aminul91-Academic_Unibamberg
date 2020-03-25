package de.uniba.rz.app;



import de.uniba.rz.app.udpimplementation.TicketManagementBackendUdp;
import de.uniba.rz.app.webservice.TicketManagementBackendWS;
import de.uniba.rz.entities.TicketException;
import de.uniba.rz.ui.swing.MainFrame;
import de.uniba.rz.ui.swing.SwingMainController;
import de.uniba.rz.ui.swing.SwingMainModel;

/**
 * Main class to start the TicketManagement5000 client application Currently
 * only a local backend implementation is registered.<br>
 * 
 * To add additional implementations modify the method
 * <code>evaluateArgs(String[] args)</code>
 *
 * @see #evaluateArgs(String[])
 */
public class Main {

	/**
	 * Starts the TicketManagement5000 application based on the given arguments
	 * 
	 * <p>
	 * <b>TODO No changes needed here - but documentation of allowed args should
	 * be updated</b>
	 * </p>
	 * 
	 * @param args
	 * @throws TicketException 
	 */
	public static void main(String[] args) throws TicketException {
		TicketManagementBackend backendToUse = evaluateArgs(args);
		SwingMainController control = new SwingMainController(backendToUse);
		SwingMainModel model = new SwingMainModel(backendToUse);
		MainFrame mf = new MainFrame(control, model);

		control.setMainFrame(mf);
		control.setSwingMainModel(model);

		control.start();
	}

	/**
	 * Determines which {@link TicketManagementBackend} should be used by
	 * evaluating the given {@code args}.
	 * 
	 * If <code>null</code>, an empty array or an unknown argument String is
	 * passed, the default {@code LocalTicketManagementBackend} is used.
	 * 
	 * <p>
	 * <b>This method must be modified in order to register new implementations
	 * of {@code TicketManagementBackend}.</b>
	 * </p>
	 * 
	 * @param args
	 *            a String array to be evaluated
	 * @return the selected {@link TicketManagementBackend} implementation
	 * @throws TicketException 
	 * 
	 * @see TicketManagementBackend
	 */
	private static TicketManagementBackend evaluateArgs(String[] args) throws TicketException {
		if (args == null || args.length == 0) {
			System.out.println("No arguments passed. Using local backend implemenation.");
			return new LocalTicketManagementBackend();
		} else {
			switch (args[0]) {
			case "local":
				return new LocalTicketManagementBackend();
            case "UDP":
                return new TicketManagementBackendUdp();
            case "WS":
                return new TicketManagementBackendWS();
            case "GRPC":
            	System.out.println("Entering GRPC implementation");
               return new TicketManagmentBackendGrpc("localhost", 5556);
			case "JMS":
				if(args.length < 4)
					return new LocalTicketManagementBackend();
				String sender = args[1];
				String ctx = args[2];
				String localStoragePath = args[3];
				return new JMSTicketManagementBackend(localStoragePath, sender, ctx);
			// Default case for unknown implentations
                
			default:
				System.out.println("Unknown backend type. Using local backend implementation.");
				return new LocalTicketManagementBackend();
			}

		}
	}
}
