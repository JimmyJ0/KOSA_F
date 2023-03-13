package documentsystemservice.behaviour;

import documentsystemservice.service.IRouteDetails;
import documentsystemservice.structure.GuenstigerReisenTarif;
import documentsystemservice.structure.NormalTarif;
import documentsystemservice.structure.SchnaeppchenTarif;
import documentsystemservice.structure.TicketDocumentTemplate;

public class TicketFactory {

	public TicketDocumentTemplate getTicketDocument(String priceGroup, IRouteDetails routeDetails) {

		TicketDocumentTemplate document = null;

		switch (priceGroup) {
		case "Normaltarif": {
			document = new NormalTarif(routeDetails.getRoute(), routeDetails.getDistance(), routeDetails.getTarif(),
					routeDetails.getPrice(), "Spare bei deiner naechsten Reise 10% mit dem Code 'crazy23'");
			break;
		}
		case "GünstigerReisen-Tarif": {
			document = new GuenstigerReisenTarif(routeDetails.getRoute(), routeDetails.getDistance(),
					routeDetails.getTarif(), routeDetails.getPrice(), "Das Ticket für die Sparfuechse!");
			break;
		}
		case "Schnäppchen-Tarif": {
			document = new SchnaeppchenTarif(routeDetails.getRoute(), routeDetails.getDistance(),
					routeDetails.getTarif(), routeDetails.getPrice(), "Das Ticket für alle Schnaeppchenjaeger!");
			break;
		}
		}

		return document;
	}

}

