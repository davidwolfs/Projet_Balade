package gui.membre;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.BaladeDAO;
import dao.MembreDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Membre;
import gui.Main;

public class Dashboard_Membre implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private Membre currentMembre;
	private JLabel labelBonjour;
	private JButton BaladeButton;
	private JButton VehiculeButton;
	//private JButton DisponibiliteButton;
	private JButton CategorieButton;
	private JButton PayementButton;
	
	private JButton creerBaladeButton;
	private JButton modifierBaladeButton;
	private JButton supprimerBaladeButton;
	private JButton voirBaladeButton;
	private JButton deconnexionButton;
	private JPanel p;
	private Calendrier calendrier;

	public Dashboard_Membre(JFrame f, Connection connect, Membre currentMembre, Calendrier calendrier) 
	{
		this.connect = connect;
		this.f = f;
		this.currentMembre = currentMembre;
		labelBonjour = new JLabel("Bonjour " + currentMembre.getPrenom() + " " + currentMembre.getNom() + ", vous êtes connecté en tant que : Membre");
		BaladeButton = new JButton("Balade");
		VehiculeButton = new JButton("Véhicule");
		//DisponibiliteButton = new JButton("Disponibilité");
		CategorieButton = new JButton("Catégorie");
		PayementButton = new JButton("Payement");
		deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(0, 1));

		p.add(labelBonjour);
		p.add(BaladeButton);
		p.add(VehiculeButton);
		//p.add(DisponibiliteButton);
		p.add(CategorieButton);
		p.add(PayementButton);
		p.add(deconnexionButton);
		
		f.add(p);
		
		BaladeButton.addActionListener(new BaladeButtonListener(f, currentMembre, calendrier));
		VehiculeButton.addActionListener(new VehiculeButtonListener(f, currentMembre, calendrier));
		//DisponibiliteButton.addActionListener(new DisponibiliteButtonListener(f, currentMembre));
		CategorieButton.addActionListener(new CategorieButtonListener(f, currentMembre, calendrier));
		PayementButton.addActionListener(new PayementButtonListener(f, currentMembre, calendrier));
		deconnexionButton.addActionListener(new deconnexionButtonListener(f, calendrier));
		
		f.pack();
	}

	public void actionPerformed(ActionEvent arg0) {
		/*JFrame frame = new JFrame();
		ToDelete creerBalade = new ToDelete(frame, connect);
		frame.setVisible(true);
		System.out.println(connect);*/
		
		/*Balade balade = new Balade(1, listVehicule.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);*/
	}
	
	private class BaladeButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private Calendrier calendrier;

		public BaladeButtonListener(JFrame f, Membre currentMembre, Calendrier calendrier)
		{
			this.f = f;
			this.currentMembre = currentMembre;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MembreDAO membreDAO = new MembreDAO(connect);
			currentMembre = membreDAO.findIdByMembre(currentMembre);
			
			if(membreDAO.VerifierPayementCotisationAnnuelleChangeStatus(currentMembre))
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.showMenuBalade_Membre(currentMembre, calendrier);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Veuillez payer votre cotisation.");
			}
		}
	}
	
	private class VehiculeButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private Calendrier calendrier;
		
		public VehiculeButtonListener(JFrame f, Membre currentMembre, Calendrier calendrier)
		{
			this.f = f;
			this.currentMembre = currentMembre;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			MembreDAO membreDAO = new MembreDAO(connect);
			currentMembre = membreDAO.findIdByMembre(currentMembre);
			if(membreDAO.VerifierPayementCotisationAnnuelleChangeStatus(currentMembre))
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();
				Main.showMenuVehicule_Membre(currentMembre, calendrier);
				//f.revalidate();
				//f.getLayout().removeLayoutComponent(f);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Veuillez payer votre cotisation.");
			}
			
		}
	}
	
	/*private class DisponibiliteButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;

		public DisponibiliteButtonListener(JFrame f, Membre currentMembre)
		{
			this.f = f;
			this.currentMembre = currentMembre;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			//Main.showMenuDisponibilite(currentMembre);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		/*}
	}*/
	
	private class CategorieButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private Calendrier calendrier;

		public CategorieButtonListener(JFrame f, Membre currentMembre, Calendrier calendrier)
		{
			this.f = f;
			this.currentMembre = currentMembre;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			MembreDAO membreDAO = new MembreDAO(connect);
			currentMembre = membreDAO.findIdByMembre(currentMembre);
			
			if(membreDAO.VerifierPayementCotisationAnnuelleChangeStatus(currentMembre))
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.showMenuCategorie_Membre(currentMembre, calendrier);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Veuillez payer votre cotisation.");
			}
		}
	}
	
	private class PayementButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private Calendrier calendrier;

		public PayementButtonListener(JFrame f, Membre currentMembre, Calendrier calendrier)
		{
			this.f = f;
			this.currentMembre = currentMembre;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showMenu_Payement(currentMembre, calendrier);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class deconnexionButtonListener implements ActionListener
	{
		private JFrame f;
		private Calendrier calendrier;

		public deconnexionButtonListener(JFrame f, Calendrier calendrier)
		{
			this.f = f;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.creerConnexion(calendrier);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
