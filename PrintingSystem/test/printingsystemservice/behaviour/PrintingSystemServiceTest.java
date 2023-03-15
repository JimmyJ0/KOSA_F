package printingsystemservice.behaviour;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import printingsystem.behaviour.PrintingSystemService;
import printingsystem.service.IPrintableDetails;

class PrintingSystemServiceTest {

	private PrintingSystemService printingSystemService;

	
	@BeforeEach
	void setUp() throws Exception {
		printingSystemService = new PrintingSystemService("PricingSystemTest");
		
	}

	@Test
	void canRouteCreated() {
		IPrintableDetails printable = new IPrintableDetails() {

			@Override
			public String getContent() {
				// TODO Auto-generated method stub
				return "TestTicket";
			}

			};


		
		assertTrue(printingSystemService.printDetails(printable.getContent()));
	}
		
}
