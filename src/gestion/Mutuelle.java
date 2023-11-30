package gestion;


import javax.swing.DefaultRowSorter;
import javax.swing.table.TableModel;

public class Mutuelle {

	private int Mut_ID;
	public Entreprise  Entreprise ;
	private int Mut_Departement;
	private int Mut_Prise_En_Charge;

	/**
	 * @param Mut_ID
	 * @param Entreprise
	 * @param Mut_Departement
	 * @param Mut_Prise_En_Charge
	 */
	public Mutuelle(int Mut_ID, Entreprise Entreprise, int Mut_Departement, int Mut_Prise_En_Charge) {
		super();
		this.Mut_ID = Mut_ID;
		setEntreprise (Entreprise );
		this.Mut_Departement = Mut_Departement ;
		this.Mut_Prise_En_Charge = Mut_Prise_En_Charge;
	}

	public Entreprise  getEntreprise () {
		return Entreprise ;
	}

	public void setEntreprise (Entreprise  Entreprise ) {
		try {
			if (Entreprise  == null) {
				throw new NullPointerException("l'Entreprise  ne peut etre null");
			} else {
				this.Entreprise  = Entreprise ;
			}
		} catch (Exception exception) {
		}
	}

	public int getDepartement() {
		return Mut_Departement;
	}

	public void setDepartement(int departement) {
		Mut_Departement = departement;
	}

	public int getPrise_En_Charge() {
		return Mut_Prise_En_Charge;
	}

	public void setPrise_En_Charge(int prise_En_Charge) {
		Mut_Prise_En_Charge = prise_En_Charge;
	}

	public int getMut_ID() {
		return Mut_ID;
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
}