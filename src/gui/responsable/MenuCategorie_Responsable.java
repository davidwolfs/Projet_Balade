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
import javax.swing.JOptionPane;
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
	private JButton organiserCalendrierButton;
	private JButton voirCalendrierButton;
	private JButton devenirResponsableButton;
	private JButton NePlusEtreResponsableButton;
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
		List<Categorie> listCategorie = categorieDAO.listCategorie();
		//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] categories = listCategorie.toArray();
		
		labelCategorie = new JLabel("Catégorie : ");
		labelMsgErreur = new JLabel();
		organiserCalendrierButton = new JButton("Publier le calendrier");
		voirCalendrierButton = new JButton("Voir calendrier");
		devenirResponsableButton = new JButton("Devenir responsable");
		NePlusEtreResponsableButton = new JButton("Ne plus être responsable");
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
		p2.add(organiserCalendrierButton);
		p2.add(voirCalendrierButton);
		p2.add(devenirResponsableButton);
		p2.add(NePlusEtreResponsableButton);
		p3.add(retourButton);
		p3.add(deconnexionButton);
		f.add(p);
		f.add(p2);
		f.add(p3);
		
		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1));
		organiserCalendrierButton.addActionListener(new organiserCalendrierButtonListener(f, jlist1));
		voirCalendrierButton.addActionListener(new voirCalendrierButtonListener(f, jlist1));
		devenirResponsableButton.addActionListener(new devenirResponsableButtonListener(f, jlist1, currentResponsable));
		NePlusEtreResponsableButton.addActionListener(new NePlusEtreResponsableButtonListener(f, jlist1, currentResponsable));
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
			CategorieDAO categorieDAO = new CategorieDAO(connect);
			System.out.println("Categorie sélectionnée : " + categorieSelected);
			
			if(listeCategorie.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner une catégorie.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else if(categorieDAO.isNotResponsable((Categorie)categorieSelected, currentResponsable))
			{
				JOptionPane.showMessageDialog(null, "Vous devez d'abord devenir responsable de cette catégorie.");
			}
			else
			{
				System.out.println((Categorie)categorieSelected + " " + currentResponsable.getNom());
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
		public void actionPerformed(ActionEvent e) 
		{
			CategorieDAO categorieDAO = new CategorieDAO(connect);
			System.out.println("Categorie sélectionnée : " + categorieSelected);
			if(listeCategorie.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner une catégorie.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else if(categorieDAO.isNotResponsable((Categorie)categorieSelected, currentResponsable))
			{
				JOptionPane.showMessageDialog(null, "Vous devez d'abord devenir responsable de cette catégorie.");
			}
			else
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Categorie categorie = (Categorie)categorieSelected;
				Main.VoirCalendrier(currentResponsable, categorie);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
		}
	}
	
	private class devenirResponsableButtonListener implements ActionListener
	{
		private JFrame f;
		private JList listeCategorie;
		private Responsable currentResponsable;
		CategorieDAO categorieDAO = new CategorieDAO(connect);
		
		public devenirResponsableButtonListener(JFrame f, JList listeCategorie, Responsable currentResponsable)
		{
			this.f = f;
			this.listeCategorie = listeCategorie;
			this.currentResponsable = currentResponsable;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(listeCategorie.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner une catégorie.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else if(categorieDAO.isResponsable((Categorie)listeCategorie.getSelectedValue(), currentResponsable))
			{
				JOptionPane.showMessageDialog(null, "Vous êtes déjà responsable pour cette catégorie.");
			}
			else if(categorieDAO.haveAlreadyResponsable((Categorie)listeCategorie.getSelectedValue(), currentResponsable))
			{
				JOptionPane.showMessageDialog(null, "Il y a déjà un responsable pour cette catégorie.");
			}
			else
			{
				System.out.println("Responsable actuel : " + currentResponsable);
				JOptionPane.showMessageDialog(null, "Vous gérer la catégorie : " + listeCategorie.getSelectedValue());
				Categorie categorie = (Categorie)listeCategorie.getSelectedValue();
				categorie.setResponsable(currentResponsable);
				currentResponsable.setCategorie(categorie);
				CategorieDAO categorieDAO = new CategorieDAO(connect);
				categorieDAO.update_Categorie_Responsable((Categorie)listeCategorie.getSelectedValue());
			}
		}
	}
	
	private class NePlusEtreResponsableButtonListener implements ActionListener
	{
		private JFrame f;
		private JList listeCategorie;
		private Responsable currentResponsable;
		CategorieDAO categorieDAO = new CategorieDAO(connect);
		
		public NePlusEtreResponsableButtonListener(JFrame f, JList listeCategorie, Responsable currentResponsable)
		{
			this.f = f;
			this.listeCategorie = listeCategorie;
			this.currentResponsable = currentResponsable;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(listeCategorie.isSelectionEmpty())
			{
				labelMsgErreur.setText("Veuillez sélectionner une catégorie.");
				p4.add(labelMsgErreur);
				f.add(p4);
				f.pack();
			}
			else if(categorieDAO.isNotResponsable((Categorie)listeCategorie.getSelectedValue(), currentResponsable))
			{
				JOptionPane.showMessageDialog(null, "Vous n'êtes pas responsable de cette catégorie.");
			}
			else
			{
				System.out.println("Vous n'êtes plus reponsable de cette catégorie.");
				JOptionPane.showMessageDialog(null, "Vous n'êtes plus reponsable de cette catégorie.");
				Categorie categorie = (Categorie)listeCategorie.getSelectedValue();
				categorie.setResponsable(null);
				currentResponsable.setCategorie(null);
				CategorieDAO categorieDAO = new CategorieDAO(connect);
				categorieDAO.delete_Categorie_Responsable(categorie);
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
