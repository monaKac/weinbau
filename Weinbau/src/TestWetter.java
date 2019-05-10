import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Hier werden verschiedene Wetter-Objekte erzeugt
 * @author marcogoette
 *
 */
public class TestWetter {
	
	@Test
	public void testeBerechneBewolkung() {
		Weinberg weinberg = new Weinberg(new Winzer("test", "test"), new Status(), 50, 50);
		Wetter wetter1 = new Wetter(1, weinberg, 13, 12, 8, 51, 801); 
		assertEquals(Bewoelkung.REGEN, wetter1.getBewoelkung());
		Wetter wetter2 = new Wetter(1, weinberg, 0, 12, 4, 51, 801); 
		assertEquals(Bewoelkung.SCHNEE, wetter2.getBewoelkung());
		Wetter wetter3 = new Wetter(1, weinberg, 13, 75, 7, 30, 800); 
		assertEquals(Bewoelkung.STURM, wetter3.getBewoelkung());
		Wetter wetter4 = new Wetter(1, weinberg, 13, 12, 2, 30, 800); 
		assertEquals(Bewoelkung.BEWOELKT, wetter4.getBewoelkung());
		Wetter wetter5 = new Wetter(1, weinberg, 13, 12, 4, 20, 800); 
		assertEquals(Bewoelkung.LEICHT_BEWOELKT, wetter5.getBewoelkung());
		Wetter wetter6 = new Wetter(1, weinberg, 13, 12, 7, 15, 800); 
		assertEquals(Bewoelkung.SONNIG, wetter6.getBewoelkung());
	}
}
