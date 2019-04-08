
public class Pflanzen {
	private double zuckergehalt; 
	private double groesse; 
	private Weinberg weinberg; 
	private int alter;  
	private boolean krankheit;
	public double getZuckergehalt() {
		return zuckergehalt;
	}
	public void setZuckergehalt(double zuckergehalt) {
		this.zuckergehalt = zuckergehalt;
	}
	public double getGroesse() {
		return groesse;
	}
	public void setGroesse(double groesse) {
		this.groesse = groesse;
	}
	
	public boolean isKrank() {
		return krankheit;
	}
	public void setKrank(boolean krankheit) {
		this.krankheit = krankheit;
	}
}
