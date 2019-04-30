import java.util.ArrayList;

public class Winzer {
	
	private static int nextId = 1;
	private int id; 
	private String vorname;
	private String nachname;
	private ArrayList<Weinberg> weinberge;
	
	public Winzer() {
		this("Max","Musterwinzer");
	}
	
	public Winzer(String vorname, String nachname) {
		//set id this.id = ; 
		
		this.id = nextId; 
		nextId++; 
		this.vorname = vorname;
		this.nachname = nachname;
		weinberge = new ArrayList<>();
	}

	public ArrayList<Weinberg> getWeinberge() {
		return weinberge;
	}
	
	public void addWeinberg(Weinberg weinberg) {		
		for(Weinberg w : this.weinberge) {
			if (w.equals(weinberg)) {
				throw new RuntimeException("Weinberg existiert schon in Liste");
			}
		}
		weinberge.add(weinberg);
	}
	
	public void removeWeinberg(Weinberg weinberg) {
		weinberge.remove(weinberg);
	}


	public String getNachname() {
		return nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public int getId() {
		return id;
	}
	


	
	
}
