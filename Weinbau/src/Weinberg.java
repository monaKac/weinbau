
public class Weinberg {
	private int id;
	private Winzer winzer; 
	private double bodenfeuchtigkeit; 
	private Status status;
	
	
	public Weinberg(Winzer winzer, Status status) {
		//this.id = //Methode um unique id zu generieren 
		this.setWinzer(winzer);
		this.status = status; 
		this.bodenfeuchtigkeit = 0.0; 
		
	}
	
	public Weinberg(Winzer winzer, Status status, double bodenfeuchtigkeit) {
		//this.id = //Methode um unique id zu generieren 
		this.setWinzer(winzer);
		this.status = status; 
		this.bodenfeuchtigkeit = bodenfeuchtigkeit; 
	}
	
	
	
	public double getBodenfeuchtigkeit() {
		return bodenfeuchtigkeit;
	}
	public void setBodenfeuchtigkeit(double bodenfeuchtigkeit) {
		this.bodenfeuchtigkeit = bodenfeuchtigkeit;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	} 
	
	@Override
	public boolean equals(Object object) {
		if(object.getClass()!= Weinberg.class) {
			return false;
		}
		Weinberg weinberg = (Weinberg)object; 
		if(this.id == weinberg.id) {
			return true; 
		}else {
		return false; 
		}
	}

	public Winzer getWinzer() {
		return winzer;
	}

	public void setWinzer(Winzer winzer) {
		this.winzer = winzer;
	}
	
	public int getId() {
		return id;
	}
	
}
