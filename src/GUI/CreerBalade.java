package GUI;

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

import DAO.BaladeDAO;
import DAO.MembreDAO;
import DAO.ResponsableDAO;
import DAO.VehiculeDAO;
import exo.Balade;
import exo.Membre;
import exo.Responsable;
import exo.Vehicule;

public class CreerBalade implements ActionListener 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private JLabel labelListeVehicule;
	private JLabel labelListeVehiculeAddedToBalade;
	private JLabel labelLieuDepart;
	private JLabel labelDateDepart;
	private JLabel forfait;
	private JLabel libelle;
	private JList listeVehicule;
	private JList listeVehiculeAddedToBalade;
	private JTextField lieuDepartField;
	private JTextField dateDepartField;
	private JTextField forfaitField;
	private JTextField libelleField;
	private JButton createBaladeButton;
	private JButton retourButton;
	private JPanel p;
	

	
	public CreerBalade(JFrame f, Connection connect) 
	{
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] vehicules = listVehicule.toArray();
		this.connect = connect;
		controllingFrame = f;
		labelListeVehicule = new JLabel("Liste Véhicule");
		labelListeVehiculeAddedToBalade = new JLabel("Véhicule ajouté à la balade");
		labelLieuDepart = new JLabel("Lieu départ");
		labelDateDepart = new JLabel("Date départ");
		forfait = new JLabel("Forfait");
		libelle = new JLabel("Libellé");
		String listVehicules = vehicules.toString();
		listeVehicule = new JList(vehicules);
		listeVehiculeAddedToBalade = new JList();
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		libelleField = new JTextField(15);
		
		createBaladeButton = new JButton("Créer");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(7, 2));
		
		p.add(libelle);
		p.add(libelleField);
		p.add(labelLieuDepart);
		p.add(lieuDepartField);
		p.add(labelDateDepart);
		p.add(dateDepartField);
		p.add(forfait);
		p.add(forfaitField);
		p.add(labelListeVehicule);
		p.add(listeVehicule);
		p.add(labelListeVehiculeAddedToBalade);
		p.add(listeVehiculeAddedToBalade);
		p.add(createBaladeButton);
		p.add(retourButton);
		
		createBaladeButton.addActionListener(this);
		retourButton.addActionListener(new retourButtonListener(f));
		f.add(p);
		f.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Balade balade = new Balade(1, lieuDepartField.getText(), dateDepartField.getText(), Double.parseDouble(forfaitField.getText()), libelleField.getText());
		Vehicule vehiculeSelectionne = (Vehicule) listeVehicule.getSelectedValue();
		Vehicule vehicule = new Vehicule(vehiculeSelectionne.getIDV(), vehiculeSelectionne.getChauffeur(), vehiculeSelectionne.getNombrePlace(), vehiculeSelectionne.getImmatriculation(), vehiculeSelectionne.getNombrePlaceVelo());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		vehiculeDAO.create(vehicule);
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
			//f.removeAll();*/
			Main.showMenuBalade();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}


