import java.util.ArrayList;

public class Datenbank {

	private static ArrayList<Wetter> wetterDaten;

	public static Winzer getWinzer() {
		Winzer winzer = new Winzer("Frank", "Weinbauer");
		Status status = new Status(); 
		Weinberg weinberg = new Weinberg(winzer, status); 
		winzer.addWeinberg(weinberg);
		return winzer;
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
		ArrayList<Weinberg> weinberge = getWinzer().getWeinberge();
		for (int d = 0; d <= 20; d++) {
			for (int w = 1; w < 4; w++) {
				Weinberg weinberg = weinberge.get(w);
				double temp = Math.random() * 8 + 23;
				double wind = Math.random() * 40;
				int sonnenstunden = (int) (Math.random() * 8 + 6);
				int regenwahrscheinlichkeit = (int) (Math.random() * 100);
				int niederschlag =  (int) (Math.random() * 13000);				
				wetterDaten.add(new Wetter(d, weinberg, temp, wind, sonnenstunden, regenwahrscheinlichkeit, niederschlag));
			}
		}
	}
}
