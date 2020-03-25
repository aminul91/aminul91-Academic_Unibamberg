package searchservice;

import javax.xml.ws.Endpoint;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url = "http://localhost:8080/searchService";

		System.out.println("Starting Web Service Endpoint...");
		Endpoint endpoint = Endpoint.publish(url, new searchServiceImplementation());
		System.out.println("Endpoint available at " + url);
		System.out.println("Press any key to exit...");
		//System.in.read();
		//endpoint.stop();
		// System.out.println("Server ready to serve your JAX-WS requests...");
		

	}

}
