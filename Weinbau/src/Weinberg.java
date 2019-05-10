import java.util.ArrayList;

/**
 * 
 * @author marcogoette
 *
 */
public class Weinberg {
	private int id;
	private static int nextId = 1; 
	private Winzer winzer; 
	private int bodenfeuchtigkeit; //zwischen 0 und 100 
	private Status status;
	private int mineraliengehalt; // zwischen 0 und 100
	private ArrayList<Pflanzen> pflanzen; 
	private int alter; //in Monaten
	private String kommentar; 
	private String name; 
	
	/**
	 * Konstruktor der ohne Parameter einen Test-Weinberg erzeugt, welcher die jeweiligen Standardparameter besitzt
	 */
	public Weinberg () {
		this(new Winzer(),new Status(),0,0);
	}
	
	/**
	 * Überladener Konstruktor welcher durch folgende Parameter aufgerufen wird
	 * @param winzer
	 * @param status
	 * @param bodenfeuchtigkeit
	 * @param mineraliengehalt
	 */
	public Weinberg(Winzer winzer, Status status, int bodenfeuchtigkeit, int mineraliengehalt) {
		//this.id = //Methode um unique id zu generieren 
		this.setWinzer(winzer);
		this.status = status; 
		this.bodenfeuchtigkeit = bodenfeuchtigkeit; 
		this.id = nextId; 
		this.setMineraliengehalt(mineraliengehalt); 
		nextId++;  
		this.setAlter(0);
		pflanzen = new ArrayList<>();
	}
	
	/**
	 * Überladener Konstruktor, welcher durch die zusätzliche Variable alter aufgerufen wird
	 * @param winzer
	 * @param status
	 * @param bodenfeuchtigkeit
	 * @param mineraliengehalt
	 * @param alter
	 */
	public Weinberg(Winzer winzer, Status status, int bodenfeuchtigkeit, int mineraliengehalt, int alter) {
		//this.id = //Methode um unique id zu generieren 
		this.setWinzer(winzer);
		this.status = status; 
		this.bodenfeuchtigkeit = bodenfeuchtigkeit; 
		this.id = nextId; 
		this.setMineraliengehalt(mineraliengehalt); 
		this.setAlter(alter); 
		nextId++; 
		pflanzen = new ArrayList<>();
	}
	
	
	/**
	 * Gibt die Bodenfeuchtigkeit des Weinbergs zurück
	 * @return
	 */
	public int getBodenfeuchtigkeit() {
		return bodenfeuchtigkeit;
	}
	
	/**
	 * Setzt die Bodenfeuchtigkeit auf den übergebenen int Wert (zwischen 0 und 100)
	 * @param bodenfeuchtigkeit
	 */
	public void setBodenfeuchtigkeit(int bodenfeuchtigkeit) {
		this.bodenfeuchtigkeit = bodenfeuchtigkeit;
	}
	
	/**
	 * Gibt den Status des Weinbergs zurück
	 * @return
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Setzt den Status des Weinbergs auf das übergebene Parameter
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	} 
	
	/**
	 * Prüft das übergebene Objekt auf den Klassentyp und die ID
	 */
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

	/**
	 * Gibt den Winzer des Weinbergs zurück
	 * @return
	 */
	public Winzer getWinzer() {
		return winzer;
	}

	/**
	 * Setzt/ ändert den Winzer auf das übergebene Parameter
	 * @param winzer
	 */
	public void setWinzer(Winzer winzer) {
		this.winzer = winzer;
	}
	
	/**
	 * Gibt die ID des Weinbergs zurück
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Berechnet die Pflanzengröße anhand der vergangenen Zeit
	 * @return
	 */
	public int getPflanzenGroesse() {
		double groesse = 0; 
		for(int i = 0; i<pflanzen.size(); i++) {
			groesse = groesse + pflanzen.get(i).getGroesse();
		}
		groesse = groesse/ pflanzen.size(); 
		return (int) groesse;
	}
	
	/**
	 * Berechnet den Zuckergehalt anhand der vergangenen Zeit
	 * @return
	 */
	public int getZuckergehalt() {
		double zucker = 0; 
		for(int i = 0; i<pflanzen.size(); i++) {
			zucker = zucker + pflanzen.get(i).getZuckergehalt();
		}
		zucker = zucker/ pflanzen.size(); 
		return (int) zucker;
	}
	
	/**
	 * Prüft ob die Pflanze in der ArrayList als krank deklariert wurde
	 * @return
	 */
	public boolean isKrank() {
		boolean krank = false; 
		for(int i = 0; i<pflanzen.size(); i++) {
			if(pflanzen.get(i).isKrank()==true) {
				krank = true; 
			}
		}
		return krank;
		 
	}
	
	/**
	 * Gibt alle in der ArrayList enthaltene Pflanzen zurück
	 * @return
	 */
	public ArrayList<Pflanzen> getPflanzen() {
		return pflanzen;
	}
	
	/**
	 * Falls die Pflanze nach Prüfung nicht in der ArrayList besteht, wird sie dieser hinzugefügt
	 * @param pflanze
	 */
	public void addPflanze(Pflanzen pflanze) {		
		for(Pflanzen p : this.pflanzen) {
			if (p.equals(pflanze)) {
				throw new RuntimeException("Pflanze existiert schon in Liste");
			}
		}
		pflanzen.add(pflanze);
	}
	
	/**
	 * Entfernt die übergeben Pflanze aus der Liste
	 * @param pflanze
	 */
	public void removePflanze(Pflanzen pflanze) {
		pflanzen.remove(pflanze);
	}

	/**
	 * Gibt den Mineralgehlat zwischen 0 und 100 zurück
	 * @return
	 */
	public int getMineraliengehalt() {
		return mineraliengehalt;
	}

	/**
	 * Setzt den Mineralgehalt auf den übergeben int Wert
	 * @param mineraliengehalt
	 */
	public void setMineraliengehalt(int mineraliengehalt) {
		this.mineraliengehalt = mineraliengehalt;
	}

	/**
	 * Gibt das Alter der Pflanze zurück
	 * @return
	 */
	public int getAlter() {
		return alter;
	}

	/**
	 * Setzt das Alter der Pflanze auf den übergebenen int-Wert
	 * @param alter
	 */
	public void setAlter(int alter) {
		this.alter = alter;
	}

	/**
	 * Gibt den zum Weinberg gehörigen Kommentar zurück
	 * @return
	 */
	public String getKommentar() {
		return kommentar;
	}

	/**
	 * Setzt den Kommentar auf den übergebenen String
	 * @param kommentar
	 */
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	/**
	 * gibt den Name des Weinbergs zurück
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Name des Weinbergs auf den übergeben String
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * toString Methode, welche prüft ob der Name gleich null oder leer ist und anschließend den Namen des Weinbergs als String zurück gibt
	 */
	@Override 
	public String toString() {
		if(this.name==null || this.name == "")
			return "Weinberg "+this.id; 
		return "Weinberg "+this.name; 
	}
	
}
