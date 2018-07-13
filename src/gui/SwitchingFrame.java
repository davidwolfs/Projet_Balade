package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SwitchingFrame {
	
	private static String firstText = "Practice makes perfect";
	private static String textButton1 = "Change text";
	private static String textButton2 = "Display new text";

	private static class Panel1 extends JPanel implements ActionListener{

		private JLabel label;
		private JButton button;

		public Panel1(String label) {
			this.label = new JLabel(label);
			this.button = new JButton(textButton1);

			this.button.addActionListener(this);

			this.setLayout(new GridLayout(0, 1));
			
			this.add(this.label, BorderLayout.CENTER);
			this.add(this.button);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Container container = this.getParent();
			container.remove(this);
			
			Panel2 panel2 = new Panel2();
			container.add(panel2);
			
			container.revalidate();
			container.repaint();
		}
	}
	
	private static class Panel2 extends JPanel implements ActionListener{
		
		private JTextArea textArea;
		private JButton button;
		
		public Panel2() {
			this.textArea = new JTextArea();
			this.button = new JButton(textButton2);
			
			this.button.addActionListener(this);
			
			this.setLayout(new GridLayout(0, 1));
			
			this.add(this.textArea);
			this.add(this.button);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container container = this.getParent();
			container.remove(this);
			
			Panel1 panel1 = new Panel1(this.textArea.getText());
			container.add(panel1);
			
			container.revalidate();
			container.repaint();
		}

	}

	
	private static void createAndShowGui() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panel1 panel1 = new Panel1(firstText);
		frame.add( panel1);
		
        //Set up the content pane.
//        frame.addComponentsToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		createAndShowGui();
	}


}
