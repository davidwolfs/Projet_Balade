package gui.responsable;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.BaladeDAO;
import dao.CategorieDAO;
import dao.MembreDAO;
import dao.ResponsableDAO;
import dao.VehiculeDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Categorie;
import exo.Membre;
import exo.Responsable;
import exo.Vehicule;
import gui.Main;

public class CreerCategorie implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private Responsable currentResponsable;
	private JLabel labelSupplement;
	private JLabel labelTypePneu;
	private JLabel labelNom;
	private JLabel labelMembre;
	private JLabel labelMsgErreur;
	private JTextField supplementField;
	private JTextField typePneuField;
	private JTextField nomField;
	private JTextField membreField;
	private JButton createBaladeButton;
	private JButton retourButton;
	private	JPanel p;
	private	JPanel p2;
	
	public CreerCategorie(JFrame f, Connection connect, Responsable currentResponsable) 
	{
		/*VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] vehicules = listVehicule.toArray();*/
		this.connect = connect;
		this.f = f;
		this.currentResponsable = currentResponsable;
		labelSupplement = new JLabel("Supplement");
		labelTypePneu = new JLabel("Type Pneu");
		labelNom = new JLabel("Nom");
		labelMembre = new JLabel("Membre");
		labelMsgErreur = new JLabel();
		//String listVehicules = vehicules.toString();
		supplementField = new JTextField(5);
		typePneuField = new JTextField(15);
		nomField = new JTextField(15);
		membreField = new JTextField(15);
		createBaladeButton = new JButton("Créer");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(7, 2));
		p2 = new JPanel(new GridLayout(1,1));
		
		p.add(labelSupplement);
		p.add(supplementField);
		p.add(labelTypePneu);
		p.add(typePneuField);
		p.add(labelNom);
		p.add(nomField);
		p.add(labelMembre);
		p.add(membreField);
		p.add(createBaladeButton);
		p.add(retourButton);
		
		createBaladeButton.addActionListener(this);
		retourButton.addActionListener(new retourButtonListener(f));
		f.add(p);
		f.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		int idMembre = Integer.parseInt(membreField.getText());
		if(supplementField.getText().isEmpty() || typePneuField.getText().isEmpty() || nomField.getText().isEmpty() || membreField.getText().isEmpty())
		{
			labelMsgErreur.setText("Veuillez remplir tous les champs.");
			p2.add(labelMsgErreur);
			f.add(p2);
			f.pack();
			System.out.println("Veuillez remplir tous les champs.");
		}
		else
		{
			Categorie categorie = new Categorie(Integer.parseInt(supplementField.getText()), typePneuField.getText(), nomField.getText());
			CategorieDAO categorieDAO = new CategorieDAO(connect);
			categorieDAO.create(categorie, currentResponsable, idMembre);
			Container cp = f.getContentPane();
			cp.removeAll();
			Main.showMenuCategorie_Responsable(currentResponsable);
		}
	}
	
	private class retourButtonListener implements ActionListener
	{
		private JFrame f;

		public retourButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			Main.showMenuCategorie_Responsable(currentResponsable);
		}
	}
}


