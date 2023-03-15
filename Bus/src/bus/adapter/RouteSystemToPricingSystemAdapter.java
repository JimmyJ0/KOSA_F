package bus.adapter;

import java.util.HashMap;

import org.osgi.service.event.Event;

import abstractcomponent.structure.AbstractComponent;
import pricingsystem.behaviour.service.IPriceable;
import routesystemservice.structure.Route;

public class RouteSystemToPricingSystemAdapter {
	
	
	public RouteSystemToPricingSystemAdapter() {

	}
	
	public void map(AbstractComponent pricingSystem, Event event) {
		Route route = (Route) event.getProperty("Route");
		HashMap<String, IPriceable> eventProperties = new HashMap<String, IPriceable>();
		
		IPriceable priceable = new IPriceable() {


			@Override
			public double getDistance() {
				return route.getDistance();
			}

			@Override
			public String getRoute() {
				return route.getRoute().toString();
			}
			
		};
				
		eventProperties.put("route", priceable);
		
		pricingSystem.handleEvent(new Event("RouteCreated", eventProperties));

	}
	
	

}
