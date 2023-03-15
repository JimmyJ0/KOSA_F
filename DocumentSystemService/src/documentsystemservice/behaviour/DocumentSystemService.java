package documentsystemservice.behaviour;

import java.util.HashMap;
import java.util.logging.Logger;

import org.osgi.service.event.Event;

import abstractcomponent.structure.AbstractComponent;
import busservice.service.BusServices;
import documentsystemservice.service.IRouteDetails;
import documentsystemservice.structure.GuenstigerReisenTarif;
import documentsystemservice.structure.NormalTarif;
import documentsystemservice.structure.SchnaeppchenTarif;
import documentsystemservice.structure.TicketDocumentTemplate;

public class DocumentSystemService extends AbstractComponent{
	private static final Logger LOG = Logger.getLogger(DocumentSystemService.class.getName());

	private BusServices busService;

	public DocumentSystemService(String name) {
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
		IRouteDetails routeDetails = ((IRouteDetails) event.getProperty("RouteDetails"));
		
		TicketDocumentTemplate ticket = createTicket(routeDetails);
		LOG.info("Ticket created");
		sendTicketCreatedEvent(ticket);
		
	}
	
	public TicketDocumentTemplate createTicket(IRouteDetails routeDetails) {
		TicketFactory ticketFactory = new TicketFactory();
		TicketDocumentTemplate ticket = ticketFactory.getTicketDocument(routeDetails.getTarif(), routeDetails);
		
		ticket = createTicketDocument(ticket);
		return ticket;
		

	}
	
	private TicketDocumentTemplate createTicketDocument(TicketDocumentTemplate ticket) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nThanks for traveling with CRAZY TRAVEL \n\n");
		sb.append("Tariff: " + ticket.getTarif() + "\n");
		sb.append("Route: " + ticket.getRoute() + "\n");
		sb.append("Distance: " + ticket.getDistance() + " km \n");
		sb.append("Price: " + ticket.getPrice() + " EURO \n");
		sb.append("Datum: " + ticket.getDate() + "\n\n");

		switch(ticket.getTarif()) {
		case "GünstigerReisen-Tarif": sb.append(((GuenstigerReisenTarif) ticket).getSomeAdditionalGuenstigerReisenInfos()); break;
		case "NormalTarif": sb.append(((NormalTarif) ticket).getSomeAdditionalNormalTarifInfos()); break;
		case "Schnäppchen-Tarif": sb.append(((SchnaeppchenTarif) ticket).getSomeAdditionalSchnaeppchenInfos()); break;
		}
		
		sb.append("\n\nWe wish you a pleasant trip!\n");
		
		ticket.setTicketContent(sb.toString());

		return ticket;
	}

	private void sendTicketCreatedEvent(TicketDocumentTemplate ticket) {
		HashMap<String, TicketDocumentTemplate> ticketProperties = new HashMap<>();
		ticketProperties.put("ticket", ticket);
		
		Event ticketCreatedEvent = new Event("TicketCreated", ticketProperties);
		busService.sendEvent(ticketCreatedEvent);
		
		busService.postEvent(ticketCreatedEvent);
	}



}
