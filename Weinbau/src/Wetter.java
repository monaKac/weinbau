/**
 * 
 * @author marcogoette
 *
 */
public class Wetter {
	private int datum;
	private int wind; //zwischen 0 und 150 km/h
	private int id;
	private static int nextId = 1; 
	private int temp;
	private int sonnenstunden; 
	private Bewoelkung bewoelkung; 
	private Weinberg weinberg; 
	private int regenwahrscheinlichkeit; //zwischen 0 und 100
	private int niederschlag; // in ml pro m^2 
	
	
	
	/**
	 * Hier wird das Wetter mithilfe vom Datum für einen Weinberg erzeugt
	 * Eher Test-Methode
	 * @param datum
	 * @param weinberg
	 */
	public Wetter(int datum, Weinberg weinberg) {
		this.datum = datum;
		this.weinberg = weinberg; 
		//this.id = Methode zum id generieren;
	}
	
	/**
	 * Überschriebener Konstruktor, falls oben genannte Parameter übergeben werden
	 * @param datum
	 * @param weinberg
	 * @param temp
	 * @param wind
	 * @param sonnenstunden
	 * @param regenwahrscheinlichkeit
	 * @param niederschlagen
	 */
	public Wetter(int datum, Weinberg weinberg, int temp, int wind, int sonnenstunden, int regenwahrscheinlichkeit, int niederschlag) {
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
	
	/**
	 * Gibt den Wert der Variable Datum zurück
	 * @return
	 */
	public int getDatum() {
		return datum;
	}
	
	/**
	 *  Gibt den Wert der Variable Wind zurück
	 * @return
	 */
	public double getWind() {
		return wind;
	}
	
	/**
	 * Setzt die Windgeschwindigkeit auf den übergebenen Wert
	 * @param wind
	 */
	public void setWind(int wind) {
		this.wind = wind;
	}
	
	/**
	 * Gibt die Temperatur zurück
	 * @return
	 */
	public double getTemp() {
		return temp;
	}
	
	/**
	 * Setzt die Temperatur auf den übergebenen Wert
	 * @param temp
	 */
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	/**
	 * Gibt die Anzahl der Sonnenstunden zurück
	 * @return
	 */
	public double getSonnenstunden() {
		return sonnenstunden;
	}
	
	/**
	 * Setzt die Anzahl der Sonnenstunden auf den übergeben Wert
	 * @param sonnenstunden
	 */
	public void setSonnenstunden(int sonnenstunden) {
		this.sonnenstunden = sonnenstunden;
	}
	
	/**
	 * Gibt einen der Enums Bewölkung zurück
	 * @return
	 */
	public Bewoelkung getBewoelkung() {
		return bewoelkung;
	}
	
	/**
	 * Setzt Bewölkung auf das übergeben Enum Bewölkung
	 * @param bewoelkung
	 */
	public void setBewoelkung(Bewoelkung bewoelkung) {
		this.bewoelkung = bewoelkung;
	}
	
	/**
	 * Gibt den entsprechenden Weinberg zurück
	 * @return
	 */
	public Weinberg getWeinberg() {
		return weinberg;
	}

	/**
	 * Gibt die Anzahl der ml/m^2 zurück
	 * @return
	 */
	public int getNiederschlag() {
		return niederschlag;
	}

	/**
	 * Setzt den Nidergeschlag in ml/m^2 auf den übergebenen int Wert
	 * @param niederschlag
	 */
	public void setNiederschlag(int niederschlag) {
		this.niederschlag = niederschlag;
	}

	/**
	 * Ginbt die Regenwahrscheinlichkeit zwischen 0 und 100 zurück
	 * @return
	 */
	public int getRegenwahrscheinlichkeit() {
		return regenwahrscheinlichkeit;
	}

	/**
	 * Setzt die Regenwahrscheinlichkeit auf den übergebenen int Wert
	 * @param regenwahrscheinlichkeit
	 */
	public void setRegenwahrscheinlichkeit(int regenwahrscheinlichkeit) {
		this.regenwahrscheinlichkeit = regenwahrscheinlichkeit;
	}
	
	/**
	 * Hier wird mithilfe der Wetterdaten entschieden welches Enum Bewölkung am besten passt
	 * Wichtig für das richtige Icon
	 */
	private void berechneBewoelkung() {
		if(wind>70) {
			this.bewoelkung= Bewoelkung.STURM; 
		}else if(regenwahrscheinlichkeit>50 && niederschlag >800) {
			if(this.temp<1) {
				this.bewoelkung = Bewoelkung.SCHNEE; 
			}else {
				this.bewoelkung= Bewoelkung.REGEN; 
			}
		}else if(this.sonnenstunden <3) {
			this.bewoelkung= Bewoelkung.BEWOELKT; 
		}else if(this.sonnenstunden<5) {
			this.bewoelkung= Bewoelkung.LEICHT_BEWOELKT; 
		}else {
			this.bewoelkung= Bewoelkung.SONNIG; 
		}
		
	}

	/**
	 * Gibt die Wetter-ID zurück
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gibt die Wetterdaten als Text/ String zurück
	 */
	public String toString() {
		return "Wetter: Datum :"+this.datum+", Temp :"+this.temp+", Regen :"+this.niederschlag+", Wahrscheinlichkeit :"+this.regenwahrscheinlichkeit+", Wind :"+this.wind+", Sonnenstunden :" + this.sonnenstunden +", Bewoelkung : "+this.bewoelkung.toString();
	}


	
}
