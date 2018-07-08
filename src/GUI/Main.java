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

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		JFrame frame = new JFrame();
		Connection connect = DriverACCESS.getInstance();
		//Connexion connexion = new Connexion(frame);
		CreerUser con = new CreerUser(frame, connect);
		//Dashboard dashboard = new Dashboard(frame, connect);
		frame.setVisible(true);
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				Connexion.createAndShowGUI();
			}
		});*/
	}
}