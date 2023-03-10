package documentsystemservice.behaviour;

import java.util.HashMap;

import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;
import busservice.BusServices;
import documentsystemservice.service.IRouteDetails;
import documentsystemservice.structure.GuenstigerReisenTarif;
import documentsystemservice.structure.NormalTarif;
import documentsystemservice.structure.SchnaeppchenTarif;
import documentsystemservice.structure.TicketDocumentTemplate;

public class DocumentSystemService extends AbstractComponent{
	
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
		System.out.println(event.getTopic());
		IRouteDetails routeDetails = ((IRouteDetails) event.getProperty("RouteDetails"));
		
		createTicket(routeDetails);
	}
	
	private void createTicket(IRouteDetails routeDetails) {
		TicketFactory ticketFactory = new TicketFactory();
		TicketDocumentTemplate ticket = ticketFactory.getTicketDocument(routeDetails.getTarif(), routeDetails);
		
		printTicket(ticket);
		
		HashMap<String, TicketDocumentTemplate> ticketProperties = new HashMap<>();
		ticketProperties.put("ticket", ticket);
		
		Event ticketCreatedEvent = new Event("TicketCreated", ticketProperties);
		busService.sendEvent(ticketCreatedEvent);
	}
	
	private void printTicket(TicketDocumentTemplate ticket) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nThanks for traveling with CRAZY TRAVEL \n\n");
		sb.append("Tarif: " + ticket.getTarif() + "\n");
		sb.append("Route: " + ticket.getRoute() + "\n");
		sb.append("Distance: " + ticket.getDistance() + " km \n");
		sb.append("Price: " + ticket.getPrice() + " EURO \n\n");
		
		switch(ticket.getTarif()) {
		case "GünstigerReisen-Tarif": sb.append(((GuenstigerReisenTarif) ticket).getSomeAdditionalGuenstigerReisenInfos()); break;
		case "NormalTarif": sb.append(((NormalTarif) ticket).getSomeAdditionalNormalTarifInfos()); break;
		case "Schnäppchen-Tarif": sb.append(((SchnaeppchenTarif) ticket).getSomeAdditionalSchnaeppchenInfos()); break;
		}
		
		sb.append("\n\nWe wish you a pleasant trip!");
		
		System.out.println(sb.toString());
		
		Event ticketPrintedEvent = new Event("TicketPrinted", new HashMap<>());
		busService.sendEvent(ticketPrintedEvent);
	}




}
