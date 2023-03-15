package pricingsystemservice.behaviour;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pricingsystem.behaviour.PricingSystemService;
import pricingsystem.service.IPriceable;


class PricingSystemServiceTest {

	private PricingSystemService pricingSystemService;

	
	@BeforeEach
	void setUp() throws Exception {
		pricingSystemService = new PricingSystemService("PricingSystemTest");
		
	}

	@Test
	void canRouteCreated() {
		IPriceable routeDetails = new IPriceable() {

			@Override
			public double getDistance() {
				// TODO Auto-generated method stub
				return 222;
			}

			@Override
			public String getRoute() {
				// TODO Auto-generated method stub
				return "Test_Route";
			}


		};
		assertNotNull(pricingSystemService.runPricingSystem(routeDetails));
	}

}
