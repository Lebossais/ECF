package gestion;

import Frame.ListePanier;

import javax.swing.*;
import javax.swing.table.TableModel;

public class Panier {

    Achat Achat;
     Medicament Medicament;
     int Panier_Qte;

    /**
     *
     * @param Achat
     * @param Medicament
     * @param Panier_Qte
     */
    public Panier (Achat Achat, Medicament Medicament, int Panier_Qte) {
        this.Achat = Achat;
        this.Medicament = Medicament;
        this.Panier_Qte = Panier_Qte;
    }

    public gestion.Achat getAchat() {
        return Achat;
    }

    public  gestion.Medicament getMedicament() {
        return Medicament;
    }

    public  int getPanier_Qte() {
        return Panier_Qte;
    }

    /**
     *
     * @return
     */
	@Override
    public String toString() {
    	return "\n Quantité : " + getPanier_Qte()  + Medicament + "\n Prix total : " + "€"; //ListePanier.prixtotal()+
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