package documentsystemservice.structure;

public class GuenstigerReisenTarif extends TicketDocumentTemplate {
	
	private String someAdditionalGuenstigerReisenInfos;
	
	public GuenstigerReisenTarif(String route, double distance, String tarif, double price, String guenstigerReisenInfos) {
		super(route, distance, tarif, price);
		this.someAdditionalGuenstigerReisenInfos = guenstigerReisenInfos;
	}

	public String getSomeAdditionalGuenstigerReisenInfos() {
		return someAdditionalGuenstigerReisenInfos;
	}
	

}
