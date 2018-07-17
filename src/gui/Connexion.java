package gui;

import javax.swing.*;

import dao.MembreDAO;
import dao.ResponsableDAO;
import dao.TresorierDAO;
import exo.Membre;
import exo.Responsable;
import exo.Tresorier;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.Arrays;

/* PasswordDemo.java requires no other files. */

public class Connexion extends JPanel implements ActionListener 
{
	private static String Connexion = "Connexion";
	private static String CreateUser = "Créer un compte";
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private JLabel labelUser;
	private JLabel labelPassword;
	private JTextField userField;
	private JPasswordField passwordField;
	private JLabel labelResponsable;
	private JLabel labelMembre;
	private JLabel labelTresorier;
	private JLabel labelMsgErreur;
	private JRadioButton responsableRadio;
	private JRadioButton membreRadio;
	private JRadioButton tresorierRadio;
	private JButton connexionButton;
	private JButton createUserButton;
	private JPanel p;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private Responsable currentResponsable;
	private Membre currentMembre;
	private Tresorier currentTresorier;

	public Connexion(JFrame f, Connection connect) {
		this.connect = connect;
		controllingFrame = f;
		labelUser = new JLabel("User : ");
		labelPassword = new JLabel("Password : ");
		userField = new JTextField(15);
		passwordField = new JPasswordField(15);
		labelResponsable = new JLabel("Responsable");
		labelMembre = new JLabel("Membre");
		labelTresorier = new JLabel("Trésorier");
		labelMsgErreur = new JLabel("Veuillez entrer tous les champs.");
		responsableRadio = new JRadioButton();
		membreRadio = new JRadioButton();
		tresorierRadio = new JRadioButton();
		connexionButton = new JButton("Connexion");
		connexionButton.setSize(15,15);
		createUserButton = new JButton("Créer un compte");
		createUserButton.setSize(15,15);
		p = new JPanel(new GridLayout(2, 2));
		p2 = new JPanel(new GridLayout(1,3));
		p3 = new JPanel(new GridLayout(1,1));
		p4 = new JPanel(new GridLayout(1,2));
		

		// Use the default FlowLayout.

		// Create everything.
		passwordField.setActionCommand(Connexion);
		passwordField.addActionListener(this);
		connexionButton.addActionListener(new ConnexionButtonListener(f));
		createUserButton.addActionListener(new CreateUserButtonListener(f));
		
		passwordField = new JPasswordField(15);
		// passwordField.setLabelFor(passwordField);

		// Lay out everything.
		p.add(labelUser);
		p.add(userField);
		p.add(labelPassword);
		p.add(passwordField);
		p2.add(labelResponsable);
		p2.add(responsableRadio);
		p2.add(labelMembre);
		p2.add(membreRadio);
		p2.add(labelTresorier);
		p2.add(tresorierRadio);
		//p3.add(labelMsgErreur);
		p4.add(connexionButton);
		p4.add(createUserButton);
		f.setLayout(new GridLayout(4, 2));
		f.add(p);
		f.add(p2);
		//f.add(p3);
		f.add(p4);
		f.pack();
		
		ButtonGroup personneRadio = new ButtonGroup();
		personneRadio.add(responsableRadio);
		personneRadio.add(membreRadio);
		personneRadio.add(tresorierRadio);
	}

	protected JComponent createButtonPanel() {

		connexionButton.setActionCommand(Connexion);
		createUserButton.setActionCommand(CreateUser);
		connexionButton.addActionListener(this);
		createUserButton.addActionListener(this);

		p.add(connexionButton);
		p.add(createUserButton);

		return p;
	}

	public void actionPerformed(ActionEvent e) {
		/*String cmd = e.getActionCommand();

		if (Connexion.equals(cmd)) { // Process the password.
			char[] input = passwordField.getPassword();
			if (isPasswordCorrect(input)) {
				JOptionPane.showMessageDialog(controllingFrame, "Success! You typed the right password.");
			} else {
				JOptionPane.showMessageDialog(controllingFrame, "Invalid password. Try again.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}

			// Zero out the possible password, for security.
			Arrays.fill(input, '0');

			passwordField.selectAll();
			resetFocus();
		} else { // The user has asked for help.
			JOptionPane.showMessageDialog(controllingFrame,
					"You can get the password by searching this example's\n"
							+ "source code for the string \"correctPassword\".\n"
							+ "Or look at the section How to Use Password Fields in\n"
							+ "the components section of The Java Tutorial.");
		}*/
	}

	/**
	 * Checks the passed-in array against the correct password. After this method
	 * returns, you should invoke eraseArray on the passed-in array.
	 */
	/*private static boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		// Zero out the password.
		Arrays.fill(correctPassword, '0');

		return isCorrect;
	}

	// Must be called from the event dispatch thread.
	protected void resetFocus() {
		passwordField.requestFocusInWindow();
	}*/

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	/*static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("PasswordDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		final Connexion newContentPane = new Connexion(frame);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Make sure the focus goes to the right component
		// whenever the frame is initially given the focus.
		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				newContentPane.resetFocus();
			}
		});

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}*/
	
	private class ConnexionButtonListener implements ActionListener
	{
		private JFrame f;
		
		public ConnexionButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		
		public void choixTypePersonne()
		{
			labelMsgErreur.setText("Veuillez sélectionner un type de personne.");
			p3.add(labelMsgErreur);
			f.add(p3);
			f.pack();
		}
		
		public boolean champsVide()
		{
			boolean vide = false;
			if(userField.getText().isEmpty() || passwordField.getText().isEmpty())
			{
				labelMsgErreur.setText("Veuillez remplir tous les champs.");
				p3.add(labelMsgErreur);
				f.add(p3);
				f.pack();
				System.out.println("OK");
				vide = true;
			}
			return vide;
		}
		
		public void loginpasswordIncorrect()
		{
			labelMsgErreur.setText("Login et/ou mot de passe incorrect.");
			p3.add(labelMsgErreur);
			f.add(p3);
			f.pack();
			System.out.println("OK");
		}
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MembreDAO membreDAO	= new MembreDAO(connect);
			ResponsableDAO responsableDAO	= new ResponsableDAO(connect);
			TresorierDAO tresorierDAO	= new TresorierDAO(connect);
			
			if(responsableRadio.isSelected())
			{
				if(!champsVide())
				{
					if(responsableDAO.findByEmailPassword(userField.getText(), passwordField.getText()))
					{
						Container cp = f.getContentPane();
						cp.removeAll();
						Main.showDashboard_Responsable(currentResponsable);
					}
					else
					{
						loginpasswordIncorrect();
					}
				}
			}
			else if(membreRadio.isSelected())
			{
				if(!champsVide())
				{
					if(membreDAO.findByEmailPassword(userField.getText(), passwordField.getText()))
					{
						currentMembre = membreDAO.findMembreByEmailPassword(userField.getText(), passwordField.getText());
						Container cp = f.getContentPane();
						cp.removeAll();
						Main.showDashboard_Membre(currentMembre);
					}
					else 
					{
						loginpasswordIncorrect();
					}
				}
			}
			
			else if(tresorierRadio.isSelected())
			{
				if(!champsVide())
				{
					if(tresorierDAO.findByEmailPassword(userField.getText(), passwordField.getText()))
					{
						Container cp = f.getContentPane();
						cp.removeAll();
						Main.showDashboard_Tresorier(currentTresorier);
					}
					else 
					{
						loginpasswordIncorrect();
					}
				}
			}
			else 
			{
				choixTypePersonne();
			}
		}
	}
	
	private class CreateUserButtonListener implements ActionListener
	{
		private JFrame f;

		public CreateUserButtonListener(JFrame f)
		{
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container cp = f.getContentPane();
			cp.removeAll();
			//f.removeAll();
			Main.creerUser();
			
			cp.revalidate();
			cp.repaint();
			/*f.revalidate();*/
			//f.getLayout().removeLayoutComponent(f);
		}
	}
}
