package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.CategorieDAO;
import dao.MembreDAO;
import dao.ResponsableDAO;
import dao.TresorierDAO;
import exo.Calendrier;
import exo.Categorie;
import exo.Membre;
import exo.Personne;
import exo.Responsable;
import exo.Tresorier;

public class CreerUser extends JPanel implements ActionListener {
	private Connection connect;
	private JFrame f; // needed for dialogs
	private JLabel labelNom;
	private JLabel labelPrenom;
	private JLabel labelDateNaiss;
	private JLabel labelEmail;
	private JLabel labelPassword;
	private JLabel labelSolde;
	private JLabel labelResponsable;
	private JLabel labelMembre;
	private JLabel labelTresorier;
	private JLabel labelMsgErreur;
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField dateNaissField;
	private JTextField emailField;
	private JTextField passwordField;
	private JTextField soldeField;
	private JRadioButton responsableRadio;
	private JRadioButton membreRadio;
	private JRadioButton tresorierRadio;
	private JButton createUserButton;
	private JButton retourButton;
	private JPanel p;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private Calendrier calendrier;
	
	public CreerUser(JFrame f, Connection connect, Calendrier calendrier)
	{
		this.connect = connect;
		this.f = f;
		labelNom = new JLabel("Nom");
		labelPrenom = new JLabel("Prenom");
		labelDateNaiss = new JLabel("Date de naissance");
		labelEmail = new JLabel("Email");
		labelPassword = new JLabel("Password");
		labelSolde = new JLabel("Solde");
		labelMsgErreur = new JLabel();
		nomField = new JTextField(15);
		prenomField = new JTextField(15);
		dateNaissField = new JTextField(8);
		emailField = new JTextField(25);
		passwordField = new JTextField(15);
		soldeField = new JTextField(10);
		labelResponsable = new JLabel("Responsable");
		labelMembre = new JLabel("Membre");
		labelTresorier = new JLabel("Trésorier");
		responsableRadio = new JRadioButton();
		membreRadio = new JRadioButton();
		tresorierRadio = new JRadioButton();
		createUserButton = new JButton("Créer");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(6,2));
		p2 = new JPanel(new GridLayout(1,3));
		p3 = new JPanel(new GridLayout(1,2));
		p4 = new JPanel(new GridLayout(1,1));
	
		
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
		p.add(labelSolde);
		p.add(soldeField);
		p2.add(labelResponsable);
		p2.add(responsableRadio);
		p2.add(labelMembre);
		p2.add(membreRadio);
		p2.add(labelTresorier);
		p2.add(tresorierRadio);
		
		p3.add(createUserButton);
		p3.add(retourButton);

		//p4.add(labelMsgErreur);
		
		f.setLayout(new GridLayout(4, 1));
		f.add(p);
		f.add(p2);
		f.add(p3);
		//f.add(p4);
		
		
		createUserButton.addActionListener(this);
		retourButton.addActionListener(new ReturnListener(f, calendrier));
		
		ButtonGroup personneRadio = new ButtonGroup();
		personneRadio.add(responsableRadio);
		personneRadio.add(membreRadio);
		personneRadio.add(tresorierRadio);
		
		
		f.pack();
	}
	
	public void choixTypePersonne()
	{
		labelMsgErreur.setText("Veuillez sélectionner un type de personne.");
		p4.add(labelMsgErreur);
		f.add(p4);
		f.pack();
	}
	
	public boolean champsVide()
	{
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		String regex2 = "[0-99999999]";
		
		Pattern pattern = Pattern.compile(regex);
		Pattern pattern2 = Pattern.compile(regex2);
		Matcher matcher = pattern.matcher(emailField.getText());
		Matcher matcher2 = pattern2.matcher(soldeField.getText());
		System.out.println();
		boolean valid = true;
		if(nomField.getText().isEmpty() || prenomField.getText().isEmpty() || dateNaissField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty() || soldeField.getText().isEmpty())
		{
			labelMsgErreur.setText("Veuillez remplir tous les champs.");
			p4.add(labelMsgErreur);
			f.add(p4);
			f.pack();
			System.out.println("OK");
			valid = false;
		}
		
		else if (!(matcher.matches()))
		{
			labelMsgErreur.setText("Veuillez entrer un e-mail valide.");
			p4.add(labelMsgErreur);
			f.add(p4);
			f.pack();
			System.out.println("OK");
			valid = false;
		}
		
		/*else if (!(matcher2.matches()))
		{
			labelMsgErreur.setText("Veuillez entrer un solde valide.");
			p4.add(labelMsgErreur);
			f.add(p4);
			f.pack();
			System.out.println("OK");
			valid = false;
		}*/
		
		return valid;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(responsableRadio.isSelected())
		{
			if(champsVide())
			{
				Responsable responsable = new Responsable(nomField.getText(), prenomField.getText(), dateNaissField.getText(), emailField.getText(), passwordField.getText());
				ResponsableDAO responsableDAO = new ResponsableDAO(connect);
				if(responsableDAO.alreadyExist(emailField.getText()))
				{
					labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
					p4.add(labelMsgErreur);
					f.add(p4);
					f.pack();
				}
				else
				{
					responsableDAO.create(responsable);
					Container cp = f.getContentPane();
					cp.removeAll();
					Main.showDashboard_Responsable(responsable, calendrier);
					f.revalidate();
					f.repaint();
				}
			}
		}
		else if(membreRadio.isSelected())
		{
			if(champsVide())
			{
				Membre membre = new Membre(nomField.getText(), prenomField.getText(), dateNaissField.getText(), emailField.getText(), passwordField.getText(), Double.parseDouble(soldeField.getText()));
				MembreDAO membreDAO = new MembreDAO(connect);
				/*CategorieDAO categorieDAO = new CategorieDAO(connect);
				Categorie categorie = new Categorie();
				categorie.setNom("VTT");*/
				if(membreDAO.alreadyExist(emailField.getText()))
				{
					labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
					p4.add(labelMsgErreur);
					f.add(p4);
					f.pack();
				}
				else
				{
					membreDAO.create(membre);
					/*categorieDAO.create_Categorie_Membre(categorie, membre);
					membre.AjouterCategorie(categorie);*/
					Container cp = f.getContentPane();
					cp.removeAll();
					Main.showDashboard_Membre(membre, calendrier);
					//f.removeAll();
					f.revalidate();
					f.repaint();
				}
			}
		}
		
		else if(tresorierRadio.isSelected())
		{
			if(champsVide())
			{
				Tresorier tresorier = new Tresorier(nomField.getText(), prenomField.getText(), dateNaissField.getText(), emailField.getText(), passwordField.getText());
				TresorierDAO tresorierDAO = new TresorierDAO(connect);
				if(tresorierDAO.alreadyExist(emailField.getText()))
				{
					labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
					p4.add(labelMsgErreur);
					f.add(p4);
					f.pack();
				}
				else
				{
					tresorierDAO.create(tresorier);
					Container cp = f.getContentPane();
					cp.removeAll();
					Main.showDashboard_Tresorier(tresorier, calendrier);
					//f.removeAll();
					f.revalidate();
					f.repaint();
				}
			}
		}
		else
		{
			choixTypePersonne();
		}
	}
	
	private class ReturnListener implements ActionListener
	{
		private JFrame f;
		private Calendrier calendrier;

		public ReturnListener(JFrame f, Calendrier calendrier)
		{
			this.f = f;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			Main.creerConnexion(calendrier);
			f.revalidate();
			f.repaint();
		}
	}
	
}
