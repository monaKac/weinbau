
public class Wetter {
	private int datum;
	private double wind;
	private int id;
	private static int nextId = 1; 
	private double temp;
	private double sonnenstunden; 
	private Bewoelkung bewoelkung; 
	private Weinberg weinberg; 
	private int regenwahrscheinlichkeit; //zwischen 0 und 100
	private int niederschlag; // in ml pro m^2 
	
	
	
	
	public Wetter(int datum, Weinberg weinberg) {
		this.datum = datum;
		this.weinberg = weinberg; 
		//this.id = Methode zum id generieren;
	}
	
	public Wetter(int datum, Weinberg weinberg, double temp, double wind, double sonnenstunden, int regenwahrscheinlichkeit, int niederschlag) {
		this.datum = datum;
		this.weinberg = weinberg; 
		this.id = nextId;
		nextId++;
		this.temp = temp; 
		this.wind = wind; 
		this.sonnenstunden = sonnenstunden; 
		this.setRegenwahrscheinlichkeit(regenwahrscheinlichkeit); 
		this.setNiederschlag(niederschlag); 
		berechneBewoelkung(); 
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

	public int getNiederschlag() {
		return niederschlag;
	}

	public void setNiederschlag(int niederschlag) {
		this.niederschlag = niederschlag;
	}

	public int getRegenwahrscheinlichkeit() {
		return regenwahrscheinlichkeit;
	}

	public void setRegenwahrscheinlichkeit(int regenwahrscheinlichkeit) {
		this.regenwahrscheinlichkeit = regenwahrscheinlichkeit;
	}
	
	private void berechneBewoelkung() {
		//Entscheidung welcher Wert des Enums Bewoelkung am besten passt
		//-> später wichtig für Icon 
		if(regenwahrscheinlichkeit>50 && niederschlag >800) {
			if(this.temp<0) {
				this.bewoelkung = Bewoelkung.SCHNEE; 
			}else {
				this.bewoelkung= Bewoelkung.REGEN; 
			}
		}else if(wind>70) {
			this.bewoelkung= Bewoelkung.STURM; 
		}else if(this.sonnenstunden <3) {
			this.bewoelkung= Bewoelkung.BEWOELKT; 
		}else if(this.sonnenstunden<5) {
			this.bewoelkung= Bewoelkung.LEICHT_BEWOELKT; 
		}else {
			this.bewoelkung= Bewoelkung.SONNIG; 
		}
		
	}

	public int getId() {
		return id;
	}


	
}
