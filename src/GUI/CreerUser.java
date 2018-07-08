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

import DAO.MembreDAO;
import DAO.ResponsableDAO;
import DAO.TresorierDAO;
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
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField dateNaissField;
	private JTextField emailField;
	private JTextField passwordField;
	private JButton createUserButton;
	private JButton retourButton;
	private JPanel p;

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
		createUserButton = new JButton("Créer");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(6, 2));

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
		p.add(createUserButton);
		p.add(retourButton);

		f.add(p);
		
		createUserButton.addActionListener(this);
		retourButton.addActionListener(this);
		
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
}
