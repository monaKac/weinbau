import static org.junit.jupiter.api.Assertions.assertEquals;

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
	public void testBewaesserung() throws Exception {
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
		int bewaesserungsmenge = empfehlung.getBewaesserungsmenge(); 
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
		int duengen = empfehlung.getDuenger(); 
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
