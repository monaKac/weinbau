/**
 * Verschieden Phasen des Weinbergs
 * Zudem gibt es für jedes Enum eine toString-Methode, welche den String der Phase zurück gibt
 * @author marcogoette
 *
 */
public enum Weinbergstatus {
	/**
	 * 
	 */
	WINTERRUHE( 40, 70, 50, 800, 5000, 6, 4, 10, 30, 0, 0){
		public String toString() {
			return "Winterruhe"; 
		}
	}, REBSCHNITT( 20, 60, 50, 1000, 10000, 6, 4, 15, 30, 0, 10){
		public String toString() {
			return "Rebschnitt"; 
		}
	}, REBERZIEHUNG( 35, 60, 50, 300, 7000, 6, 6, 15, 30, 0, 20){
		public String toString() {
			return "Reberziehung"; 
		}
	}, BODENARBEIT( 35, 60, 50, 800, 7000, 6, 9, 17, 30, 0, 30){
		public String toString() {
			return "Bodenarbeit"; 
		}
	}, PFLANZENSCHUTZ( 35, 60, 50, 600, 4000, 7, 13, 20, 30, 0, 40){
		public String toString() {
			return "Pflanzenschutz"; 
		}
	}, BEFRUCHTUNG( 35, 60, 50, 750, 4000, 8, 15, 35, 40, 0, 50){
		public String toString() {
			return "Befruchtung"; 
		}
	}, LAUBARBEIT( 35, 60, 50, 750, 3000, 7, 15, 35, 30, 16, 60){
		public String toString() {
			return "Laubarbeit"; 
		}
	}, ERNTE( 35, 60, 50, 750, 4000, 7, 10, 28, 30, 16, 70){
		public String toString() {
			return "Ernte"; 
		}
	};
	
	/** 
	 * Minimale erlaubte Bodenfeuchtigkeit 
	 */
	private int minBodenfeuchtigkeit; 
	
	/**
	 * Maximale erlaubte Bodenfeuchtigkeit
	 */
	private int maxBodenfeuchtigkeit; 
	
	/**
	 * Minimaler erlaubter Mineraliengehalt des Bodens 
	 */
	private int mineraliengehalt; 
	
	/** 
	 * Minimaler durchschnittlicher Niederschlag pro Tag 
	 */
	private int minNiederschlag; 
	
	/** 
	 * Maximaler durchschnittlicher Niederschlag pro Tag
	 *  
	 */
	private int maxNiederschlag; 
	
	/**
	 * Gewuenschte durchschnittliche Sonnenstunden pro Tag 
	 */
	private int sonnenstunden; 
	
	/**
	 * Minimale durchschnittliche Temperatur 
	 */
	private int minTemperatur; 
	
	/**
	 * Maximale durchscnittliche Temperatur
	 */
	private int maxTemperatur;
	
	/**
	 * Max Windgeschwindigkeit in km/h
	 */
	private int wind; 
	
	/**
	 * Gewuenschter minimaler Zuckergehalt
	 */
	private int zuckergehalt; 
	
	/**
	 * Gewuenschte minimale Groesse in cm 
	 */
	private int groesse; 
	
	/**
	 * Konstruktor mit Standardwerten fuer Weinbergstati
	 * Parameter sind gewuenschte Werte des Weinbauerns 
	 * Weinbauer kann durch individuelle Configuartion diese Werte anpassen 
	 * @param boden minimale Bodenfeuchtigkeit
	 * @param mineralien minimaler Mineraliengehalt
	 * @param niederschlag  durchschnittlicher Niederschlag pro Tag
	 * @param sonnenstunden durchschnittliche Sonnenstunden pro Tag
	 * @param temp durchschnittliche Temperatur 
	 * @param wind durchschnittliche Windgeschwindigkeit in km/h
	 * @param zuckergehalt minimaler Zuckergehalt
	 * @param groesse minimale Groesse in cm
	 */
	private Weinbergstatus(int minBoden,int maxBoden, int mineralien, int minNiederschlag, int maxNiederschlag, int sonnenstunden, int minTemp, int maxTemp, int wind, int zuckergehalt, int groesse) {
		this.minBodenfeuchtigkeit = minBoden;
		this.maxBodenfeuchtigkeit = maxBoden; 
		this.mineraliengehalt = mineralien; 
		this.minNiederschlag = minNiederschlag; 
		this.maxNiederschlag = maxNiederschlag; 
		this.sonnenstunden = sonnenstunden;  
		this.maxTemperatur = maxTemp; 
		this.minTemperatur = minTemp; 
		this.wind = wind; 
		this.zuckergehalt = zuckergehalt; 
		this.groesse = groesse; 
	}
	


	public int getMineraliengehalt() {
		return mineraliengehalt;
	}

	public void setMineraliengehalt(int mineraliengehalt) {
		this.mineraliengehalt = mineraliengehalt;
	}



	public int getSonnenstunden() {
		return sonnenstunden;
	}

	public void setSonnenstunden(int sonnenstunden) {
		this.sonnenstunden = sonnenstunden;
	}



	public int getWind() {
		return wind;
	}

	public void setWind(int wind) {
		this.wind = wind;
	}

	public int getZuckergehalt() {
		return zuckergehalt;
	}

	public void setZuckergehalt(int zuckergehalt) {
		this.zuckergehalt = zuckergehalt;
	}

	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public int getMinTemperatur() {
		return minTemperatur;
	}

	public void setMinTemperatur(int minTemperatur) {
		this.minTemperatur = minTemperatur;
	}

	public int getMaxTemperatur() {
		return maxTemperatur;
	}

	public void setMaxTemperatur(int maxTemperatur) {
		this.maxTemperatur = maxTemperatur;
	}

	public int getMinNiederschlag() {
		return minNiederschlag;
	}

	public void setMinNiederschlag(int minNiederschlag) {
		this.minNiederschlag = minNiederschlag;
	}

	public int getMaxNiederschlag() {
		return maxNiederschlag;
	}

	public void setMaxNiederschlag(int maxNiederschlag) {
		this.maxNiederschlag = maxNiederschlag;
	}

	public int getMinBodenfeuchtigkeit() {
		return minBodenfeuchtigkeit;
	}

	public void setMinBodenfeuchtigkeit(int minBodenfeuchtigkeit) {
		this.minBodenfeuchtigkeit = minBodenfeuchtigkeit;
	}

	public int getMaxBodenfeuchtigkeit() {
		return maxBodenfeuchtigkeit;
	}

	public void setMaxBodenfeuchtigkeit(int maxBodenfeuchtigkeit) {
		this.maxBodenfeuchtigkeit = maxBodenfeuchtigkeit;
	}
	
	
}
