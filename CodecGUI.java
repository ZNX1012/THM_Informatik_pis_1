//package pis.hue1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author S.Ellenschlaeger
 * Graphische Oberfl�che f�r Codec Schnittstelle.
 * Auswahlm�glichkeit zwischen W�rfel-, und C�serverfahren.
 * Kodierung und Dekodierung mit ver�nderbarem Schl�sselworten.
 */
public class CodecGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Codec caes, wuer;
	
	private JFrame c;
	private JPanel center, top;
	private JTextArea klartext, geheimtext;
	private JTextField caesarSchluessel, wuerfelSchluessel1, wuerfelSchluessel2;
	private JButton kodieren, dekodieren;
	private JButton beenden;
	private JRadioButton caesar, wuerfel;
	
	/**
	 * Konstruktor der GUI
	 * Erstellt das Grundkonstrukt der GUI,
	 * und meldet alle Buttons beim ActionListener an. 
	 */
	public CodecGUI(Codec c1, Codec c2) {

		caes = c1;
		wuer = c2;
		
		c = new JFrame("Textverschl�sselung");
		c.setSize(1200, 500);
		c.setVisible(true);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(new BorderLayout());
		
		center = new JPanel();
		center.setLayout(new BorderLayout());
		c.add(center, BorderLayout.CENTER);
		
		top = new JPanel();
		top.setLayout(new FlowLayout());
		c.add(top, BorderLayout.NORTH);
		
		//AUSWAHLBUTTON + SCHL�SSEL
		ButtonGroup group = new ButtonGroup();
		caesar = new JRadioButton("C�sar");
		group.add(caesar);
		wuerfel = new JRadioButton("W�rfel");
		group.add(wuerfel);
		caesarSchluessel = new JTextField("Schl�ssel C�ser", 15);
		wuerfelSchluessel1 = new JTextField("Schl�ssel W�rfel 1", 15);
		wuerfelSchluessel2 = new JTextField("Schl�ssel W�rfel 2", 15); 
		top.add(caesar);
		top.add(caesarSchluessel);
		top.add(wuerfel);
		top.add(wuerfelSchluessel1);
		top.add(wuerfelSchluessel2);
		
		//KLARTEXT + GEHEIMTEXT
		klartext = new JTextArea("KLARTEXT", 20, 45);
		c.add(klartext, BorderLayout.WEST);
		geheimtext = new JTextArea("GEHEIMTEXT", 20, 45);
		c.add(geheimtext, BorderLayout.EAST);
		
		//BUTTONS Kodieren + Dekodieren
		kodieren = new JButton(">> kodieren >>");
		dekodieren = new JButton("<< dekodieren <<");
		kodieren.addActionListener(this);
		dekodieren.addActionListener(this);
		center.add(kodieren, BorderLayout.NORTH);
		center.add(dekodieren, BorderLayout.SOUTH);
		
		//BEENDEN
		beenden = new JButton("Beenden");
		beenden.addActionListener(this);
		c.add(beenden, BorderLayout.SOUTH);
	}

	/**
	 * Legt fest was beim benutzen der Buttons passiert.
	 * Beenden, Auswahl zwischen C�ser/W�rfel,
	 * Kodieren und Dekodieren.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//BEENDEN
		if(e.getSource() == beenden) {
			System.exit(EXIT_ON_CLOSE);
		}	

		//KODIEREN
		else if(e.getSource() == kodieren) {
			if(caesar.isSelected()) {
				String s = caesarSchluessel.getText();
				try {
					caes.setzeLosung(s);
				}catch(IllegalArgumentException i) {
					JFrame fehler = new JFrame();
					JOptionPane.showMessageDialog(fehler, "Schluessel darf nur 25 Zeichen lang sein", "Fehler", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String klar = klartext.getText();
				geheimtext.setText(caes.kodiere(klar));
			}
			if(wuerfel.isSelected()) {
				String s1 = wuerfelSchluessel1.getText();
				try {
					wuer.setzeLosung(s1);
				}catch(IllegalArgumentException i) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Nur Buchstaben als Losung erlaubt", "Fehler", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String klar = klartext.getText();
				String einfachKodiert = wuer.kodiere(klar);
				String s2 = wuerfelSchluessel2.getText();
				try {
					wuer.setzeLosung(s2);
				}catch(IllegalArgumentException i) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Nur Buchstaben als Losung erlaubt", "Fehler", JOptionPane.WARNING_MESSAGE);
					return;
				}
				geheimtext.setText(wuer.kodiere(einfachKodiert));
			}
		}

		//DEKODIEREN
		else if(e.getSource() == dekodieren) {
			if(caesar.isSelected()) {
				String s = caesarSchluessel.getText();
				try {
					caes.setzeLosung(s);
				}catch(IllegalArgumentException i) {
					JFrame fehler = new JFrame();
					JOptionPane.showMessageDialog(fehler, "Schluessel darf nur 25 Zeichen lang sein", "Fehler", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String geheim = geheimtext.getText();
				klartext.setText(caes.dekodiere(geheim));
			}
			if(wuerfel.isSelected()) {
				String s2 = wuerfelSchluessel2.getText();
				try {
					wuer.setzeLosung(s2);
				}catch(IllegalArgumentException i) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Nur Buchstaben als Losung erlaubt", "Fehler", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String geheim = geheimtext.getText();
				String einfachDekodiert = wuer.dekodiere(geheim);
				String s1 = wuerfelSchluessel1.getText();
				try {
					wuer.setzeLosung(s1);
				}catch(IllegalArgumentException i) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Nur Buchstaben als Losung erlaubt", "Fehler", JOptionPane.WARNING_MESSAGE);
					return;
				}
				klartext.setText(wuer.dekodiere(einfachDekodiert));
			}
		}

	}	
}
