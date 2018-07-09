package GUI;

import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import DAO.BaladeDAO;
import DAO.DAO;
import Driver.DriverACCESS;
import exo.Balade;

public class Main {
	
	private static JFrame frame = new JFrame();
	private static Connection connect = DriverACCESS.getInstance();
	
	public static void creerConnexion()
	{
		Connexion connexion = new Connexion(frame);
		frame.setVisible(true);
	}
	
	public static void creerUser()
	{
		CreerUser con = new CreerUser(frame, connect);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		creerConnexion();
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