package gui.membre;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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
import dao.VehiculeDAO;
import exo.Balade;
import exo.Categorie;
import exo.Membre;
import exo.Vehicule;
import gui.Main;

public class AjouterVehiculeBalade 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private Membre currentMembre;
	private Balade baladeSelected;
	private JLabel labelNombrePlaces;
	private JLabel labelImmatriculation;
	private JLabel labelnombrePlaceVelo;
	private JLabel labelMsgErreur;
	private JList listVehicule;
	private ListSelectionModel listSelectionModel;
	private JTextField nombrePlacesField;
	private JTextField immatriculationField;
	private JTextField nombrePlaceVeloField;
	private JButton rejoindreButton;
	private JButton ajouterVehiculeButton;
	private JButton ajouterVehiculeBaladeButton;
	private JButton retourButton;
	private JPanel p;
	private JPanel p2;
	private List<Categorie> listCategorie;
	private Vehicule vehicule;
	
	public AjouterVehiculeBalade(JFrame f, Connection connect, Membre currentMembre, List<Categorie> listCategorie, Vehicule vehicule, Balade baladeSelected) 
	{
		System.out.println("LISTE CATEGORIE : " + listCategorie);
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		List<Vehicule> listVehicule = vehiculeDAO.listVehiculeByMembre(currentMembre);
		Object[] vehicules = listVehicule.toArray();
		
		this.connect = connect;
		this.f = f;
		this.currentMembre = currentMembre;
		
		labelMsgErreur = new JLabel();
		//String listVehicules = vehicules.toString();

		//Vehicule vehiculeSelectionne = (Vehicule) listeVehicule.getSelectedValue();
		//balade.AjouterVehicule(vehiculeSelectionne);

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
	    
	    ajouterVehiculeBaladeButton = new JButton("Ajouter un véhicule à la balade");
	    ajouterVehiculeButton = new JButton("Ajouter un nouveau véhicule");
	
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(5, 2));
		p2 = new JPanel(new GridLayout(5, 2));
		
		p.add(ajouterVehiculeBaladeButton);
		p.add(ajouterVehiculeButton);
		p.add(retourButton);
		
		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1));
		
		ajouterVehiculeBaladeButton.addActionListener(new ajouterVehiculeBaladeButtonListener(f, jlist1, currentMembre, baladeSelected));
		ajouterVehiculeButton.addActionListener(new ajouterVehiculeButtonListener(f, currentMembre));
		retourButton.addActionListener(new retourButtonListener(f, listCategorie, vehicule, currentMembre));
		f.add(p);
		f.add(p2);
		f.pack();
	}

	private class SharedListSelectionHandler implements ListSelectionListener 
	{
		private JFrame f;
		private JList listeVehicule;
		
		public SharedListSelectionHandler(JFrame f, JList jlist1)
		{
			this.f = f;
			this.listeVehicule = jlist1;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listeVehicule.getSelectedIndex();
			System.out.println("Vehicule :" + listeVehicule.getSelectedValue());
			System.out.println(listeVehicule.getSelectedValue().getClass());
			VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
			
			//listeVehicule.setListData(vehiculeDAO.((Vehicule)listeVehicule.getSelectedValue()).toArray());
			
			//listeVehicule.repaint();
			Container container = listeVehicule.getParent();
			container.revalidate();
			container.repaint();
		}
	}
	
	private class ajouterVehiculeBaladeButtonListener implements ActionListener 
	{
		private JFrame f;
		private JList listeVehicule;
		private Membre currentMembre;
		private Balade baladeSelected;
		
		public ajouterVehiculeBaladeButtonListener(JFrame f, JList jlist1, Membre currentMembre, Balade baladeSelected)
		{
			this.f = f;
			this.listeVehicule = jlist1;
			this.currentMembre = currentMembre;
			this.baladeSelected = baladeSelected;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(listeVehicule.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner un véhicule.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
			}
			else
			{
				System.out.println("Véhicule ajouté à la balade.");
				Container cp = f.getContentPane();
				cp.removeAll();
				BaladeDAO baladeDAO = new BaladeDAO(connect);
				System.out.println("Balade : " + (Balade)baladeSelected);
				System.out.println("Vehicule : " + (Vehicule)listeVehicule.getSelectedValue());
				Vehicule vehicule = (Vehicule)listeVehicule.getSelectedValue();
				System.out.println("Immatriculation Véhicule : " + vehicule.getImmatriculation());
				System.out.println("Membre : " + currentMembre);
				Balade balade = (Balade)baladeSelected;
				balade.AjouterVehicule((Vehicule)listeVehicule.getSelectedValue());
				System.out.println(balade);
				vehicule.AjouterMembre(currentMembre);
				baladeDAO.create_Ligne_Balade_SansMembre((Balade)baladeSelected, (Vehicule)listeVehicule.getSelectedValue());
				System.out.println("vehicule selectionne : " + listeVehicule.getSelectedValue());
				System.out.println("INFOS VEHICULE : " + vehicule);
				Main.RejoindreBalade(listCategorie, currentMembre);
			}
			
			/*if(nombrePlacesField.getText().isEmpty() || immatriculationField.getText().isEmpty() || nombrePlaceVeloField.getText().isEmpty())
			{
				labelMsgErreur.setText("Veuillez entrer tous les champs.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
			}
			else 
			{
				Vehicule vehicule = new Vehicule(1, Integer.parseInt(nombrePlacesField.getText()), immatriculationField.getText(), Integer.parseInt(nombrePlaceVeloField.getText()));
				VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
				if(vehiculeDAO.alreadyExist(immatriculationField.getText()))
				{
					labelMsgErreur.setText("Cette immatriculation existe déjà.");
					p2.add(labelMsgErreur);
					f.add(p2);
					f.pack();
				}
				else
				{
					
				}
			}*/
		}
	}
	private class ajouterVehiculeButtonListener implements ActionListener 
	{
		private JFrame f;
		private Membre currentMembre;
		
		public ajouterVehiculeButtonListener(JFrame f, Membre currentMembre)
		{
			this.f = f;
			this.currentMembre = currentMembre;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			Container cp = f.getContentPane();
			cp.removeAll();
			Main.showMenuVehicule_Membre(currentMembre);
			
			/*if(nombrePlacesField.getText().isEmpty() || immatriculationField.getText().isEmpty() || nombrePlaceVeloField.getText().isEmpty())
			{
				labelMsgErreur.setText("Veuillez entrer tous les champs.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
			}
			else 
			{
				Vehicule vehicule = new Vehicule(1, Integer.parseInt(nombrePlacesField.getText()), immatriculationField.getText(), Integer.parseInt(nombrePlaceVeloField.getText()));
				VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
				if(vehiculeDAO.alreadyExist(immatriculationField.getText()))
				{
					labelMsgErreur.setText("Cette immatriculation existe déjà.");
					p2.add(labelMsgErreur);
					f.add(p2);
					f.pack();
				}
				
			}*/
		}
	}
	
	
	
	private class retourButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private List<Categorie> listCategorie;
		private Vehicule vehicule;
		
		public retourButtonListener(JFrame f, List<Categorie> listCategorie, Vehicule vehicule, Membre currentMembre)
		{
			this.f = f;
			this.listCategorie = listCategorie;
			this.vehicule = vehicule;
			this.currentMembre = currentMembre;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("LISTE CATEGORIE RETOUR : " + listCategorie);
			System.out.println(vehicule);
			for(Categorie cat : listCategorie)
			{
				currentMembre.AjouterCategorie(cat);
				
			}
			
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.RejoindreBalade(listCategorie, currentMembre);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
