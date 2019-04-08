
public class Weinberg {
	private int id;
	private Winzer winzer; 
	private double bodenfeuchtigkeit; 
	private int status;
	
	
	public void Weinberg(Winzer winzer, int status) {
		//this.id = //Methode um unique id zu generieren 
		this.winzer = winzer;
		this.status = status; 
		this.bodenfeuchtigkeit = 0.0; 
		
	}
	
	public void Weinberg(Winzer winzer, int status, double bodenfeuchtigkeit) {
		//this.id = //Methode um unique id zu generieren 
		this.winzer = winzer;
		this.status = status; 
		this.bodenfeuchtigkeit = bodenfeuchtigkeit; 
	}
	
	
	
	public double getBodenfeuchtigkeit() {
		return bodenfeuchtigkeit;
	}
	public void setBodenfeuchtigkeit(double bodenfeuchtigkeit) {
		this.bodenfeuchtigkeit = bodenfeuchtigkeit;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	} 
	
	
}
