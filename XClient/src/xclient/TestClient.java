package xclient;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.util.tracker.ServiceTracker;

import ticketautomaton.behaviour.TicketAutomat;
import ticketautomaton.service.ITicketAutomatService;

public class TestClient implements BundleActivator {
	private static final Logger LOG = Logger.getLogger(TestClient.class.getName());

	private static BundleContext context;
	private ITicketAutomatService ticketAutomat;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		ServiceTracker<TicketAutomat, TicketAutomat> tracker = new ServiceTracker<TicketAutomat, TicketAutomat>(
				bundleContext, TicketAutomat.class, null);
		tracker.open();
		if (tracker != null)
			ticketAutomat = tracker.getService();
		tracker.close();

		TestClient.context = bundleContext;
		runTicketAutomat();
	}

	public void stop(BundleContext bundleContext) throws Exception {

		TestClient.context = null;
	}

	public void runTicketAutomat() {
		ticketAutomat.runTicketAutomat();
		restartAutomat();

	}

	// Improvised. Should react on an event
	private void restartAutomat() {
		Bundle[] bundles = TestClient.getContext().getBundles();

		Optional<Bundle> ticketAutomat = Arrays.stream(bundles)
				.filter(bundle -> bundle.getSymbolicName().equals("TicketAutomaton")).findFirst();

		ticketAutomat.ifPresent(bundle -> {
			try {
				bundle.stop();
				LOG.info("TICKET CREATED\n\n");

				LOG.info("TicketAutomaton Restartet");
				bundle.start();
				start(context);

			} catch (BundleException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

}
