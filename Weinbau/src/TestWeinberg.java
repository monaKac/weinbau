import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestWeinberg {

	private static Weinberg weinberg;
	@BeforeAll
	public static void setup() {
		Winzer winzer = new Winzer("Frank", "Weinbauer");
		Status status = new Status(); 
		weinberg = new Weinberg(winzer, status, 50, 50, 25); 
		weinberg.addPflanze(new Pflanzen(8, 15, false));
		weinberg.addPflanze(new Pflanzen(8, 15, false));
		weinberg.addPflanze(new Pflanzen(8, 15, false));
		weinberg.addPflanze(new Pflanzen(8, 15, false));
		weinberg.addPflanze(new Pflanzen(8, 15, false));
	}
	
	@Test
	public void testGroesse() {
		assertEquals(15, weinberg.getPflanzenGroesse());
		
	}
	@Test
	public void testZucker() {
			assertEquals(8, weinberg.getZuckergehalt());
	}
	@Test
	public void testKrank() {
		assertEquals(false, weinberg.isKrank()); 
	}
	
	@Test
	public void testeEquals() {
		Winzer keinWeinberg = new Winzer("test", "Winzer"); 
		Weinberg weinberg2 = weinberg; //gleich weinberg
		Weinberg weinbergAnders = new Weinberg(keinWeinberg,new Status(), 50, 50); //andererWeinberg
		assertTrue(weinberg.equals(weinberg2));
	
		assertFalse(weinberg.equals(keinWeinberg)); 
		assertFalse(weinberg.equals(weinbergAnders)); 
	}
}
