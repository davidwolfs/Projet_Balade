package GUI;

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
	private JButton deconnexionButton;
	private JPanel p;

	public Dashboard(JFrame f, Connection connect) {
		this.connect = connect;
		controllingFrame = f;
		
		creerBaladeButton = new JButton("Créer balade");
		modifierBaladeButton = new JButton("Modifier balade");
		supprimerBaladeButton = new JButton("Supprimer balade");
		voirBaladeButton = new JButton("Voir balade");
		deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(3, 0));

		p.add(creerBaladeButton);
		p.add(modifierBaladeButton);
		p.add(supprimerBaladeButton);
		p.add(voirBaladeButton);
		p.add(deconnexionButton);
		
		f.add(p);
		
		creerBaladeButton.addActionListener(this);
		modifierBaladeButton.addActionListener(this);
		supprimerBaladeButton.addActionListener(this);
		voirBaladeButton.addActionListener(new voirBaladeButtonListener(f));
		deconnexionButton.addActionListener(new deconnexionButtonListener(f));
		
		f.pack();
	}

	public void actionPerformed(ActionEvent arg0) {
		JFrame frame = new JFrame();
		CreerBalade creerBalade = new CreerBalade(frame, connect);
		frame.setVisible(true);
		System.out.println(connect);
		
		/*Balade balade = new Balade(1, listVehicule.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);*/
	}
	
	private class voirBaladeButtonListener implements ActionListener
	{
		private JFrame f;

		public voirBaladeButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.Liste_Balade();
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
