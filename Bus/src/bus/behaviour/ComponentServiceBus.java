package bus.behaviour;

import java.util.HashMap;
import java.util.logging.Logger;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import abstractcomponent.structure.AbstractComponent;
import bus.adapter.DocumentSystemToMessagingSystemAdapter;
import bus.adapter.DocumentSystemToPrintingSystemAdapter;
import bus.adapter.PricingSystemToDocumentSystemAdapter;
import bus.adapter.RouteSystemToPricingSystemAdapter;
import busservice.BusServices;

public class ComponentServiceBus implements BusServices, EventAdmin {
	private static final Logger LOG = Logger.getLogger(ComponentServiceBus.class.getName());

	HashMap<String, AbstractComponent> components = new HashMap<String, AbstractComponent>();

	@Override
	public void registerComponent(AbstractComponent component) {
		components.put(component.getName(), component);
		LOG.info("Registered Component: " + component);
	}

	// EventAdmin
	@Override
	public void postEvent(Event event) {

		new DocumentSystemToPrintingSystemAdapter().map(components.get("PrintingSystem"), event);

	}

	@Override
	public void sendEvent(Event event) {
		switch (event.getTopic()) {

		case "TicketAutomatonStarted":
			components.get("RouteSystem").handleEvent(event);
			break;
		case "RouteCreated":
			new RouteSystemToPricingSystemAdapter().map(components.get("PricingSystem"), event);
			break;
		case "PriceAdded":
			new PricingSystemToDocumentSystemAdapter().map(components.get("DocumentSystem"), event);
			break;

		case "TicketCreated":
			new DocumentSystemToMessagingSystemAdapter().map(components.get("MessagingSystem"), event);
			break;

		}

	}

}
