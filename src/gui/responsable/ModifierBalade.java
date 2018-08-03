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
import exo.Calendrier;
import exo.Membre;
import exo.Responsable;
import exo.Vehicule;
import gui.Main;

public class ModifierBalade implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private Responsable currentResponsable;
	private JLabel labelLibelle;
	private JLabel labelLibelleValeur;
	private JLabel labelLieuDepart;
	private JLabel labelDateDepart;
	private JLabel labelForfait;
	private JLabel labelMsgErreur;
	private JTextField lieuDepartField;
	private JTextField dateDepartField;
	private JTextField forfaitField;
	private JButton modifierBaladeButton;
	private JButton retourButton;
	private	JPanel p;
	private	JPanel p2;
	private Object baladeSelected;
	
	public ModifierBalade(JFrame f, Connection connect, Object baladeSelected, Responsable currentResponsable, Calendrier calendrier)
	{
		/*VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] vehicules = listVehicule.toArray();*/
		this.connect = connect;
		this.f = f;
		this.baladeSelected = baladeSelected;
		this.currentResponsable = currentResponsable;
		labelLibelle = new JLabel("Libellé");
		labelLibelleValeur = new JLabel();
		labelLieuDepart = new JLabel("Lieu départ");
		labelDateDepart = new JLabel("Date départ");
		labelForfait = new JLabel("Forfait");
		labelMsgErreur = new JLabel();
		//String listVehicules = vehicules.toString();
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		
		labelLibelleValeur.setText(((Balade)baladeSelected).getLibelle());
		lieuDepartField.setText(((Balade)baladeSelected).getLieuDepart());
		dateDepartField.setText(((Balade)baladeSelected).getDateDepart());
		forfaitField.setText(Double.toString(((Balade)baladeSelected).getForfaitBalade()));
		
		modifierBaladeButton = new JButton("Modifier");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(7, 2));
		p2 = new JPanel(new GridLayout(1,1));
		
		p.add(labelLibelle);
		p.add(labelLibelleValeur);
		p.add(labelLieuDepart);
		p.add(lieuDepartField);
		p.add(labelDateDepart);
		p.add(dateDepartField);
		p.add(labelForfait);
		p.add(forfaitField);
		p.add(modifierBaladeButton);
		p.add(retourButton);
		
		modifierBaladeButton.addActionListener(this);
		retourButton.addActionListener(new retourButtonListener(f, currentResponsable, calendrier));
		f.add(p);
		f.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		String regex = "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$";
		 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(forfaitField.getText());
		
		if(lieuDepartField.getText().isEmpty() || dateDepartField.getText().isEmpty() || forfaitField.getText().isEmpty())
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
			((Balade)baladeSelected).setLieuDepart(lieuDepartField.getText());
			((Balade)baladeSelected).setDateDepart(dateDepartField.getText());
			((Balade)baladeSelected).setForfaitBalade(Double.parseDouble(forfaitField.getText()));
			System.out.println("Mon nouvel objet balade : " + ((Balade)baladeSelected));
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			 //new Balade(1, , , Double.parseDouble(forfaitField.getText()), libelleField.getText());
			baladeDAO.update((Balade)baladeSelected);
			//baladeDAO.update(balade);
			Container cp = f.getContentPane();
			cp.removeAll();
			Calendrier calendrier = null;
			Main.showMenuBalade_Responsable(currentResponsable, calendrier);
		}
	}
	
	private class retourButtonListener implements ActionListener
	{
		private JFrame f;
		private Responsable currentResponsable;
		private Calendrier calendrier;

		public retourButtonListener(JFrame f, Responsable currentResponsable, Calendrier calendrier)
		{
			this.f = f;
			this.currentResponsable = currentResponsable;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			Main.showMenuBalade_Responsable(currentResponsable, calendrier);
		}
	}
}


