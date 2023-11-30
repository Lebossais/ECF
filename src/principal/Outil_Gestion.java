package principal;

import Configuration.SQL.Singleton;
import DAO.DAOClient;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Outil_Gestion {

	int i = 0;
	Liste liste = null;
	HistoriqueOrdonnances listed = null;
	HistoriqueAchat listeds = null;
	static Connection con = Singleton.getInstanceDB();
	static DAOClient daoClient = new DAOClient();
	private JFrame PageDacceuil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Outil_Gestion window = new Outil_Gestion();
					window.PageDacceuil.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Outil_Gestion() {
		listed = new HistoriqueOrdonnances();
		listeds = new HistoriqueAchat();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		PageDacceuil = new JFrame();
		PageDacceuil.setTitle("Page d'acceuil");
		PageDacceuil.setIconImage(Toolkit.getDefaultToolkit().getImage(Outil_Gestion.class.getResource("/Configuration/bank/Logo-removebg-preview.png")));
		PageDacceuil.setBounds(100, 100, 470, 360);
		PageDacceuil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		PageDacceuil.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar, BorderLayout.NORTH);

		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Effectuer un Achat");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Achat(e);
			}
		});
		mnMenu.add(mntmNewMenuItem_1);

		JMenuItem Quitter = new JMenuItem("Quitter");
		Quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Quitter(e);
			}
		});
		mnMenu.add(Quitter);

		JMenu Menu_Historique = new JMenu("Historique");
		Menu_Historique.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(Menu_Historique);

		JMenuItem mntmNewMenuItem = new JMenuItem("Historique Ordonnances");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AfficherHistoOrdo(e);
			}
		});
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		Menu_Historique.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Historique Achat");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AfficherHistoAchat(e);
			}
		});
		mntmNewMenuItem_2.setHorizontalAlignment(SwingConstants.LEFT);
		Menu_Historique.add(mntmNewMenuItem_2);

		JMenu Menu_Client = new JMenu("Client");
		menuBar.add(Menu_Client);

		JMenuItem Liste_Client = new JMenuItem("Afficher Liste Client");
		Liste_Client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AfficherListe(e);
			}
		});
		Menu_Client.add(Liste_Client);

		JPanel panel_1 = new JPanel();
		PageDacceuil.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setBackground(Color.white);
		JLabel image = new JLabel(new ImageIcon("C:\\PROJET\\JAVA\\Sparadrap-main\\src\\Configuration\\bank\\Logo-Sparadrap.jpg"));
		panel_1.add(image);
	}

	/**
	 *
	 * @param e
	 */
	private void Achat (ActionEvent e) {
		FrmAchat achat = new FrmAchat();
		achat.Achat.setVisible(true);
	}

	/**
	 *
	 * @param e
	 */
	private void Quitter(ActionEvent e) {
			System.out.println(e.getActionCommand().toString());
			System.out.println(e.getSource().toString());
			
			int sortie = JOptionPane.showConfirmDialog(null, "Etes-vous sûr ?", 
					"Quitter", JOptionPane.YES_NO_OPTION);
			
			if (sortie == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

	/**
	 *
	 * @param e
	 */
		private void AfficherListe(ActionEvent e) {
			if (i == 0) {
				liste = new Liste();
				liste.setVisible(true);
				i = 1;
			}else if (i == 1) {
				liste.setVisible(true);
			}
		}

	/**
	 *
	 * @param e
	 */
	private void AfficherHistoOrdo(ActionEvent e) {
			listed.setVisible(true);
		}

	/**
	 *
	 * @param e
	 */
	private void AfficherHistoAchat(ActionEvent e) {
			listeds.setVisible(true);
		}
	}