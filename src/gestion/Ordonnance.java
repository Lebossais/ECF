package gestion;


import javax.swing.DefaultRowSorter;
import javax.swing.table.TableModel;

public class Ordonnance {

	private int Ord_ID;
	private String Ord_Num;
	private String Ord_Date;
	public gestion.Medecin Medecin;
	public gestion.Specialiste Specialiste;
	public gestion.Client Client;
	
	/**
	 * 
	 * @param Ord_Num
	 * @param Medecin
	 * @param Specialiste
	 * @param Ord_Date
	 */
	public Ordonnance(int Ord_ID, String Ord_Num, Client Client, Medecin Medecin, Specialiste Specialiste, String Ord_Date) {
		super();
		this.Ord_ID = Ord_ID;
		this.Ord_Num = Ord_Num;
		this.Client = Client;
		setMedecin(Medecin);
		this.Specialiste = Specialiste;
		this.Ord_Date = Ord_Date;
	}

	public void setOrd_ID(int ord_ID) {
		Ord_ID = ord_ID;
	}
	/**
	 * 
	 * @return
	 */
	public String getOrd_Num() {
		return Ord_Num;
	}
	/**
	 * 
	 * @param Ord_Num
	 */
	public void setOrd_Num(String Ord_Num) {
		Ord_Num = Ord_Num;
	}
	/**
	 * 
	 * @return
	 */
	public String getOrd_Date() {
		return Ord_Date;
	}
	/**
	 * 
	 * @param Ord_Date
	 */
	public void setOrd_Date(String Ord_Date) {
		Ord_Date = Ord_Date;
	}
	/**
	 * 
	 * @return
	 */
	public gestion.Medecin getMedecin() {
		return Medecin;
	}
	/**
	 * 
	 * @param Medecin
	 */
	public void setMedecin(gestion.Medecin Medecin) {
		try {
			if (Medecin == null) {
				throw new NullPointerException("le Medecin ne peut etre null");
			} else {
				this.Medecin = Medecin;
			}
		}
		catch(Exception exception) {
		}
	}
	/**
	 * 
	 * @return
	 */
	public gestion.Specialiste getSpecialiste() {
		return Specialiste;
	}
	/**
	 * 
	 * @param specialiste
	 */
	public void setSpecialiste(gestion.Specialiste specialiste) {
		Specialiste = specialiste;
	}

	public int getOrd_ID() {
		return Ord_ID;
	}

	public gestion.Client getClient() {
		return Client;
	}

	/**
	 * 
	 * @return
	 */
	public static TableModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @return
	 */
	public static int[] getSelectedRows() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @return
	 */
	public static DefaultRowSorter<TableModel, Integer> getRowSorter() {
		// TODO Auto-generated method stub
		return null;
	}
	  
	public String toString() {
		String c = null;
		String b = null;
		if (Specialiste != null) {
			c = Specialiste.getPersonne().getPrenom();
			b = Specialiste.getSpecialite();
		} else if (Specialiste == null) {
			c = "Aucun";
			b = null;
		}
		//"Numéro Ordonnance", "Nom Médecin", "Nom Specialiste", "Date de l'ordonnance"
		return  "\n" +"Numéro Ordonnance : " + Ord_Num + "\n Nom Medecin : " + Medecin.getPersonne().getPrenom() + "\n Specialiste : " + c +" ( "+ b + " ) ";
	}
	  
}