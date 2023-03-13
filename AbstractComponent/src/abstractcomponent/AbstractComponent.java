package abstractcomponent;

import componentservice.service.ComponentService;


// Rahmen für alle Komponenten. Vererbt nicht nur sich selbst, sondern auch die tolle Eigenschaft mit Events umzugehen
// (Siehe ComponentService-Interface).
public abstract class AbstractComponent implements ComponentService {
	
	private String name;
	
	public AbstractComponent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	

}
