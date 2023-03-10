package pricingsystem.structure;

public class RoutePrice {
	
	private String route;
	private double distance;
	private String tarif;
	private double price;
	
	public RoutePrice(String route, double distance, String tarif, double price) {
		super();
		this.route = route;
		this.distance = distance;
		this.tarif = tarif;
		this.price = price;
	}

	public String getRoute() {
		return route;
	}

	public double getDistance() {
		return distance;
	}

	public double getPrice() {
		return price;
	}

	public String getTarif() {
		return tarif;
	}


	

}
