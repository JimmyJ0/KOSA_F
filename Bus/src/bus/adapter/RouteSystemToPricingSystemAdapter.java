package bus.adapter;

import java.util.HashMap;

import org.osgi.service.event.Event;
import routesystemservice.structure.Route;
import abstractcomponent.AbstractComponent;

public class RouteSystemToPricingSystemAdapter {
	
	
	public RouteSystemToPricingSystemAdapter() {

	}
	
	public void map(AbstractComponent targetComponent, Event event) {
		Route route = (Route) event.getProperty("Route");
		System.out.println(route.getDistance());
		HashMap<String, Double> eventProperties = new HashMap<String, Double>();
		eventProperties.put("distance", ((Route) event.getProperty("Route")).getDistance());
		targetComponent.handleEvent(new Event("RouteCreated",eventProperties));
	}
	
	

}
