package GUI;

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

import DAO.BaladeDAO;
import exo.Balade;

public class Dashboard implements ActionListener 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private JButton creerBaladeButton;
	private JButton modifierBaladeButton;
	private JButton supprimerBaladeButton;
	private JButton voirBaladeButton;
	private JPanel p;

	public Dashboard(JFrame f, Connection connect) {
		this.connect = connect;
		controllingFrame = f;
		creerBaladeButton = new JButton("Créer balade");
		modifierBaladeButton = new JButton("Modifier balade");
		supprimerBaladeButton = new JButton("Supprimer balade");
		voirBaladeButton = new JButton("Voir balade");
		
		p = new JPanel(new GridLayout(3, 0));

		p.add(creerBaladeButton);
		p.add(modifierBaladeButton);
		p.add(supprimerBaladeButton);
		p.add(voirBaladeButton);

		f.add(p);
		
		creerBaladeButton.addActionListener(this);
		modifierBaladeButton.addActionListener(this);
		supprimerBaladeButton.addActionListener(this);
		voirBaladeButton.addActionListener(this);
		
		f.pack();
	}

	public void actionPerformed(ActionEvent arg0) {
		System.out.println("OK");
		JFrame frame = new JFrame();
		CreerBalade creerBalade = new CreerBalade(frame, connect);
		frame.setVisible(true);
		
		
		/*Balade balade = new Balade(1, listVehicule.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);*/
	}
}
