package bus.behaviour;

import java.util.HashMap;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import abstractcomponent.AbstractComponent;
import bus.adapter.DocumentSystemToMessagingSystemAdapter;
import bus.adapter.DocumentSystemToPrintingSystemAdapter;
import bus.adapter.PricingSystemToDocumentSystemAdapter;
import bus.adapter.RouteSystemToPricingSystemAdapter;
import busservice.BusServices;

public class ComponentServiceBus implements BusServices, EventAdmin {
	
	HashMap<String, AbstractComponent> components = new HashMap<String, AbstractComponent>();
	
	@Override
	public void registerComponent(AbstractComponent component) {
		components.put(component.getName(), component);
		System.out.println("Registered Component: " + component);
		
	}
	
	// EventAdmin
	@Override
	public void postEvent(Event event) {
		
		System.out.println("Ticket wird gedruckt.");
		new DocumentSystemToPrintingSystemAdapter().map(components.get("PrintingSystem"), event);

		
	}

	@Override
	public void sendEvent(Event event) {
		System.out.println("INCOMING EVENT: " + event.getTopic());
		
		switch(event.getTopic()) {
		
		case "TicketAutomatonStarted": components.get("RouteSystem").handleEvent(event); break;
		case "RouteCreated": new RouteSystemToPricingSystemAdapter().map(components.get("PricingSystem"), event); break;
		case "PriceAdded": new PricingSystemToDocumentSystemAdapter().map(components.get("DocumentSystem"), event); break;
		
		case "TicketCreated": new DocumentSystemToMessagingSystemAdapter().map(components.get("MessagingSystem"), event); break;

		}
	
	}


}
