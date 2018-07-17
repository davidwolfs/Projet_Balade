package gui.responsable;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

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

public class ModifierBalade implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private JLabel labelLibelle;
	private JLabel labelLieuDepart;
	private JLabel labelDateDepart;
	private JLabel labelForfait;
	private JTextField libelleField;
	private JTextField lieuDepartField;
	private JTextField dateDepartField;
	private JTextField forfaitField;
	private JButton modifierBaladeButton;
	private JButton retourButton;
	private	JPanel p;
	
	public ModifierBalade(JFrame f, Connection connect) 
	{
		/*VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] vehicules = listVehicule.toArray();*/
		this.connect = connect;
		this.f = f;
		labelLibelle = new JLabel("Libellé");
		labelLieuDepart = new JLabel("Lieu départ");
		labelDateDepart = new JLabel("Date départ");
		labelForfait = new JLabel("Forfait");
		//String listVehicules = vehicules.toString();
		libelleField = new JTextField(15);
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		
		modifierBaladeButton = new JButton("Modifier");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(7, 2));
		
		p.add(labelLibelle);
		p.add(libelleField);
		p.add(labelLieuDepart);
		p.add(lieuDepartField);
		p.add(labelDateDepart);
		p.add(dateDepartField);
		p.add(labelForfait);
		p.add(forfaitField);
		p.add(modifierBaladeButton);
		p.add(retourButton);
		
		modifierBaladeButton.addActionListener(this);
		retourButton.addActionListener(new retourButtonListener(f));
		f.add(p);
		f.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Balade balade = new Balade(1, lieuDepartField.getText(), dateDepartField.getText(), Double.parseDouble(forfaitField.getText()), libelleField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.update(balade);
		Container cp = f.getContentPane();
		cp.removeAll();
		Main.showMenuBalade_Responsable();
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
			Main.showMenuBalade_Responsable();
		}
	}
}


