package messagingsystem.behaviour;

import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;
import busservice.BusServices;

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
		printConfirmation();
	}
	
	public void printConfirmation() {
		System.out.println("\n\nCONFIRMATION \n");
	}



}
