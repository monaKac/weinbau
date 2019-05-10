/**
 * 
 * @author marcogoette
 *
 */
public class Status {
	double prozent;
	Weinbergstatus status; 
	
	/**
	 * Konstruktor der ein Status-Objekt mit WINTERRUHE und 0 prozent erzeugt
	 */
	public Status() {
		this.status = Weinbergstatus.WINTERRUHE; 
		this.prozent = 0.0; 
	}
	
	/**
	 * überschriebener Konstruktor der einen Status mit dem übergebenen Status und 0 prozent erzeugt
	 * @param status
	 */
	public Status(Weinbergstatus status) {
		this.status = status; 
		this.prozent = 0.0; 
	}
	
	/**
	 * überschriebener Konstruktor der einen Status mit einem übergebenen Status und Prozentwert erzeugt
	 * @param status
	 * @param prozent
	 */
	public Status(Weinbergstatus status, double prozent) {
		this.status = status; 
		this.prozent = prozent; 
	}
	
	/**
	 * Wert der Variablen prozent wird verändert
	 * @param prozent
	 */
	public void setProzent(double prozent) {
		this.prozent = prozent; 
	}
	
	/**
	 * Setzten eines anderen Status sowie Prozentwert
	 * @param status
	 * @param prozent
	 */
	public void setStatus(Weinbergstatus status, double prozent) {
		this.status = status; 
		this.prozent = prozent; 
	}
	
	/**
	 * Setzt Status auf den übergebenen Status und prozent auf 0
	 * @param status
	 */
	public void setStatus(Weinbergstatus status) {
		this.status = status; 
		this.prozent = 0.0; 
	}
	
	/**
	 * Gibt prozent wert zurück
	 * @return
	 */
	public double getProzent() {
		return prozent;
	}
	
	/**
	 * Gibt den status des Weinbergs zurück
	 * @return
	 */
	public Weinbergstatus getWeinbergstatus() {
		return status; 
	}
	
	/**
	 * Testet ob ein übergebenes Objekt identisch zu dem Weinberg ist
	 */
	@Override
	public boolean equals(Object object) {
		if(object.getClass()!= Status.class) {
			return false;
		}
		Status status = (Status)object; 
		if(this.status == status.status && this.prozent == status.prozent) {
			return true; 
		}else {
		return false; 
		}
	}
	
}
