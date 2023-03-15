package documentsystemservice.structure;

import java.time.LocalDate;

public abstract class TicketDocumentTemplate {

	private String route;
	private double distance;
	private String tarif;
	private double price;
	private String ticketContent;
	
	private LocalDate date;
	
	public TicketDocumentTemplate(String route, double distance, String tarif, double price) {
		super();
		this.route = route;
		this.distance = Math.round(distance * 100.0)/100.0;;
		this.tarif = tarif;
		this.price = Math.round(price * 100.0)/100.0;
		
		date = LocalDate.now();
	}

	public String getRoute() {
		return route;
	}

	public double getDistance() {
		return distance;
	}

	public String getTarif() {
		return tarif;
	}

	public double getPrice() {
		return price;
	}
	
	public String getDate() {
		return date.toString();
	}
	
	public String getTicketContent() {
		return this.ticketContent;
	}

	public void setTicketContent(String ticketContent) {
		this.ticketContent = ticketContent;
	}


	
}
