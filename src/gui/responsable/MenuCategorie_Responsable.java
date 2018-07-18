package gui.responsable;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
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
import dao.CategorieDAO;
import dao.VehiculeDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Categorie;
import exo.Responsable;
import gui.Main;

public class MenuCategorie_Responsable extends JPanel implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private JLabel labelCategorie;
	private JLabel labelMsgErreur;
	private Responsable currentResponsable;
	private ListSelectionModel listSelectionModel;
	private JButton creerCategorieButton;
	private JButton modifierCategorieButton;
	private JButton supprimerCategorieButton;
	private JButton organiserCalendrierButton;
	private JButton voirCalendrierButton;
	private JButton retourButton;
	private JButton deconnexionButton;
	private JPanel p;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private Object categorieSelected;

	public MenuCategorie_Responsable(JFrame f, Connection connect, Responsable currentResponsable) 
	{
		this.connect = connect;
		this.f = f;
		this.currentResponsable = currentResponsable;
		
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		CategorieDAO categorieDAO = new CategorieDAO(connect);
		List<Categorie> listCategorie = categorieDAO.listCategorie(currentResponsable);
		//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] categories = listCategorie.toArray();
		
		labelCategorie = new JLabel("Catégorie : ");
		labelMsgErreur = new JLabel();
		creerCategorieButton = new JButton("Créer catégorie");
		modifierCategorieButton = new JButton("Modifier catégorie");
		supprimerCategorieButton = new JButton("Supprimer catégorie");
		organiserCalendrierButton = new JButton("Organiser et publier le calendrier");
		voirCalendrierButton = new JButton("Voir calendrier");
		retourButton = new JButton("Retour");
		deconnexionButton = new JButton("Déconnexion");
		
		
		//deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(1,1));
		p2 = new JPanel(new GridLayout(3,1));
		p3 = new JPanel(new GridLayout(2,1));
		p4 = new JPanel(new GridLayout(1,1));
		
	    JList jlist1 = new JList(categories);
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
		p2.add(creerCategorieButton);
		p2.add(modifierCategorieButton);
		p2.add(supprimerCategorieButton);
		p2.add(organiserCalendrierButton);
		p3.add(voirCalendrierButton);
		p3.add(retourButton);
		p3.add(deconnexionButton);
		f.add(p);
		f.add(p2);
		f.add(p3);
		
		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1));
		creerCategorieButton.addActionListener(new creerCategorieButtonListener(f));
		modifierCategorieButton.addActionListener(new modifierCategorieButtonListener(f, jlist1));
		supprimerCategorieButton.addActionListener(new supprimerCategorieButtonListener(f, jlist1, currentResponsable));
		organiserCalendrierButton.addActionListener(new organiserCalendrierButtonListener(f, jlist1));
		voirCalendrierButton.addActionListener(new voirCalendrierButtonListener(f, jlist1));
		retourButton.addActionListener(new retourButtonListener(f, currentResponsable));
		deconnexionButton.addActionListener(new deconnexionButtonListener(f));
		
		f.pack();
	}
	
	private class SharedListSelectionHandler implements ListSelectionListener 
	{
		private JList listeCategorie;
		private JFrame f;
		
		public SharedListSelectionHandler(JFrame f, JList jlist1)
		{
			this.f = f;
			this.listeCategorie = jlist1;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listeCategorie.getSelectedIndex();
			System.out.println("Categorie :" + (Categorie)listeCategorie.getSelectedValue());
			System.out.println(listeCategorie.getSelectedValue().getClass());
			categorieSelected = listeCategorie.getSelectedValue();

			
			//listeVehicule.repaint();
			Container container = listeCategorie.getParent();
			container.revalidate();
			container.repaint();
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		/*JFrame frame = new JFrame();
		ToDelete creerBalade = new ToDelete(frame, connect);
		frame.setVisible(true);
		System.out.println(connect);*/
		
		/*Balade balade = new Balade(1, listVehicule.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);*/
	}
	
	private class creerCategorieButtonListener implements ActionListener
	{
		private JFrame f;

		public creerCategorieButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.CreerCategorie(currentResponsable);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class modifierCategorieButtonListener implements ActionListener
	{
		private JList listeBalade;
		private JFrame f;
		
		public modifierCategorieButtonListener(JFrame f, JList listeBalade)
		{
			this.f = f;
			this.listeBalade = listeBalade;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			categorieSelected = listeBalade.getSelectedValue();
			System.out.println("Catégorie : " + categorieSelected);
			
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			
			if(categorieSelected != null)
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				//Main.ModifierBalade(categorieSelected);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
			else 
			{
				labelMsgErreur.setText("Veuillez sélectionner une balade.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
		}
	}
	
	private class supprimerCategorieButtonListener implements ActionListener
	{
		private JList listeCategorie;
		private JFrame f;
		private Responsable currentResponsable;
		
		public supprimerCategorieButtonListener(JFrame f, JList listeCategorie, Responsable currentResponsable)
		{
			this.f = f;
			this.listeCategorie = listeCategorie;
			this.currentResponsable = currentResponsable;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			categorieSelected = listeCategorie.getSelectedValue();
			System.out.println("Balade : " + categorieSelected);
			
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			
			if(categorieSelected != null)
			{
				//categorieDAO.delete((Categorie)categorieSelected);
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.showMenuBalade_Responsable(currentResponsable);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
				
			}
			else 
			{
				labelMsgErreur.setText("Veuillez sélectionner une balade.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}

			
		}
	}
	
	private class organiserCalendrierButtonListener implements ActionListener
	{
		private JFrame f;
		private JList listeCategorie;

		public organiserCalendrierButtonListener(JFrame f, JList listeCategorie)
		{
			this.f = f;
			this.listeCategorie = listeCategorie;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Categorie sélectionnée : " + categorieSelected);
			
			if(listeCategorie.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner une catégorie.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.OrganiserCalendrier(currentResponsable, (Categorie)categorieSelected);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}
	
	private class voirCalendrierButtonListener implements ActionListener
	{
		private JFrame f;
		private JList listeCategorie;

		public voirCalendrierButtonListener(JFrame f, JList listeCategorie)
		{
			this.f = f;
			this.listeCategorie = listeCategorie;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.VoirCalendrier(currentResponsable);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
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
			Main.showDashboard_Responsable(currentResponsable);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class deconnexionButtonListener implements ActionListener
	{
		private JFrame f;

		public deconnexionButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.creerConnexion();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
