package gestion;

import utilitaire.Regex;

import java.util.regex.Pattern;

public class Entreprise {

    private int Ent_ID;
    private String Ent_Raison_Sociale;
    private String Ent_Telephone;
    private String Ent_Email;
    private Adresse Adresse;

    /**
     *
     * @param Ent_ID
     * @param Ent_Raison_Sociale
     * @param Ent_Telephone
     * @param Ent_Email
     * @param Adresse
     */
    public Entreprise (int Ent_ID, String Ent_Raison_Sociale, String Ent_Telephone, String Ent_Email, Adresse Adresse){
        super();
    this.Ent_ID = Ent_ID;
    this.Ent_Raison_Sociale = Ent_Raison_Sociale;
    this.Ent_Telephone = Ent_Telephone;
    this.Ent_Email = Ent_Email;
    this.Adresse = Adresse;
    }

    public int getEnt_ID() {
        return Ent_ID;
    }

    public String getEnt_Raison_Sociale() {
        return Ent_Raison_Sociale;
    }
    public void setEnt_Raison_Sociale(String ent_Raison_Sociale) {
        Ent_Raison_Sociale = ent_Raison_Sociale;
    }

    public String getEnt_Telephone() {
        return Ent_Telephone;
    }

    public void setEnt_Telephone(String Ent_Telephone) {
        if (Pattern.matches(Regex.getRegexPhone, Ent_Telephone)) {
            this.Ent_Telephone = Ent_Telephone;
        }
        else {
            throw new IllegalArgumentException("Numéro de Téléphone incorrect");
        }
    }

    public String getEnt_Email() {
        return Ent_Email;
    }

    public void setEnt_Email(String ent_Email) {
        if (Pattern.matches(Regex.getRegexEmail, Ent_Email)) {
            this.Ent_Email = Ent_Email ;
        }
        else {
            throw new IllegalArgumentException("Saisie de l'E-mail incorrecte");
        }
    }
    public Adresse getAdresse() {
        return Adresse;
    }

    public void setAdresse(Adresse Adresse) {
        try {
            if (Adresse == null) {
                throw new NullPointerException("l'Adresse ne peut etre null");
            } else {
                this.Adresse = Adresse;
            }
        }
        catch(Exception exception){
        }
    }
}
