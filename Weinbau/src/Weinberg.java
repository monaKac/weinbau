import java.util.ArrayList;

public class Weinberg {
	private int id;
	private static int nextId = 1; 
	private Winzer winzer; 
	private int bodenfeuchtigkeit; //zwischen 0 und 100 
	private Status status;
	private int mineraliengehalt; // zwischen 0 und 100
	private ArrayList<Pflanzen> pflanzen; 
	private int alter; //in Monaten
	
	
//	public Weinberg(Winzer winzer, Status status) {
//		//this.id = //Methode um unique id zu generieren 
//		this.setWinzer(winzer);
//		this.status = status; 
//		this.bodenfeuchtigkeit = 0; 
//		this.id = nextId; 
//		nextId++; 
//		
//	}
	
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
	
	
	
	public int getBodenfeuchtigkeit() {
		return bodenfeuchtigkeit;
	}
	public void setBodenfeuchtigkeit(int bodenfeuchtigkeit) {
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
	
	public int getPflanzenGroesse() {
		double groesse = 0; 
		for(int i = 0; i<pflanzen.size(); i++) {
			groesse = groesse + pflanzen.get(i).getGroesse();
		}
		groesse = groesse/ pflanzen.size(); 
		return (int) groesse;
	}
	
	public int getZuckergehalt() {
		double zucker = 0; 
		for(int i = 0; i<pflanzen.size(); i++) {
			zucker = zucker + pflanzen.get(i).getZuckergehalt();
		}
		zucker = zucker/ pflanzen.size(); 
		return (int) zucker;
	}
	
	public boolean isKrank() {
		boolean krank = false; 
		for(int i = 0; i<pflanzen.size(); i++) {
			if(pflanzen.get(i).isKrank()==true) {
				krank = true; 
			}
		}
		return krank;
		 
	}
	
	public ArrayList<Pflanzen> getPflazen() {
		return pflanzen;
	}
	
	public void addPflanze(Pflanzen pflanze) {		
		for(Pflanzen p : this.pflanzen) {
			if (p.equals(pflanze)) {
				throw new RuntimeException("Pflanze existiert schon in Liste");
			}
		}
		pflanzen.add(pflanze);
	}
	
	public void removePflanze(Pflanzen pflanze) {
		pflanzen.remove(pflanze);
	}



	public int getMineraliengehalt() {
		return mineraliengehalt;
	}



	public void setMineraliengehalt(int mineraliengehalt) {
		this.mineraliengehalt = mineraliengehalt;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}
	

	
}
