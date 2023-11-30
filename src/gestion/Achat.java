package gestion;

public class Achat {

	String Achat_Date;
	int Achat_ID;
	Client	Client;
	Ordonnance Ordonnance;

	/**
	 *
	 * @param Achat_ID
	 * @param Achat_Date
	 * @param Client
	 * @param Ordonnance
	 */
	public Achat(int Achat_ID, String Achat_Date, Client Client, Ordonnance Ordonnance) {
		this.Achat_ID = Achat_ID;
		this.Achat_Date = Achat_Date;
		this.Client = Client;
		this.Ordonnance = Ordonnance;
	}

	public void setAchat_ID(int achat_ID) {
		Achat_ID = achat_ID;
	}

	public String getAchat_Date() {
		return Achat_Date;
	}

	public int getAchat_ID() {
		return Achat_ID;
	}

	public gestion.Client getClient() {
		return Client;
	}

	public gestion.Ordonnance getOrdonnance() {

		if (Ordonnance == null){

		}
		return Ordonnance;
	}
}
