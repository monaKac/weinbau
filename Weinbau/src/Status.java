
public class Status {
	double prozent;
	Weinbergstatus status; 
	
	public Status() {
		this.status = Weinbergstatus.WINTERRUHE; 
		this.prozent = 0.0; 
	}
	
	public Status(Weinbergstatus status) {
		this.status = status; 
		this.prozent = 0.0; 
	}
	
	public Status(Weinbergstatus status, double prozent) {
		this.status = status; 
		this.prozent = prozent; 
	}
	
	public void setProzent(double prozent) {
		this.prozent = prozent; 
	}
	
	public void setStatus(Weinbergstatus status, double prozent) {
		this.status = status; 
		this.prozent = prozent; 
	}
	
	public void setStatus(Weinbergstatus status) {
		this.status = status; 
		this.prozent = 0.0; 
	}
	
	public double getProzent() {
		return prozent;
	}
	
	public Weinbergstatus getWeinbergstatus() {
		return status; 
	}
	
}
