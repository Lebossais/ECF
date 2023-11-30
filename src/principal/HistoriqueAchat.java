package principal;

import DAO.DAOAchat;
import Frame.ListeAchat;
import gestion.Achat;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class HistoriqueAchat extends JFrame {

private static final long serialVersionUID = 1L;
public static ListeAchat modele = new ListeAchat();
private JTable tableau = new JTable(modele);
public JFrame frame;
private DAOAchat daoAchat = new DAOAchat();
TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableau.getModel());

/*
	Classe qui permet de gérer la frame hors JTable
 */
	/**
	 *
	 */
	public HistoriqueAchat() {
	setIconImage(Toolkit.getDefaultToolkit().getImage(HistoriqueAchat.class.getResource("/Configuration/bank/Logo-removebg-preview.png")));
    setTitle("Historique des Achat");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
    JPanel boutons = new JPanel();

    boutons.add(new JButton(new InformationsAction()));
    boutons.add(new JButton(new FilterAction()));
    boutons.add(new JButton(new RetourAction()));
    boutons.add(new JButton(new QuitterAction()));

    getContentPane().add(boutons, BorderLayout.SOUTH);
    tableau.setAutoCreateRowSorter(true); 
    tableau.setRowSorter(sorter);
    sorter.setSortsOnUpdates(true);
  

    pack();
}

	/**
	 *
	 */
	class FilterAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

		private FilterAction() {
    super("Filtrer");
}

public void actionPerformed(ActionEvent e) {
	try {
    String regex = JOptionPane.showInputDialog("Indiquer la Date : ");
    
    sorter.setRowFilter(RowFilter.regexFilter(regex,1,2,3,4,5));
	} 
	// capture sur le Boutton annuler
	catch (NullPointerException e2) {	}
}
}
class RetourAction extends AbstractAction {
	/**
	 * 
	 */
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
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InformationsAction() {
        super("Informations");
    }

 public void actionPerformed(ActionEvent e) {
	 try {
    int[] selection = tableau.getSelectedRows();
    int[] modelIndexes = new int[selection.length];
    for(int i = 0; i < selection.length; i++){
        modelIndexes[i] = tableau.getRowSorter().convertRowIndexToModel(selection[i]);
    }

    Arrays.sort(modelIndexes);

    for (Achat c : daoAchat.findALL()) {
        if (c.getAchat_ID()==((int) tableau.getModel().getValueAt(modelIndexes[0], 0))) {
            JOptionPane.showMessageDialog(null, "Voici les informations :" + c.toString()); // Tout les détails inscrite lors de l'achat
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
		e2.printStackTrace();
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
}