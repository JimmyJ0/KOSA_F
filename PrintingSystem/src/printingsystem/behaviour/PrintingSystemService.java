package printingsystem.behaviour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;
import busservice.BusServices;
import printingsystem.behaviour.service.IPrintableDetails;

public class PrintingSystemService extends AbstractComponent {

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
		// TODO Interface erstellen für das DocumentTemplate und Adapter erstellen
		System.out.println(event.getTopic());
		IPrintableDetails printDetails = ((IPrintableDetails) event.getProperty("PrintDetails"));
		printDetails(printDetails.getContent());
		
	}

	private void printDetails(String printDetails) {
		// TODO Muss noch zur File
		 try {
	            FileWriter fileWriter = new FileWriter(LOG_FILE, false); // Set true to append to the file instead of overwriting it
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            bufferedWriter.write(printDetails);
	            bufferedWriter.newLine(); // Add a new line for the next log message
	            bufferedWriter.close();
	            System.out.println("Log message written to " + LOG_FILE);
	        } catch (IOException e) {
	            System.err.println("Error writing to log file: " + e);
	        }
		
	}
}