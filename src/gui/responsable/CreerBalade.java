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
import dao.MembreDAO;
import dao.ResponsableDAO;
import dao.VehiculeDAO;
import exo.Balade;
import exo.Membre;
import exo.Responsable;
import exo.Vehicule;
import gui.Main;

public class CreerBalade implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private Responsable currentResponsable;
	private JLabel labelLibelle;
	private JLabel labelLieuDepart;
	private JLabel labelDateDepart;
	private JLabel labelForfait;
	private JLabel labelMsgErreur;
	private JTextField libelleField;
	private JTextField lieuDepartField;
	private JTextField dateDepartField;
	private JTextField forfaitField;
	private JButton createBaladeButton;
	private JButton retourButton;
	private	JPanel p;
	private	JPanel p2;
	
	public CreerBalade(JFrame f, Connection connect, Responsable currentResponsable) 
	{
		/*VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] vehicules = listVehicule.toArray();*/
		this.connect = connect;
		this.f = f;
		this.currentResponsable = currentResponsable;
		labelLibelle = new JLabel("Libellé");
		labelLieuDepart = new JLabel("Lieu départ");
		labelDateDepart = new JLabel("Date départ");
		labelForfait = new JLabel("Forfait");
		labelMsgErreur = new JLabel();
		//String listVehicules = vehicules.toString();
		libelleField = new JTextField(15);
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		
		createBaladeButton = new JButton("Créer");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(7, 2));
		p2 = new JPanel(new GridLayout(1,1));
		
		p.add(labelLibelle);
		p.add(libelleField);
		p.add(labelLieuDepart);
		p.add(lieuDepartField);
		p.add(labelDateDepart);
		p.add(dateDepartField);
		p.add(labelForfait);
		p.add(forfaitField);
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
		String regex = "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$";
		 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(forfaitField.getText());
		
		if(libelleField.getText().isEmpty() || lieuDepartField.getText().isEmpty() || dateDepartField.getText().isEmpty() || forfaitField.getText().isEmpty())
		{
			labelMsgErreur.setText("Veuillez remplir tous les champs.");
			p2.add(labelMsgErreur);
			f.add(p2);
			f.pack();
			System.out.println("Veuillez remplir tous les champs.");
		}
		else if(!(matcher.matches()))
		{
			labelMsgErreur.setText("Le champs forfait ne peut contenir que des chiffres");
			p2.add(labelMsgErreur);
			f.add(p2);
			f.pack();
		}
		else
		{
			Balade balade = new Balade(1, lieuDepartField.getText(), dateDepartField.getText(), Double.parseDouble(forfaitField.getText()), libelleField.getText());
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			baladeDAO.create(balade);
			Container cp = f.getContentPane();
			cp.removeAll();
			Main.showMenuBalade_Responsable(currentResponsable);
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
			Main.showMenuBalade_Responsable(currentResponsable);
		}
	}
}


