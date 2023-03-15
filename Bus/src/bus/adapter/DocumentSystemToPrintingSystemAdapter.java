package bus.adapter;

import java.util.HashMap;

import org.osgi.service.event.Event;

import abstractcomponent.structure.AbstractComponent;
import documentsystemservice.structure.TicketDocumentTemplate;
import printingsystem.behaviour.service.IPrintableDetails;

public class DocumentSystemToPrintingSystemAdapter {
	
	public void map(AbstractComponent printingSystem, Event event) {

		TicketDocumentTemplate ticket = (TicketDocumentTemplate) event.getProperty("ticket");
		HashMap<String, IPrintableDetails> eventProperties = new HashMap<String, IPrintableDetails>();

		IPrintableDetails ticketDetails = new IPrintableDetails() {

			@Override
			public String getContent() {
				return ticket.getTicketContent();
			}

		};
		eventProperties.put("PrintDetails", ticketDetails);
		
		printingSystem.handleEvent(new Event("GotAllPrintDetails", eventProperties));

	}
}
