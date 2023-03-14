package messagingsystem.behaviour;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import messagingsystem.service.IMessagable;

class MessagingSystemTest {

	private MessagingSystemService messagingSystemService;

	
	@BeforeEach
	void setUp() throws Exception {
		messagingSystemService = new MessagingSystemService("MessagingSystemTest");
		
	}

	@Test
	void canRouteCreated() {
		IMessagable messagable = new IMessagable() {

			@Override
			public String getContent() {
				return "Message erfolgreich ausgegeben: Dies ist eine Testausgabe!";
			}



		};
		assertTrue(messagingSystemService.printConfirmation(messagable.getContent()));
	}
}
