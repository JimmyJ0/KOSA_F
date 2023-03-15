package documentsystemservice.behaviour;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import documentsystemservice.service.IRouteDetails;

class DocumentSystemServiceTest {
	
	private DocumentSystemService documentSystemService;

	@BeforeEach
	void setUp() throws Exception {
		documentSystemService = new DocumentSystemService("DocumentSystemTest");
	}

	@Test
	void canTicketCreated() {
		
		IRouteDetails routeDetails = new IRouteDetails() {

			@Override
			public String getRoute() {
				return "HAMBURG_LUENBURG";
			}

			@Override
			public Double getDistance() {
				return 50.0;
			}

			@Override
			public String getTarif() {
				return "Normaltarif";
			}

			@Override
			public Double getPrice() {
				return 2.50;
			}

		};
		
		assertNotNull(documentSystemService.createTicket(routeDetails));
		
		
		
	}

}
