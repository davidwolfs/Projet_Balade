package gui.membre;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.BaladeDAO;
import dao.CategorieDAO;
import dao.MembreDAO;
import dao.VehiculeDAO;
import exo.Balade;
import exo.Categorie;
import exo.Membre;
import exo.Responsable;
import gui.Main;

public class MenuCategorie_Membre extends JPanel implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private JLabel labelCategorie;
	private JLabel labelMsgErreur;
	private Membre currentMembre;
	private ListSelectionModel listSelectionModel;
	private JTextField libelleField;
	private JTextField lieuDepartField;
	private JTextField dateDepartField;
	private JTextField forfaitField;
	private JButton affilierCategorieButton;
	private JButton retourButton;
	private JButton deconnexionButton;
	private JPanel p;
	private JPanel p2;
	private Object categorieSelected;

	public MenuCategorie_Membre(JFrame f, Connection connect, Membre currentMembre) 
	{
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		CategorieDAO categorieDAO = new CategorieDAO(connect);
		List<Categorie> listCategorie = categorieDAO.listCategorie();
		//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] categories = listCategorie.toArray();
		//Object[] vehicules = listVehicule.toArray();
		this.connect = connect;
		this.f = f;
		this.currentMembre = currentMembre;
		labelCategorie = new JLabel("Catégorie : ");
		labelMsgErreur = new JLabel();
		libelleField = new JTextField(15);
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		affilierCategorieButton = new JButton("S'affilier à la catégorie");
		retourButton = new JButton("Retour");
		deconnexionButton = new JButton("Déconnexion");
		
		
		//deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(4, 1));
		p2 = new JPanel(new GridLayout(1,1));
		
	    JList jlist1 = new JList(categories);
	    jlist1.setVisibleRowCount(4);
	    
	    DefaultListModel model = new DefaultListModel();
	    model.ensureCapacity(100);
	    for (int i = 0; i < 100; i++) {
	        model.addElement(Integer.toString(i));
	    }
	    
	    JScrollPane scrollPane1 = new JScrollPane(jlist1);
	    f.add(scrollPane1, BorderLayout.NORTH);
	    
	    jlist1.setVisibleRowCount(4);
	    jlist1.setFixedCellHeight(12);
	    jlist1.setFixedCellWidth(200);


	    f.setSize(300, 350);
	    f.setVisible(true);
	    
	    p.add(scrollPane1);
	    p.add(affilierCategorieButton);
		p.add(retourButton);
		p.add(deconnexionButton);
		f.add(p);
		f.add(p2);
		
		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1));
		affilierCategorieButton.addActionListener(new affilierCategorieButton(f, currentMembre, jlist1));
		retourButton.addActionListener(new retourButtonListener(f, currentMembre));
		deconnexionButton.addActionListener(new deconnexionButtonListener(f));
		
		f.pack();
	}
	
	private class SharedListSelectionHandler implements ListSelectionListener 
	{
		private JList listeCategorie;
		private JFrame f;
		
		public SharedListSelectionHandler(JFrame f, JList jlist1)
		{
			this.f = f;
			this.listeCategorie = jlist1;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listeCategorie.getSelectedIndex();
			System.out.println("Categorie :" + (Categorie)listeCategorie.getSelectedValue());
			System.out.println(listeCategorie.getSelectedValue().getClass());
			categorieSelected = listeCategorie.getSelectedValue();
		
			
			//listeVehicule.repaint();
			Container container = listeCategorie.getParent();
			container.revalidate();
			container.repaint();
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		JFrame frame = new JFrame();
		//ToDelete creerBalade = new ToDelete(frame, connect);
		frame.setVisible(true);
		System.out.println(connect);
		
		/*Balade balade = new Balade(1, listVehicule.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);*/
	}
	
	private class affilierCategorieButton implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private JList listeCategorie;
		
		public affilierCategorieButton(JFrame f, Membre currentMembre, JList jlist1)
		{
			this.f = f;
			this.currentMembre = currentMembre;
			this.listeCategorie = jlist1;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			CategorieDAO categorieDAO = new CategorieDAO(connect);
			MembreDAO membreDAO = new MembreDAO(connect);
			System.out.println(currentMembre.getListCategorie());
			if(listeCategorie.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner une catégorie.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
			}
			/*else if(categorieDAO.appartientCategorie(currentMembre))
			{
				JOptionPane.showMessageDialog(null, "Vous appartenez déjà à cette catégorie.");
			}*/
			else
			{
				JOptionPane.showMessageDialog(null, "Vous venez de vous affilier à la catégorie : " + listeCategorie.getSelectedValue());
				//currentMembre = membreDAO.findMembreByEmailPassword(currentMembre.getEmail(), currentMembre.getPassword());
				Categorie categorie = (Categorie)listeCategorie.getSelectedValue();
				System.out.println("Nom de la catégorie choisie : " + categorie.getNom());
				//System.out.println(currentMembre.getListCategorie());
				System.out.println("Categorie : " + categorie.toString());
				System.out.println("Membre : " + currentMembre);
				System.out.println("Catégorie : " + categorie);
				currentMembre.AjouterCategorie(categorie);
				System.out.println(currentMembre.getListCategorie());
				categorieDAO.create_Categorie_Membre((Categorie)listeCategorie.getSelectedValue(), currentMembre);
			}
		}
	}
	
	private class retourButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		
		public retourButtonListener(JFrame f, Membre currentMembre)
		{
			this.f = f;
			this.currentMembre = currentMembre;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showDashboard_Membre(currentMembre);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class deconnexionButtonListener implements ActionListener
	{
		private JFrame f;

		public deconnexionButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.creerConnexion();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
