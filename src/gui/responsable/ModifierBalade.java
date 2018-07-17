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
	private Object baladeSelected;
	
	public ModifierBalade(JFrame f, Connection connect, Object baladeSelected)
	{
		/*VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] vehicules = listVehicule.toArray();*/
		this.connect = connect;
		this.f = f;
		this.baladeSelected = baladeSelected;
		labelLibelle = new JLabel("Libellé");
		labelLieuDepart = new JLabel("Lieu départ");
		labelDateDepart = new JLabel("Date départ");
		labelForfait = new JLabel("Forfait");
		//String listVehicules = vehicules.toString();
		libelleField = new JTextField(15);
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		
		libelleField.setText(((Balade)baladeSelected).getLibelle());
		lieuDepartField.setText(((Balade)baladeSelected).getLieuDepart());
		dateDepartField.setText(((Balade)baladeSelected).getDateDepart());
		forfaitField.setText(Double.toString(((Balade)baladeSelected).getForfait()));
		
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
		//baladeSelected.(1, lieuDepartField.getText(), dateDepartField.getText(), Double.parseDouble(forfaitField.getText()), libelleField.getText());
		((Balade)baladeSelected).setLibelle(libelleField.getText());
		((Balade)baladeSelected).setLieuDepart(lieuDepartField.getText());
		((Balade)baladeSelected).setDateDepart(dateDepartField.getText());
		((Balade)baladeSelected).setForfait(Double.parseDouble(forfaitField.getText()));
		
		System.out.println("Mon nouvel objet balade : " + ((Balade)baladeSelected));
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		 //new Balade(1, , , Double.parseDouble(forfaitField.getText()), libelleField.getText());
		baladeDAO.update((Balade)baladeSelected);
		//baladeDAO.update(balade);
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


