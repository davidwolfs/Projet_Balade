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
	private JTextField libelleField;
	private JTextField lieuDepartField;
	private JTextField dateDepartField;
	private JTextField forfaitField;
	private JButton creerBaladeButton;
	private JButton modifierBaladeButton;
	private JButton supprimerBaladeButton;
	private JButton retourButton;
	private JButton deconnexionButton;
	private JPanel p;
	private JPanel p2;
	private Object categorieSelected;

	public MenuCategorie_Responsable(JFrame f, Connection connect, Responsable currentResponsable) 
	{
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		CategorieDAO categorieDAO = new CategorieDAO(connect);
		List<Categorie> listCategorie = categorieDAO.listCategorie();
		//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] categories = listCategorie.toArray();
		//Object[] vehicules = listVehicule.toArray();
		this.connect = connect;
		this.f = f;
		this.currentResponsable = currentResponsable;
		labelCategorie = new JLabel("Cat�gorie : ");
		labelMsgErreur = new JLabel();
		libelleField = new JTextField(15);
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		creerBaladeButton = new JButton("Cr�er cat�gorie");
		modifierBaladeButton = new JButton("Modifier cat�gorie");
		supprimerBaladeButton = new JButton("Supprimer cat�gorie");
		retourButton = new JButton("Retour");
		deconnexionButton = new JButton("D�connexion");
		
		
		//deconnexionButton = new JButton("D�connexion");
		p = new JPanel(new GridLayout(4, 1));
		p2 = new JPanel(new GridLayout(1,1));
		
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
		p.add(creerBaladeButton);
		p.add(modifierBaladeButton);
		p.add(supprimerBaladeButton);
		p.add(retourButton);
		p.add(deconnexionButton);
		f.add(p);
		f.add(p2);
		
		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1));
		creerBaladeButton.addActionListener(new creerBaladeButtonListener(f));
		modifierBaladeButton.addActionListener(new modifierBaladeButtonListener(f, jlist1));
		supprimerBaladeButton.addActionListener(new supprimerBaladeButtonListener(f, jlist1, currentResponsable));
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
		JFrame frame = new JFrame();
		ToDelete creerBalade = new ToDelete(frame, connect);
		frame.setVisible(true);
		System.out.println(connect);
		
		/*Balade balade = new Balade(1, listVehicule.getText(), prenomField.getText(), new Date(1994-02-18), emailField.getText(), passwordField.getText());
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		baladeDAO.create(balade);*/
	}
	
	private class creerBaladeButtonListener implements ActionListener
	{
		private JFrame f;

		public creerBaladeButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.CreerBalade();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class modifierBaladeButtonListener implements ActionListener
	{
		private JList listeBalade;
		private JFrame f;
		
		public modifierBaladeButtonListener(JFrame f, JList listeBalade)
		{
			this.f = f;
			this.listeBalade = listeBalade;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			baladeSelected = listeBalade.getSelectedValue();
			System.out.println("Balade : " + baladeSelected);
			
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			
			if(baladeSelected != null)
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.ModifierBalade(baladeSelected);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
			else 
			{
				labelMsgErreur.setText("Veuillez s�lectionner une balade.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
			}
		}
	}
	
	private class supprimerBaladeButtonListener implements ActionListener
	{
		private JList listeBalade;
		private JFrame f;
		private Responsable currentResponsable;
		
		public supprimerBaladeButtonListener(JFrame f, JList listeBalade, Responsable currentResponsable)
		{
			this.f = f;
			this.listeBalade = listeBalade;
			this.currentResponsable = currentResponsable;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			baladeSelected = listeBalade.getSelectedValue();
			System.out.println("Balade : " + baladeSelected);
			
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			
			if(baladeSelected != null)
			{
				baladeDAO.delete((Balade)baladeSelected);
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();*/
				Main.showMenuBalade_Responsable(currentResponsable);
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
				
			}
			else 
			{
				labelMsgErreur.setText("Veuillez s�lectionner une balade.");
				p2.add(labelMsgErreur);
				f.add(p2);
				f.pack();
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