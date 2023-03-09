package pricingsystem.structure;

public class Price {
	private double nt;
	private double grt;
	private double st;

	public Price(double distance) {
		this.nt = distance * 0.03;
		this.grt = this.nt * 0.75;
		this.st = this.nt * 0.5;
	}

	public double getNT() {
		return nt;
	}

	public double getGRT() {
		return grt;
	}

	public double getST() {
		return st;
	}
}
