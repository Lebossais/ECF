package principal;

import DAO.DAOPanier;
import Frame.ListePanier;
import gestion.Panier;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class PanierTable extends JFrame {
    private static final long serialVersionUID = 1L;

    public static ListePanier modele = new ListePanier();
    private static JTable tableau = new JTable(modele);
    static JPanel boutons = new JPanel();
    public static JFrame frame;
    public DAOPanier daoPanier = new DAOPanier();
    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableau.getModel());

    /*
	    Classe qui permet de gérer la frame hors JTable
    */

    public PanierTable() {
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        boutons.add(new JButton(new InformationsAction()));
        boutons.add(new JButton(new RemoveAction()));

        getContentPane().add(boutons, BorderLayout.SOUTH);
        tableau.setAutoCreateRowSorter(true);
        tableau.setRowSorter(sorter);
        sorter.setSortable(2, false);
        sorter.setSortsOnUpdates(true);

        pack();
    }

    private class RemoveAction extends AbstractAction {

        private static final long serialVersionUID = 1L;

        private RemoveAction() {
            super("Supprimmer");
        }
        public void actionPerformed(ActionEvent e) {
            int[] selection = tableau.getSelectedRows();
            int[] modelIndexes = new int[selection.length];

            for(int i = 0; i < selection.length; i++){
                modelIndexes[i] = tableau.getRowSorter().convertRowIndexToModel(selection[i]);
            }

            Arrays.sort(modelIndexes);

            for(int i = modelIndexes.length - 1; i >= 0; i--){
                daoPanier.deletebyID(modelIndexes[i]);
                // modele.removeClient(modelIndexes[i]);
            }
            revalidate();
        }
    }

    private class InformationsAction extends AbstractAction {

        private static final long serialVersionUID = 1L;

        private InformationsAction() {
            super("Informations");
        }

        public void actionPerformed(ActionEvent e) {
            int[] selection = tableau.getSelectedRows();
            int[] modelIndexes = new int[selection.length];

            for(int i = 0; i < selection.length; i++){
                modelIndexes[i] = tableau.getRowSorter().convertRowIndexToModel(selection[i]);
            }

            Arrays.sort(modelIndexes);

            for (Panier p : daoPanier.findALL()) {
                if (p.getMedicament().equals(tableau.getModel().getValueAt(modelIndexes[0], 0))){
                    JOptionPane.showMessageDialog(null, "Voici les informations :" + p.toString());
                }
            }
        }
    }
}