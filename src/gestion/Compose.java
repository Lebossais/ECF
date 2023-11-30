package gestion;

import javax.swing.DefaultRowSorter;
import javax.swing.table.TableModel;

public class Compose {

	public Ordonnance Ordonnance;
	Medicament Medicament;
	int Compose_Qte;

	/**
	 *
	 * @param Ordonnance
	 * @param Medicament
	 */
	public Compose(Ordonnance Ordonnance, Medicament Medicament, int Compose_Qte) {
		super();
		this.Ordonnance = Ordonnance;
		this.Medicament = Medicament;
		this.Compose_Qte = Compose_Qte;
	}

	public Ordonnance getOrdonnance() {
		return Ordonnance;
	}

	public Medicament getMedicament() {
		return Medicament;
	}

	public int getCompose_Qte() {
		return Compose_Qte;
	}

	public void setOrd_ID(Ordonnance Ordonnance) {
		Ordonnance = Ordonnance;
	}

	public void setMedi_ID(Medicament medi_ID) {
		Medicament = medi_ID;
	}

	public void setCompose_Qte(int compose_Qte) {
		Compose_Qte = compose_Qte;
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
	//@Override
	//public String toString() {
	//	return "\n Date de l'achat: " + Date  +"\n Personne : " + Personne + "\n Numéro de Securité Sociale : " + Num_Secu + "\n Mutuelle : " + Mutuelle.getPersonne().getPrenom()
	// + "\n Numéro de Departement : " +Mutuelle.getDepartement() +" \n Prise en Charge :" + Mutuelle.getPrise_En_Charge()+ "%"+ "\n\n Ordonnance : " + Ordonnance + "\n\n Résumé Commande : " + Medicament;
	//}
}