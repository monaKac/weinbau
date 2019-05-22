/**
 * 
 * @author D074003
 * @version 1.0
 * Ist die Schnittstelle der GUI zu dem Weinberg
 * Fuehrt Aktionen wie Bewaeserung, Pflanzenschutz und Duengen aus. 
 * In weiteren Versionen wird es fertiggestellt sein, bisher ist es noch ein Prototype.
 */
public class SchnittstelleFeldanwendung {

	/**
	 * 
	 * @param weinberg
	 * @param menge
	 * Startet Bewaesserung eines vollautomatischen Weinberges
	 */
	public static void starteBewaesserung(Weinberg weinberg, int menge) {
		System.out.println("Starte Bewaesserung"); 
	}
	
	/**
	 * 
	 * @param weinberg
	 * @param menge
	 * Startet Pflanzenschutz eines vollautomatischen Weinberges
	 */
	public static void startePflanzenschutz(Weinberg weinberg, int menge) {
		System.out.println("Starte Pflanzenschutz: "+menge+"ml");
	}
	
	/**
	 * 
	 * @param weinberg
	 * @param menge
	 * Startet Duengen eienes vollautomatischen Weinberges
	 */
	public static void starteDuengen(Weinberg weinberg, int menge) {
		System.out.println("Starte Duengen: "+menge + "ml");
	}
	

	
	
	
	
}
