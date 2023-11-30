package Frame;

import DAO.DAOPanier;
import gestion.Panier;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;

public class ListePanier  extends AbstractTableModel {

    private final DAOPanier daoPanier = new DAOPanier();
    public static final ArrayList<Panier> panier = new ArrayList<>();
    private static final long serialVersionUID = 5380417556060869746L;

    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(Panier.getModel());

     /*
              Permet de définir les entêtes du Tableau (Sera toujours initialiser à 0 ligne)
       */
    private final String[] entetes = {"Nom du Medicament", "Catégorie", "Quantité", "Prix Unitaire", "Prix Total"};

    public ListePanier(){
        super();
    }

    /*
			Permet de définir le nombres maximum de lignes du Tableau
	*/
    /**
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return getListePanier().size();
    }


   /*
			Permet de définir le nombres colonnes du Tableau
	*/
    /**
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    /*
			Permet de mettre les noms des entêtes en face des colonnes
			(En fonction de la position de l'entêtes et du nombre de la colonne)
	*/
    /**
     *
     * @param columnIndex  the column being queried
     * @return
     */
    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    /**
     *
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ListePanier.getPanier(rowIndex).getMedicament().getNom();
            case 1:
                return panier.get(rowIndex).getMedicament().getCategorie().getCat_Categorie();
            case 2:
                return panier.get(rowIndex).getPanier_Qte();
            case 3:
                return panier.get(rowIndex).getMedicament().getPrix() + " €";
            case 4:
                return CalculPrix(rowIndex) + "0 €";
            default:
                return null; //Ne devrait jamais arriver
        }
    }

    /**
     *
     * @param rowIndex
     */
    public void remove(int rowIndex) {
        panier.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     *
     * @param rowIndex
     * @return
     */
    private double CalculPrix(int rowIndex){

        double var1 = panier.get(rowIndex).getMedicament().getPrix();
        double var2 = panier.get(rowIndex).getPanier_Qte();
        double Prixtotal = var1 * var2;
        return Prixtotal;

    }

    /**
     *
     * @return
     */
    public static ArrayList<Panier> getListePanier(){
        return panier;
    }

    /**
     *
     * @param i
     * @return
     */
    public static Panier getPanier(int i){
        return panier.get(i);
    }

    /**
     *
     */
    public static void setPanier(){
        ArrayList<gestion.Panier> Panier = panier;
    }
}
