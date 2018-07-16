package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.MembreDAO;
import dao.ResponsableDAO;
import dao.TresorierDAO;
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
	private JLabel labelMsgErreur;
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
	private JPanel p4;
	
	public CreerUser(JFrame f, Connection connect) {
		this.connect = connect;
		controllingFrame = f;
		labelNom = new JLabel("Nom");
		labelPrenom = new JLabel("Prenom");
		labelDateNaiss = new JLabel("Date de naissance");
		labelEmail = new JLabel("Email");
		labelPassword = new JLabel("Password");
		labelMsgErreur = new JLabel();
		nomField = new JTextField(15);
		prenomField = new JTextField(15);
		dateNaissField = new JTextField(8);
		emailField = new JTextField(25);
		passwordField = new JTextField(15);
		labelResponsable = new JLabel("Responsable");
		labelMembre = new JLabel("Membre");
		labelTresorier = new JLabel("Tr�sorier");
		responsableRadio = new JRadioButton();
		membreRadio = new JRadioButton();
		tresorierRadio = new JRadioButton();
		createUserButton = new JButton("Cr�er");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(5,2));
		p2 = new JPanel(new GridLayout(1,3));
		p3 = new JPanel(new GridLayout(1,1));
		p4 = new JPanel(new GridLayout(1,2));
		
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
		
		p3.add(labelMsgErreur);
		
		p4.add(createUserButton);
		p4.add(retourButton);

		f.setLayout(new GridLayout(3, 1));
		f.add(p);
	
		f.add(p2);
		f.add(p3);
		f.add(p4);
		
		/*responsableRadio.addActionListener(new ResponsableListener(f));
		tresorierRadio.addActionListener(new TresorierListener(f));*/
		createUserButton.addActionListener(this);
		retourButton.addActionListener(new ReturnListener(f));
		
		ButtonGroup personneRadio = new ButtonGroup();
		personneRadio.add(responsableRadio);
		personneRadio.add(membreRadio);
		personneRadio.add(tresorierRadio);
		
		
		f.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(responsableRadio.isSelected())
		{
			if(nomField.getText().isEmpty() || prenomField.getText().isEmpty() || dateNaissField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty())
			{
				JLabel msgErreur = new JLabel("Veuillez remplir tous les champs.");
				p3.add(msgErreur);
				controllingFrame.add(p3);
				controllingFrame.pack();
				System.out.println("OK");
			}
			else
			{
				Responsable personne = new Responsable(1, nomField.getText(), prenomField.getText(), dateNaissField.getText(), emailField.getText(), passwordField.getText());
				ResponsableDAO responsableDAO = new ResponsableDAO(connect);
				responsableDAO.create(personne);
			}
		}
		else if(membreRadio.isSelected())
		{
			if(nomField.getText().isEmpty() || prenomField.getText().isEmpty() || dateNaissField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty())
			{
				JLabel msgErreur = new JLabel("Veuillez remplir tous les champs.");
				p3.add(msgErreur);
				controllingFrame.add(p3);
				controllingFrame.pack();
				System.out.println("OK");
			}
			else 
			{
				Membre membre = new Membre(1, nomField.getText(), prenomField.getText(), dateNaissField.getText(), emailField.getText(), passwordField.getText());
				MembreDAO membreDAO = new MembreDAO(connect);
				membreDAO.create(membre);
			}
		}
		
		else if(tresorierRadio.isSelected())
		{
			if(nomField.getText().isEmpty() || prenomField.getText().isEmpty() || dateNaissField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty())
			{
				JLabel msgErreur = new JLabel("Veuillez remplir tous les champs.");
				p3.add(msgErreur);
				controllingFrame.add(p3);
				controllingFrame.pack();
				System.out.println("OK");
			}
			else 
			{
				Tresorier tresorier = new Tresorier(1, nomField.getText(), prenomField.getText(), dateNaissField.getText(), emailField.getText(), passwordField.getText());
				TresorierDAO tresorierDAO = new TresorierDAO(connect);
				tresorierDAO.create(tresorier);
			}
		}
		else
		{
			JLabel msgErreur = new JLabel("Veuillez s�lectionner un type de personne.");
			p3.add(msgErreur);
			controllingFrame.add(p3);
			controllingFrame.pack();
		}
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
			Container cp = f.getContentPane();
			cp.removeAll();
			Main.creerConnexion();
			//f.removeAll();
			f.revalidate();
			f.repaint();
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
}