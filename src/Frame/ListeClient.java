package Frame;


import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.DAOClient;
import gestion.Client;

public class ListeClient extends AbstractTableModel {

	private DAOClient daoClient = new DAOClient();

		private static final long serialVersionUID = 5380417556060869746L;

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(Client.getModel());

		/*
				Permet de définir les entêtes du Tableau
		 */
		/**
	 * 	Retourne les entetes du tableau
	 */
	private final String[] entetes = {"Prenom", "Nom", "N° de Secu", "Telephone"};

	/*
			Permet de définir le nombres maximum de lignes du Tableau
	*/
	/**
	 *
	 * @return
	 */
	public int getRowCount() {
	        return daoClient.findALL().size();
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
	                return daoClient.findALL().get(rowIndex).getPersonne().getPrenom();
	            case 1:
	                return daoClient.findALL().get(rowIndex).getPersonne().getName();
	            case 2:
	                return daoClient.findALL().get(rowIndex).getNumero_Secu();
	            case 3 :
	            	return daoClient.findALL().get(rowIndex).getPersonne().getTelephone();
	            default:
	                return null; //Ne devrait jamais arriver
	        }
	    }

		/**
	     * 
	     * @param Liste
	     */
	    public void Retour(JTable Liste) {
	    }
}
