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
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.BaladeDAO;
import exo.Balade;
import exo.Membre;
import gui.Main;

public class Dashboard_Membre implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private Membre currentMembre;
	
	JMenuBar menuBar = new JMenuBar();
	private JLabel labelBonjour;
	private JButton BaladeButton;
	private JButton RemboursementeButton;
	private JButton DisponibiliteButton;
	private JButton CategorieButton;
	private JButton PayementButton;
	
	private JButton creerBaladeButton;
	private JButton modifierBaladeButton;
	private JButton supprimerBaladeButton;
	private JButton voirBaladeButton;
	private JButton deconnexionButton;
	private JPanel p;

	public Dashboard_Membre(JFrame f, Connection connect, Membre currentMembre) {
		this.connect = connect;
		this.f = f;
		this.currentMembre = currentMembre;
		labelBonjour = new JLabel("Bonjour, vous êtes connecté en tant que : " + currentMembre.getNom());
		
		BaladeButton = new JButton("Balade");
		RemboursementeButton = new JButton("Remboursement");
		DisponibiliteButton = new JButton("Disponibilité");
		CategorieButton = new JButton("Catégorie");
		PayementButton = new JButton("Payement");
		deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(0, 1));

		p.add(labelBonjour);
		p.add(BaladeButton);
		p.add(RemboursementeButton);
		p.add(DisponibiliteButton);
		p.add(CategorieButton);
		p.add(PayementButton);
		p.add(deconnexionButton);
		
		f.add(p);
		
		BaladeButton.addActionListener(new BaladeButtonListener(f));
		RemboursementeButton.addActionListener(new RemboursementeButtonListener(f));
		DisponibiliteButton.addActionListener(new DisponibiliteButtonListener(f));
		CategorieButton.addActionListener(new CategorieButtonListener(f));
		PayementButton.addActionListener(new PayementButtonListener(f));
		deconnexionButton.addActionListener(new deconnexionButtonListener(f));
		
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

		public BaladeButtonListener(JFrame f)
		{
			this.f = f;
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
	
	private class RemboursementeButtonListener implements ActionListener
	{
		private JFrame f;

		public RemboursementeButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			//Main.showMenuRemboursement();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class DisponibiliteButtonListener implements ActionListener
	{
		private JFrame f;

		public DisponibiliteButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			//Main.showMenuDisponibilite();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class CategorieButtonListener implements ActionListener
	{
		private JFrame f;

		public CategorieButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			//Main.showMenuCategorie();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class PayementButtonListener implements ActionListener
	{
		private JFrame f;

		public PayementButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			//Main.showMenuPayement();
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
