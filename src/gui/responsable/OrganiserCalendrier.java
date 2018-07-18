package gui.responsable;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.BaladeDAO;
import dao.CalendrierDAO;
import dao.CategorieDAO;
import dao.VehiculeDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Categorie;
import exo.Membre;
import exo.Responsable;
import exo.Vehicule;
import gui.Main;

public class OrganiserCalendrier 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private Responsable currentResponsable;
	private Categorie categorieSelected;
	private ListSelectionModel listSelectionModel;
	private JLabel labelDate;
	private JLabel labelnombrePlaceVelo;
	private JLabel labelMsgErreur;
	private JTextField dateField;
	private JButton rejoindreButton;
	private JButton ajoutButton;
	private JButton retourButton;
	private JPanel p;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private Object baladeSelected;
	
	public OrganiserCalendrier(JFrame f, Connection connect, Responsable currentResponsable, Categorie categorieSelected) 
	{
		this.connect = connect;
		this.f = f;
		this.currentResponsable = currentResponsable;
		this.categorieSelected = categorieSelected;
		
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		List<Balade> listBalade = baladeDAO.listBalade();
		//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] balades = listBalade.toArray();
		//Object[] vehicules = listVehicule.toArray();
		
		
		
		labelDate = new JLabel("Date : ");
		labelMsgErreur = new JLabel();
		dateField = new JTextField(2);
		
		ajoutButton = new JButton("Ajouter");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(1, 1));
		p2 = new JPanel(new GridLayout(1, 2));
		p3 = new JPanel(new GridLayout(1, 1));
		p4 = new JPanel(new GridLayout(1, 2));
		
		JList jlist1 = new JList(balades);
	    jlist1.setVisibleRowCount(4);
	    
	    DefaultListModel model = new DefaultListModel();
	    model.ensureCapacity(100);
	    for (int i = 0; i < 100; i++) {
	        model.addElement(Integer.toString(i));
	    }
	    
	    JScrollPane scrollPane1 = new JScrollPane(jlist1);
	    f.add(scrollPane1, BorderLayout.NORTH);
	    
	    jlist1.setVisibleRowCount(4);
	    jlist1.setFixedCellHeight(12);
	    jlist1.setFixedCellWidth(200);


	    f.setSize(300, 350);
	    f.setVisible(true);
	    
	    p.add(scrollPane1);
		p2.add(labelDate);
		p2.add(dateField);
		p3.add(ajoutButton);
		p3.add(retourButton);
		
		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1));
		ajoutButton.addActionListener(new ajoutButtonListener(f, jlist1, currentResponsable));
		retourButton.addActionListener(new retourButtonListener(f, currentResponsable));
		f.add(p);
		f.add(p2);
		f.add(p3);
		f.pack();
	}

	private class SharedListSelectionHandler implements ListSelectionListener 
	{
		private JList listeBalade;
		private JFrame f;
		
		public SharedListSelectionHandler(JFrame f, JList jlist1)
		{
			this.f = f;
			this.listeBalade = jlist1;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listeBalade.getSelectedIndex();
			System.out.println("Balade :" + (Balade)listeBalade.getSelectedValue());
			System.out.println(listeBalade.getSelectedValue().getClass());
			baladeSelected = listeBalade.getSelectedValue();

			
			//listeVehicule.repaint();
			Container container = listeBalade.getParent();
			container.revalidate();
			container.repaint();
		}
	}

	private class ajoutButtonListener implements ActionListener 
	{
		private JFrame f;
		private Responsable currentResponsable;
		private JList listeBalade;
		
		public ajoutButtonListener(JFrame f, JList listeBalade, Responsable currentResponsable)
		{
			this.f = f;
			this.listeBalade = listeBalade;
			this.currentResponsable = currentResponsable;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(listeBalade.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionné une balade.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else if(dateField.getText().isEmpty())
			{
				labelMsgErreur.setText("Veuillez entrer tous les champs.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else 
			{
				Categorie categorie;
				categorie = (Categorie)categorieSelected;
				System.out.println("Catégorie : " + categorie);
				BaladeDAO baladeDAO = new BaladeDAO(connect);
				List<Balade> listBalade = baladeDAO.listBalade();
				Balade balade;
				balade = (Balade)baladeSelected;
				System.out.println("Balade : " + balade);
				
				Calendrier calendrier = new Calendrier(dateField.getText());
				calendrier.AjouterBalade(balade);
				CalendrierDAO calendrierDAO = new CalendrierDAO(connect);
				calendrierDAO.create(calendrier, balade, categorie);
				
				System.out.println("Calendrier avant : " + categorie.toString());
				categorie.setSupplement(500);
				
				categorie.setCalendrier(calendrier);
				System.out.println("Calendrier après : " + categorie.toString());
				
				//System.out.println("Liste des balades dans le calendrier : " + calendrier.getListBalade());
				System.out.println("Liste des calendrier dans la catégorie : " + categorie.getCalendrier());
				
				
				Container cp = f.getContentPane();
				cp.removeAll();
				Main.showMenuCategorie_Responsable(currentResponsable);
			}
		}
	}
	
	private class retourButtonListener implements ActionListener
	{
		private JFrame f;
		private Responsable currentResponsable;

		public retourButtonListener(JFrame f, Responsable currentResponsable)
		{
			this.f = f;
			this.currentResponsable = currentResponsable;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showMenuCategorie_Responsable(currentResponsable);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
