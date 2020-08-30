package pis.hue1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasse soll Funktionen der Verschlüsselungsmethode Doppelwuerfel-Verschlüsselung realisieren.
 * Dabei implementiert die Klasse das Interface Codec und überschreibt dessen Methoden
 */

public class Wuerfel implements Codec
{
	/**Erstellen eines String der zum kodieren und dekodieren benötigt wird
	 * Variable schluessel1 darf nicht leer sein, darf keine Leerzeichen enthalten, darf keine Sonderzeichen 
	 * und keine Zahlen enthalten
	 * 
	 */
	private String schluessel1;
	/**
	 * Überschreiben der Methode kodiere
	 */
    public Wuerfel(String losung) 
    {
    	this.schluessel1 = losung;
    }
    public Wuerfel()
    {
    }
	@Override
	public String kodiere(String klartext)
	{
		/**Erstellen eines StringBuffers in und einer for-Schleife um Buchstaben des Klartextes in Kleinbuchstaben umzuwandeln.
		 * Jeder Buchstabe des Klartextes wird in der for-Schleife durchgegangen und in ein Kleinbuchstaben umgewandelt und
		 * dem StrigBuffer angehängt.
		 */
		StringBuffer s1 = new StringBuffer(schluessel1.length());
		for(int l = 0; l < schluessel1.length(); l++)
		{
	        char c = schluessel1.charAt(l);
	        char b;
	        b = Character.toLowerCase(c);
		    s1.append(b);    
	    }
		/**
		 * Erzeugen und Speichern der Permutation von Zahlen des Losungswortes
		 * Jeder Buchstabe des Schluessels wird mit jedem verglichen, wenn er größer ist, wird dessen Zahl erhöht und im Array
		 * gespeichert. Um die relative Ordnung gleicher Buchstaben beizubehalten, wird geprüft, ob dieser weiter vorne oder
		 * weiter hinter steht. Wenn er weiter vorne steht wird dessen Wert erhöht.
		 */
		//schluessel1.toLowerCase();
		int[] Permutation = new int[schluessel1.length()];
        for (int i=0; i<schluessel1.length(); i++) 
        {
            for (int j=0; j<schluessel1.length(); j++) {
                if(s1.charAt(i) > s1.charAt(j)) 
                {
                    Permutation[i]++;
                }
   
                if(s1.charAt(i) == s1.charAt(j) && i > j) //Prüfen ob Buchstabe schon mal vorkam, wenn ja Zahl erhöhen
                {
                    Permutation[i]++;
                }
            }
        }    
        /**Erstellen eines StringBuffers um Kodierten Text aus einzelnen Buchstaben zusammenzustetzen*/
        StringBuffer s = new StringBuffer(klartext.length());
        /**Erstellen eines Int um die Position des Buchstaben im klartext zu speichern*/
        int Zahl;
        /**
         * In den ersten beiden for-Schleifen wir die richtige Spalte ausgewählt
         * In der dritten for-Schleife wird die Reihe ausgewählt in der sich der zu anhängende
         * Buchstabe befindet.
         */
        for(int i = 0; i < schluessel1.length(); i++)
        {
        	for(int h = 0; h < schluessel1.length(); h++)
        	{
        		for(int k = 0; k <= (klartext.length()/schluessel1.length()); k++ )
        		{
        			/**Position der ersten, zweiten.... Zahl(Spalte) innerhalb der Permutation finden*/
        			if(i == Permutation[h])
        			{
        				/**Zahlen der jeweiligen Spalte an den StringBuffer anhängen
        				 * Aber nur wenn sich der Buchstabe innerhalb der Länge des Klartextes befindet, um in der letzten Reihe 
        				 * leere Spalten abzuschneiden
        				 */
        				Zahl = k * schluessel1.length() + h;
        				if(Zahl < klartext.length())
        				{
        					s.append(klartext.charAt(Zahl));
        				}
        			}
        		}
        	}
        }
        /**@return String mit kodiertem text*/
        return s.toString();
        
	}
	/**
	 * Überschreiben der dekodieren Methode
	 * Im Grunde selbes Vorgehen wie beim dekodieren
	 */
	@Override
	public String dekodiere(String geheimtext)
	{
		StringBuffer s1 = new StringBuffer(schluessel1.length());
		for(int l = 0; l < schluessel1.length(); l++)
		{
	        char c = schluessel1.charAt(l);
	        char b;
	        b = Character.toLowerCase(c);
		    s1.append(b);    
	    }
		//schluessel1.toLowerCase();
		//char[] chars = schluessel1.toCharArray();
		//Arrays.sort(chars);
		int[] Permutation = new int[schluessel1.length()];
        for (int i=0; i<schluessel1.length(); i++) 
        {
            for (int j=0; j<schluessel1.length(); j++) 
            {
                if(s1.charAt(i) > s1.charAt(j)) 
                {
                    Permutation[i]++;
                }
   
                if(s1.charAt(i) == s1.charAt(j) && i > j) //Prüfen ob Buchstabe schon mal vorkam, wenn ja Zahl erhöhen
                {
                    Permutation[i]++;
                }
            }
        }
        /**
         * Erstellen der Inversen der Permutaton, da dies die Vertauschung der Spalten rückgängig macht
         */
        int[] InversePerm = new int[schluessel1.length()];
        for(int k = 0; k < schluessel1.length(); k++)
        {
        	InversePerm[Permutation[k]] = k;
        }
        
        StringBuffer s = new StringBuffer(geheimtext.length());
        int Zahl;
        /**Erstellen eines Int um den geheimtext durchzugehen*/
        int Stelle = 0;
        /**Erstellen eines Array um Buchstabe an einer Position, nämlich der Postion des dekodierten Text, zu speichern*/
        char[] Text = new char[geheimtext.length()];
        /**
         * Erste for-Schleife geht die Spalten durch, Zweite for-Schleife geht die Zeilen durch. 
         */
        for(int l = 0; l < schluessel1.length(); l++)
        {
        	for(int m = 0; m <= (geheimtext.length()/schluessel1.length()); m++ )
        		{
        				/**
        				 * Position des Buchstaben im dekodierten Text herausfinden und Buchstabe an dieser Position in unserem
        				 * Charater-Array speichern. Quasi ein zusammensetzen meines dekodiertem Text in einem Array
        				 */
        				Zahl = m * schluessel1.length() + InversePerm[l];
        				/**Prüfen ob Zahl sich innerhalb der Länge des Geheimtextes befindet um in der letzten
        				 * Zeile leere Spalten abzuschneiden
        				 */
        				if(Zahl<geheimtext.length())
        				{
        					Text[Zahl] = geheimtext.charAt(Stelle);
        					Stelle++;
        				}

        	}
        }
        /**
         * Mit einer for-Schleife, die im Array gespeicherten Buchstaben an meinen StringBuffer nacheinander anhängen und
         * dekodierten String zurückgeben
         */
        for(int u = 0; u < geheimtext.length(); u++)
        {
        	s.append(Text[u]);
        }
        return s.toString();

	}
	/**
	 * Überschreiben der Methode gibLosung
	 * @return String mit schluessel
	 */
	@Override
	public String gibLosung()
	{
		return this.schluessel1; 
		//return this.schluessel2;
	/**
	 * Überschreiben der Methode setzeLosung
	 * Bei Eingabe keines Schlüssels, einer Zahl, einem unerlaubten Zeichen oder eines Leerzeichen wird eine Exception geworfen.
	 * 
	 * @param String mit schluessel
	 */
	}
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
