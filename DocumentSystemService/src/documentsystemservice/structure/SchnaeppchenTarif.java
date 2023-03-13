package documentsystemservice.structure;

public class SchnaeppchenTarif extends TicketDocumentTemplate {

	private String someAdditionalSchnaeppchenInfos;

	public SchnaeppchenTarif(String route, double distance, String tarif, double price, String schnaeppchenInfos) {
		super(route, distance, tarif, price);
		this.someAdditionalSchnaeppchenInfos = schnaeppchenInfos;
	}

	public String getSomeAdditionalSchnaeppchenInfos() {
		return someAdditionalSchnaeppchenInfos;
	}

}
