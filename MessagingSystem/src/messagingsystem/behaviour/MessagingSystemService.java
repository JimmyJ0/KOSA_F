package messagingsystem.behaviour;

import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;
import busservice.BusServices;
import messagingsystem.service.IMessagable;

public class MessagingSystemService extends AbstractComponent {
	
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
	
	public void printConfirmation(String details) {
		
		System.out.println(details);
	}



}
