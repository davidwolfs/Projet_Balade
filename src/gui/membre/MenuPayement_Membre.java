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
import exo.Calendrier;
import exo.Categorie;
import exo.Membre;
import exo.Vehicule;
import gui.Main;

public class MenuPayement_Membre extends JPanel implements ActionListener 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private Membre currentMembre;
	private JButton payerCotisationAnnuelleButton;
	private JButton retourButton;
	private JPanel p;


	public MenuPayement_Membre(JFrame f, Connection connect, Membre currentMembre, Calendrier calendrier) {
		this.connect = connect;
		controllingFrame = f;
		this.currentMembre = currentMembre;
	//	rechercherBaladeButton = new JButton("Rechercher balade");
		payerCotisationAnnuelleButton = new JButton("Payer cotisation annuelle");
		// Button = new JButton("");
		retourButton = new JButton("Retour");
		
		
		
		//deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(2,0));
		
	//	p.add(rechercherBaladeButton);
		p.add(payerCotisationAnnuelleButton);
		p.add(retourButton);

		
		f.add(p);
	
	//	rechercherBaladeButton.addActionListener(new rechercherBaladeButtonListener(f));
		payerCotisationAnnuelleButton.addActionListener(new payerCotisationAnnuelleButtonListener(f, currentMembre));
		retourButton.addActionListener(new retourButtonListener(f, calendrier));
		
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
	
	private class payerCotisationAnnuelleButtonListener implements ActionListener
	{
		private JFrame f;
		private Membre currentMembre;
		public payerCotisationAnnuelleButtonListener(JFrame f, Membre currentMembre)
		{
			this.f = f;
			this.currentMembre = currentMembre;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			MembreDAO membreDAO = new MembreDAO(connect);
			CategorieDAO categorieDAO = new CategorieDAO(connect);
			
			currentMembre = membreDAO.findIdByMembre(currentMembre);
			currentMembre = membreDAO.getSoldeMembre(currentMembre);
			double soldeMembre = currentMembre.getSolde();
			double forfait = 20;
			
			if(membreDAO.VerifierPayementCotisationAnnuelleChangeStatus(currentMembre))
			{
				java.time.LocalDate.now();
				JOptionPane.showMessageDialog(null,  "Vous avez déjà payé votre cotisation pour cette année.");
			}
			else if(soldeMembre < forfait)
			{
				JOptionPane.showMessageDialog(null,  "Vous n'avez pas les moyens de payer votre cotisation annuelle.");
			}
			else
			{
				JOptionPane.showMessageDialog(null,  "Votre solde : " + currentMembre.getSolde());
				currentMembre.PayerCotisation(20);
				JOptionPane.showMessageDialog(null,  "Votre solde : " + currentMembre.getSolde());
				membreDAO.payerCotisationAnnuelle(currentMembre);
				membreDAO.payerCotisationAnnuelleChangeStatus(currentMembre);
				JOptionPane.showMessageDialog(null,  "Votre cotisation annuelle a bien été payée.");
			}
		}
	}
	
	private class retourButtonListener implements ActionListener
	{
		private JFrame f;
		private Calendrier calendrier;

		public retourButtonListener(JFrame f, Calendrier calendrier)
		{
			this.f = f;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("OK");
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showDashboard_Membre(currentMembre, calendrier);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
