package pis.hue1;
/**
 * Interface dient als Schnittstelle zwischen der GUI und den Verschlüsselungs-
 * methoden.
 * Somit soll eine Lose Kopplung zwischen der GUI und den Verschlüsselungs-
 * methoden zu schaffen
 */
public interface Codec 
{	/**
	*Der Methode kodiere muss ein String als Parameter übergeben werden.
	*Methode kodiert einen übergebenen Klartext mithilfe einer Losung.
	*Die Methode muss aber auch Zugriff auf die Losungswörter bekommen, da sie diese zum kodieren benötigt.
	*
	*@param klartext
	*@return String mit kodiertem klartext
	*/
		
	public String kodiere(String klartext);
	/**
	 * Der Methode dekodiere muss ein String als Parameter übergeben werden.
	 * Methode dekodiert einen übergeben Klartext mithilfe einer Losung.
	 * Die Methode muss Zugriff auf die Losungswörter bekommen, da sie diese zum dekodieren benötigt.
	 * 
	 * @param geheimtext
	 * @return String mit dekodiertem geheimtext
	 */
	public String dekodiere(String geheimtext);
	/**
	 * Die Methode soll die Losung zurückgeben
	 * 
	 * @return String mit der Losung
	 */
	public String gibLosung();
	/**
	 * Mit der Methode soll die Losung gesetzt werden können und bei einer nicht unterstützten Eingabe
	 * soll eine Exception geworfen werden.
	 * 
	 * @param schluessel
	 * @throws IllegalArgumentException
	 */
	
	public void setzeLosung(String schluessel)throws
	IllegalArgumentException; //bei ungeeignetem Schlüssel
}
