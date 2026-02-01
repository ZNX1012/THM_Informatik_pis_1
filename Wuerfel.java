//package pis.hue1;

import java.util.*;

/**
 * @author S.Ellenschlaeger
 * Verschl�sselung von Texten mithilfe des W�rfelverfahrens.
 * Bei doppelter Verschl�sselung, mit verschiedenen Schl�sseln,
 * ergibt sich das Doppelw�rfel-Verfahren.
 * Implementiert die Schnittstelle Codec.
 */
public class Wuerfel implements Codec{

	/**
	 * Klasseninvariante:
	 * 		a) schluessel != null;
	 * 		b) schluessel darf nur Buchstaben enthalten (�,�,� auch erlaubt).
	 */
	private String losungString;
	private int[] losung; 
	
	public Wuerfel(String losung) {
		this.setzeLosung(losung);
	}
	public Wuerfel() {
		this.setzeLosung("Schwarzwald");
	}
	
	/**
	 * Kodierung des Klartextes mit dem W�rfel-Verfahren.
	 * Alle Zeichen im Klartext erlaubt.
	 */
	@Override
	public String kodiere(String klartext) {

		char[] klartextChar = klartext.toCharArray();
		StringBuilder kodiert = new StringBuilder();

		for(int spalte = 0; spalte < losung.length; spalte++) {
			for(int pos = 0; pos < losung.length; pos++) {
				if(losung[pos] == spalte) {
					int i = 0;
					while((pos + (i * losung.length)) < klartextChar.length) {
						kodiert.append(klartextChar[pos + (i * losung.length)]);
						i++;
					}
				}
			}	
		}
		return kodiert.toString();
	}

	/**
	 * Dekodierung des Geheimtextes mit dem W�rfel.
	 * Alle Zeichen als Geheimtext erlaubt.
	 */
	@Override
	public String dekodiere(String geheimtext) {

		char[] geheimtextChar = geheimtext.toCharArray();
		char[] dekodiert = new char[geheimtext.length()];

		int volleLaenge = geheimtextChar.length / losung.length;
		int rest = geheimtextChar.length % losung.length;

		int index = 0;
		for(int spalte = 0; spalte < losung.length; spalte++) {
			for(int pos = 0; pos < losung.length; pos++) {
				if(losung[pos] == spalte) {

					if(pos < rest) { 		//Eine Zeile mehr als "volleLaenge"
						for(int i = 0; i < (volleLaenge +1); i++) {
							dekodiert[(pos + (i * losung.length))] = geheimtextChar[index];
							index++;
						}
					}

					else if(pos >= rest) { 	//Nur "volleLaenge" an Zeilen
						for(int i = 0; i < volleLaenge; i++) {
							dekodiert[(pos + (i * losung.length))] = geheimtextChar[index];
							index++;
						}
					}
				}
			}
		}
		String ausgabe = new String(dekodiert, 0, dekodiert.length);
		return ausgabe;
	}
	
	@Override
	public String gibLosung() {
		return losungString;
	}

	/**
	 * Ermittelt das char[] Array mit der Zahlenlosung.
	 * Nur Buchstaben als Schluessel erlaubt.
	 */
	@Override
	public void setzeLosung(String schluessel) throws IllegalArgumentException {

		char[] test = schluessel.toCharArray();
		for(int i = 0; i < test.length; i++) {
			if(!Character.isLetter(test[i])) {
				throw new IllegalArgumentException();
			}
		}

		losung = new int[schluessel.length()];
		losungString = schluessel;

		schluessel = schluessel.toUpperCase();
		char[] losungChar = schluessel.toCharArray();
		Arrays.sort(losungChar);

		for(int i = 0; i < schluessel.length(); i++) {

			for(int j = 0; j < losungChar.length; j++) {
				if (schluessel.charAt(i) == losungChar[j]) {
					//Hier wird gepr�ft ob es diesen Buchstaben schon einmal gab, wenn ja wird um 1 erh�ht.
					int mehrfachBuchstabe = 0;
					for(int x = 0; x < i; x++) {
						if (schluessel.charAt(x) == schluessel.charAt(i)) {
							mehrfachBuchstabe++;
						}
					}
					losung[i] = j + mehrfachBuchstabe;
					break;
				}
			}
		}
	}
}
