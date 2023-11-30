package gestion;

public class Adresse {

    int Adr_Numero_Rue ;
    String Adr_Nom_Rue ;
    int Adr_Code_Postal ;
    String Adr_Ville ;
    int Adr_ID;

    /**
     *
     * @param Adr_Numero_Rue
     * @param Adr_Nom_Rue
     * @param Adr_Code_Postal
     * @param Adr_Ville
     */
    public Adresse (int Adr_ID, int Adr_Numero_Rue, String Adr_Nom_Rue, int Adr_Code_Postal, String Adr_Ville) {
        super();
        this.Adr_ID = Adr_ID;
        this.Adr_Numero_Rue = Adr_Numero_Rue;
        setNom_rue(Adr_Nom_Rue);
        this.Adr_Code_Postal = Adr_Code_Postal;
        setVille(Adr_Ville);
    }

        public int getNumero_rue() {
            return Adr_Numero_Rue;
        }

        public void setNumero_rue(int numero_rue) {
            this.Adr_Numero_Rue = numero_rue;
        }

        public String getNom_rue() {
            return Adr_Nom_Rue;
        }

        public void setNom_rue(String nom_rue) {
            try {
                if (nom_rue == null) {
                    throw new NullPointerException("la Rue ne peut etre null");
                } else {
                    this.Adr_Nom_Rue = nom_rue;
                }
            }
            catch(Exception exception){

            }
        }

        public int getCodePostal() {
            return Adr_Code_Postal;
        }

        public void setCodePostal(int codePostal) {
            this.Adr_Code_Postal = codePostal;
        }

        public String getVille() {
            return Adr_Nom_Rue;
        }

        public void setVille(String ville) {
            try {
                if (ville == null) {
                    throw new NullPointerException("la Ville ne peut etre null");
                } else {
                    this.Adr_Ville = ville;
                }
            }
            catch(Exception exception){
            }
        }

        public int getAdr_ID() {
            return Adr_ID;
        }

    public void setAdr_ID(int adr_ID) {
        Adr_ID = adr_ID;
    }

    @Override
        public String toString() {
            return Adr_Numero_Rue + ", " + Adr_Nom_Rue + ", " + Adr_Code_Postal + ", "+ Adr_Ville;
        }

}