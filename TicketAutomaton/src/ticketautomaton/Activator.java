package ticketautomaton;

import java.util.HashMap;
import messagingsystem.behaviour.MessagingSystemService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;

import bus.behaviour.ComponentServiceBus;
import documentsystemservice.behaviour.DocumentSystemService;
import pricingsystem.behaviour.PricingSystemService;
import printingsystem.behaviour.PrintingSystemService;
import routesystemservice.behaviour.RouteSystemService;

// Sammelstelle aller Abhängigkeiten
// Instanziiert Komponenten und injiziert den Bus
// Sendet das "StartEvent" um die Kette in Gang zu setzen..

public class Activator implements BundleActivator {

	private ComponentServiceBus componentServiceBus;
	private RouteSystemService routeSystemService;
	private PricingSystemService pricingSystemService;
	private DocumentSystemService documentSystemService;
	private MessagingSystemService messagingSystemService;
	private PrintingSystemService printingSystemService;

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		componentServiceBus = new ComponentServiceBus();
		routeSystemService = new RouteSystemService("RouteSystem");
		pricingSystemService = new PricingSystemService("PricingSystem");
		documentSystemService = new DocumentSystemService("DocumentSystem");
		messagingSystemService = new MessagingSystemService("MessagingSystem");
		printingSystemService = new PrintingSystemService("PrintingSystem");
		
		bundleContext.registerService(ComponentServiceBus.class, componentServiceBus, null);
		bundleContext.registerService(RouteSystemService.class, routeSystemService, null);
		bundleContext.registerService(PricingSystemService.class, pricingSystemService, null);
		bundleContext.registerService(DocumentSystemService.class, documentSystemService, null);
		bundleContext.registerService(MessagingSystemService.class, messagingSystemService, null);
		bundleContext.registerService(PrintingSystemService.class, printingSystemService, null);
		
		routeSystemService.setBus(componentServiceBus);
		pricingSystemService.setBus(componentServiceBus);
		documentSystemService.setBus(componentServiceBus);
		messagingSystemService.setBus(componentServiceBus);
		printingSystemService.setBus(componentServiceBus);
		
		
		componentServiceBus.sendEvent(new Event("TicketAutomatonStarted", new HashMap<>()));
	
		Activator.context = bundleContext;
		//TODO Automaton automatisch restarten lassen

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
