package documentsystemservice.structure;

public class NormalTarif extends TicketDocumentTemplate {
	
	private String someAdditionalNormalTarifInfos;
	
	public NormalTarif(String route, double distance, String tarif, double price, String normalTarifInfos) {
		super(route, distance, tarif, price);
		this.someAdditionalNormalTarifInfos = normalTarifInfos;
	}

	public String getSomeAdditionalNormalTarifInfos() {
		return someAdditionalNormalTarifInfos;
	}
	
	

}
