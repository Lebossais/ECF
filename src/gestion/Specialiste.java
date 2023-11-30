package gestion;

import javax.swing.DefaultRowSorter;
import javax.swing.table.TableModel;

import utilitaire.Regex;

import java.util.regex.Pattern;

public class Specialiste {

	private int Spe_ID;
	Personne Personne;
	private String Spe_Specialite;

	/**
	 * 
	 * @param Personne
	 * @param Spe_Specialite
	 */
	public Specialiste(int Spe_ID, String Spe_Specialite, Personne Personne) {
		super();
		this.Spe_ID = Spe_ID;
		setPersonne(Personne);
		setSpecialite(Spe_Specialite);
	}
	
	/**
	 * 
	 * @return
	 */
	public Personne getPersonne() {
		return Personne;
	}

	/**
	 * 
	 * @param Personne
	 */
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

	/**
	 * 
	 * @return
	 */
	public String getSpecialite() {
		return Spe_Specialite;
	}

	/**
	 * 
	 * @param Specialite
	 */
	public void setSpecialite(String Specialite) {
		try {
			if (Personne == null) {
				throw new NullPointerException("la Specialité ne peut etre null");
			} else {
				this.Spe_Specialite = Specialite;;
			}
		}
		catch(Exception exception){

		}
	}

	public int getSpe_ID() {
		return Spe_ID;
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