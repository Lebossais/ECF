package principal;

import DAO.*;
import Frame.ListePanier;
import gestion.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class FrmAchat {
	JFrame Achat;
	private JTextField txtDateOrdonnance;
	private JTextField txtNumOrdonnance;
	static JLabel txtDate = new JLabel();
	boolean b = false;
	JComboBox comboBoxCategorie;
	JComboBox comboBoxClient;
	JComboBox comboBoxMutuelle;
	JComboBox comboBoxSpe;
	JComboBox comboBoxMed;
	JComboBox comboBoxMedicament;
	private JTextField txtQuantite;
	private DAOMedecin daoMedecin = new DAOMedecin();
	private DAOSpecialiste daoSpecialiste = new DAOSpecialiste();
	private DAOClient daoClient = new DAOClient();
	private DAOMutuelle daoMutuelle = new DAOMutuelle();
	private DAOCategorie daoCategorie = new DAOCategorie();
	private DAOMedicament daoMedicament = new DAOMedicament();
	private DAOOrdonnance daoOrdonnance = new DAOOrdonnance();
	private DAOCompose daoCompose = new DAOCompose();
	private DAOAchat daoAchat = new DAOAchat();
	private DAOPanier daoPanier = new DAOPanier();
	public JFrame frame;
	public static ListePanier modele = new ListePanier();
	private JTable tableau = new JTable(modele);
	private Thread n = new Thread(new traitement_heur());
	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableau.getModel());

/*
	Initialise la Frame avec la recherche de toute les données et aussi l'horloge de la date actuelle
 */
	/**
	 * Create the application.
	 */
	public FrmAchat() {
		n.start();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Achat = new JFrame();
		Achat.setTitle("Achat");
		Achat.setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAchat.class.getResource("/Configuration/bank/Logo-removebg-preview.png")));
		Achat.setBounds(100, 100, 639, 641);
		Achat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel_1 = new JPanel();
		Achat.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JRadioButton IfOrdonnance = new JRadioButton("Avec Ordonnance");
		IfOrdonnance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		IfOrdonnance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AfficherOrdonnance(e);
			}
		});
		IfOrdonnance.setBounds(25, 427, 130, 23);
		panel_1.add(IfOrdonnance);

		JLabel Client = new JLabel("Prenom Client :");
		Client.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Client.setBounds(25, 11, 103, 20);
		panel_1.add(Client);

		JLabel Mutuelle = new JLabel("Mutuelle :");
		Mutuelle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Mutuelle.setBounds(246, 11, 84, 20);
		panel_1.add(Mutuelle);

		JLabel nomMedecin = new JLabel("Medecin :\r\n");
		nomMedecin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomMedecin.setBounds(25, 478, 151, 29);
		panel_1.add(nomMedecin);

		JLabel NomSpecialiste = new JLabel("Specialiste :");
		NomSpecialiste.setFont(new Font("Tahoma", Font.PLAIN, 13));
		NomSpecialiste.setBounds(246, 478, 151, 29);
		panel_1.add(NomSpecialiste);

		txtDateOrdonnance = new JTextField();
		txtDateOrdonnance.setText("AAAA-MM-JJ");
		txtDateOrdonnance.setEnabled(false);
		txtDateOrdonnance.setColumns(10);
		txtDateOrdonnance.setBounds(445, 509, 151, 29);
		panel_1.add(txtDateOrdonnance);

		JLabel DateOrdonnance = new JLabel("Date de l'ordonnance :");
		DateOrdonnance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		DateOrdonnance.setBounds(445, 478, 151, 29);
		panel_1.add(DateOrdonnance);

		txtNumOrdonnance = new JTextField();
		txtNumOrdonnance.setText("XXX");
		txtNumOrdonnance.setEnabled(false);
		txtNumOrdonnance.setColumns(10);
		txtNumOrdonnance.setBounds(418, 425, 151, 29);
		panel_1.add(txtNumOrdonnance);

		JLabel NumOrdonnance = new JLabel("Numéro Ordonnance :");
		NumOrdonnance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		NumOrdonnance.setBounds(269, 428, 139, 20);
		panel_1.add(NumOrdonnance);

		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDate.setBounds(462, 40, 151, 29);
		panel_1.add(txtDate);

		JLabel Date = new JLabel("Date :");
		Date.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Date.setBounds(462, 11, 84, 20);
		panel_1.add(Date);

		comboBoxMutuelle = new JComboBox();
		comboBoxMutuelle.setBounds(244, 40, 151, 29);
		panel_1.add(comboBoxMutuelle);
		for (int i = 1; i < daoMutuelle.findALL().size() + 1; i++) {
			comboBoxMutuelle.addItem(daoMutuelle.find(i).getEntreprise().getEnt_Raison_Sociale());
		}

		comboBoxMed = new JComboBox();
		comboBoxMed.setEnabled(false);
		comboBoxMed.setBounds(25, 509, 151, 29);
		panel_1.add(comboBoxMed);
		for (int i = 1; i < daoMedecin.findALL().size() + 1; i++) {
			comboBoxMed.addItem(daoMedecin.find(i).getPersonne().getPrenom());
		}

		comboBoxSpe = new JComboBox();
		comboBoxSpe.setEnabled(false);
		comboBoxSpe.setBounds(246, 508, 151, 30);
		panel_1.add(comboBoxSpe);
		for (int i = 1; i < daoSpecialiste.findALL().size() + 1; i++) {
			comboBoxSpe.addItem(daoSpecialiste.find(i).getPersonne().getPrenom());
		}
		comboBoxSpe.addItem(null);

		comboBoxClient = new JComboBox();
		comboBoxClient.setBounds(25, 39, 151, 29);
		panel_1.add(comboBoxClient);
		for (int i = 1; i < daoClient.findALL().size() + 1; i++) {
			comboBoxClient.addItem(daoClient.find(i).getPersonne().getPrenom());
		}

		comboBoxMedicament = new JComboBox();
		comboBoxMedicament.setBounds(245, 125, 130, 29);
		panel_1.add(comboBoxMedicament);

		comboBoxCategorie = new JComboBox();
		comboBoxCategorie.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxMedicament.removeAllItems();
				for (int i = 1; i < daoMedicament.findALL().size() + 1; i++) {
					if (comboBoxCategorie.getSelectedItem().equals(daoMedicament.find(i).getCategorie().getCat_Categorie())) {
						comboBoxMedicament.addItem(daoMedicament.find(i).getNom());
					}
				}
			}
		});
		comboBoxCategorie.setBounds(25, 125, 130, 29);
		panel_1.add(comboBoxCategorie);
		for (int i = 1; i < daoCategorie.findALL().size() + 1; i++) {
			comboBoxCategorie.addItem(daoCategorie.find(i).getCat_Categorie());
		}

		JLabel lblCategorie = new JLabel("Catégorie :");
		lblCategorie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategorie.setBounds(26, 94, 84, 20);
		panel_1.add(lblCategorie);

		txtQuantite = new JTextField();
		txtQuantite.setColumns(10);
		txtQuantite.setBounds(430, 127, 97, 24);
		panel_1.add(txtQuantite);

		JLabel lblMedicament = new JLabel("Medicament :");
		lblMedicament.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMedicament.setBounds(246, 94, 84, 20);
		panel_1.add(lblMedicament);

		JLabel lblQuantit = new JLabel("Quantité :");
		lblQuantit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantit.setBounds(430, 94, 84, 20);
		panel_1.add(lblQuantit);

		JPanel panel = new JPanel();
		panel.setBounds(0, 569, 623, 33);
		panel_1.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnReinitialiser = new JButton("Reinitialiser Achat");
		panel.add(btnReinitialiser);
		btnReinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton ValideButton = new JButton("Valider");
		panel.add(ValideButton);
		ValideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Valider(e);
			}
		});

		JButton RetourButton = new JButton("Retour");
		panel.add(RetourButton);
		RetourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Retour(e);
			}
		});

		JButton QuitterButton = new JButton("Quitter");
		panel.add(QuitterButton);
		QuitterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quitter(e);
			}
		});

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(507, 166, 89, 23);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ajouter(e);
			}
		});
		panel_1.add(btnAjouter);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 200, 571, 196);
		panel_1.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnReinitialiserPanier = new JButton("Réinitialiser");
		btnReinitialiserPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reinitPanier(e);
			}
		});
		panel_3.add(btnReinitialiserPanier);

		JButton btnInformations = new JButton("Informations");
		btnInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInformations(e);
			}
		});
		panel_3.add(btnInformations);

		JButton Retirer = new JButton("Retirer");
		Retirer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Retirer(e);
			}
		});
		panel_3.add(Retirer);

		JScrollPane jScrollPane1 = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		jScrollPane1.setViewportView(tableau);
		tableau.setAutoCreateRowSorter(true);
		tableau.setRowSorter(sorter);
		sorter.setSortsOnUpdates(true);
		panel_2.setLayout(gl_panel_2);
	}

	/*
		Gestion de l'affichage du radio Button (Avec ou Sans Ordonnance)
	 */
	/**
	 *
	 * @param e
	 */
	private void AfficherOrdonnance(ActionEvent e) {
		if (b == false) {
			b = true;
		} else if (b == true) {
			b = false;
		}
		InitializeOrdonnance(b);
	}

	/**
	 *
	 * @param b
	 */
	private void InitializeOrdonnance(boolean b) {
		if (b == true) {
			txtNumOrdonnance.setEnabled(true);
			txtDateOrdonnance.setEnabled(true);
			comboBoxSpe.setEnabled(true);
			comboBoxMed.setEnabled(true);
		} else if (b == false) {
			txtNumOrdonnance.setEnabled(false);
			txtDateOrdonnance.setEnabled(false);
			comboBoxSpe.setEnabled(false);
			comboBoxMed.setEnabled(false);
		}
	}

	/*
		Renvoie les Informations du ou des cases sélectionnés dans le Panier
	 */
	/**
	 *
	 * @param e
	 */
	private void btnInformations(ActionEvent e) {
		try {
			int[] selection = tableau.getSelectedRows();
			int[] modelIndexes = new int[selection.length];

			for (int i = 0; i < selection.length; i++) {
				modelIndexes[i] = tableau.getRowSorter().convertRowIndexToModel(selection[i]);
			}

			Arrays.sort(modelIndexes);

			for (Medicament m : daoMedicament.findALL()) {
				if (m.getNom().equals(tableau.getModel().getValueAt(modelIndexes[0], 0))) {
					JOptionPane.showMessageDialog(null, "Voici les informations :" + m.toString());
				}
			}
		} catch (Exception ex) {
			ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(HistoriqueOrdonnances.class.getResource("/Configuration/bank/Warning.gif")));
			Image image = icon.getImage();
			Image newimg = image.getScaledInstance(120, 120, Image.SCALE_REPLICATE);
			icon = new ImageIcon(newimg);
			JOptionPane.showMessageDialog(frame,
					"Veuillez sélectionner au moins une case",
					"Erreur",
					JOptionPane.PLAIN_MESSAGE,
					icon);
			ex.printStackTrace();
		}
	}

	/*
		Reinitialise le Panier en cours
	 */
	/**
	 *
	 * @param e
	 */
	private void reinitPanier(ActionEvent e) {
		ListePanier.panier.clear();
		modele.fireTableDataChanged();
	}

	/*
		Retirer le ou les lignes sélectionner du Panier qui est en cours
	 */
	/**
	 *
	 * @param e
	 */
	private void Retirer(ActionEvent e) {
		try {
			int[] selection = tableau.getSelectedRows();
			int[] modelIndexes = new int[selection.length];

			for (int i = 0; i < selection.length; i++) {
				modelIndexes[i] = tableau.getRowSorter().convertRowIndexToModel(selection[i]);
			}

			Arrays.sort(modelIndexes);

			for (int i = modelIndexes.length - 1; i >= 0; i--) {
				ListePanier.panier.remove(modelIndexes[i]);
			}
			modele.fireTableDataChanged();
		} catch (Exception ex) {
			ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(HistoriqueOrdonnances.class.getResource("/Configuration/bank/Warning.gif")));
			Image image = icon.getImage();
			Image newimg = image.getScaledInstance(120, 120, Image.SCALE_REPLICATE);
			icon = new ImageIcon(newimg);
			JOptionPane.showMessageDialog(frame,
					"Veuillez sélectionner au moins une case",
					"Erreur",
					JOptionPane.PLAIN_MESSAGE,
					icon);
			ex.printStackTrace();
		}
	}

	/**
	 *
	 * @param e
	 */
	private void Retour(ActionEvent e) {
		Achat.dispose();
	}

	/**
	 *
	 * @param e
	 */
	private void Quitter(ActionEvent e) {
		System.out.println(e.getActionCommand().toString());
		System.out.println(e.getSource().toString());

		int sortie = JOptionPane.showConfirmDialog(frame, "Etes-vous sûr ?",
				"Quitter", JOptionPane.YES_NO_OPTION);

		if (sortie == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/*
		Cherche dans la BDD le médicament sélectionné et l'ajoute dans le Panier en cours
	 */
	/**
	 *
	 * @param e
	 */
	private void Ajouter(ActionEvent e) {
		Medicament m = null;
		int Qte;

		try {
			for (int i = 0; i < daoMedicament.findALL().size(); i++) {
				Object p = comboBoxMedicament.getSelectedItem();
				if (p.equals(daoMedicament.find(i + 1).getNom())) {
					m = daoMedicament.find(i + 1);
					break;
				}
			}
			Qte = Integer.parseInt(txtQuantite.getText());
			ListePanier.panier.add(new Panier(null, m, Qte));
		} catch (java.lang.NumberFormatException e2) {
			JOptionPane.showConfirmDialog(null, "Ajout non effectué ", "Erreur", JOptionPane.DEFAULT_OPTION);
		}
		modele.fireTableDataChanged();
	}

	/*
		Etape de Validation de l'Achat :
			-	Sans Ordonnance :
				- Créer d'abord l'Achat en prenant le Client / La Date / Et fixe l'Ordonnance à null
				- Puis Créer les nombres de lignes correspondantes aux Panier et les associes avec l'achat et le médicament

			- Avec Ordonnance:
				- Créer l'Ordonnance dans un premier temps
				- Créer le "Compose" avec l'Id de l'Ordonnance ainsi que les médicaments associer au "Panier"
				- Créer l'Achat qui est associé à l'Ordonnance puis avec la Date et le Client

		Clear le Panier à la fin lorsqu'il y a Validation de toutes les étapes
	 */
	/**
	 *
	 * @param e
	 */
	private void Valider(ActionEvent e) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		int i;
		Client client = null;
		Ordonnance ord = null;
		Specialiste spe = null;
		Achat achat = null;
		String Date = null;
		Medecin med = null;

		try {
			for (i = 1; i < comboBoxClient.getItemCount()+1; i++) {
				Object p = comboBoxClient.getSelectedItem();
				if (p.equals(daoClient.find(i).getPersonne().getPrenom())) {
					client = daoClient.find(i);
					break;
				}
			}
			for (i = 1; i < comboBoxMutuelle.getItemCount()+1; i++) {
				Object p = comboBoxMutuelle.getSelectedItem();
				if (p.equals(daoMutuelle.find(i).getEntreprise().getEnt_Raison_Sociale())) {
					break;
				}
			}
			Date = txtDate.getText();

			if (b == true) {
				for (i = 1; i < comboBoxMed.getItemCount()+1; i++) {
					Object p = comboBoxMed.getSelectedItem();
					if (p.equals(daoMedecin.find(i).getPersonne().getPrenom())) {
						med = daoMedecin.find(i);
						break;
					}
				}
				for (i = 1; i < comboBoxSpe.getItemCount()+1; i++) {
					Object p = comboBoxSpe.getSelectedItem();
					if (p == null) {
						spe = null;
						break;
					} else if (p.equals(daoSpecialiste.find(i).getPersonne().getPrenom())) {
						spe = daoSpecialiste.find(i);
						break;
					}
				}
				ord = new Ordonnance(1, txtNumOrdonnance.getText(), client, med, spe, Date);
				int NewIDOrd = daoOrdonnance.createOrdonnance(ord);
				ord.setOrd_ID(NewIDOrd);
				for (i = 0; i < ListePanier.panier.size(); i++) {
					daoCompose.create(new Compose(ord, ListePanier.getPanier(i).getMedicament(), ListePanier.getPanier(i).getPanier_Qte()));
				}
				daoAchat.create(new Achat(1, dtf.format(now), client, ord));
			} else if (b == false) {
				achat = new Achat(1, dtf.format(now), client, ord);
				int NewIDAchat = daoAchat.createAchatPanier(achat);
				achat.setAchat_ID(NewIDAchat);
				for (i = 0; i < ListePanier.panier.size(); i++) {
					daoPanier.create(new Panier(achat, ListePanier.getPanier(i).getMedicament(), ListePanier.getPanier(i).getPanier_Qte()));
				}
			}
			ListePanier.panier.clear();
			Achat.dispose();
			int input = JOptionPane.showConfirmDialog(null,
					"Nouvel achat effectué", "Validation", JOptionPane.DEFAULT_OPTION);
		} catch (java.lang.NumberFormatException e2) {
			JOptionPane.showConfirmDialog(null, "Achat non effectué ", "Erreur", JOptionPane.DEFAULT_OPTION);
		}
	}


	/*
		Classe interne qui gère l'horloge et qui fait afficher l'heure actuelle sous format
		Jour / Mois / Année et Heure : Minutes : Secondes et lance un timer pour actualiser l'heure
	 */
	/**
	 *
	 */
	class traitement_heur implements Runnable {
		public void run() {
			Boolean bool = true;
			while (bool) {
				SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				Time heure;
				heure = new Time(0);
				heure.setTime(System.currentTimeMillis());
				FrmAchat.txtDate.setText(s.format(date)+ " " + heure);

				try {
					n.sleep(1000);
				}catch (Exception e){

				}
			}
		}
	}
}

