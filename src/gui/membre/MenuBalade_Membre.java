package gui.membre;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.BaladeDAO;
import dao.CategorieDAO;
import dao.MembreDAO;
import exo.Balade;
import exo.Categorie;
import exo.Membre;
import exo.Vehicule;
import gui.Main;

public class MenuBalade_Membre extends JPanel implements ActionListener 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private Membre currentMembre;
	//private JButton rechercherBaladeButton;
	private JButton rejoindreBaladeButton;
	private JButton supprimerBaladeButton;
	private JButton voirBaladeButton;
	private JButton retourButton;
	private JButton deconnexionButton;
	private JPanel p;
	private List<Categorie> listCategorie;
	private Vehicule vehicule;

	public MenuBalade_Membre(JFrame f, Connection connect, Membre currentMembre) {
		this.connect = connect;
		controllingFrame = f;
		this.currentMembre = currentMembre;
	//	rechercherBaladeButton = new JButton("Rechercher balade");
		rejoindreBaladeButton = new JButton("Rejoindre balade");
		retourButton = new JButton("Retour");
		deconnexionButton = new JButton("Déconnexion");
		
		
		//deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(3, 0));
		
	//	p.add(rechercherBaladeButton);
		p.add(rejoindreBaladeButton);
		p.add(retourButton);
		p.add(deconnexionButton);
		
		f.add(p);
	
	//	rechercherBaladeButton.addActionListener(new rechercherBaladeButtonListener(f));
		rejoindreBaladeButton.addActionListener(new rejoindreBaladeButtonListener(f, listCategorie, vehicule, currentMembre));
		retourButton.addActionListener(new retourButtonListener(f));
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
	
	/*private class rechercherBaladeButtonListener implements ActionListener
	{
		private JFrame f;

		public rechercherBaladeButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			//Main.CreerBalade(currentResponsable);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		/*}
	}*/
	
	private class rejoindreBaladeButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		private List<Categorie> listCategorie;
		private Vehicule vehicule;

		public rejoindreBaladeButtonListener(JFrame f, List<Categorie> listCategorie, Vehicule vehicule, Membre currentMembre)
		{
			this.f = f;
			this.listCategorie = listCategorie;
			this.vehicule = vehicule;
			this.currentMembre = currentMembre;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			CategorieDAO categorieDAO = new CategorieDAO(connect);
			/*if(categorieDAO.appartientCategorie(currentMembre))
			{*/
			if(currentMembre.getListCategorie().size() > 0 )
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.RejoindreBalade(listCategorie, vehicule, currentMembre);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Veuillez d'abord vous affilier à une catégorie.");
			}
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
			System.out.println("OK");
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
