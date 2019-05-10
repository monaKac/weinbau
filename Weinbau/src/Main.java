/**
 * 
 * @author marcogoette
 *
 */
public class Main {

	/**
	 * Test-Klasse der Anwendung
	 * Hier werden 2 Datenbank für Winzer und Wetter initialisiert, die GUI-Klasse initialisiert und ein Objekt der Klasse Feldarbeiter erzeugt
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Datenbank initialisieren 
		Datenbank.initialisiereWinzer(); 
		Datenbank.initialisiereWetter();
		//GUI initialisieren
		GUI.initialisiere(Datenbank.getWinzer());
		
		Feldarbeiter feldarbeiterAnwendung = new Feldarbeiter(Datenbank.getWinzer()); 
	}

}
