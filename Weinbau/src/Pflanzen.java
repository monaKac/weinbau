
public class Pflanzen {
	private double zuckergehalt; 
	private double groesse; 
	private boolean krankheit;
	private int id; 
	private static int nextId = 1; 
	
	public Pflanzen(double zuckergehalt, double groesse, boolean krank) {
		this.zuckergehalt = zuckergehalt;
		this.groesse = groesse; 
		this.krankheit = krank; 
		this.id = nextId;
		nextId++; 
	}
	
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
