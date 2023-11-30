package gestion;


public class Categorie {

	String Cat_Categorie;
	int Cat_ID;

	/**
	 *
	 * @param Cat_ID
	 * @param Categorie
	 */
	public Categorie (int Cat_ID, String Categorie) {
	super();
	this.Cat_ID = Cat_ID;
	this.Cat_Categorie = Categorie;
}

	public int getCat_ID() {
		return Cat_ID;
	}

	public void setCat_ID(int ID) {
		this.Cat_ID = ID;
	}

	public String getCat_Categorie() {
		return Cat_Categorie;
	}

	public void setCat_Categorie(String Categorie) {
	this.Cat_Categorie = Categorie;
	}
}