package routesystemservice.behaviour;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RouteSystemServiceTest {

	private RouteSystemService routeSystemService;

	
	@BeforeEach
	void setUp() throws Exception {
		routeSystemService = new RouteSystemService("RouteSystemTest");
		
	}

	@Test
	void canRouteCreated() {
		assertNotNull(routeSystemService.createRoute());
	}

}
