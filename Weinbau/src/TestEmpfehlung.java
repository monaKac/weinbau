import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestEmpfehlung {
	private static Empfehlung	empfehlung;
	private static Winzer		winzer;
	private static Weinberg		weinberg;

	@BeforeAll
	public static void setup() {
		Datenbank.initialisiereWinzer();
		Datenbank.initialisiereWetter();
		winzer = Datenbank.getWinzer();
		weinberg = winzer.getWeinberge().get(0);
		for (int i = 0; i < 25; i++)
			weinberg.addPflanze(new Pflanzen(16, 60, false));
		empfehlung = new Empfehlung(weinberg, 10);

	}

	@Test
	public void testWinterruhe() throws Exception {
		// Uebergang in Rebschnitt

		// Uebergang moeglich
		weinberg.setStatus(new Status(Weinbergstatus.WINTERRUHE, 100));
		Wetter[] wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 9, 12, 6, 80, 2000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 9, 12, 6, 80, 2000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 9, 12, 6, 80, 2000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 9, 12, 6, 80, 2000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 9, 12, 6, 80, 2000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 9, 12, 6, 80, 2000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 9, 12, 6, 80, 2000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungWinterruhe();
		assertTrue(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.REBSCHNITT)));

		// Uebergang nicht moeglich Austrieb hat noch nicht stattgefunden
		weinberg.setStatus(new Status(Weinbergstatus.WINTERRUHE, 50));
		empfehlung.empfehlungWinterruhe();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.REBSCHNITT)));

		// Uebergang nicht moeglich Temperatur zu kalt
		weinberg.setStatus(new Status(Weinbergstatus.WINTERRUHE, 100));
		wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 3, 12, 6, 80, 2000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 3, 12, 6, 80, 2000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 3, 12, 6, 80, 2000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 3, 12, 6, 80, 2000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 3, 12, 6, 80, 2000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 3, 12, 6, 80, 2000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 3, 12, 6, 80, 2000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungWinterruhe();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.REBSCHNITT)));

	}

	@Test
	public void testRebschnitt() throws Exception {
		// Uebergang in Reberziehung

		// Uebergang moeglich
		weinberg.setStatus(new Status(Weinbergstatus.REBSCHNITT, 100));
		Wetter[] wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 2000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 2000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 80, 2000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 10, 12, 6, 80, 2000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 10, 12, 6, 80, 2000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 10, 12, 6, 80, 2000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 10, 12, 6, 80, 2000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungRebschnitt();
		assertTrue(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.REBERZIEHUNG)));

		// Uebergang nicht moeglich Rebschnitt nicht fertig
		weinberg.setStatus(new Status(Weinbergstatus.REBSCHNITT, 30));
		empfehlung.empfehlungRebschnitt();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.REBERZIEHUNG)));

		// Uebergang nicht moeglich zu trocken
		weinberg.setStatus(new Status(Weinbergstatus.REBSCHNITT, 100));
		wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 10, 2000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 10, 2000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 10, 2000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 10, 12, 6, 10, 2000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 10, 12, 6, 10, 2000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 10, 12, 6, 10, 2000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 10, 12, 6, 10, 2000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungRebschnitt();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.REBERZIEHUNG)));
	}

	@Test
	public void testReberziehung() throws Exception {
		// Uebergang in Bodenarbeit

		// Uebergang moeglich
		weinberg.setStatus(new Status(Weinbergstatus.REBERZIEHUNG, 100));
		weinberg.setBodenfeuchtigkeit(40);
		Wetter[] wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungReberziehung();
		assertTrue(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BODENARBEIT)));

		// Uebergang nicht moeglich nicht 100 Prozent
		weinberg.setStatus(new Status(Weinbergstatus.REBERZIEHUNG, 30));
		empfehlung.empfehlungReberziehung();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BODENARBEIT)));

		// Uebergang nicht moeglich Boden zu trocken
		weinberg.setStatus(new Status(Weinbergstatus.REBERZIEHUNG, 100));
		weinberg.setBodenfeuchtigkeit(10);
		empfehlung.empfehlungReberziehung();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BODENARBEIT)));

		// Uebergang nicht moeglich Boden zu feucht
		weinberg.setBodenfeuchtigkeit(75);
		empfehlung.empfehlungReberziehung();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BODENARBEIT)));

		// Uebergang nicht moeglich stark Regen
		wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 10, 12, 6, 80, 10000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungReberziehung();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BODENARBEIT)));

	}

	@Test
	public void testBodenarbeit() throws Exception {
		// Uebergang Pflanzenschutz

		// Uebergang moeglich
		weinberg.setStatus(new Status(Weinbergstatus.BODENARBEIT, 100));
		Wetter[] wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 12, 80, 2000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 16, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 16, 80, 1000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 10, 12, 16, 80, 1000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungBodenarbeit();
		assertTrue(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.PFLANZENSCHUTZ)));

		// Uebergang nicht moeglich Bodenarbeit noch nicht abgeschlossen
		weinberg.setStatus(new Status(Weinbergstatus.BODENARBEIT, 80));
		empfehlung.empfehlungBodenarbeit();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.PFLANZENSCHUTZ)));

		// Uebergang nicht moeglich Wetter nicht entsprechend
		weinberg.setStatus(new Status(Weinbergstatus.BODENARBEIT, 100));
		wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 3, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 3, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 3, 80, 1000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 10, 12, 3, 80, 1000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 10, 12, 3, 80, 1000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 10, 12, 3, 80, 1000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 10, 12, 3, 80, 1000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungBodenarbeit();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.PFLANZENSCHUTZ)));
	}

	@Test
	public void testPflanzenschutz() throws Exception {
		// Uebergang zu Befruchtung

		// Uebergang moeglich
		weinberg.setStatus(new Status(Weinbergstatus.PFLANZENSCHUTZ, 100));
		empfehlung.empfehlungPflanzenschutz();
		assertTrue(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BEFRUCHTUNG)));

		// Warnung Pflanzenschutz
		weinberg.setStatus(new Status(Weinbergstatus.PFLANZENSCHUTZ, 30));
		weinberg.setBodenfeuchtigkeit(60);
		Wetter[] wetterTestDaten = new Wetter[3];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 3, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 3, 80, 3000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 3, 80, 1000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungPflanzenschutz();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BEFRUCHTUNG)));
	}

	@Test
	public void testBefruchtung() throws Exception {
		// Uebergang Laubarbeit

		// Uebergang moeglich
		weinberg.setStatus(new Status(Weinbergstatus.BEFRUCHTUNG, 100));
		Wetter[] wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungBefruchtung();
		assertTrue(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.LAUBARBEIT)));

		// Uebergang nicht moeglich zu geringer Niederschlag
		weinberg.setStatus(new Status(Weinbergstatus.BEFRUCHTUNG, 100));
		wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 100);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 100);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 80, 100);
		wetterTestDaten[3] = new Wetter(1, weinberg, 10, 12, 6, 80, 100);
		wetterTestDaten[4] = new Wetter(1, weinberg, 10, 12, 6, 80, 100);
		wetterTestDaten[5] = new Wetter(1, weinberg, 10, 12, 6, 80, 100);
		wetterTestDaten[6] = new Wetter(1, weinberg, 10, 12, 6, 80, 100);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungBefruchtung();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.LAUBARBEIT)));

		// Uebergang nicht moeglich Befruchtung noch nicht abgeschlossen
		weinberg.setStatus(new Status(Weinbergstatus.LAUBARBEIT, 80));
		empfehlung.empfehlungBefruchtung();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.LAUBARBEIT)));

	}

	@Test
	public void testLaubarbeit() throws Exception {
		// Uebergang Ernte

		// Uebergang moeglich
		ArrayList<Pflanzen> pflanzen = weinberg.getPflanzen();
		for (int i = 0; i < pflanzen.size(); i++) {
			pflanzen.get(i).setGroesse(60);
			pflanzen.get(i).setZuckergehalt(16);
		}
		weinberg.setStatus(new Status(Weinbergstatus.LAUBARBEIT, 100));
		empfehlung.empfehlungLaubarbeit();
		assertTrue(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.ERNTE)));

		// Uebergang nicht moeglich Laubarbeit noch nicht abgeschlossen
		weinberg.setStatus(new Status(Weinbergstatus.LAUBARBEIT, 80));
		empfehlung.empfehlungLaubarbeit();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.ERNTE)));

		// Uebergang nicht moeglich Pflanzen nicht gross genug
		for (Pflanzen p : pflanzen) {
			p.setGroesse(30);
			p.setZuckergehalt(16);
		}
		weinberg.setStatus(new Status(Weinbergstatus.LAUBARBEIT, 100));
		empfehlung.empfehlungLaubarbeit();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.ERNTE)));

		// Uebergang nicht moeglich Zuckergehalt PFlanzen zu niedrig
		for (Pflanzen p : pflanzen) {
			p.setGroesse(60);
			p.setZuckergehalt(10);
		}
		weinberg.setStatus(new Status(Weinbergstatus.LAUBARBEIT, 100));
		empfehlung.empfehlungErnte();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.ERNTE)));

	}

	@Test
	public void testErnte() throws Exception {
		// Uebergang Winterruhe

		// Uebergang moeglich
		weinberg.setStatus(new Status(Weinbergstatus.ERNTE, 100));
		Wetter[] wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 6, 12, 6, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 1, 12, 6, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 1, 12, 6, 80, 1000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 1, 12, 6, 80, 1000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 1, 12, 6, 80, 1000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 1, 12, 6, 80, 1000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 1, 12, 6, 80, 1000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungErnte();
		assertTrue(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.WINTERRUHE)));

		// Uebergang nicht moeglich Ernte noch nicht abgeschlossen
		weinberg.setStatus(new Status(Weinbergstatus.ERNTE, 10));
		empfehlung.empfehlungErnte();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.WINTERRUHE)));

	}

	@Test
	public void testBewaesserung() throws Exception {
		weinberg.setBodenfeuchtigkeit(30);
		weinberg.setMineraliengehalt(40);
		Wetter[] wetterTestDaten = new Wetter[7];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[3] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[4] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[5] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[6] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungBewaesserung();
		int bewaesserungsmenge = empfehlung.sollBewaessern();
		assertEquals(275, bewaesserungsmenge);
	}

	@Test
	public void testDuengen() throws Exception {
		Wetter[] wetterTestDaten = new Wetter[2];
		weinberg.setMineraliengehalt(20);

		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		empfehlung.empfehlungDuengen();
		int duengen = empfehlung.sollDuengen();
		assertEquals(11040, duengen);
	}

	@Test
	public void testDurchschnittNied() throws Exception {
		Wetter[] wetterTestDaten = new Wetter[3];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		empfehlung.setWettervorhersage(wetterTestDaten);
		double niederschlag = empfehlung.durchschnittNiederschlag(3);
		assertEquals(800, niederschlag);
	}

	@Test
	public void testDurchschnittTemp() throws Exception {
		Wetter[] wetterTestDaten = new Wetter[3];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);

		empfehlung.setWettervorhersage(wetterTestDaten);
		int temp = empfehlung.durchschnittTemp(3);
		assertEquals(10, temp);
	}

	@Test
	public void testDurchschnittSonneh() throws Exception {
		Wetter[] wetterTestDaten = new Wetter[3];
		wetterTestDaten[0] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[1] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);
		wetterTestDaten[2] = new Wetter(1, weinberg, 10, 12, 6, 80, 1000);

		empfehlung.setWettervorhersage(wetterTestDaten);
		int temp = empfehlung.durchschnitSonnenstunden(3);
		assertEquals(6, temp);
	}

}
