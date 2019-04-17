
public class SchnittstelleFeldanwendung {

	public static void starteBewaesserung(Weinberg weinberg, int menge) {
		
	}
	
	public static void startePflanzenschutz(Weinberg weinberg, int menge) {
		
	}
	
	public static void starteDuengen(Weinberg weinberg, int menge) {
		
	}
	
	public static void naechsterStatus(Weinberg weinberg, Weinbergstatus status) {
		weinberg.setStatus(new Status(status));
	}
	

	
	
}
