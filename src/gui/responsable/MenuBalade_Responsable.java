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
import dao.VehiculeDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Categorie;
import exo.Responsable;
import gui.Main;

public class MenuBalade_Responsable extends JPanel implements ActionListener 
{
	private Connection connect;
	private JFrame f; // needed for dialogs
	private JLabel labelBalade;
	private JLabel labelMsgErreur;
	private Responsable currentResponsable;
	private Categorie categorieChoisie;
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
	private JPanel p3;
	private JPanel p4;
	private Object baladeSelected;

	public MenuBalade_Responsable(JFrame f, Connection connect, Responsable currentResponsable, Calendrier calendrier) 
	{
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		List<Balade> listBalade = baladeDAO.listBaladeDisponible();
		//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] balades = listBalade.toArray();
		//Object[] vehicules = listVehicule.toArray();
		this.connect = connect;
		this.f = f;
		this.currentResponsable = currentResponsable;
		labelBalade = new JLabel("Balades : ");
		labelMsgErreur = new JLabel();
		libelleField = new JTextField(15);
		lieuDepartField = new JTextField(15);
		dateDepartField = new JTextField(15);
		forfaitField = new JTextField(5);
		creerBaladeButton = new JButton("Créer balade");
		modifierBaladeButton = new JButton("Modifier balade");
		supprimerBaladeButton = new JButton("Supprimer balade");
		retourButton = new JButton("Retour");
		deconnexionButton = new JButton("Déconnexion");
		
		
		//deconnexionButton = new JButton("Déconnexion");
		p = new JPanel(new GridLayout(1, 1));
		p2 = new JPanel(new GridLayout(3, 1));
		p3 = new JPanel(new GridLayout(2,1));
		p4 = new JPanel(new GridLayout(1,1));
		
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
		p2.add(creerBaladeButton);
		p2.add(modifierBaladeButton);
		p2.add(supprimerBaladeButton);
		p3.add(retourButton);
		p3.add(deconnexionButton);
		f.add(p);
		f.add(p2);
		f.add(p3);
		
		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1, currentResponsable));
		creerBaladeButton.addActionListener(new creerBaladeButtonListener(f, calendrier));
		modifierBaladeButton.addActionListener(new modifierBaladeButtonListener(f, jlist1, calendrier));
		supprimerBaladeButton.addActionListener(new supprimerBaladeButtonListener(f, jlist1, currentResponsable, calendrier));
		retourButton.addActionListener(new retourButtonListener(f, currentResponsable, calendrier));
		deconnexionButton.addActionListener(new deconnexionButtonListener(f, calendrier));
		
		f.pack();
	}
	
	private class SharedListSelectionHandler implements ListSelectionListener 
	{
		private JList listeBalade;
		private JFrame f;
		private Responsable currentResponsable;
		
		public SharedListSelectionHandler(JFrame f, JList jlist1, Responsable currentResponsable)
		{
			this.f = f;
			this.listeBalade = jlist1;
			this.currentResponsable = currentResponsable;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listeBalade.getSelectedIndex();
			System.out.println("balade :" + listeBalade.getSelectedValue());
			System.out.println(listeBalade.getSelectedValue().getClass());
			baladeSelected = listeBalade.getSelectedValue();
		
			Container container = listeBalade.getParent();
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
	
	private class creerBaladeButtonListener implements ActionListener
	{
		private JFrame f;
		private Calendrier calendrier;

		public creerBaladeButtonListener(JFrame f, Calendrier calendrier)
		{
			this.f = f;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.CreerBalade(currentResponsable, calendrier);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class modifierBaladeButtonListener implements ActionListener
	{
		private JList listeBalade;
		private JFrame f;
		private Calendrier calendrier;
		
		public modifierBaladeButtonListener(JFrame f, JList listeBalade, Calendrier calendrier)
		{
			this.f = f;
			this.listeBalade = listeBalade;
			this.calendrier = calendrier;
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
				Main.ModifierBalade(baladeSelected, currentResponsable, calendrier);
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
	
	private class supprimerBaladeButtonListener implements ActionListener
	{
		private JList listeBalade;
		private JFrame f;
		private Responsable currentResponsable;
		private Calendrier calendrier;
		
		public supprimerBaladeButtonListener(JFrame f, JList listeBalade, Responsable currentResponsable, Calendrier calendrier)
		{
			this.f = f;
			this.listeBalade = listeBalade;
			this.currentResponsable = currentResponsable;
			this.calendrier = calendrier;
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
				Main.showMenuBalade_Responsable(currentResponsable, calendrier);
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
			//f.removeAll();*/
			Main.showDashboard_Responsable(currentResponsable, calendrier);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
	
	private class deconnexionButtonListener implements ActionListener
	{
		private JFrame f;
		private Calendrier calendrier;

		public deconnexionButtonListener(JFrame f, Calendrier calendrier)
		{
			this.f = f;
			this.calendrier = calendrier;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.creerConnexion(calendrier);
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
