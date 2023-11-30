package gestion;

import utilitaire.Regex;

import javax.swing.table.TableModel;
import java.util.regex.Pattern;

    public class Personne {

        private int Per_ID;
        private String Per_Prenom ;
        private String Per_Nom ;
        private Adresse Adresse;
        private String Per_Telephone;
        private String Per_Email;

        /**
         *
         * @param Per_Prenom
         * @param Per_Name
         * @param Adresse
         * @param Per_Telephone
         * @param Per_Email
         */
        public Personne(int Per_ID, String Per_Prenom, String Per_Name, gestion.Adresse Adresse, String Per_Telephone, String Per_Email) {
            super();
            this.Per_ID = Per_ID;
            setPrenom(Per_Prenom);
            setName(Per_Name);
            this.Adresse = Adresse;
            setTelephone(Per_Telephone);
            setEmail(Per_Email);
        }
        /**
         *
         * @return
         */
        public String getPrenom() {
            return Per_Prenom;
        }
        /**
         *
         * @param Prenom
         */
        public void setPrenom(String Prenom) {
            if (Pattern.matches(Regex.getRegexPrenom, Prenom)) {
                this.Per_Prenom = Prenom;
            }
            else {
                throw new IllegalArgumentException("Saisie du Prenom incorrecte");
            }
        }
        /**
         *
         * @return
         */
        public String getName() {
            return Per_Nom;
        }

        @Override
        public String toString() {
            return " Prénom : " + Per_Prenom +"\n Nom : " + Per_Nom + "\n Adresse : " + Adresse + "\n N°Telephone : " + Per_Telephone
                    + "\n Email : " + Per_Email ;
        }

        /**
         *
         * @param Nom
         */
        public void setName(String Nom) {
            if (Pattern.matches(Regex.getRegexNom, Nom)) {
                this.Per_Nom = Nom;
            }
            else {
                throw new IllegalArgumentException("Saisie du Nom incorrecte");
            }

        }

        /**
         *
         * @return
         */
        public Adresse getAdresse() {
            return Adresse;
        }

        /**
         *
         * @param Adresse
         */
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

        /**
         * @return
         */
        public String getTelephone() {
            return Per_Telephone;
        }

        /**
         *
         * @param Telephone
         */
        public void setTelephone(String Telephone) {
            if (Pattern.matches(Regex.getRegexPhone, Telephone)) {
                this.Per_Telephone = Telephone;
            }
            else {
                throw new IllegalArgumentException("Numéro de Téléphone incorrect");
            }
        }

        /**
         *
         * @return
         */
        public String getEmail() {
            return Per_Email;
        }

        /**
         *
         * @param Email
         */
        public void setEmail(String Email) {
            if (Pattern.matches(Regex.getRegexEmail, Email)) {
                this.Per_Email = Email ;
            }
            else {
                throw new IllegalArgumentException("Saisie de l'E-mail incorrecte");
            }
        }

        public int getPer_ID() {
            return Per_ID;
        }

        public void setPer_ID(int per_ID) {
            Per_ID = per_ID;
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
    }