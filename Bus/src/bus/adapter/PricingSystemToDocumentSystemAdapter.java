package bus.adapter;

import java.util.HashMap;

import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;
import documentsystemservice.service.IRouteDetails;
import pricingsystem.structure.RoutePrice;

public class PricingSystemToDocumentSystemAdapter {

	public void map(AbstractComponent documentSystem, Event event) {

		RoutePrice routePrice = (RoutePrice) event.getProperty("RouteDetails");
		HashMap<String, IRouteDetails> eventProperties = new HashMap<String, IRouteDetails>();

		IRouteDetails routeDetails = new IRouteDetails() {

			@Override
			public String getRoute() {
				return routePrice.getRoute();
			}

			@Override
			public Double getDistance() {
				return routePrice.getDistance();
			}

			@Override
			public String getTarif() {
				return routePrice.getTarif();
			}

			@Override
			public Double getPrice() {
				return routePrice.getPrice();
			}

		};
		eventProperties.put("RouteDetails", routeDetails);
		
		documentSystem.handleEvent(new Event("GotAllRouteDetails", eventProperties));

	}

}
