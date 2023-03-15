package ticketautomaton.behaviour;

import java.util.HashMap;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;
import org.osgi.util.tracker.ServiceTracker;

import bus.behaviour.ComponentServiceBus;
import ticketautomaton.service.ITicketAutomatService;

public class TicketAutomat implements ITicketAutomatService {
	
	private BundleContext context;
	
	
	public TicketAutomat(BundleContext context) {
		this.context = context;
	}

	public void runTicketAutomat() {
		ServiceTracker<ComponentServiceBus, ComponentServiceBus> tracker = new ServiceTracker<ComponentServiceBus, ComponentServiceBus>(context, ComponentServiceBus.class, null);
		tracker.open();
		ComponentServiceBus bus = tracker.getService();
		tracker.close();
		bus.sendEvent(new Event("TicketAutomatonStarted", new HashMap<>()));
		
		

	}
	
	

}
