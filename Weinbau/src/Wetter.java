
public class Wetter {
	private int datum;
	private double wind;
	private int id;
	private double temp;
	private double sonnenstunden; 
	private Bewoelkung bewoelkung; 
	private Weinberg weinberg; 
	
	
	
	public Wetter(int datum, Weinberg weinberg) {
		this.datum = datum;
		this.weinberg = weinberg; 
		//this.id = Methode zum id generieren;
	}
	
	public Wetter(int datum, Weinberg weinberg, double temp, double wind, double sonnenstunden, Bewoelkung bewoelkung) {
		this.datum = datum;
		this.weinberg = weinberg; 
		//this.id = Methode zum id generieren;
		this.temp = temp; 
		this.wind = wind; 
		this.sonnenstunden = sonnenstunden; 
		this.bewoelkung = bewoelkung; 
	}
	
	
	public int getDatum() {
		return datum;
	}
	
	public double getWind() {
		return wind;
	}
	public void setWind(double wind) {
		this.wind = wind;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getSonnenstunden() {
		return sonnenstunden;
	}
	public void setSonnenstunden(double sonnenstunden) {
		this.sonnenstunden = sonnenstunden;
	}
	public Bewoelkung getBewoelkung() {
		return bewoelkung;
	}
	public void setBewoelkung(Bewoelkung bewoelkung) {
		this.bewoelkung = bewoelkung;
	}
	public Weinberg getWeinberg() {
		return weinberg;
	}
	
	
}
