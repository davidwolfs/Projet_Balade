package gui.tresorier;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.BaladeDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Membre;
import exo.Tresorier;
import gui.Main;

public class Dashboard_Tresorier implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private Tresorier currentTresorier;
	private JLabel labelBonjour;
//	private JButton BaladeButton;
	private JButton RemboursementeButton;
	private JButton DisponibiliteButton;
//	private JButton CategorieButton;
	private JButton PayementButton;
	
/*	private JButton creerBaladeButton;
	private JButton modifierBaladeButton;
	private JButton supprimerBaladeButton;
	private JButton voirBaladeButton;*/
	private JButton deconnexionButton;
	private JPanel p;

	public Dashboard_Tresorier(JFrame f, Connection connect, Tresorier currentTresorier, Calendrier calendrier) {
		this.connect = connect;
		this.f = f;
		this.currentTresorier = currentTresorier;
		labelBonjour = new JLabel("Bonjour " + currentTresorier.getPrenom() + " " + currentTresorier.getNom() + ", vous êtes connecté en tant que : Trésorier");
	//	BaladeButton = new JButton("Balade");
		RemboursementeButton = new JButton("Remboursement et payement");
	//	DisponibiliteButton = new JButton("Disponibilité");
	//	CategorieButton = new JButton("Catégorie");
	//	PayementButton = new JButton("Payement");
		deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(0, 1));

		p.add(labelBonjour);
	//	p.add(BaladeButton);
		p.add(RemboursementeButton);
	//	p.add(DisponibiliteButton);
	//	p.add(CategorieButton);
	//	p.add(PayementButton);
		p.add(deconnexionButton);
		
		f.add(p);
		
	//	BaladeButton.addActionListener(this);
		RemboursementeButton.addActionListener(new RemboursementeButtonListener(f, connect, currentTresorier, calendrier));
	//	DisponibiliteButton.addActionListener(this);
	//	CategorieButton.addActionListener(new CategorieButtonListener(f));
	//	PayementButton.addActionListener(new PayementButtonListener(f));
		deconnexionButton.addActionListener(new deconnexionButtonListener(f, calendrier));
		
		f.pack();
	}

	public void actionPerformed(ActionEvent arg0) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		System.out.println(connect);
		
		/*Balade balade = new Balade(1, listVehicule.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);*/
	}
	
	private class RemboursementeButtonListener implements ActionListener
	{
		private JFrame f;
		private Connection connect;
		private Tresorier currentTresorier;
		private Calendrier calendrier;

		public RemboursementeButtonListener(JFrame f, Connection connect, Tresorier currentTresorier, Calendrier calendrier)
		{
			this.f = f;
			this.connect = connect;
			this.currentTresorier = currentTresorier;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showMenuRemboursement_Tresorier(currentTresorier, calendrier);
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
