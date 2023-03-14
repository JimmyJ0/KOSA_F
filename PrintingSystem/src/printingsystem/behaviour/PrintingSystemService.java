package printingsystem.behaviour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;
import busservice.BusServices;
import printingsystem.behaviour.service.IPrintableDetails;

public class PrintingSystemService extends AbstractComponent {

	private static final Logger LOG = Logger.getLogger(PrintingSystemService.class.getName());

	private BusServices busService;
	private static final String LOG_FILE = "ticket.log";
	
	public PrintingSystemService(String name) {
		super(name);
	}

	public void setBus(BusServices bus) {
		busService = bus;
		registerComponent();
	}

	private void registerComponent() {
		busService.registerComponent(this);

}

	@Override
	public void handleEvent(Event event) {
		System.out.println(event.getTopic());
		IPrintableDetails printDetails = ((IPrintableDetails) event.getProperty("PrintDetails"));
		printDetails(printDetails.getContent());
		
	}

	public boolean printDetails(String printDetails) {
		 try {
	            FileWriter fileWriter = new FileWriter(LOG_FILE, false); 
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            bufferedWriter.write(printDetails);
	            bufferedWriter.newLine(); 
	            bufferedWriter.close();
	            System.out.println("Ticket written to " + LOG_FILE);
	            LOG.info("Ticket printed");
	        } catch (IOException e) {
	            System.err.println("Error writing the ticket: " + e);
	            LOG.info("Ticket printing failed!");
	        }
		return true;
		
	}
}