package GUI;

import javax.swing.*;

import DAO.MembreDAO;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.Arrays;

/* PasswordDemo.java requires no other files. */

public class Connexion extends JPanel implements ActionListener 
{
	private static String Connexion = "Connexion";
	private static String CreateUser = "Cr�er un compte";
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private JLabel labelUser;
	private JLabel labelPassword;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton connexionButton;
	private JButton createUserButton;
	private JPanel p;

	public Connexion(JFrame f, Connection connect) {
		this.connect = connect;
		controllingFrame = f;
		labelUser = new JLabel("User : ");
		labelPassword = new JLabel("Password : ");
		userField = new JTextField(15);
		passwordField = new JPasswordField(15);
		connexionButton = new JButton("Connexion");
		createUserButton = new JButton("Cr�er un compte");
		p = new JPanel(new GridLayout(4, 2));

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
		p.add(connexionButton);
		p.add(createUserButton);

		f.add(p);
		f.pack();
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
		String cmd = e.getActionCommand();

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
		}
	}

	/**
	 * Checks the passed-in array against the correct password. After this method
	 * returns, you should invoke eraseArray on the passed-in array.
	 */
	private static boolean isPasswordCorrect(char[] input) {
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
	}

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
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MembreDAO membreDAO	= new MembreDAO(connect);
			
			if(membreDAO.findByEmailPassword(userField.getText(), passwordField.getText()))
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();
				Main.showDashboard();
				System.out.println("LOGIN");
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			}
			else 
			{
				JLabel msgErreur = new JLabel("Login et/ou mot de passe incorrect.");
				p.add(msgErreur);
				f.add(p);
				f.pack();
				System.out.println("OK");
			}
			
			
			/*if(userField.getText().equals("david.wolfs@condorcet.be") && passwordField.getText().equals("test"))
			{
				Container cp = f.getContentPane();
				cp.removeAll();
				//f.removeAll();
				Main.showDashboard();
				System.out.println("LOGIN");
				/*f.revalidate();*/
				//f.getLayout().removeLayoutComponent(f);
			/*}
			else 
			{
				JLabel msgErreur = new JLabel("Login et/ou mot de passe incorrect.");
				p.add(msgErreur);
				f.add(p);
				f.pack();
				System.out.println("OK");
			}*/
			
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
