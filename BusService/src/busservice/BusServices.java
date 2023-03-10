package busservice;

import org.osgi.service.event.Event;

import abstractcomponent.AbstractComponent;

// Gibt Methoden vor, mit denen Komponenten mit dem Bus interagieren können
public interface BusServices {

	void registerComponent(AbstractComponent component);

	void sendEvent(Event event);
	
	void postEvent(Event event);

}
