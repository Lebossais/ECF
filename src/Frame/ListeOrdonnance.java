package Frame;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.DAOOrdonnance;
import gestion.Ordonnance;

public class ListeOrdonnance extends AbstractTableModel {

	private DAOOrdonnance daoOrdonnance = new DAOOrdonnance();

	private static final long serialVersionUID = 5380417556060869746L;

	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(gestion.Ordonnance.getModel());

            /*
                    Permet de définir les entêtes du Tableau
             */
    private final String[] entetes = {"Numéro Ordonnance", "Prénom Médecin", "Date de l'ordonnance"};

    	public ListeOrdonnance() {
    		super();
    	}

    /*
			Permet de définir le nombres maximum de lignes du Tableau
	*/
    /**
     *
     * @return
     */
	public int getRowCount() {
        return (int) daoOrdonnance.findALL().size();
    }

    /*
			Permet de définir le nombres colonnes du Tableau
	*/
    /**
     *
     * @return
     */
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
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    /*
			Permet de remplir les lignes du Tableau avec les valeurs de la BDD
			Fonctionne de façon à comparer l'index de la ligne avec la place de l'objet dans la BDD
			(Index 1 Prends les informations de l'objet à la position 1 de la liste des Objets qui est renvoyé par le FindAll())
	*/
    /**
     *
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return
     */
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
        case 0:
        	return  daoOrdonnance.findALL().get(rowIndex).getOrd_Num();
        case 1:
            return daoOrdonnance.findALL().get(rowIndex).getMedecin().getPersonne().getPrenom();
        case 2 :
        	return daoOrdonnance.findALL().get(rowIndex).getOrd_Date();
        default:
            return null; //Ne devrait jamais arriver
	}
}
}
