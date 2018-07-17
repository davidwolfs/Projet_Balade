package gui;

import java.awt.event.ActionListener;
import java.sql.Connection;
import gui.membre.Dashboard_Membre;
import gui.membre.MenuBalade_Membre;
import gui.responsable.*;
import gui.membre.*;
import gui.tresorier.Dashboard_Tresorier;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dao.BaladeDAO;
import dao.DAO;
import dao.ResponsableDAO;
import driver.DriverACCESS;
import exo.Balade;
import exo.Membre;
import exo.Responsable;
import exo.Tresorier;


public class Main {
	
	private static JFrame frame = new JFrame();
	private static Connection connect = DriverACCESS.getInstance();
	private Object baladeSelected;
	private Responsable currentResponsable;
	private Membre currentMembre;
	private Tresorier currentTresorier;
	
	public static void creerConnexion()
	{
		Connexion connexion = new Connexion(frame, connect);
		frame.setVisible(true);
	}
	
	public static void creerUser()
	{
		CreerUser con = new CreerUser(frame, connect);
		frame.setVisible(true);
	}
	
	public static void showDashboard_Membre(Membre currentMembre)
	{
		Dashboard_Membre dashboardMembre = new Dashboard_Membre(frame, connect, currentMembre);
		frame.setVisible(true);
	}
	
	public static void showDashboard_Responsable(Responsable currentResponsable)
	{
		Dashboard_Responsable dashboardResponsable = new Dashboard_Responsable(frame, connect, currentResponsable);
		frame.setVisible(true);
	}
	
	public static void showDashboard_Tresorier(Tresorier currentTresorier)
	{
		Dashboard_Tresorier dashboardTresorier = new Dashboard_Tresorier(frame, connect, currentTresorier);
		frame.setVisible(true);
	}
	
	public static void CreerBalade()
	{
		CreerBalade creerBalade = new CreerBalade(frame, connect);
		frame.setVisible(true);
	}
	
	public static void ModifierBalade(Object baladeSelected)
	{
		ModifierBalade creerBalade = new ModifierBalade(frame, connect, baladeSelected);
		frame.setVisible(true);
	}
	
	public static void showMenuBalade_Membre(Membre currentMembre)
	{
		MenuBalade_Membre menuBalade = new MenuBalade_Membre(frame, connect, currentMembre);
		frame.setVisible(true);
	}
	
	public static void showMenuBalade_Responsable()
	{
		MenuBalade_Responsable menuBalade = new MenuBalade_Responsable(frame, connect);
		frame.setVisible(true);
	}
	
	public static void RejoindreBalade(Membre currentMembre)
	{
		RejoindreBalade rejoindreBalade = new RejoindreBalade(frame, connect, currentMembre);
		frame.setVisible(true);
	}
	
	public static void AjoutVehicule()
	{
		AjouterVehicule ajouterBalade = new AjouterVehicule(frame, connect);
		frame.setVisible(true);
	}
	
	/*public static void showMenuRemboursement()
	{
		MenuRemboursement = new MenuRemboursement(frame, connect);membre@membre.com	
		frame.setVisible(true);
	}
	
	public static void showMenuDisponibilite()
	{
		 MenuDisponibilite = new MenuDisponibilite(frame, connect);
		frame.setVisible(true);
	}
	
	public static void showMenuCategorie()
	{
		MenuCategorie = new MenuCategorie(frame, connect);
		frame.setVisible(true);
	}
	
	public static void showMenuPayement()
	{
		MenuPayement = new MenuPayement(frame, connect);
		frame.setVisible(true);
	}*/
	
	public static void Liste_Balade()
	{
		Liste_Balade listeBalade = new Liste_Balade(frame, connect);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		creerConnexion();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*BaladeDAO baladeDAO = new BaladeDAO(connect);
		Balade balade = new Balade();
		balade = baladeDAO.find(18);
		System.out.println(balade.getLibelle());
		System.out.println("Balade 18 : " + baladeDAO.find(18));
		
		ResponsableDAO responsableDAO = new ResponsableDAO(connect);
		Responsable responsable = new Responsable();
		responsable = responsableDAO.find(75);
		System.out.println(responsable.getNom());
		System.out.println("Responsable 18 : " + responsableDAO.find(18));
		//CreerUser con = new CreerUser(frame, connect);
		/*Dashboard dashboard = new Dashboard(frame, connect);*/
		//frame.setVisible(true);
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				Connexion.createAndShowGUI();
			}
		});*/
	}
}