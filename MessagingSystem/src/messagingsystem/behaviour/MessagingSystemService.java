package messagingsystem.behaviour;

import java.util.logging.Logger;

import org.osgi.service.event.Event;

import abstractcomponent.structure.AbstractComponent;
import busservice.BusServices;
import messagingsystem.service.IMessagable;

public class MessagingSystemService extends AbstractComponent {
	private static final Logger LOG = Logger.getLogger(MessagingSystemService.class.getName());

	private BusServices busService;

	public MessagingSystemService(String name) {
		super(name);
	}
	
	public void setBus(BusServices componentServiceBus) {
		this.busService = componentServiceBus;
		registerComponent();
	}
	
	private void registerComponent() {
		busService.registerComponent(this);

	}

	@Override
	public void handleEvent(Event event) {
		IMessagable messagable = (IMessagable) event.getProperty("MessagableDetails");
		
		printConfirmation(messagable.getContent());
	}
	
	public boolean printConfirmation(String details) {
		
		System.out.println(details);
		LOG.info("Message written");
		return true;
	}



}
