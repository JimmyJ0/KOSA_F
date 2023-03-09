package pricingsystem.behaviour;

import java.util.Arrays;
import java.util.Optional;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;
import busservice.BusServices;
import pricingsystem.Activator;
import pricingsystem.configuration.InputValidator;
import pricingsystem.structure.Price;

public class PricingSystemService extends AbstractComponent {

	private BusServices busService;

	public PricingSystemService(String name) {
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
		System.out.println("BASKDASKLD: " + event.getProperty("distance"));
		runPricingSystem((double) event.getProperty("distance"));

	}

	
	private void disableConsole() {
		Bundle[] bundles = Activator.getContext().getBundles();
		Arrays.stream(bundles).filter(bundle -> bundle.getSymbolicName().equals("org.apache.felix.gogo.shell")).findFirst().
		ifPresent(bundle -> {
			try {
				System.out.println("Deactivate gogo-shell");
				bundle.stop();
			} catch (BundleException e) {
				e.printStackTrace();
			}
		});
	}

	private void runPricingSystem(double distance) {

		InputValidator iv = new InputValidator();
		disableConsole();
		System.out.println("\n\n");
		// System.out.println("TOPIC: " + event.getTopic());
		// System.out.println("ROUTE: " + event.getProperty("route"));
		// System.out.println("DISTANCE: " + event.getProperty("distance"));

//		Price price = new Price(Double.parseDouble(event.getProperty("distance").toString()));
		Price price = new Price(distance);
		
		System.out.println(String.format("1. Normaltarif: %.2f€", price.getNT()));
		System.out.println(String.format("2. GünstigerReisen-Tarif: %.2f€", price.getGRT()));
		System.out.println(String.format("3. Schnäppchen-Tarif: %.2f€", price.getST()));
		System.out.println("Please select your Ticketoption: ");
		int input = iv.getSingleNumber(3);
//		int input = 3;

		double chosenPrice = 0.0;
		String tarif = "";
		switch (input) {
		case 1:
			chosenPrice = price.getNT();
			tarif = "Normaltarif";
			break;
		case 2:
			chosenPrice = price.getGRT();
			tarif = "GünstigerReisen-Tarif";
			break;
		case 3:
			chosenPrice = price.getST();
			tarif = "Schnäppchen-Tarif";
			break;
		}

		System.out.println(String.format("The selected Tarif is the '%s' with a Price of %.2f€.", tarif, chosenPrice));
	}

}
