package gestion;

import javax.swing.DefaultRowSorter;
import javax.swing.table.TableModel;

public class Medecin {

	private int Med_ID;
	public Personne Personne;
	private String Med_Aggrement;

	/**
	 * @param Med_ID
	 * @param Personne
	 * @param Med_Aggrement
	 */
	public Medecin(int Med_ID, Personne Personne, String Med_Aggrement) {
		super();
		this.Med_ID = Med_ID;
		setPersonne(Personne);
		this.Med_Aggrement = Med_Aggrement;
	}

	public Personne getPersonne() {
		return Personne;
	}

	public void setPersonne(Personne Personne) {
		try {
			if (Personne == null) {
				throw new NullPointerException("la Personne ne peut etre null");
			} else {
				this.Personne = Personne;
			}
		}
		catch(Exception exception){
		}
	}

	public String getAggrement() {
		return Med_Aggrement;
	}

	public void setAggrement(String aggrement) {
		Med_Aggrement = aggrement;
	}

	public int getMed_ID() {
		return Med_ID;
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