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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.BaladeDAO;
import dao.CategorieDAO;
import dao.VehiculeDAO;
import exo.Balade;
import exo.Categorie;
import exo.Membre;
import exo.Responsable;
import exo.Vehicule;
import gui.Main;

public class MenuDisponibilite_Membre extends JPanel implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private JLabel labelDisponibilite;
	private JLabel labelMsgErreur;
	private Membre currentMembre;
	private ListSelectionModel listSelectionModel;
	private JTextField disponibiliteField;
	private JTextField lieuDepartField;
	private JTextField dateDepartField;
	private JTextField forfaitField;
	private JButton posterDisponibiliteButton;
	private JButton retourButton;
	private JButton deconnexionButton;
	private JPanel p;
	private JPanel p2;
	private Object categorieSelected;

	public MenuDisponibilite_Membre(JFrame f, Connection connect, Membre currentMembre) 
	{
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		CategorieDAO categorieDAO = new CategorieDAO(connect);
		//List<Categorie> listCategorie = categorieDAO.listCategorie(currentMembre);
		List<Vehicule> listVehicule = vehiculeDAO.listVehiculeByMembre(currentMembre);
		Object[] vehicules = listVehicule.toArray();
		this.connect = connect;
		this.f = f;
		this.currentMembre = currentMembre;
		labelDisponibilite = new JLabel("Disponibilite : ");
		labelMsgErreur = new JLabel();
		disponibiliteField = new JTextField(15);
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		posterDisponibiliteButton = new JButton("Poster ses disponibilités");
		retourButton = new JButton("Retour");
		deconnexionButton = new JButton("Déconnexion");
		
		
		//deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(4, 1));
		p2 = new JPanel(new GridLayout(1,1));
		
	    JList jlist1 = new JList(vehicules);
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
	    p.add(posterDisponibiliteButton);
		p.add(retourButton);
		p.add(deconnexionButton);
		f.add(p);
		f.add(p2);
		
		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1));
		posterDisponibiliteButton.addActionListener(new posterDisponibiliteButton(f, currentMembre, jlist1));
		retourButton.addActionListener(new retourButtonListener(f, currentMembre));
		deconnexionButton.addActionListener(new deconnexionButtonListener(f));
		
		f.pack();
	}
	
	private class SharedListSelectionHandler implements ListSelectionListener 
	{
		private JList listVehicule;
		private JFrame f;
		
		public SharedListSelectionHandler(JFrame f, JList jlist1)
		{
			this.f = f;
			this.listVehicule = jlist1;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listVehicule.getSelectedIndex();
			System.out.println("Vehicule :" + (Vehicule)listVehicule.getSelectedValue());
			System.out.println(listVehicule.getSelectedValue().getClass());
			categorieSelected = listVehicule.getSelectedValue();
		
			
			//listeVehicule.repaint();
			Container container = listVehicule.getParent();
			container.revalidate();
			container.repaint();
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		System.out.println(connect);
		
		/*Balade balade = new Balade(1, listVehicule.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);*/
	}
	
	private class posterDisponibiliteButton implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private JList jlist1;
		
		public posterDisponibiliteButton(JFrame f, Membre currentMembre, JList jlist1)
		{
			this.f = f;
			this.currentMembre = currentMembre;
			this.jlist1 = jlist1;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(jlist1.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner un véhicule.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
			}
			else 
			{
				System.out.println(jlist1.getSelectedValue());
				Vehicule vehicule;
				vehicule = (Vehicule)jlist1.getSelectedValue();
				
				int nombrePlaceMembre = vehicule.getNombrePlaceMembre();
				int nombrePlaceVelo = vehicule.getNombrePlaceVelo();
				VehiculeDAO categorieDAO = new VehiculeDAO(connect);
				//categorieDAO.
				/*Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				//Main.showDashboard_Membre(currentMembre);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
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
