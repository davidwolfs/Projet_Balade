package gui.membre;

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
import dao.VehiculeDAO;
import exo.Balade;
import exo.Vehicule;
import gui.Main;

public class RejoindreBalade 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private JLabel labelBalade;
	private JLabel labelVehicule;
	private JList listeBalade;
	private JList listeVehicule;
	private ListSelectionModel listSelectionModel;
	private JButton rejoindreButton;
	private JButton ajoutVehiculeButton;
	private JButton retourButton;
	private JPanel p;
	private Object baladeSelected;
	//String listVehicules = vehicules.toString();
	//listeVehicule = new JList(vehicules);


	public RejoindreBalade(JFrame f, Connection connect) 
	{
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		BaladeDAO baladeDAO = new BaladeDAO(connect);
		List<Balade> listBalade = baladeDAO.listBalade();
		//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] balades = listBalade.toArray();
		//Object[] vehicules = listVehicule.toArray();
		this.connect = connect;
		controllingFrame = f;
		labelBalade = new JLabel("Balades : ");
		labelVehicule = new JLabel("Véhicules pour la balade sélectionnée : ");
		listeBalade = new JList(balades);
		listeVehicule = new JList();
		//String listVehicules = vehicules.toString();

		//Vehicule vehiculeSelectionne = (Vehicule) listeVehicule.getSelectedValue();
		//balade.AjouterVehicule(vehiculeSelectionne);

	    JList jlist1 = new JList(balades);
	    jlist1.setVisibleRowCount(4);
	    
	    DefaultListModel model = new DefaultListModel();
	    model.ensureCapacity(100);
	    for (int i = 0; i < 100; i++) {
	        model.addElement(Integer.toString(i));
	    }
	    
	    JScrollPane scrollPane1 = new JScrollPane(jlist1);
	    f.add(scrollPane1, BorderLayout.NORTH);

	    JScrollPane scrollPane2 = new JScrollPane(listeVehicule);
		f.add(scrollPane2, BorderLayout.NORTH);
		 
	    jlist1.setVisibleRowCount(4);
	    jlist1.setFixedCellHeight(12);
	    jlist1.setFixedCellWidth(200);


	    f.setSize(300, 350);
	    f.setVisible(true);
	    
		rejoindreButton = new JButton("Rejoindre");
		ajoutVehiculeButton = new JButton("Ajout véhicule");
		retourButton = new JButton("Retour");
		p = new JPanel(new GridLayout(7, 2));

		p.add(labelBalade);
		p.add(scrollPane1);
		p.add(labelVehicule);
		p.add(scrollPane2);
		p.add(rejoindreButton);
		p.add(ajoutVehiculeButton);
		p.add(retourButton);



		listSelectionModel  = jlist1.getSelectionModel();
		listSelectionModel.addListSelectionListener(
				new SharedListSelectionHandler(f, jlist1, listeVehicule));

		rejoindreButton.addActionListener(new rejoindreButtonListener(f));
		ajoutVehiculeButton.addActionListener(new ajoutVehiculeButtonListener(f));
		retourButton.addActionListener(new retourButtonListener(f));
		f.add(p);
		f.pack();
	}

	private class SharedListSelectionHandler implements ListSelectionListener 
	{
		private JList listeBalade;
		private JList listeVehicule;
		private JFrame f;
		
		public SharedListSelectionHandler(JFrame f, JList jlist1, JList listeVehicule)
		{
			this.f = f;
			this.listeBalade = jlist1;
			this.listeVehicule = listeVehicule;
		}

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();

			int index = listeBalade.getSelectedIndex();
			System.out.println("balade :" + listeBalade.getSelectedValue());
			System.out.println(listeBalade.getSelectedValue().getClass());
			baladeSelected = listeBalade.getSelectedValue();
			VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
			
			listeVehicule.setListData(vehiculeDAO.listVehicule((Balade)listeBalade.getSelectedValue()).toArray());
			
			//listeVehicule.repaint();
			Container container = listeVehicule.getParent();
			container.revalidate();
			container.repaint();
		}
	}

	private class rejoindreButtonListener implements ActionListener
	{
		private JFrame f;

		public rejoindreButtonListener(JFrame f)
		{
			this.f = f;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// requete ajout insert into liste_balade
			System.out.println("Balade : " + baladeSelected);
			System.out.println("Véhicule : " + listeVehicule.getSelectedValue());
			
			BaladeDAO baladeDAO = new BaladeDAO(connect);
			baladeDAO.create((Balade)baladeSelected);
			
			System.out.println("Liste balade : " + baladeDAO.listBalade());
			
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showDashboard_Membre();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}

	private class ajoutVehiculeButtonListener implements ActionListener
	{
		private JFrame f;

		public ajoutVehiculeButtonListener(JFrame f)
		{
			this.f = f;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// requete ajout insert into liste_balade


			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();*/
			Main.showDashboard_Membre();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
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
			//f.removeAll();*/
			Main.showDashboard_Membre();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
