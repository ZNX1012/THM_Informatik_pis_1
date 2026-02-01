//package pis.hue1;

/**
 * Bildet die Schnittstellendefinition f�r alle Verschl�sselungsverfahren.
 * Alle Verschl�sselungen sind gezwunden diese Methoden zu implementieren.
 */
public interface Codec {

	/**
	 * @param klartext, String mit dem Klartext, welcher verschl�sselt werden soll.
	 * Verschl�sselt den Klartext mit dem entsprechenden Verschl�sselungsverfahren.
	 * @return Gibt den Geheimtext als String zur�ck.
	 */
	public String kodiere(String klartext);
	
	/** 
	 * @param geheimtext, String mit dem Geheimtext, welcher entschl�sselt werden soll.
	 * Entschl�sselt den Geheimtext mit dem entsprechenden Verschl�sselungsverfahren.
	 * @return Gibt den Klartext als String zur�ck.
	 */
	public String dekodiere(String geheimtext);
	
	/**
	 * Soll dem benutzer sagen, welches Schl�sselwort im Moment benutzt wird.
	 * @return losung, String mit dem, im Moment gesetzten, Schl�sselwort.
	 */
	public String gibLosung();
	
	/**
	 * @param schluessel, String mit dem Schl�sselwort
	 * Benutzt ein Schl�sselwort um damit eine Losung zu ermitteln.
	 * @throws IllegalArgumentException, Ausnahmebehandlung: wirft eine IllegalArgumentException, 
	 * 										falls das Schl�sselwort so nicht erlaubt ist.
	 */
	public void setzeLosung(String schluessel)throws IllegalArgumentException;
	
}
