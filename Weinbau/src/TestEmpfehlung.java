import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestEmpfehlung  {
	private static Empfehlung empfehlung; 
	private static Winzer winzer;
	private static Weinberg weinberg; 

	@BeforeAll
	public static void setup() {
		Datenbank.initialisiereWinzer();
		Datenbank.initialisiereWetter();
		 winzer = Datenbank.getWinzer();
		 weinberg = winzer.getWeinberge().get(0); 
		 for(int i= 0; i<25; i++)
				weinberg.addPflanze(new Pflanzen(5,50, false));
		 empfehlung = new Empfehlung(weinberg, 10); 
		
		
	}
	
	@Test
	public void testRebschnitt() throws Exception {
		//Uebergang in Reberziehung 
		
		
		//Uebergang moeglich
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
		
		//Uebergang nicht moeglich Rebschnitt nicht fertig
		weinberg.setStatus(new Status(Weinbergstatus.REBSCHNITT, 30));
		empfehlung.empfehlungRebschnitt();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.REBERZIEHUNG))); 
		
		//Uebergang nicht moeglich zu trocken 
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
		
		//Uebergang moeglich 
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
		
		//Uebergang nicht moeglich nicht 100 Prozent
		weinberg.setStatus(new Status(Weinbergstatus.REBERZIEHUNG, 30));
		empfehlung.empfehlungReberziehung();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BODENARBEIT))); 

		//Uebergang nicht moeglich Boden zu trocken
		weinberg.setStatus(new Status(Weinbergstatus.REBERZIEHUNG, 100));
		weinberg.setBodenfeuchtigkeit(10);
		empfehlung.empfehlungReberziehung();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BODENARBEIT))); 
		
		//Uebergang nicht moeglich Boden zu feucht
		weinberg.setBodenfeuchtigkeit(55);
		empfehlung.empfehlungReberziehung();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BODENARBEIT))); 
		
		//Uebergang nicht moeglich stark Regen
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
	public void testBodenarbeit() throws Exception{
		//Uebergang Pflanzenschutz 
		
		//Uebergang moeglich
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
		
		//Uebergang nicht moeglich Bodenarbeit noch nicht abgeschlossen 
		weinberg.setStatus(new Status(Weinbergstatus.BODENARBEIT, 80));
		empfehlung.empfehlungBodenarbeit();
		assertFalse(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.PFLANZENSCHUTZ))); 
		
		//Uebergang nicht moeglich Wetter nicht entsprechend 
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
	public void testPflanzenschutz() throws Exception{
		//Uebergang zu Befruchtung
		
		//Uebergang moeglich
		weinberg.setStatus(new Status(Weinbergstatus.PFLANZENSCHUTZ, 100));
		empfehlung.empfehlungPflanzenschutz();
		assertTrue(empfehlung.getEmpfehlungsstatus().equals(new Status(Weinbergstatus.BEFRUCHTUNG))); 
		
		//Warnung Pflanzenschutz 
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
		assertEquals(6240, duengen);
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
