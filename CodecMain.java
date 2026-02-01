//package pis.hue1;

import javax.swing.SwingUtilities;

public class CodecMain {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Codec c1 = new Caesar();
				Codec c2 = new Wuerfel();
				new CodecGUI(c1, c2);
			}
		});

		/*
		//Test aus der Aufgabenstellung
		Wuerfel w = new Wuerfel("Programmierung");
		String s1 =w.dekodiere("stfbetidneanuuunesihlehgwnerntzlteitrrgdewzreukhsueijohecibesuenhmtiendheaaeltnslonacdilmibuzmvcgnszesrigevennge");
		w.setzeLosung("Vergnuegen");
		System.out.println(w.dekodiere(s1));
		 */
		
	}
}
