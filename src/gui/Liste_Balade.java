package gui;

import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Liste_Balade 
{
	private Connection connect;
	private JFrame controllingFrame; // needed for dialogs
	private JPanel p;
	JList list = new JList();
	
	
	public Liste_Balade(JFrame f, Connection connect) 
	{
		this.connect = connect;
		controllingFrame = f;
		p = new JPanel(new GridLayout(3, 0));
		
		f.add(p);
		f.pack();
	} 
	
}

