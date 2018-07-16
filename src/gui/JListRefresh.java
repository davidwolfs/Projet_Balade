package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * L'objectif de cette classe est de donner un petit pour le changemenet
 * dynamique du contenu d'une Jlist. La JList sera remplie avec 5 chiffres
 * choisis aléatoirement entre 0 et 10, et qui seront remplacés à chaque fois
 * que l'utilisateur appuiera sur le bouton 'refresh'
 */
public class JListRefresh {

	private JFrame frame;
	private JList list;
	private JPanel buttonsPanel;
	private JButton buttonRefresh;
	private JButton buttonReset;

	private class ButtonListenerRefresh implements ActionListener {

		private JList list;

		public ButtonListenerRefresh(JList list) {
			this.list = list;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.list.setListData(JListRefresh.generateRandomArray());
		}

	}

	private class ButtonListenerReset implements ActionListener {

		private JList list;

		public ButtonListenerReset(JList list) {
			this.list = list;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.list.setListData(this.resetContent());
		}

		private Integer[] resetContent() {
			Integer[] content = new Integer[5];
			for (int i = 0; i < 5; i++) {
				// on génère un double entre 0 et 1, on multiplie par 10 pour avoir un double
				// entre 0 et 10, et on prend la valeur entière de ce double
				content[i] = new Integer(0);
			}
			return content;
		}
	}

	private class ListListener implements ListSelectionListener, MouseListener {

		private String selectedValue = "";

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// lorsque l'utilisateur arrête d'appuyer sur le bouton, un popup apparait
			JOptionPane.showMessageDialog(frame, "The selected value is " + this.selectedValue);

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// récupération de la valeur contenue dans la ligne sur laquelle a cliqué
			// l'utilisateur
			JList list = (JList) e.getSource();
			// le test est nécessaire car au moment du reset ou du refresh, cette méthode
			// est appelée mais le contenu de la JList est vide, si le test est commenté une
			// erreur apparaitra dans les logs
			if (list.getSelectedValue() != null)
				this.selectedValue = list.getSelectedValue().toString();
		}

	}

	public JListRefresh() {
		// initialisation des champs
		this.frame = new JFrame("test refresh Jlist");
		this.list = new JList<>();
		// cette configuration pour ne permettre la sélection que d'une seule ligne
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.buttonsPanel = new JPanel();
		this.buttonRefresh = new JButton("Refresh");
		this.buttonReset = new JButton("Reset");

		// association des listeners avec les éléments graphiques
		ListListener listListener = new ListListener();
		this.list.addMouseListener(listListener);
		this.list.addListSelectionListener(listListener);
		this.buttonRefresh.addActionListener(new ButtonListenerRefresh(this.list));
		this.buttonReset.addActionListener(new ButtonListenerReset(this.list));

		// remplissage de la liste
		this.list.setListData(JListRefresh.generateRandomArray());

		// association des éléments graphiques
		this.buttonsPanel.setLayout(new FlowLayout());
		this.buttonsPanel.add(this.buttonRefresh);
		this.buttonsPanel.add(this.buttonReset);

		frame.setLayout(new GridLayout(2, 1));
		this.frame.add(this.list);
		this.frame.add(this.buttonsPanel);
		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);

	}

	private static Integer[] generateRandomArray() {
		Integer[] content = new Integer[5];
		for (int i = 0; i < 5; i++) {
			// on génère un double entre 0 et 1, on multiplie par 10 pour avoir un double
			// entre 0 et 10, et on prend la valeur entière de ce double
			content[i] = new Integer(new Double((Math.random() * 10)).intValue());
		}
		return content;
	}

	public static void main(String[] args) {
		JListRefresh listRefresh = new JListRefresh();

	}

}
