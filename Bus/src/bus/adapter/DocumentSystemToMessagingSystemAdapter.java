package bus.adapter;

import java.util.HashMap;
import messagingsystem.service.IMessagable;
import org.osgi.service.event.Event;

import abstractcomponent.structure.AbstractComponent;
import documentsystemservice.structure.TicketDocumentTemplate;
import printingsystem.behaviour.service.IPrintableDetails;

public class DocumentSystemToMessagingSystemAdapter {
	public void map(AbstractComponent messagingSystem, Event event) {

		TicketDocumentTemplate ticket = (TicketDocumentTemplate) event.getProperty("ticket");
		HashMap<String, IMessagable> eventProperties = new HashMap<String, IMessagable>();

		IMessagable ticketDetails = new IMessagable() {

			@Override
			public String getContent() {
				return ticket.getTicketContent();
			}

		};
		eventProperties.put("MessagableDetails", ticketDetails);
		
		messagingSystem.handleEvent(new Event("GotAllMessagableDetails", eventProperties));

	}
}
