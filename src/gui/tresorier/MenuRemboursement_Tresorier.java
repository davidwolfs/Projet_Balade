package gui.tresorier;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.BaladeDAO;
import dao.MembreDAO;
import dao.VehiculeDAO;
import exo.Balade;
import exo.Categorie;
import exo.Membre;
import exo.Tresorier;
import exo.Vehicule;
import gui.Main;

public class MenuRemboursement_Tresorier 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private Tresorier currentTresorier;
	private Balade baladeSelected;
	private JLabel labelBalade;
	private JLabel labelChauffeur;
	private JLabel labelPassager;
	private JLabel labelMsgErreur;
	private JList listeBalade;
	private JList listeVehicule;
	private JList listePassager;
	private ListSelectionModel listSelectionModel;
	private ListSelectionModel listSelectionModel2;
	private JButton quitterVeloButton;
	private JButton retourButton;
	private JPanel p;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private Object vehiculeSelected;
	private List<Categorie> listCategorie;
	private Vehicule vehicule;
	//String listVehicules = vehicules.toString();
	//listeVehicule = new JList(vehicules);


	public MenuRemboursement_Tresorier(JFrame f, Connection connect, Tresorier currentTresorier) 
	{
		System.out.println("infos vehicule dans rejoindre balade : " + vehicule);
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		System.out.println(currentTresorier.getEmail());
		List<Balade> listBalade = baladeDAO.listBalade();
				//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] balades = listBalade.toArray();
		//Object[] vehicules = listVehicule.toArray();
		this.connect = connect;
		controllingFrame = f;
		this.currentTresorier = currentTresorier;
		labelBalade = new JLabel("Liste des balades : ");
		labelChauffeur = new JLabel("Liste des chauffeurs : ");
		labelPassager = new JLabel("Liste des passagers : ");
		labelMsgErreur = new JLabel();
		listeBalade = new JList(balades);
		listeVehicule = new JList();
		listePassager = new JList();
		//String listVehicules = vehicules.toString();

		//Vehicule vehiculeSelectionne = (Vehicule) listeVehicule.getSelectedValue();
		//balade.AjouterVehicule(vehiculeSelectionne);

	    JList jlist1 = new JList(balades);
	    jlist1.setVisibleRowCount(4);
	    
	    DefaultListModel model = new DefaultListModel();
	    model.ensureCapacity(100);
	    for (int i = 0; i < 100; i++) {
	        model.addElement(Integer.toString(i));
	    }
	    
	    JScrollPane scrollPane1 = new JScrollPane(jlist1);
	    f.add(scrollPane1, BorderLayout.NORTH);

	    JScrollPane scrollPane2 = new JScrollPane(listeVehicule);
		f.add(scrollPane2, BorderLayout.NORTH);
		
		JScrollPane scrollPane3 = new JScrollPane(listePassager);
		f.add(scrollPane3, BorderLayout.NORTH);
		 
	    jlist1.setVisibleRowCount(4);
	    jlist1.setFixedCellHeight(12);
	    jlist1.setFixedCellWidth(200);


	    f.setSize(300, 350);
	    f.setVisible(true);

		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(4, 2));
		p2 = new JPanel(new GridLayout(1,2));
		p3 = new JPanel(new GridLayout(4,1));
		p4 = new JPanel(new GridLayout(1,1));
		p5 = new JPanel(new GridLayout(1,1));
		
		p.add(labelBalade);
		p.add(scrollPane1);
		p.add(labelChauffeur);
		p.add(scrollPane2);
		p.add(labelPassager);
		p.add(scrollPane3);
		
		p3.add(retourButton);



		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1, vehicule, listeVehicule));
		
		listSelectionModel2  = jlist1.getSelectionModel();
		listSelectionModel2.addListSelectionListener(
				new SharedListSelectionHandler2(f, jlist1, vehicule, listePassager));
		
		retourButton.addActionListener(new retourButtonListener(f, listCategorie, vehicule, currentTresorier));
		f.add(p);
		f.add(p2);
		f.add(p3);
		f.pack();
	}

	private class SharedListSelectionHandler implements ListSelectionListener 
	{
		private JList listeBalade;
		private JList listeVehicule;
		private JFrame f;
		private Vehicule vehicule;
		
		public SharedListSelectionHandler(JFrame f, JList jlist1, Vehicule vehicule, JList listeVehicule)
		{
			this.f = f;
			this.listeBalade = jlist1;
			this.vehicule = vehicule;
			this.listeVehicule = listeVehicule;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listeBalade.getSelectedIndex();
			System.out.println("balade :" + listeBalade.getSelectedValue());
			JOptionPane.showMessageDialog(null, listeBalade.getSelectedValue());
			System.out.println(listeBalade.getSelectedValue().getClass());
			baladeSelected = (Balade)listeBalade.getSelectedValue();
			VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
			System.out.println(vehiculeDAO.listChauffeur((Balade)listeBalade.getSelectedValue()));
			listeVehicule.setListData(vehiculeDAO.listChauffeur((Balade)listeBalade.getSelectedValue()).toArray());
			
			//listeVehicule.repaint();
			Container container = listeVehicule.getParent();
			container.revalidate();
			container.repaint();
		}
	}

	private class SharedListSelectionHandler2 implements ListSelectionListener 
	{
		private JList listeBalade;
		private JList listePassager;
		private JFrame f;
		private Vehicule vehicule;
		
		public SharedListSelectionHandler2(JFrame f, JList jlist1, Vehicule vehicule, JList listePassager)
		{
			this.f = f;
			this.listeBalade = jlist1;
			this.vehicule = vehicule;
			this.listePassager = listePassager;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listeBalade.getSelectedIndex();
			System.out.println("balade :" + listeBalade.getSelectedValue());
			JOptionPane.showMessageDialog(null, listeBalade.getSelectedValue());
			System.out.println(listeBalade.getSelectedValue().getClass());
			baladeSelected = (Balade)listeBalade.getSelectedValue();
			VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
			System.out.println(vehiculeDAO.listChauffeur((Balade)listeBalade.getSelectedValue()));
			listePassager.setListData(vehiculeDAO.listPassager((Balade)listeBalade.getSelectedValue()).toArray());
			
			//listeVehicule.repaint();
			Container container = listeVehicule.getParent();
			container.revalidate();
			container.repaint();
		}
	}
	
	private class rejoindreButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private List<Categorie> listCategorie;
		private Vehicule vehicule;
		public rejoindreButtonListener(JFrame f, List<Categorie> listCategorie, Vehicule vehicule, Membre currentMembre)
		{
			this.f = f;
			this.listCategorie = listCategorie;
			this.vehicule = vehicule;
			this.currentMembre = currentMembre;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// requete ajout insert into liste_balade
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			MembreDAO membreDAO = new MembreDAO(connect);
			Vehicule vehicule;
			vehicule = (Vehicule)listeVehicule.getSelectedValue();
			Balade balade;
			
			int nombrePlacesUtilisees;
			
			currentMembre = membreDAO.getSoldeMembre(currentMembre);
			double soldeMembre = currentMembre.getSolde();
			double forfait = baladeDAO.getForfait(baladeSelected);
			if(listeVehicule.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner un véhicule.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else if(baladeDAO.getPlaceMembreUtilisee((Vehicule)listeVehicule.getSelectedValue(), baladeSelected) >= vehicule.getNombrePlaceMembre())
			{
				JOptionPane.showMessageDialog(null, "Il n'y a plus de place membre dans ce véhicule.");
			}
			else if(baladeDAO.getPlaceVeloUtilisee((Vehicule)listeVehicule.getSelectedValue(), baladeSelected) >= vehicule.getNombrePlaceMembre())
			{
				JOptionPane.showMessageDialog(null, "Il n'y a plus de place vélo dans ce véhicule.");
			}
			else if(baladeDAO.membrealreadyInBalade((Balade)baladeSelected, currentMembre))
			{
				JOptionPane.showMessageDialog(null, "Vous participer déjà à cette balade.");
			}
			else if(soldeMembre < forfait)
			{
				JOptionPane.showMessageDialog(null, "Vous n'avez pas les moyens pour participer à cette balade.");
			}
			else
			{
				System.out.println("Solde membre : " + currentMembre.getSolde());
				System.out.println("Nom membre : " + currentMembre.getNom());
				System.out.println("Forfait : " + forfait);
				System.out.println("Solde avant : " + currentMembre.getSolde());
				System.out.println("Nom Membre courant : " + currentMembre.getNom());
				currentMembre.soustraitSolde(forfait);
				System.out.println("Solde après : " + currentMembre.getSolde());
				double soldeSoustrait = currentMembre.getSolde();
				membreDAO.update_solde(currentMembre);
				System.out.println("Balade : " + baladeSelected);
				System.out.println("Véhicule : " + listeVehicule.getSelectedValue());
				System.out.println("Véhicule sélectionné : " + (Vehicule)listeVehicule.getSelectedValue());
				System.out.println("Membre : " + currentMembre.getNom());
				/*Membre membre = new Membre();
				
				(Vehicule)listeVehicule.getSelectedValue()).toArray()*/
			
				VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
				baladeDAO.create((Balade)baladeSelected);
				
				//System.out.println("Liste balade : " + baladeDAO.listBalade(currentMembre.getListCategorie()));
				balade = (Balade) baladeSelected; 
				System.out.println("Avant ajout véhicule : " +  balade.getListVehicule());
				System.out.println("Balade toString Avant : " +  balade.toString());
				balade.AjouterVehicule(vehicule);
				vehicule.AjouterMembre(currentMembre);
				System.out.println("Après ajout véhicule : " + balade.getListVehicule());
				System.out.println("Balade toString Après : " +  balade.toString());
				
				System.out.println("Avant ajout membre : " +  vehicule.getListMembre());
				System.out.println("Vehicule toString Avant : " +  vehicule.toString());
				vehicule.AjouterMembre(currentMembre);
				/*vehicule.setNombrePlaceMembre(1);
				vehicule.setNombrePlaceVelo(1);*/
				
				System.out.println("Après ajout membre : " +  vehicule.getListMembre());
				System.out.println("Vehicule toString Après : " +  vehicule.toString());
				System.out.println("AVANT AJOUT : " + listCategorie);
				JOptionPane.showMessageDialog(null, "Vous avez rejoint la balade : " + balade.getLibelle() + " avec le véhicule immatriculé : " + vehicule.getImmatriculation());
				baladeDAO.create_Ligne_Balade_Membre((Balade)baladeSelected, (Vehicule)listeVehicule.getSelectedValue(), currentMembre);
				baladeDAO.create_Ligne_Balade_Velo((Balade)baladeSelected, (Vehicule)listeVehicule.getSelectedValue(), currentMembre);
				//vehiculeDAO.update(vehicule);
				
				System.out.println("APRES AJOUT : " + listCategorie);
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.AjoutVehicule(currentMembre, listCategorie, vehicule, baladeSelected);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}
	
	private class rejoindreMembreButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private List<Categorie> listCategorie;
		private Vehicule vehicule;
		
		public rejoindreMembreButtonListener(JFrame f, List<Categorie> listCategorie, Vehicule vehicule, Membre currentMembre)
		{
			this.f = f;
			this.listCategorie = listCategorie;
			this.vehicule = vehicule;
			this.currentMembre = currentMembre;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// requete ajout insert into liste_balade
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			MembreDAO membreDAO = new MembreDAO(connect);
			Vehicule vehicule;
			vehicule = (Vehicule)listeVehicule.getSelectedValue();
			Balade balade;
			
			int nombrePlacesUtilisees;
			
			currentMembre = membreDAO.getSoldeMembre(currentMembre);
			double soldeMembre = currentMembre.getSolde();
			double forfait = baladeDAO.getForfait(baladeSelected);
			if(listeVehicule.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner un véhicule.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else if(baladeDAO.getPlaceMembreUtilisee((Vehicule)listeVehicule.getSelectedValue(), baladeSelected) >= vehicule.getNombrePlaceMembre())
			{
				JOptionPane.showMessageDialog(null, "Il n'y a plus de place membre dans ce véhicule.");
			}
			/*else if(baladeDAO.getPlaceVeloUtilisee((Vehicule)listeVehicule.getSelectedValue(), baladeSelected) >= vehicule.getNombrePlaceMembre())
			{
				JOptionPane.showMessageDialog(null, "Il n'y a plus de place vélo dans ce véhicule.");
			}*/
			else if(baladeDAO.membrealreadyInBalade((Balade)baladeSelected, currentMembre))
			{
				JOptionPane.showMessageDialog(null, "Vous participer déjà à cette balade.");
			}
			else if(soldeMembre < forfait)
			{
				JOptionPane.showMessageDialog(null, "Vous n'avez pas les moyens pour participer à cette balade.");
			}
			else
			{
				System.out.println("Solde membre : " + currentMembre.getSolde());
				System.out.println("Nom membre : " + currentMembre.getNom());
				System.out.println("Forfait : " + forfait);
				System.out.println("Solde avant : " + currentMembre.getSolde());
				System.out.println("Nom Membre courant : " + currentMembre.getNom());
				currentMembre.soustraitSolde(forfait);
				System.out.println("Solde après : " + currentMembre.getSolde());
				double soldeSoustrait = currentMembre.getSolde();
				membreDAO.update_solde(currentMembre);
				System.out.println("Balade : " + baladeSelected);
				System.out.println("Véhicule : " + listeVehicule.getSelectedValue());
				System.out.println("Véhicule sélectionné : " + (Vehicule)listeVehicule.getSelectedValue());
				System.out.println("Membre : " + currentMembre.getNom());
				/*Membre membre = new Membre();
				
				(Vehicule)listeVehicule.getSelectedValue()).toArray()*/
			
				VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
				baladeDAO.create((Balade)baladeSelected);
				
				//System.out.println("Liste balade : " + baladeDAO.listBalade(currentMembre.getListCategorie()));
				balade = (Balade) baladeSelected; 
				System.out.println("Avant ajout véhicule : " +  balade.getListVehicule());
				System.out.println("Balade toString Avant : " +  balade.toString());
				balade.AjouterVehicule(vehicule);
				vehicule.AjouterMembre(currentMembre);
				System.out.println("Après ajout véhicule : " + balade.getListVehicule());
				System.out.println("Balade toString Après : " +  balade.toString());
				
				System.out.println("Avant ajout membre : " +  vehicule.getListMembre());
				System.out.println("Vehicule toString Avant : " +  vehicule.toString());
				vehicule.AjouterMembre(currentMembre);
				vehicule.setNombrePlaceMembre(1);
				//vehicule.setNombrePlaceVelo(1);
				
				System.out.println("Après ajout membre : " +  vehicule.getListMembre());
				System.out.println("Vehicule toString Après : " +  vehicule.toString());
	
				JOptionPane.showMessageDialog(null, "Vous avez rejoint la balade : " + balade.getLibelle() + " avec le véhicule immatriculé : " + vehicule.getImmatriculation());
				baladeDAO.create_Ligne_Balade_Membre((Balade)baladeSelected, (Vehicule)listeVehicule.getSelectedValue(), currentMembre);
			//	baladeDAO.create_Ligne_Balade_Velo((Balade)baladeSelected, (Vehicule)listeVehicule.getSelectedValue(), currentMembre);
				//vehiculeDAO.update(vehicule);
				
				
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.AjoutVehicule(currentMembre, listCategorie, vehicule, baladeSelected);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}

	private class rejoindreVehiculeButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private List<Categorie> listCategorie;
		private Vehicule vehicule;
		
		public rejoindreVehiculeButtonListener(JFrame f, List<Categorie> listCategorie, Vehicule vehicule, Membre currentMembre)
		{
			this.f = f;
			this.listCategorie = listCategorie;
			this.vehicule = vehicule;
			this.currentMembre = currentMembre;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// requete ajout insert into liste_balade
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			MembreDAO membreDAO = new MembreDAO(connect);
			Vehicule vehicule;
			vehicule = (Vehicule)listeVehicule.getSelectedValue();
			Balade balade;
			
			int nombrePlacesUtilisees;
			
			currentMembre = membreDAO.getSoldeMembre(currentMembre);
			double soldeMembre = currentMembre.getSolde();
			double forfait = baladeDAO.getForfait(baladeSelected);
			if(listeVehicule.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner un véhicule.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else if(baladeDAO.getPlaceVeloUtilisee((Vehicule)listeVehicule.getSelectedValue(), baladeSelected) >= vehicule.getNombrePlaceMembre())
			{
				JOptionPane.showMessageDialog(null, "Il n'y a plus de place vélo dans ce véhicule.");
			}
			/*else if(baladeDAO.veloalreadyInBalade((Balade)baladeSelected, currentMembre))
			{
				JOptionPane.showMessageDialog(null, "Vous participer déjà à cette balade.");
			}
			else if(soldeMembre < forfait)
			{
				JOptionPane.showMessageDialog(null, "Vous n'avez pas les moyens pour participer à cette balade.");
			}*/
			else
			{
				System.out.println("Solde membre : " + currentMembre.getSolde());
				System.out.println("Nom membre : " + currentMembre.getNom());
				System.out.println("Forfait : " + forfait);
				System.out.println("Solde avant : " + currentMembre.getSolde());
				System.out.println("Nom Membre courant : " + currentMembre.getNom());
				currentMembre.soustraitSolde(forfait);
				System.out.println("Solde après : " + currentMembre.getSolde());
				double soldeSoustrait = currentMembre.getSolde();
				membreDAO.update_solde(currentMembre);
				System.out.println("Balade : " + baladeSelected);
				System.out.println("Véhicule : " + listeVehicule.getSelectedValue());
				System.out.println("Véhicule sélectionné : " + (Vehicule)listeVehicule.getSelectedValue());
				System.out.println("Membre : " + currentMembre.getNom());
				/*Membre membre = new Membre();
				
				(Vehicule)listeVehicule.getSelectedValue()).toArray()*/
			
				VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
				baladeDAO.create((Balade)baladeSelected);
				
				//System.out.println("Liste balade : " + baladeDAO.listBalade(currentMembre.getListCategorie()));
				balade = (Balade) baladeSelected; 
				System.out.println("Avant ajout véhicule : " +  balade.getListVehicule());
				System.out.println("Balade toString Avant : " +  balade.toString());
				balade.AjouterVehicule(vehicule);
				//vehicule.AjouterMembre(currentMembre);
				System.out.println("Après ajout véhicule : " + balade.getListVehicule());
				System.out.println("Balade toString Après : " +  balade.toString());
				
				//vehicule.setNombrePlaceVelo(1);
				
				System.out.println("Après ajout membre : " +  vehicule.getListMembre());
				System.out.println("Vehicule toString Après : " +  vehicule.toString());
	
				JOptionPane.showMessageDialog(null, "Votre vélo a bien été ajouté à la balade : " + balade.getLibelle() + " dans le véhicule immatriculé : " + vehicule.getImmatriculation());
				baladeDAO.create_Ligne_Balade_Velo((Balade)baladeSelected, (Vehicule)listeVehicule.getSelectedValue(), currentMembre);
				//vehiculeDAO.update(vehicule);
				
				
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.AjoutVehicule(currentMembre, listCategorie, vehicule, baladeSelected);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}
	
	private class ajoutVehiculeButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private JList listeBalade;
		private List<Categorie> listCategorie;
		private Vehicule vehicule;
		
		public ajoutVehiculeButtonListener(JFrame f, List<Categorie> listCategorie, Vehicule vehicule, Membre currentMembre, JList jlist1)
		{
			this.f = f;
			this.listCategorie = listCategorie;
			this.vehicule = vehicule;
			this.currentMembre = currentMembre;
			this.listeBalade = jlist1;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// requete ajout insert into liste_balade
			if(listeBalade.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner une balade.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.AjoutVehicule(currentMembre, listCategorie, vehicule, baladeSelected);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}
	
	private class quitterButtonListener implements ActionListener
	{
		private JFrame f;
		private List<Categorie> listCategorie;
		private Membre currentMembre;
		private Vehicule vehicule;
		private JList listeBalade;

		public quitterButtonListener(JFrame f, List<Categorie> listCategorie, Membre currentMembre, Vehicule vehicule, JList jlist1)
		{
			this.f = f;
			this.listCategorie = listCategorie;
			this.currentMembre = currentMembre;
			this.vehicule = vehicule;
			this.listeBalade = jlist1;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(listeBalade.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(null, "Veuillez sélectionner une balade.");
			}
			else 
			{
				// requete ajout insert into liste_balade
				BaladeDAO baladeDAO = new BaladeDAO(connect);
				baladeSelected = (Balade)listeBalade.getSelectedValue();
				Balade balade = (Balade)baladeSelected;
				baladeDAO.delete_Membre_Ligne_Balade((Balade)baladeSelected, currentMembre);
				System.out.println("Balade sélectionnée : " + baladeSelected);
				System.out.println("La balade : " + baladeSelected + "a bien été supprimée.");
				JOptionPane.showMessageDialog(null, "Vous avez quitté la balade : " + balade.getLibelle());
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.showDashboard_Membre(currentMembre);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}
	
	private class quitterMembreButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private List<Categorie> listCategorie;
		private JList listeBalade;
		private Vehicule vehicule;

		
		public quitterMembreButtonListener(JFrame f, List<Categorie> listCategorie, Membre currentMembre, Vehicule vehicule, JList jlist1)
		{
			this.f = f;
			this.listCategorie= listCategorie;
			this.currentMembre = currentMembre;
			this.vehicule = vehicule;
			this.listeBalade = jlist1;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(listeBalade.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(null, "Veuillez sélectionner une balade.");
			}
			else 
			{
				// requete ajout insert into liste_balade
				BaladeDAO baladeDAO = new BaladeDAO(connect);
				baladeSelected = (Balade)listeBalade.getSelectedValue();
				Balade balade = (Balade)baladeSelected;
				baladeDAO.delete_Membre_Ligne_Balade((Balade)baladeSelected, currentMembre);
				System.out.println("Balade sélectionnée : " + baladeSelected);
				System.out.println("La balade : " + baladeSelected + "a bien été supprimée.");
				JOptionPane.showMessageDialog(null, "Le membre a bien quitté la balade : " + balade.getLibelle());
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.AjoutVehicule(currentMembre, listCategorie, vehicule, baladeSelected);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}
	
	private class quitterVeloButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private List<Categorie> listCategorie;
		private JList listeBalade;
		private Vehicule vehicule;

		public quitterVeloButtonListener(JFrame f, List<Categorie> listCategorie, Membre currentMembre, Vehicule vehicule, JList jlist1)
		{
			this.f = f;
			this.listCategorie = listCategorie;
			this.currentMembre = currentMembre;
			this.vehicule = vehicule;
			this.listeBalade = jlist1;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(listeBalade.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(null, "Veuillez sélectionner une balade.");
			}
			else 
			{
				// requete ajout insert into liste_balade
				BaladeDAO baladeDAO = new BaladeDAO(connect);
				baladeSelected = (Balade)listeBalade.getSelectedValue();
				Balade balade = (Balade)baladeSelected;
				baladeDAO.delete_Velo_Ligne_Balade((Balade)baladeSelected, currentMembre);
				System.out.println("Balade sélectionnée : " + baladeSelected);
				System.out.println("La balade : " + baladeSelected + "a bien été supprimée.");
				JOptionPane.showMessageDialog(null, "Le vélo a bien quitté la balade : " + balade.getLibelle());
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.AjoutVehicule(currentMembre, listCategorie, vehicule, baladeSelected);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}
	
	private class retourButtonListener implements ActionListener
	{
		private JFrame f;
		private List<Categorie> listCategorie;
		private Vehicule vehicule;
		private Tresorier currentTresorier;
		
		public retourButtonListener(JFrame f, List<Categorie> listCategorie, Vehicule vehicule, Tresorier currentTresorier)
		{
			this.f = f;
			this.listCategorie = listCategorie;
			this.vehicule = vehicule;
			this.currentTresorier = currentTresorier;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showDashboard_Tresorier(currentTresorier);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
