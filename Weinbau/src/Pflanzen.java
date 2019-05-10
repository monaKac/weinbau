/**
 * 
 * @author marcogoette
 *
 */
public class Pflanzen {
	private double zuckergehalt; // in g
	private double groesse;   // in m
	private boolean krankheit;
	private int id; 
	private static int nextId = 1; 
	
	/**
	 * Konstruktor der eine Pflanze mit übergebenen Parametern erzeugt und eine ID vergibt
	 * @param zuckergehalt
	 * @param groesse
	 * @param krank
	 */
	public Pflanzen(double zuckergehalt, double groesse, boolean krank) {
		this.zuckergehalt = zuckergehalt;
		this.groesse = groesse; 
		this.krankheit = krank; 
		this.id = nextId;
		nextId++; 
	}
	
	/**
	 * Gibt Zuckergehalt der Pflanze zurück
	 * @return
	 */
	public double getZuckergehalt() {
		return zuckergehalt;
	}
	
	/**
	 * Setzt den Zuckergehalt auf den übergebenen double Wert
	 * @param zuckergehalt
	 */
	public void setZuckergehalt(double zuckergehalt) {
		this.zuckergehalt = zuckergehalt;
	}
	
	/**
	 * Gibt die Größe der Pflanze zurück
	 * @return
	 */
	public double getGroesse() {
		return groesse;
	}
	
	/**
	 * Setzt die Größe der Pflanze auf den übergebenen double Wert
	 * @param groesse
	 */
	public void setGroesse(double groesse) {
		this.groesse = groesse;
	}
	
	/**
	 * Liefert falls Pflanze krank ist den boolean Wert true zurück
	 * @return
	 */
	public boolean isKrank() {
		return krankheit;
	}
	
	/**
	 * Setzt die Variable krankheit auf den übergebenen boolean Wert
	 * @param krankheit
	 */
	public void setKrank(boolean krankheit) {
		this.krankheit = krankheit;
	}
	
	/**
	 * Prüfung ob die übergebene Pflanze identisch zur erzeugt ist
	 */
	@Override
	public boolean equals(Object object) {
		if(object.getClass()!= Pflanzen.class) {
			return false;
		}
		Pflanzen pflanze = (Pflanzen)object; 
		if(this.id == pflanze.id) {
			return true; 
		}else {
		return false; 
		}
	}
}
