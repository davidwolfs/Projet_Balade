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
import exo.Vehicule;
import gui.Main;

public class AjouterVehicule 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private JLabel labelNombrePlaces;
	private JLabel labelImmatriculation;
	private JLabel labelnombrePlaceVelo;
	private JTextField nombrePlacesField;
	private JTextField immatriculationField;
	private JTextField nombrePlaceVeloField;
	private JButton rejoindreButton;
	private JButton ajoutButton;
	private JButton retourButton;
	private JPanel p;
	
	public AjouterVehicule(JFrame f, Connection connect) 
	{
		this.connect = connect;
		controllingFrame = f;
		labelNombrePlaces = new JLabel("Nombre de places : ");
		labelImmatriculation = new JLabel("Immatriculation ");
		labelnombrePlaceVelo = new JLabel("Nombre de places vélos : ");
		nombrePlacesField = new JTextField(2);
		immatriculationField = new JTextField(15);
		nombrePlaceVeloField = new JTextField(2);
		
		ajoutButton = new JButton("Ajouter");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(4, 2));

		p.add(labelNombrePlaces);
		p.add(nombrePlacesField);
		p.add(labelImmatriculation);
		p.add(immatriculationField);
		p.add(labelnombrePlaceVelo);
		p.add(nombrePlaceVeloField);
		p.add(ajoutButton);
		p.add(retourButton);

		ajoutButton.addActionListener(new ajoutButtonListener(f));
		retourButton.addActionListener(new retourButtonListener(f));
		f.add(p);
		f.pack();
	}

	private class ajoutButtonListener implements ActionListener 
	{
		private JFrame f;
		
		public ajoutButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Vehicule vehicule = new Vehicule(1, Integer.parseInt(nombrePlacesField.getText()), immatriculationField.getText(), Integer.parseInt(nombrePlaceVeloField.getText()));
			VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
			vehiculeDAO.create(vehicule);
		}
	}
	
	private class retourButtonListener implements ActionListener
	{
		private JFrame f;

		public retourButtonListener(JFrame f)
		{
			this.f = f;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showDashboard_Membre();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
