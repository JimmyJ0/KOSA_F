package bus.behaviour;

import java.util.HashMap;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import abstractcomponent.AbstractComponent;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEvent(Event event) {
		System.out.println("INCOMING EVENT: " + event.getTopic());
		
		// TODO: Umstellen auf switch-case... Ging aus unerfindlichen Gründen heute nicht... 
		if(event.getTopic().equals("TicketAutomatonStarted")) {
			components.get("RouteSystem").handleEvent(event);
		}
		else if(event.getTopic().equals("RouteCreated")) {
			new RouteSystemToPricingSystemAdapter().map(components.get("PricingSystem"), event);
		}		
	}


}
