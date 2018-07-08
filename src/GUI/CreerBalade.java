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
import DAO.ResponsableDAO;
import exo.Balade;
import exo.Responsable;

public class CreerBalade implements ActionListener 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private JLabel labelListeVehicule;
	private JLabel labelLieuDepart;
	private JLabel labelDateDepart;
	private JLabel forfait;
	private JLabel libelle;
	private JTextField listeVehiculeField;
	private JTextField lieuDepartField;
	private JTextField dateDepartField;
	private JTextField forfaitField;
	private JTextField libelleField;
	private JButton createBaladeButton;
	private JPanel p;
	
	public CreerBalade(JFrame f, Connection connect) 
	{
		this.connect = connect;
		controllingFrame = f;
		labelListeVehicule = new JLabel("Liste Véhicule");
		labelLieuDepart = new JLabel("Lieu départ");
		labelDateDepart = new JLabel("Date départ");
		forfait = new JLabel("Forfait");
		libelle = new JLabel("Libellé");
		listeVehiculeField = new JTextField(15);
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		libelleField = new JTextField(15);
		
		createBaladeButton = new JButton("Créer");
		p = new JPanel(new GridLayout(6, 2));
		
		p.add(libelle);
		p.add(libelleField);
		p.add(labelLieuDepart);
		p.add(lieuDepartField);
		p.add(labelDateDepart);
		p.add(dateDepartField);
		p.add(forfait);
		p.add(forfaitField);
		p.add(labelListeVehicule);
		p.add(listeVehiculeField);
		
		p.add(createBaladeButton);
		
		createBaladeButton.addActionListener(this);
		
		f.add(p);
		f.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Balade balade = new Balade(1, lieuDepartField.getText(), dateDepartField.getText(), Double.parseDouble(forfaitField.getText()), libelleField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);
	}
}

