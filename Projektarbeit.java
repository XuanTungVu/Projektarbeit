
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Projektarbeit {

	private JFrame frame;
	private JTextField txtPreis;
	private JTextField txtName;
	private JTextField txtZurück;
	private JCheckBox chckbxNeu;
	private double geldzurück;
	private JTextField txtBar;
	private JComboBox comboBox_Genre;
	private ArrayList<Buchdaten> Buchliste = new ArrayList<Buchdaten>();
	private JTextField txtBarcode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Projektarbeit window = new Projektarbeit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Projektarbeit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 515, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JButton btnNewButton_1 = new JButton("Berechnen");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 216, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, -100, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton_1);

//Speichern-Button

		JButton btnNewButton = new JButton("Speichern");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 304, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 81, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, frame.getContentPane());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				berechnenBuecher();
				dasbuchistneu();
				String str_res = String.valueOf(zurückGeld());
				txtZurück.setText(str_res);

//Herrausgelesene Texte aus GUI
				String str_Name = txtName.getText();
				String str_Preis = txtPreis.getText();
				String str_Bar = txtBar.getText();
				String str_Zurück = txtZurück.getText();
				String str_Barcode = txtBarcode.getText();
				String str_Genre = (String) comboBox_Genre.getSelectedItem();;

//Umwandlung von String zu Double	

				double preis = Double.parseDouble(str_Preis);
				double bar = Double.parseDouble(str_Bar);
				double zurück = Double.parseDouble(str_Zurück);
				int barcode = Integer.parseInt(str_Barcode);
				String name = str_Name;
				String genre = str_Genre;

//ArrayListe
				Buchdaten bd = new Buchdaten(name, preis, bar, zurück, barcode, genre);
				initObjekt();
				Buchliste.add(bd);

				System.out.println("Name des Buches: " + name + " \nPreis des Buches: " + preis + "\nBargeld gegeben: "
						+ bar + "\nGeld zurück: " + zurück + "\nGenre: " + genre + "\nBarcode: " + barcode);
			}

		});
		frame.getContentPane().add(btnNewButton);

		txtPreis = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtPreis, 222, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtPreis, -260, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(txtPreis);
		txtPreis.setColumns(10);

		chckbxNeu = new JCheckBox("Das Buch ist neu");
		springLayout.putConstraint(SpringLayout.SOUTH, chckbxNeu, -6, SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, chckbxNeu, -4, SpringLayout.EAST, btnNewButton);
		frame.getContentPane().add(chckbxNeu);

//Berechnen-Button

		JButton btnNewButton_2 = new JButton("Berechnen");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_2, 304, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -76, SpringLayout.WEST, btnNewButton_2);
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 43, SpringLayout.SOUTH, btnNewButton_2);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_2, 277, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_2, -77, SpringLayout.EAST, frame.getContentPane());

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				berechnenBuecher();
				try {
					dasbuchistneu();
					zurückGeld();
					String str_res = String.valueOf(zurückGeld());
					txtZurück.setText(str_res);
				} catch (Exception e1) {
					txtZurück.setText("Fehler");
					txtBar.setText("Fehler");
					JOptionPane.showMessageDialog(null, "Bitte nur gültige Werte", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frame.getContentPane().add(btnNewButton_2);

		JLabel lblNamedesBuches = new JLabel("Name des Buches");
		springLayout.putConstraint(SpringLayout.WEST, chckbxNeu, 0, SpringLayout.WEST, lblNamedesBuches);
		springLayout.putConstraint(SpringLayout.WEST, lblNamedesBuches, 44, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNamedesBuches);

		txtName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPreis, 19, SpringLayout.SOUTH, txtName);
		springLayout.putConstraint(SpringLayout.EAST, txtPreis, 0, SpringLayout.EAST, txtName);
		springLayout.putConstraint(SpringLayout.WEST, txtName, 77, SpringLayout.EAST, lblNamedesBuches);
		springLayout.putConstraint(SpringLayout.EAST, txtName, -37, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblNamedesBuches, 4, SpringLayout.NORTH, txtName);
		springLayout.putConstraint(SpringLayout.NORTH, txtName, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtName, 35, SpringLayout.NORTH, frame.getContentPane());
		txtName.setColumns(10);
		frame.getContentPane().add(txtName);

		JLabel lblPreisDesBuches = new JLabel("Preis des Buches");
		springLayout.putConstraint(SpringLayout.NORTH, lblPreisDesBuches, 28, SpringLayout.SOUTH, lblNamedesBuches);
		springLayout.putConstraint(SpringLayout.WEST, lblPreisDesBuches, 0, SpringLayout.WEST, lblNamedesBuches);
		frame.getContentPane().add(lblPreisDesBuches);

		txtZurück = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, txtZurück, -169, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtZurück, -37, SpringLayout.EAST, frame.getContentPane());
		txtZurück.setColumns(10);
		frame.getContentPane().add(txtZurück);

		JLabel lblGeldzuzahlen = new JLabel("R\u00FCckgeld");
		springLayout.putConstraint(SpringLayout.WEST, lblGeldzuzahlen, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtZurück, 127, SpringLayout.EAST, lblGeldzuzahlen);
		springLayout.putConstraint(SpringLayout.NORTH, lblGeldzuzahlen, 6, SpringLayout.NORTH, txtZurück);
		frame.getContentPane().add(lblGeldzuzahlen);

		txtBar = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtZurück, 18, SpringLayout.SOUTH, txtBar);
		springLayout.putConstraint(SpringLayout.NORTH, txtBar, 19, SpringLayout.SOUTH, txtPreis);
		springLayout.putConstraint(SpringLayout.WEST, txtBar, 222, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtBar, -216, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtBar, 0, SpringLayout.EAST, txtPreis);
		txtBar.setColumns(10);
		frame.getContentPane().add(txtBar);

		JLabel lblNewLabel = new JLabel("Bargeld gegeben");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 4, SpringLayout.NORTH, txtBar);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 44, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);
		
		comboBox_Genre = new JComboBox();
		springLayout.putConstraint(SpringLayout.WEST, comboBox_Genre, 12, SpringLayout.WEST, btnNewButton_1);
		springLayout.putConstraint(SpringLayout.EAST, comboBox_Genre, 0, SpringLayout.EAST, txtPreis);
		comboBox_Genre.setModel(new DefaultComboBoxModel(new String[] {"", "Familienroman", "Fantasy", "Gesellschaftsroman", "Historischer Roman", "Horror", "Humor", "Krimi", "Liebesroman", "Science Fiction", "Steampunk", "Thriller", "Biografie", "Fachbuch", "Ratgeber", "Sachbuch"}));
		frame.getContentPane().add(comboBox_Genre);
		
		JLabel lblNewLabel_1 = new JLabel("Genre");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 3, SpringLayout.NORTH, comboBox_Genre);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, chckbxNeu);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtBarcode = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, comboBox_Genre, 18, SpringLayout.SOUTH, txtBarcode);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox_Genre, 40, SpringLayout.SOUTH, txtBarcode);
		springLayout.putConstraint(SpringLayout.NORTH, txtBarcode, 21, SpringLayout.SOUTH, txtZurück);
		springLayout.putConstraint(SpringLayout.SOUTH, txtBarcode, -120, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtBarcode, -1, SpringLayout.WEST, txtPreis);
		springLayout.putConstraint(SpringLayout.EAST, txtBarcode, -37, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Barcode");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, txtBarcode);
		frame.getContentPane().add(lblNewLabel_2);
	}

	public void berechnenBuecher() {
		try {

//Herrausgelesene Texte aus GUI

			String str_Preis = txtPreis.getText();
			String str_Bar = txtBar.getText();

//Umwandlung von String zu Double

			double preis = Double.parseDouble(str_Preis);
			double bar = Double.parseDouble(str_Bar);

			geldzurück = bar - preis;
			if (geldzurück >= 0) {
				System.out.println("Zurück zahlen: " + geldzurück);
			}
		} catch (Exception e1) {
			txtZurück.setText("Fehler");
			txtBar.setText("Fehler");
			JOptionPane.showMessageDialog(null, "Bitte nur gültige Werte", null, JOptionPane.ERROR_MESSAGE);

		}

	}

//Checkbox Das Buch ist neu
	public void dasbuchistneu() {
		boolean wert = chckbxNeu.isSelected();
		if (chckbxNeu.isSelected()) {
			System.out.println("Das Buch ist neu\n\n");
		} else {
			System.out.println("Das Buch ist nicht neu\n\n");

		}
	}

	private double zurückGeld() {
		return geldzurück;
	}

//Objekt list
	public void initObjekt() {
		Buchdaten bd1 = new Buchdaten("Kiddo", 20.0, 50.0, 30.0, 12345, "Humor");
		Buchdaten bd2 = new Buchdaten("Baby", 25.0, 50.0, 25.0, 23456, "Krimi");
		Buchdaten bd3 = new Buchdaten("JOJO", 40.0, 40.0, 0.0, 34567, "Thriller");
	}
}
