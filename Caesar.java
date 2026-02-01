//package pis.hue1;

/**
 * @author S.Ellenschlaeger
 * Verschl�sselung von Texten mithilfe des C�ser-Verfahrens.
 * Implementiert die Schnittstelle Codec.
 */
public class Caesar implements Codec {

	/**
	 * Klasseninvariante:
	 * 		a) schluessel != null;
	 * 		b) schluessel maximal 25 Zeichen lang
	 */
	private String losungString;
	private int losung;
	
	/**
	 * Kodierung des Klartextes mit C�ser.
	 * Nur Buchstaben werden kodiert, alle Sonderzeichen und andere Symbole bleiben erhalten.
	 */
	@Override
	public String kodiere(String klartext) {
		
		StringBuilder kodiert = new StringBuilder();
		char[] unkodiertKlar = klartext.toCharArray();

		for(int i = 0; i < unkodiertKlar.length; i++) {
			char buffer = unkodiertKlar[i];
			if(Character.isUpperCase(buffer)) {
				buffer = (char)(buffer + losung);
				if(buffer > 'Z') {
					buffer = (char)(buffer - 26);
				}
				kodiert.append(buffer);
			}
			else if(Character.isLowerCase(buffer)) {
				buffer = (char)(buffer + losung);
				if(buffer > 'z') {
					buffer = (char)(buffer - 26);
				}
				kodiert.append(buffer);
			}
			else {
				kodiert.append(buffer);
			}
		}
		
		return kodiert.toString();
	}
	
	/**
	 * Dekodierung des Geheimtextes mit C�ser.
	 * Nur Buchstaben werden dekodiert, alle Sonderzeichen und andere Symbole bleiben erhalten.
	 */
	@Override
	public String dekodiere(String geheimtext) {
		
		StringBuilder dekodiert = new StringBuilder();
		char[] kodiertGeheim = geheimtext.toCharArray();

		for(int i = 0; i < kodiertGeheim.length; i++) {
			char buffer = kodiertGeheim[i];
			if(Character.isUpperCase(buffer)) {
				buffer = (char)(buffer - losung);
				if(buffer < 'A') {
					buffer = (char)(buffer + 26);
				}
				dekodiert.append(buffer);
			}
			else if(Character.isLowerCase(buffer)) {
				buffer = (char)(buffer - losung);
				if(buffer < 'a') {
					buffer = (char)(buffer + 26);
				}
				dekodiert.append(buffer);
			}
			else {
				dekodiert.append(buffer);
			}
		}
		
		return dekodiert.toString();
	}
	
	@Override
	public String gibLosung() {
		return losungString;
	}
	
	/**
	 * Ermittelt die Zahl f�r die Losung anhand des Schl�sselwortes.
	 * Maximal 25 Zeichen f�r das Schl�sselwort erlaubt.
	 */
	@Override
	public void setzeLosung(String schluessel) throws IllegalArgumentException {

		char[] test = schluessel.toCharArray();
		if(test.length > 25) {
			throw new IllegalArgumentException();
		}

		losungString = schluessel;
		losung = schluessel.length();
	}
}
