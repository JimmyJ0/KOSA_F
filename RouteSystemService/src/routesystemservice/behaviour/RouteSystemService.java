package routesystemservice.behaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;
import busservice.BusServices;
import routesystemservice.Activator;
import routesystemservice.configuration.InputValidator;
import routesystemservice.structure.Locations;
import routesystemservice.structure.Route;
import routesystemservice.structure.Routes;


public class RouteSystemService extends AbstractComponent{
	
	private BusServices busService;
	
	public RouteSystemService(String name) {
		super(name);
	}

	@Override
	public void handleEvent(Event event) {
		createRoute();
	}

	public void setBus(BusServices bus) {
		busService = bus;
		registerComponent();
	}
	
	private void registerComponent() {
		busService.registerComponent(this);
	}

	private void disableConsoleForProjectTest() {
		Bundle[] bun = Activator.getContext().getBundles();
		long felixGogoShellBundleID = -1;
		
		for(int i = 0; i < bun.length; i++) {
			System.out.println(bun[i]);
			if(bun[i].getSymbolicName().equals("org.apache.felix.gogo.shell")) {
				felixGogoShellBundleID = bun[i].getBundleId();
				break;
			}
		}
		if(felixGogoShellBundleID > 0) {
			Bundle felixGogoShell = Activator.getContext().getBundle(felixGogoShellBundleID);
			try {
				felixGogoShell.stop();
			} catch (BundleException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void createRoute() {
//		disableConsoleForProjectTest(); 
		InputValidator iv = new InputValidator();
		HashMap<Integer, Locations> locations = new HashMap<Integer, Locations>();
		for (int i = 0; i < Locations.values().length; i++) {
			locations.put(i + 1, Locations.values()[i]);
		}

		System.out.println("\nWELCOME TO CRAZY TRAVEL \n ");
		System.out.println("Please select your starting position");
		for (int i = 0; i < locations.size(); i++) {
			System.out.println(i + 1 + ". " + locations.get(i + 1));
		}
		System.out.println("\n");

//		Locations startLocation = locations.get(iv.getSingleNumber(locations.size()));
		Locations startLocation = locations.get(1); // ! ZUM TESTEN

		List<Routes> availableRoutes = new ArrayList<>();

		switch (startLocation) {
		case HAMBURG: {
			availableRoutes.add(Routes.HAMBURG_BERLIN);
			availableRoutes.add(Routes.HAMBURG_FRANKFURT);
			availableRoutes.add(Routes.HAMBURG_LUENBURG);
			availableRoutes.add(Routes.HAMBURG_MUENCHEN);
			availableRoutes.add(Routes.HAMBURG_STUTTGART);
			break;
		}
		case BERLIN: {
			availableRoutes.add(Routes.BERLIN_FRANKFURT);
			availableRoutes.add(Routes.BERLIN_MUENCHEN);
			availableRoutes.add(Routes.BERLIN_STUTTGART);
			break;
		}

		case FRANKFURT: {
			availableRoutes.add(Routes.FRANKFURT_MUENCHEN);
			availableRoutes.add(Routes.FRANKFURT_STUTTGART);
			break;
		}
		case LUENEBURG: {
			availableRoutes.add(Routes.LUENBURG_BERLIN);
			availableRoutes.add(Routes.LUENBURG_FRANKFURT);
			availableRoutes.add(Routes.LUENBURG_MUENCHEN);
			availableRoutes.add(Routes.LUENBURG_STUTTGART);
			break;
		}

		case MUENCHEN: {
			availableRoutes.add(Routes.MUENCHEN_HAMBURG);
			break;
		}
		case STUTTGART: {
			availableRoutes.add(Routes.STUTTGART_MUENCHEN);
			break;
		}
		}

		System.out.println("There are following destinations available");
		for (int i = 0; i < availableRoutes.size(); i++) {
			System.out.println(i + 1 + ". "
					+ availableRoutes.get(i).toString().substring(availableRoutes.get(i).toString().indexOf("_") + 1));
		}
		System.out.println("Please pick your destination");
//		Routes chosenRoute = availableRoutes.get(iv.getSingleNumber(availableRoutes.size()) - 1);
		Routes chosenRoute = availableRoutes.get(1); // ! ZUM TESTEN

		Route newRoute = new Route(chosenRoute);

		HashMap<String, Route> routeProperties = new HashMap<String, Route>();
		routeProperties.put("Route", newRoute);
		
		Event routeCreatedEvent = new Event("RouteCreated", routeProperties);
		busService.sendEvent(routeCreatedEvent);

	}
	
}
