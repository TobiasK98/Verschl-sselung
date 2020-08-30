package pis.hue1;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class CodecGUI extends JFrame
{	
	/**GUI Komponenten meiner Klasse CodecGUI bekannt machen*/
	JTextArea klartext;											
	JTextArea geheimtext;
	JButton dekodiere;
	JButton kodiere;
	JTextArea schluessel1;
	JTextArea schluessel2;
	JComboBox Decr;
	JButton exit;
	JDialog falscheEingabe;
	private Codec _c1, _c2, _c3;			
	/**
	 * Erstellung von vier Codec-Objekten, zwei für jede Verschlüsseluns-
	 * methode. Diese vier Codec-Objekte dem Konstruktor übergeben, damit
	 * meine Klasse Main auf zwei Wuerfel und zwei Caesar Objekte zugreifen kann	
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 */
	public CodecGUI(Codec c1, Codec c2, Codec c3)		 
	{
		this._c1 = c1;
		this._c2 = c2;
		this._c3 = c3;
		
		/**GUI-Komponenten erstellen und mit einem BorderLayout anordnen*/
		JFrame frame = new JFrame("Programm zur Verschlüsselung und Entschlüsselung");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,750);
		frame.setVisible(true);
		
		/**Erstellen meiner Buttons zum kodieren und dekodieren und hinzufügen derer Funktionen*/
		JPanel panel = new JPanel();
		dekodiere = new JButton("dekodiere");
		kodiere = new JButton("kodiere");
		kodiere.addActionListener( new kodierList());
		dekodiere.addActionListener(new dekodierList());
		panel.add(kodiere);
		panel.add(dekodiere);
		
		/**Erstellen der TextAreas zum eingeben des Klartextes und Geheimtextes*/
		JPanel panel1 = new JPanel();
		klartext = new JTextArea(100,30);
		geheimtext = new JTextArea(100,30);
		geheimtext.setLineWrap(true);
		geheimtext.setWrapStyleWord(true);
		klartext.setLineWrap(true);
		klartext.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(klartext);
		JScrollPane scroll2 = new JScrollPane(geheimtext);
		scroll.setSize(300,300);
		scroll2.setSize(300,300);
		panel1.add(BorderLayout.EAST, klartext);
		panel1.add(BorderLayout.WEST, geheimtext);
		
		/**Erstellen zweier TextAreas zum eingeben der Losungen*/
		JPanel panel2 = new JPanel();
		schluessel1 = new JTextArea(2, 30);
		schluessel2 = new JTextArea(2, 30);
		panel2.add(schluessel1);
		panel2.add(schluessel2);
		
		/**Erstellen einer ComboBox zum wählen der Verschlüsselungsmethoden*/
		JPanel panel3 = new JPanel();
		String[] DecrMethods = {"Wuerfel", "Caesar"};
		Decr = new JComboBox(DecrMethods);
		panel3.add(Decr);	
		
		/**Erstellen eines Buttons zum beenden des Programms*/
		exit = new JButton("Beenden");
		exit.addActionListener(new Exit());
		panel3.add(exit);
		
		//Adding JDialog for Exceptions
		/*falscheEingabe = new JDialog();
		falscheEingabe.setTitle("Falsche Eingabe");
		falscheEingabe.setSize(200, 200);
		falscheEingabe.setVisible(true);*/
		
		/**Alle Komponenten dem Frame hinzufügen und per BorderLayout anordnen*/
		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.getContentPane().add(BorderLayout.CENTER, panel1);
		frame.getContentPane().add(BorderLayout.NORTH, panel2);
		frame.getContentPane().add(BorderLayout.SOUTH, panel3);
		frame.setVisible(true);
	}
	
	/**
	 * Erstellen einer inneren Klasse für meinen Button zum Kodieren.
	 * Implementieren der Methode actionPerformed:
	 * In meiner JComboBox-Komponente prüfen ob das Element Wuerfel oder Caesar ausgewählt ist.
	 * Wenn Wuerfel ausgewählt ist über meine Codec-Objekte _c1 und _c2 die Losung setzen und 
	 * kodieren.
	 * Wenn Caesar ausgewählt isr über meine Codec-Objekte _c3 und _c4 die Losung setzen und 
	 * kodieren.
	 *
	 */
	public class kodierList implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String klarText = klartext.getText();
			if (Decr.getSelectedItem()=="Wuerfel") 
			{
				try 
				{
					/**setzen meiner Losungswörter*/
					_c1.setzeLosung(schluessel1.getText());
					_c2.setzeLosung(schluessel2.getText());
				} 
				/**
				 * catchen meiner IllegalArgumentExceptions
				 */
				catch (IllegalArgumentException ex) 
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				/**kodieren meines Klartextes mit meines beiden Losungswörtern*/
				geheimtext.setText(_c2.kodiere(_c1.kodiere(klarText)));
	
			} 
			else if(Decr.getSelectedItem()=="Caesar")
				{
				try 
				{
						_c3.setzeLosung(schluessel1.getText());
				} 
				catch (IllegalArgumentException ex) 
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
					geheimtext.setText(_c3.kodiere(klarText));
	
				}
			}
	/**
	 * Dasselbe wie für den Button zum kodieren nur mit dem Button zum dekodieren.
	 * Hierbei wird dann mit der Methode dekodieren in umgekehrter Reihenfolge(wie zum kodieren) auf die
	 * Codec-Objekte zugegriffen
	 */
	}
	public class dekodierList implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String geheimText = geheimtext.getText();
			if (Decr.getSelectedItem()=="Wuerfel") 
			{
				try 
				{
					_c1.setzeLosung(schluessel1.getText());
					_c2.setzeLosung(schluessel2.getText());
				} 
				catch (IllegalArgumentException ex) 
				{	
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				klartext.setText(_c1.dekodiere(_c2.dekodiere(geheimText)));
	
				} 
			else if(Decr.getSelectedItem() == "Caesar")
			{
				try 
				{
					_c3.setzeLosung(schluessel1.getText());
					//_c4.setzeLosung(schluessel2.getText());
				} 
				catch (IllegalArgumentException ex) 
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				klartext.setText(_c3.dekodiere(geheimText));
	
			}
		}
	}
	/**
	 *Hinzufügen der Funktion zum beenden des Programms
	 */
	public class Exit implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	
	}

}
