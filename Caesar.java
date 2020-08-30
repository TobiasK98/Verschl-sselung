package pis.hue1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasse soll Funktionen der Verschlüsselungsmethode Caesar-Verschlüsselung realisieren.
 * Dabei wird das Interface Codec implementiert und dessen Methoden überschrieben.
 * 
 *
 */
public class Caesar implements Codec
{
	
	/**
	 * Erstellen eines privaten String, da dieser zum Kodieren und Dekodieren benötigt wird.
	 */
	private String schluessel1;
	/**
	 * Überschreiben der Methode kodiere
	 */
	@Override
	public String kodiere(String klartext) 
	{
		/**Erstellen eines StringBuffers um kodierten Text aus einzelnen Buchstaben zusammenzusetzen.
		 * Dieser muss die Länge des klartextes haben.
		 */
		StringBuffer s = new StringBuffer(klartext.length());
		//schluessel1.toLowerCase();

		/**Erste for-schleife durchläuft den Klartext*/ 
		for(int i = 0; i<klartext.length(); i++)
		{
			if(Character.isLetter(klartext.charAt(i)))
			{
					char Buchstabe = klartext.charAt(i);
					if(Buchstabe > 96 && Buchstabe < 123)
					{
						Buchstabe += schluessel1.length();
						if(Buchstabe > 'z')
						{
							Buchstabe -= 26;
						}
						s.append(Buchstabe);
					}
				else if(Buchstabe > 64 && Buchstabe < 91)
				{
					Buchstabe += schluessel1.length();
					if(Buchstabe > 'Z')
					{
						Buchstabe -= 26;
					}
					s.append(Buchstabe);
				}
			}
			else
			{
				s.append(klartext.charAt(i));
			}
		}
		/**@return des kodierten String*/
		return s.toString();  

	}
	/**Überschreiben der Methode dekodiere
	 * Selbes Vorgehen wie beim kodieren, nur das beim dekodieren die Länge des Schlüssels * (-1) genommen wird und wenn
	 * die Verschiebung nach links über den linken Rand des Alphabetes hinausgeht man +26 rechnet um an den rechten Rand des Alphabetes
	 * zu kommen.
	 */
	@Override
	public String dekodiere(String geheimtext)
	{
		StringBuffer s = new StringBuffer(geheimtext.length());
		//schluessel1.toLowerCase();

		for(int i = 0; i<geheimtext.length(); i++)
		{
					if(Character.isLetter(geheimtext.charAt(i)))
					{
							char Buchstabe = geheimtext.charAt(i);
							if(Buchstabe > 96 && Buchstabe < 123)
							{
								Buchstabe -= schluessel1.length();
								if(Buchstabe < 'a')
								{
									Buchstabe += 26;
								}
								s.append(Buchstabe);
							}
						else if(Buchstabe > 64 && Buchstabe < 91)
						{
							Buchstabe -= schluessel1.length();
							if(Buchstabe < 'A')
							{
								Buchstabe += 26;
							}
							s.append(Buchstabe);
						}
					}
					else
					{
						s.append(geheimtext.charAt(i));
					}
			}
				/**@return des kodierten String*/
				return s.toString();
	}
	/**
	 * Überschreiben der Methode gibLosung.
	 * @return String mit schluessel
	 */
	@Override
	public String gibLosung()
	{
		return this.schluessel1;
	}
	/**
	 * Überschreiben der Methode setzeLosung.
	 * Bei Eingabe keines Schlüssels, einer Zahl, einem unerlaubten Zeichen oder eines Leerzeichen wird eine Exception geworfen.
	 * 
	 * @param String mit schluessel
	 */
	@Override
	public void setzeLosung(String schluessel) 
	{
		Pattern p1 = Pattern.compile("[0-9]");
		Pattern p2 = Pattern.compile("[^A-Za-z]");
		Matcher m1 = p1.matcher(schluessel);
		Matcher m2 = p2.matcher(schluessel);
		if(schluessel == "")
		{
			throw new IllegalArgumentException("Kein Schlüssel");
		}
		else if(m1.find())
		{
			throw new IllegalArgumentException("Keine Zahlen eingeben");
		}
		else if(m2.find())
		{
			throw new IllegalArgumentException("unerlaubtes Zeichen");
		}
		else if(schluessel.contains(" "))
		{
			throw new IllegalArgumentException("Keine Leerzeichen");
		}
		this.schluessel1 = schluessel;
		
	}
}
