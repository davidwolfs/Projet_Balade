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
import exo.Vehicule;
import gui.Main;

public class RejoindreBalade 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private Membre currentMembre;
	private Balade baladeSelected;
	private JLabel labelBalade;
	private JLabel labelVehicule;
	private JLabel labelMsgErreur;
	private JList listeBalade;
	private JList listeVehicule;
	private ListSelectionModel listSelectionModel;
	private JButton rejoindreButton;
	private JButton ajoutVehiculeButton;
	private JButton quitterButton;
	private JButton retourButton;
	private JPanel p;
	private JPanel p2;
	private Object vehiculeSelected;
	//String listVehicules = vehicules.toString();
	//listeVehicule = new JList(vehicules);


	public RejoindreBalade(JFrame f, Connection connect, Membre currentMembre) 
	{
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		List<Balade> listBalade = baladeDAO.listBaladeBylistCategorie(currentMembre.getListCategorie());
				//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] balades = listBalade.toArray();
		//Object[] vehicules = listVehicule.toArray();
		this.connect = connect;
		controllingFrame = f;
		this.currentMembre = currentMembre;
		labelMsgErreur = new JLabel();
		labelBalade = new JLabel("Balades pour la (les) catégorie(s) : " + currentMembre.getListCategorie());
		labelVehicule = new JLabel("Véhicules pour la balade sélectionnée : ");
		listeBalade = new JList(balades);
		listeVehicule = new JList();
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
		 
	    jlist1.setVisibleRowCount(4);
	    jlist1.setFixedCellHeight(12);
	    jlist1.setFixedCellWidth(200);


	    f.setSize(300, 350);
	    f.setVisible(true);
	    
		rejoindreButton = new JButton("Rejoindre");
		quitterButton = new JButton("Quitter");
		ajoutVehiculeButton = new JButton("Ajout véhicule");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(8, 2));
		p2 = new JPanel(new GridLayout(1,1));
		
		p.add(labelBalade);
		p.add(scrollPane1);
		p.add(labelVehicule);
		p.add(scrollPane2);
		p.add(rejoindreButton);
		p.add(quitterButton);
		p.add(ajoutVehiculeButton);
		p.add(retourButton);



		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1, listeVehicule));

		rejoindreButton.addActionListener(new rejoindreButtonListener(f, currentMembre));
		ajoutVehiculeButton.addActionListener(new ajoutVehiculeButtonListener(f, currentMembre));
		quitterButton.addActionListener(new quitterButtonListener(f, currentMembre, jlist1));
		retourButton.addActionListener(new retourButtonListener(f, currentMembre));
		f.add(p);
		f.pack();
	}

	private class SharedListSelectionHandler implements ListSelectionListener 
	{
		private JList listeBalade;
		private JList listeVehicule;
		private JFrame f;
		
		public SharedListSelectionHandler(JFrame f, JList jlist1, JList listeVehicule)
		{
			this.f = f;
			this.listeBalade = jlist1;
			this.listeVehicule = listeVehicule;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listeBalade.getSelectedIndex();
			System.out.println("balade :" + listeBalade.getSelectedValue());
			System.out.println(listeBalade.getSelectedValue().getClass());
			baladeSelected = (Balade)listeBalade.getSelectedValue();
			VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
			
			listeVehicule.setListData(vehiculeDAO.listVehiculeByBalade((Balade)listeBalade.getSelectedValue()).toArray());
			
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

		public rejoindreButtonListener(JFrame f, Membre currentMembre)
		{
			this.f = f;
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
			
			currentMembre = membreDAO.getSoldeMembre(currentMembre);
			double soldeMembre = currentMembre.getSolde();
			double forfait = baladeDAO.getForfait(baladeSelected);
			if(listeVehicule.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner un véhicule.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
			}
			else if(vehicule.getNombrePlaceMembre() <= 0 || vehicule.getNombrePlaceVelo() <= 0)
			{
				labelMsgErreur.setText("Il n'y a plus de place disponible.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
			}
			else if(baladeDAO.alreadyInBalade((Balade)baladeSelected, currentMembre))
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
				System.out.println("ID Membre : " + currentMembre.getiD());
				System.out.println("Forfait : " + forfait);
				System.out.println("Solde avant : " + currentMembre.getSolde());
				System.out.println("ID Membre courant : " + currentMembre.getiD());
				currentMembre.soustraitSolde(forfait);
				System.out.println("Solde après : " + currentMembre.getSolde());
				double soldeSoustrait = currentMembre.getSolde();
				membreDAO.update_solde(currentMembre);
				System.out.println("Balade : " + baladeSelected);
				System.out.println("Véhicule : " + listeVehicule.getSelectedValue());
				System.out.println("Véhicule sélectionné : " + (Vehicule)listeVehicule.getSelectedValue());
				System.out.println("Membre : " + currentMembre.getiD());
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
				vehicule.setNombrePlaceVelo(1);
				
				System.out.println("Après ajout membre : " +  vehicule.getListMembre());
				System.out.println("Vehicule toString Après : " +  vehicule.toString());
	
				JOptionPane.showMessageDialog(null, "Vous avez rejoint la balade : " + balade.getLibelle() + " avec le véhicule immatriculé : " + vehicule.getImmatriculation());
				baladeDAO.create_Ligne_Balade((Balade)baladeSelected, (Vehicule)listeVehicule.getSelectedValue(), currentMembre);
				vehiculeDAO.update(vehicule);
				
				
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.showDashboard_Membre(currentMembre);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}

	private class ajoutVehiculeButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;

		public ajoutVehiculeButtonListener(JFrame f, Membre currentMembre)
		{
			this.f = f;
			this.currentMembre = currentMembre;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// requete ajout insert into liste_balade
			

			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.AjoutVehicule(currentMembre, baladeSelected);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class quitterButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private JList listeBalade;

		public quitterButtonListener(JFrame f, Membre currentMembre, JList jlist1)
		{
			this.f = f;
			this.currentMembre = currentMembre;
			this.listeBalade = jlist1;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// requete ajout insert into liste_balade
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			baladeSelected = (Balade)listeBalade.getSelectedValue();
			Balade balade = (Balade)baladeSelected;
			baladeDAO.delete_Ligne_Balade((Balade)baladeSelected, currentMembre);
			System.out.println("Balade sélectionnée : " + baladeSelected);
			System.out.println("La balade : " + baladeSelected + "a bien été supprimée.");
			JOptionPane.showMessageDialog(null, "Vous avez quitté la balade : " + balade.getLibelle());
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.AjoutVehicule(currentMembre, baladeSelected);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
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
			Main.showMenuBalade_Membre(currentMembre);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
