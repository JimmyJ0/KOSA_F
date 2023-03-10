package documentsystemservice.structure;

import java.time.LocalDate;

public abstract class TicketDocumentTemplate {

	private String route;
	private double distance;
	private String tarif;
	private double price;
	
	private LocalDate date;
	
	public TicketDocumentTemplate(String route, double distance, String tarif, double price) {
		super();
		this.route = route;
		this.distance = distance;
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
	
	

	
}
