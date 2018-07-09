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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DAO.MembreDAO;
import DAO.ResponsableDAO;
import DAO.TresorierDAO;
import exo.Categorie;
import exo.Membre;
import exo.Personne;
import exo.Responsable;
import exo.Tresorier;

public class CreerUser extends JPanel implements ActionListener {
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private JLabel labelNom;
	private JLabel labelPrenom;
	private JLabel labelDateNaiss;
	private JLabel labelEmail;
	private JLabel labelPassword;
	private JLabel labelResponsable;
	private JLabel labelMembre;
	private JLabel labelTresorier;
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField dateNaissField;
	private JTextField emailField;
	private JTextField passwordField;
	private JRadioButton responsableRadio;
	private JRadioButton membreRadio;
	private JRadioButton tresorierRadio;
	private JButton createUserButton;
	private JButton retourButton;
	private JPanel p;
	private JPanel p2;
	private JPanel p3;

	public CreerUser(JFrame f, Connection connect) {
		this.connect = connect;
		controllingFrame = f;
		labelNom = new JLabel("Nom");
		labelPrenom = new JLabel("Prenom");
		labelDateNaiss = new JLabel("Date de naissance");
		labelEmail = new JLabel("Email");
		labelPassword = new JLabel("Password");
		nomField = new JTextField(15);
		prenomField = new JTextField(15);
		dateNaissField = new JTextField(8);
		emailField = new JTextField(25);
		passwordField = new JTextField(15);
		labelResponsable = new JLabel("Responsable");
		labelMembre = new JLabel("Membre");
		labelTresorier = new JLabel("Trésorier");
		responsableRadio = new JRadioButton();
		membreRadio = new JRadioButton();
		tresorierRadio = new JRadioButton();
		createUserButton = new JButton("Créer");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(5,2));
		p2 = new JPanel(new GridLayout(1,3));
		p3 = new JPanel(new GridLayout(1,2));
		
		p.add(labelNom);
		p.add(nomField);
		p.add(labelPrenom);
		p.add(prenomField);
		p.add(labelDateNaiss);
		p.add(dateNaissField);
		p.add(labelEmail);
		p.add(emailField);
		p.add(labelPassword);
		p.add(passwordField);
		
		p2.add(labelResponsable);
		p2.add(responsableRadio);
		p2.add(labelMembre);
		p2.add(membreRadio);
		p2.add(labelTresorier);
		p2.add(tresorierRadio);
		
		p3.add(createUserButton);
		p3.add(retourButton);

		f.setLayout(new GridLayout(3, 1));
		f.add(p);
	
		f.add(p2);
		f.add(p3);
		
		createUserButton.addActionListener(this);
		retourButton.addActionListener(new ReturnListener(f));
		
		f.pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Responsable personne = new Responsable(1, nomField.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		ResponsableDAO responsableDAO = new ResponsableDAO(connect);
		responsableDAO.create(personne);
		
		Membre membre = new Membre(1, nomField.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		MembreDAO membreDAO = new MembreDAO(connect);
		membreDAO.create(membre);
		
		Tresorier tresorier = new Tresorier(1, nomField.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		TresorierDAO tresorierDAO = new TresorierDAO(connect);
		tresorierDAO.create(tresorier);
	}
	
	private class ReturnListener implements ActionListener
	{
		private JFrame f;

		public ReturnListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.creerConnexion();
			f.removeAll();
			f.revalidate();
			f.repaint();
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
}
