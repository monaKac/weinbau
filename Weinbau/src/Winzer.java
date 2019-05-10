import java.util.ArrayList;

/**
 * 
 * @author marcogoette
 *
 */

public class Winzer {

	private static int nextId = 1;
	private int id;
	private String vorname;
	private String nachname;
	private ArrayList<Weinberg> weinberge;

	/**
	 * Wird der Konstruktor ohne Parameter aufgerufen, wird der Winzer Max
	 * Musterwinzer erzeugt
	 */
	public Winzer() {
		this("Max", "Musterwinzer");
	}

	/**
	 * 
	 * @param vorname
	 * @param nachname
	 *            Überschriebener Konstruktor, der einen Winzer mit den übergebenen
	 *            Daten erzeugt
	 */
	public Winzer(String vorname, String nachname) {
		// set id this.id = ;

		this.id = nextId;
		nextId++;
		this.vorname = vorname;
		this.nachname = nachname;
		weinberge = new ArrayList<>();
	}

	/**
	 * 
	 * @return Die Methode gibt den erzeugten Weinberg zurück
	 */
	public ArrayList<Weinberg> getWeinberge() {
		return weinberge;
	}

	/**
	 * 
	 * @param weinberg
	 *            Der übergebene Weinberg wird zur Liste hinzugefügt
	 */
	public void addWeinberg(Weinberg weinberg) {
		for (Weinberg w : this.weinberge) {
			if (w.equals(weinberg)) {
				throw new RuntimeException("Weinberg existiert schon in Liste");
			}
		}
		weinberge.add(weinberg);
	}

	/**
	 * 
	 * @param weinberg
	 *            Hier kann der übergebene Weinberg aus der Liste entfernt werden
	 */
	public void removeWeinberg(Weinberg weinberg) {
		weinberge.remove(weinberg);
	}

	/**
	 * 
	 * @return Der Nachname des Winzer-Objekts wird zurückgegeben
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * 
	 * @return Der Vorname des Winzer-Objekts wird zurückgegeben
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * 
	 * @return Die ID des Winzer-Objekts wird zurückgegeben
	 */
	public int getId() {
		return id;
	}
}
