package gui;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import gui.responsable.*;
import gui.membre.*;
import gui.tresorier.Dashboard_Tresorier;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dao.BaladeDAO;
import dao.DAO;
import dao.ResponsableDAO;
import driver.DriverACCESS;
import exo.Balade;
import exo.Categorie;
import exo.Membre;
import exo.Responsable;
import exo.Tresorier;
import exo.Vehicule;


public class Main {
	
	private static JFrame frame = new JFrame("Projet Balade");
	private static Connection connect = DriverACCESS.getInstance();
	private Object baladeSelected;
	private Object categorieSelected;
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
	
	public static void CreerBalade(Responsable currentResponsable)
	{
		CreerBalade creerBalade = new CreerBalade(frame, connect, currentResponsable);
		frame.setVisible(true);
	}
	
	public static void ModifierBalade(Object baladeSelected, Responsable currentResponsable)
	{
		ModifierBalade creerBalade = new ModifierBalade(frame, connect, baladeSelected, currentResponsable);
		frame.setVisible(true);
	}
	
	public static void CreerCategorie(Responsable currentResponsable)
	{
		CreerCategorie creerCategorie = new CreerCategorie(frame, connect, currentResponsable);
		frame.setVisible(true);
	}
	
	public static void showMenuBalade_Membre(Membre currentMembre)
	{
		MenuBalade_Membre menuBalade_Membre = new MenuBalade_Membre(frame, connect, currentMembre);
		frame.setVisible(true);
	}
	
	public static void showMenuBalade_Responsable(Responsable currentResponsable)
	{
		MenuBalade_Responsable menuBalade_Responsable = new MenuBalade_Responsable(frame, connect, currentResponsable);
		frame.setVisible(true);
	}
	
	public static void showMenuCategorie_Responsable(Responsable currentResponsable)
	{
		MenuCategorie_Responsable menuCategorie_Responsable = new MenuCategorie_Responsable(frame, connect, currentResponsable);
		frame.setVisible(true);
	}
	
	public static void showMenuCategorie_Membre(Membre currentMembre)
	{
		MenuCategorie_Membre menuCategorie_Membre = new MenuCategorie_Membre(frame, connect, currentMembre);
		frame.setVisible(true);
	}
	
	public static void showMenuVehicule_Membre(Membre currentMembre)
	{
		MenuVehicule_Membre menuVehicule_Membre = new MenuVehicule_Membre(frame, connect, currentMembre);
		frame.setVisible(true);
	}
	
	public static void RejoindreBalade(List<Categorie> listCategorie, Membre currentMembre)
	{
		RejoindreBalade rejoindreBalade = new RejoindreBalade(frame, connect, listCategorie, currentMembre);
		frame.setVisible(true);
	}
	
	public static void AjoutVehicule(Membre currentMembre, List<Categorie> listCategorie, Vehicule vehicule, Balade baladeSelected)
	{
		AjouterVehiculeBalade ajouterVehiculeBalade = new AjouterVehiculeBalade(frame, connect, currentMembre, listCategorie, vehicule, baladeSelected);
		frame.setVisible(true);
	}
	
	public static void OrganiserCalendrier(Responsable currentResponsable, Categorie categorieSelected)
	{
		OrganiserCalendrier organiserCalendrier = new OrganiserCalendrier(frame, connect, currentResponsable, categorieSelected);
		frame.setVisible(true);
	}
	
	public static void VoirCalendrier(Responsable currentResponsable, Categorie categorieSelected)
	{
		VoirCalendrier voirCalendrier = new VoirCalendrier(frame, connect, currentResponsable, categorieSelected);
		frame.setVisible(true);
	}
	
	/*public static void showMenuRemboursement()
	{
		MenuRemboursement = new MenuRemboursement(frame, connect);membre@membre.com	
		frame.setVisible(true);
	}*/
	
	public static void showMenuDisponibilite(Membre currentMembre)
	{
		MenuDisponibilite_Membre menuDisponibilite_Membre = new MenuDisponibilite_Membre(frame, connect, currentMembre);
		frame.setVisible(true);
	}
	
	/*public static void showMenuCategorie()
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
		//frame.setSize(500,500);
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