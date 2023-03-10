package printingsystem.behaviour;

import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;
import busservice.BusServices;

public class PrintingSystemService extends AbstractComponent {

	private BusServices busService;

	public PrintingSystemService(String name) {
		super(name);
	}

	public void setBus(BusServices bus) {
		busService = bus;
		registerComponent();
	}

	private void registerComponent() {
		busService.registerComponent(this);

}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
}