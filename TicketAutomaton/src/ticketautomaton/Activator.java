package ticketautomaton;

import bus.behaviour.ComponentServiceBus;
import java.util.HashMap;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;

import pricingsystem.behaviour.PricingSystemService;
import routesystemservice.behaviour.RouteSystemService;

// Sammelstelle aller Abhängigkeiten
// Instanziiert Komponenten und injiziert den Bus
// Sendet das "StartEvent" um die Kette in Gang zu setzen..

public class Activator implements BundleActivator {

	private ComponentServiceBus componentServiceBus;
	private RouteSystemService routeSystemService;
	private PricingSystemService pricingSystemService;

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		componentServiceBus = new ComponentServiceBus();
		routeSystemService = new RouteSystemService("RouteSystem");
		pricingSystemService = new PricingSystemService("PricingSystem");
		
		bundleContext.registerService(ComponentServiceBus.class, componentServiceBus, null);
		bundleContext.registerService(RouteSystemService.class, routeSystemService, null);
		bundleContext.registerService(PricingSystemService.class, pricingSystemService, null);
		
		routeSystemService.setBus(componentServiceBus);
		pricingSystemService.setBus(componentServiceBus);
		
		componentServiceBus.sendEvent(new Event("TicketAutomatonStarted", new HashMap<>()));
		
		
		Activator.context = bundleContext;

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
