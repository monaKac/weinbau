import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestEmpfehlung {
	private static Empfehlung empfehlung; 

	@BeforeAll
	public static void setup() {
		Winzer winzer = new Winzer("Test", "Empfehlung");
		Weinberg weinberg = new Weinberg(winzer, new Status(), 50, 50, 12); 
		 empfehlung = new Empfehlung(weinberg, 10); 
		
		
	}
	
	@Test
	public void testErnte() {
		Wetter[] wetterTestDaten = new Wetter[7]; 
		//wetterTestDaten[0] = new Wetter(); 
		empfehlung.setWettervorhersage(wetterTestDaten);
		
		
		
		//assertEquals(); 
	}
	
	@Test
	public void testEmpfehlung() throws Exception {
		
	}
}
