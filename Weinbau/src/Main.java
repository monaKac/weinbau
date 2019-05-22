/**
 * 
 * @author marcogoette
 * Main Klasse der Anwendung hier befindet sich die main Methode und startet das Programm
 */
public class Main {

	/**
	 * Main Methode der Anwendung
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
