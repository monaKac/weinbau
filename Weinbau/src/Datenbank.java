import java.util.ArrayList;

public class Datenbank {

	private static ArrayList<Wetter> wetterDaten;
	private static Winzer winzer; 

	public static Winzer getWinzer() {
		
		return winzer;
	}
	
	public static void initialisiereWinzer() {
		winzer = new Winzer("Frank", "Weinbauer");
		Status status1 = new Status(); 
		Weinberg weinberg1 = new Weinberg(winzer, status1, 30 , 40); 
		weinberg1.setName("Wachenheimer Fuchsmantel");
		weinberg1.setKommentar("Test1");
		winzer.addWeinberg(weinberg1);
		
		Status status2 = new Status(); 
		Weinberg weinberg2 = new Weinberg(winzer, status2, 30 , 40); 
		weinberg2.setName("Forster Ungeheuer");
		winzer.addWeinberg(weinberg2);
		
		Status status3 = new Status(); 
		Weinberg weinberg3 = new Weinberg(winzer, status3, 30 , 40); 
		weinberg3.setName("Deidesheimer Hofstueck");
		winzer.addWeinberg(weinberg3);
	}

	public static ArrayList<Wetter> getWetter() {
		return wetterDaten;
	}

	public static Wetter getWetter(int datum, Weinberg weinberg) {

		for (Wetter w : wetterDaten) {
			if (w.getDatum() == datum && w.getWeinberg().equals(weinberg)) {
				return w;
			}
		}

		throw new RuntimeException(
				"Keine Wetterdaten für Datum " + datum + " und Weinberg " + weinberg + " vorhanden.");

	}

	public static void initialisiereWetter() {
		ArrayList<Weinberg> weinberge = winzer.getWeinberge();
		wetterDaten = new ArrayList<>();
		for (int d = 0; d <= 20; d++) {
			for (int w = 0; w < 3; w++) {
				Weinberg weinberg = weinberge.get(w);
				int temp = (int) (Math.random() * 8) + 23;
				int wind = (int) (Math.random() * 40);
				int sonnenstunden = (int) (Math.random() * 8 + 6);
				int regenwahrscheinlichkeit = (int) (Math.random() * 100);
				int niederschlag =  (int) (Math.random() * 13000);				
				wetterDaten.add(new Wetter(d, weinberg, temp, wind, sonnenstunden, regenwahrscheinlichkeit, niederschlag));
			}
		}
	}
}
