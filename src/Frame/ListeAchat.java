package Frame;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.DAOAchat;
import gestion.Compose;

public class ListeAchat extends AbstractTableModel {

	private DAOAchat daoAchat = new DAOAchat();

	private static final long serialVersionUID = 5380417556060869746L;
	Object p = null;
	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(Compose.getModel());

		/*
				Permet de définir les entêtes du Tableau
		 */
    private final String[] entetes = {"N°Achat","Prénom du Client", "N° de Sécu", "N° Ordonnance", "Date"}; // Les différentes entêtes

	/*
			Permet de définir le nombres maximum de lignes du Tableau
	*/
	/**
	 *
	 * @return
	 */
	@Override
	public int getRowCount() {
        return daoAchat.findALL().size();
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
				return daoAchat.findALL().get(rowIndex).getAchat_ID();
        case 1:
        	return daoAchat.findALL().get(rowIndex).getClient().getPersonne().getPrenom();
        case 2:
            return daoAchat.findALL().get(rowIndex).getClient().getNumero_Secu();
        case 3 :
        	if (daoAchat.findALL().get(rowIndex).getOrdonnance() == null) {
				return null;
			}else if (daoAchat.findALL().get(rowIndex).getOrdonnance() !=null) {
				return daoAchat.findALL().get(rowIndex).getOrdonnance().getOrd_Num();
			}
        case 4 :
        	return daoAchat.findALL().get(rowIndex).getAchat_Date();
        default:
            return null; //Ne devrait jamais arriver
	}
}
}