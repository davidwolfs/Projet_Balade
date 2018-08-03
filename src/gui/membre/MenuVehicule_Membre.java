package gui.membre;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.BaladeDAO;
import dao.VehiculeDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Membre;
import exo.Vehicule;
import gui.Main;

public class MenuVehicule_Membre 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private Membre currentMembre;
	private JLabel labelNombrePlacesMembre;
	private JLabel labelImmatriculation;
	private JLabel labelMarque;
	private JLabel labelModele;
	private JLabel labelnombrePlacesVelo;
	private JLabel labelMsgErreur;
	private JTextField nombrePlacesMembreField;
	private JTextField immatriculationField;
	private JTextField marqueField;
	private JTextField modeleField;
	private JTextField nombrePlacesVeloField;
	private JButton rejoindreButton;
	private JButton ajoutButton;
	private JButton retourButton;
	private JPanel p;
	private JPanel p2;
	
	public MenuVehicule_Membre(JFrame f, Connection connect, Membre currentMembre, Calendrier calendrier) 
	{
		this.connect = connect;
		this.f = f;
		this.currentMembre = currentMembre;
		labelImmatriculation = new JLabel("Immatriculation : ");
		labelMarque = new JLabel("Marque : ");
		labelModele = new JLabel("Modèle : ");
		labelNombrePlacesMembre = new JLabel("Nombre de places membres : ");
		labelnombrePlacesVelo = new JLabel("Nombre de places vélos : ");
		labelMsgErreur = new JLabel();
		nombrePlacesMembreField = new JTextField(2);
		immatriculationField = new JTextField(15);
		nombrePlacesVeloField = new JTextField(2);
		marqueField = new JTextField(15);
		modeleField = new JTextField(15);
		
		ajoutButton = new JButton("Ajouter");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(7, 2));
		p2 = new JPanel(new GridLayout(5, 2));

		p.add(labelImmatriculation);
		p.add(immatriculationField);
		p.add(labelMarque);
		p.add(marqueField);
		p.add(labelModele);
		p.add(modeleField);
		p.add(labelNombrePlacesMembre);
		p.add(nombrePlacesMembreField);
		p.add(labelnombrePlacesVelo);
		p.add(nombrePlacesVeloField);
		p.add(ajoutButton);
		p.add(retourButton);
		ajoutButton.addActionListener(new ajoutButtonListener(f, currentMembre, calendrier));
		retourButton.addActionListener(new retourButtonListener(f, currentMembre, calendrier));
		f.add(p);
		f.add(p2);
		f.pack();
	}

	private class ajoutButtonListener implements ActionListener 
	{
		private JFrame f;
		private Membre currentMembre;
		private Calendrier calendrier;
		
		public ajoutButtonListener(JFrame f, Membre currentMembre, Calendrier calendrier)
		{
			this.f = f;
			this.currentMembre = currentMembre;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(immatriculationField.getText().isEmpty() || nombrePlacesMembreField.getText().isEmpty() || nombrePlacesVeloField.getText().isEmpty())
			{
				labelMsgErreur.setText("Veuillez entrer tous les champs.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
			}
			else 
			{
				Vehicule vehicule = new Vehicule(immatriculationField.getText(), marqueField.getText(), modeleField.getText(), Integer.parseInt(nombrePlacesMembreField.getText()), Integer.parseInt(nombrePlacesVeloField.getText()), currentMembre);
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
					Container cp = f.getContentPane();
					cp.removeAll();
					vehiculeDAO.create(vehicule, currentMembre);
					vehicule.AjouterMembre(currentMembre);
					System.out.println("Chauffeur du véhicule : " + vehicule.getChauffeur());
					Main.showDashboard_Membre(currentMembre, calendrier);
				}
			}
		}
	}
	
	private class retourButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private Calendrier calendrier;

		public retourButtonListener(JFrame f, Membre currentMembre, Calendrier calendrier)
		{
			this.f = f;
			this.currentMembre = currentMembre;
			this.calendrier = calendrier;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showDashboard_Membre(currentMembre, calendrier);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
