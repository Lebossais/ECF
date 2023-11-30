package gestion;

import java.util.regex.Pattern;

import javax.swing.DefaultRowSorter;
import javax.swing.table.TableModel;

import utilitaire.Regex;

public class Client {

	private int Cli_ID;
	private String Cli_Date_Naissance;
	private String Cli_Numero_Secu;
	private Mutuelle Mutuelle;
	private Medecin Medecin_Traitant;
	private Specialiste Specialiste;
	public Personne Personne;
	
	/**
	 * 
	 * @param Personne
	 * @param Cli_Date_Naissance
	 * @param Cli_Numero_Secu
	 * @param Mutuelle
	 * @param Medecin_Traitant
	 * @param Specialiste
	 */
	public Client(int Cli_ID, Personne Personne, String Cli_Date_Naissance, String Cli_Numero_Secu, Mutuelle Mutuelle, Medecin Medecin_Traitant, Specialiste Specialiste) {
		super();
		this.Cli_ID = Cli_ID;
		setPersonne(Personne);
		this.Cli_Date_Naissance = Cli_Date_Naissance;
		setNumero_Secu(Cli_Numero_Secu);
		setMutuelle(Mutuelle);;
		setMedecin_Traitant(Medecin_Traitant);
		setSpecialiste(Specialiste);
	}


	@Override
	public String toString() {
		String c = null;
		if (Specialiste != null) {
			c = Specialiste.getPersonne().getPrenom();
		} else if (Specialiste == null) {
			c = "Aucun";
		}
		
		return  "\n" + Personne.toString() + "\n Date de Naissance : " + Cli_Date_Naissance + "\n Numero de Sécurité Sociale : " + Cli_Numero_Secu + "\n Mutuelle : "
				+ Mutuelle.getEntreprise().getEnt_Raison_Sociale() + " \n Medecin Traitant : " + Medecin_Traitant.getPersonne().getPrenom() + "\n Specialiste : " + c ;
	}

	public Mutuelle getMutuelle() {
		return Mutuelle;
	}

	public void setMutuelle(Mutuelle Mutuelle) {
		try {
            if (Mutuelle == null) {
                throw new NullPointerException("la Mutuelle ne peut etre null");
            } else {
                this.Mutuelle = Mutuelle;
            }
        }
        catch(Exception exception){
            
        }
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

	public String getDate_Naissance() {
		return Cli_Date_Naissance;
	}

	public void setDate_Naissance(String date_Naissance) {
		Cli_Date_Naissance = date_Naissance;
	}

	public String getNumero_Secu() {
		return Cli_Numero_Secu;
	}

	public void setNumero_Secu(String numero_Secu)throws IllegalArgumentException {
		if (Pattern.matches(Regex.getRegexSecu, numero_Secu)) {
		this.Cli_Numero_Secu = numero_Secu;
		}
		else {
			throw new IllegalArgumentException("Numéro de Sécurité Sociale incorrecte");
		}
	}

	public Medecin getMedecin_Traitant() {
		return Medecin_Traitant;
	}

	public void setMedecin_Traitant(Medecin Medecin_Traitant) {
		try {
            if (Medecin_Traitant == null) {
                throw new NullPointerException("le médecin Traitant ne peut etre null");
            } else {
                this.Medecin_Traitant = Medecin_Traitant;
            }
        }
        catch(Exception exception){
        }
	}

	public Specialiste getSpecialiste() {
		return Specialiste;
	}

	public void setSpecialiste(Specialiste Specialiste) {
		this.Specialiste = Specialiste;
	}

	public int getCli_ID() {
		return Cli_ID;
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