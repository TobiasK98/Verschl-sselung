package pis.hue1;

import javax.swing.SwingUtilities;


public class Main implements Runnable
{

	public static void main(String[] args) 
	{
		/**
		 * Codec-Objekte zum Verschlüsseln von Text Eingaben in der GUI*/
		 
		Codec wue1 = new Wuerfel();
		Codec wue2 = new Wuerfel();
		Codec cae1 = new Caesar();
		
		SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                CodecGUI gui1 = new CodecGUI(wue1, wue2, cae1);
            }
        });

	
		
	}

}
