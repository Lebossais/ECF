package principal;

import DAO.DAOClient;
import DAO.DAOPersonne;
import Frame.ListeClient;
import gestion.Client;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Liste extends JFrame {

private static final long serialVersionUID = 1L;
public static ListeClient modele = new ListeClient();
private JTable tableau = new JTable(modele);
public static JFrame frame;
public DAOClient daoclient = new DAOClient();
public DAOPersonne daoPersonne = new DAOPersonne();
TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableau.getModel());

	/*
        Classe qui permet de gérer la frame hors JTable
     */
public Liste() {
	setIconImage(Toolkit.getDefaultToolkit().getImage(Liste.class.getResource("/Configuration/bank/Logo-removebg-preview.png")));
    setTitle("Liste des Clients");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

    JPanel boutons = new JPanel();

    boutons.add(new JButton(new InformationsAction()));
    boutons.add(new JButton(new NouveauAction()));
    boutons.add(new JButton(new RemoveAction()));
    boutons.add(new JButton(new FilterAction()));
    boutons.add(new JButton(new RetourAction()));
    boutons.add(new JButton(new QuitterAction()));

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
		try {
			int[] selection = tableau.getSelectedRows();
			int[] modelIndexes = new int[selection.length];
			int a = 0;

			for (int i = 0; i < selection.length; i++) {
				modelIndexes[i] = tableau.getRowSorter().convertRowIndexToModel(selection[i]);
			}

			Arrays.sort(modelIndexes);

			for (Client c : daoclient.findALL()){
				if (c.Personne.getPrenom().equals(tableau.getModel().getValueAt(modelIndexes[0],0))){
					daoclient.delete(c);
				}
			}
			Liste.modele.fireTableDataChanged();
							HistoriqueOrdonnances.modele.fireTableDataChanged();
							HistoriqueAchat.modele.fireTableDataChanged();


			revalidate();
		} catch (Exception e2) {
			ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(HistoriqueOrdonnances.class.getResource("/Configuration/bank/Warning.gif")));
			Image image = icon.getImage();
			Image newimg = image.getScaledInstance(120,120, Image.SCALE_REPLICATE);
			icon = new ImageIcon(newimg);
			JOptionPane.showMessageDialog(frame,
					"Veuillez sélectionner au moins une case",
					"Erreur",
					JOptionPane.PLAIN_MESSAGE,
					icon);
			e2.printStackTrace();e2.printStackTrace();
		}
	}
}
class FilterAction extends AbstractAction {

	private static final long serialVersionUID = 1L;



private FilterAction() {
    super("Filtrer");
}
public void actionPerformed(ActionEvent e) {
	try {
    String regex = JOptionPane.showInputDialog("Filtre : ");
    
    sorter.setRowFilter(RowFilter.regexFilter(regex,0,1,2,3 ));
	// capture sur le Boutton annuler
	}catch (NullPointerException e2) {	}
}
}
class RetourAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private RetourAction() {
		super("Retour");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		
	}	
	}
private class InformationsAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private InformationsAction() {
		super("Informations");
	}

	public void actionPerformed(ActionEvent e) {
		try {
			int[] selection = tableau.getSelectedRows();
			int[] modelIndexes = new int[selection.length];

			for (int i = 0; i < selection.length; i++) {
				modelIndexes[i] = tableau.getRowSorter().convertRowIndexToModel(selection[i]);
			}

			Arrays.sort(modelIndexes);

			for (Client c : daoclient.findALL()) {
				if (c.Personne.getPrenom().equals(tableau.getModel().getValueAt(modelIndexes[0], 0))) {
					JOptionPane.showMessageDialog(null, "Voici les informations :" + c.toString());
				}
			}

		} catch (Exception e2) {
			ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(HistoriqueOrdonnances.class.getResource("/Configuration/bank/Warning.gif")));
			Image image = icon.getImage();
			Image newimg = image.getScaledInstance(120,120, Image.SCALE_REPLICATE);
			icon = new ImageIcon(newimg);
			JOptionPane.showMessageDialog(frame,
					"Veuillez sélectionner au moins une case",
					"Erreur",
					JOptionPane.PLAIN_MESSAGE,
					icon);
			e2.printStackTrace();e2.printStackTrace();
		}
	}
}
class QuitterAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	
	private QuitterAction() {
		super("Quitter");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand().toString());
		System.out.println(e.getSource().toString());
		
		int sortie = JOptionPane.showConfirmDialog(frame, "Etes-vous sûr ?", 
				"Quitter", JOptionPane.YES_NO_OPTION);
		
		if (sortie == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
}
class NouveauAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private NouveauAction() {
		super("Nouveau Client");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Template_Client template_client = new Template_Client();
		Template_Client.template_frame.setVisible(true);

	}
}

}