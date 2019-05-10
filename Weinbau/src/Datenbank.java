import java.util.ArrayList;

public class Datenbank {

	private static ArrayList<Wetter> wetterDaten;
	private static Winzer winzer; 

	public static Winzer getWinzer() {
		
		return winzer;
	}
	
	public static void initialisiereWinzer() {
		winzer = new Winzer("Frank", "Weinbauer");
		Status status1 = new Status(Weinbergstatus.BODENARBEIT, 100); 
		Weinberg weinberg1 = new Weinberg(winzer, status1, 30 , 40); 
		weinberg1.setName("Wachenheimer Fuchsmantel");
		weinberg1.setKommentar("Test1");
		for(int i = 0; i< 20; i++) {
		weinberg1.addPflanze(new Pflanzen(10,40,false));
		}
		winzer.addWeinberg(weinberg1);
		
		Status status2 = new Status(Weinbergstatus.LAUBARBEIT, 100); 
		Weinberg weinberg2 = new Weinberg(winzer, status2, 30 , 40); 
		weinberg2.setName("Forster Ungeheuer");
		weinberg2.setKommentar("Kommentar 2");
		for(int i = 0; i< 20; i++) {
			weinberg2.addPflanze(new Pflanzen(10,40,false));
			}
		winzer.addWeinberg(weinberg2);
		
		Status status3 = new Status(Weinbergstatus.REBSCHNITT, 100); 
		Weinberg weinberg3 = new Weinberg(winzer, status3, 30 , 40); 
		weinberg3.setName("Deidesheimer Hofstueck");
		weinberg3.setKommentar("Kommentar Weinberg 3");
		for(int i = 0; i< 20; i++) {
			weinberg3.addPflanze(new Pflanzen(10,40,true));
			}
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
				int temp = (int) Math.random() * 8 + 23;
				int wind = (int) Math.random() * 40;
				int sonnenstunden = (int) (Math.random() * 8 + 6);
				int regenwahrscheinlichkeit = (int) (Math.random() * 100);
				int niederschlag =  (int) (Math.random() * 13000);				
				wetterDaten.add(new Wetter(d, weinberg, temp, wind, sonnenstunden, regenwahrscheinlichkeit, niederschlag));
			}
		}
	}
}
