import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author D074003
 * @version 1 
 * Enum Bewoelkung stellt alle möglichen Wetterlagen des Programms dar. 
 */
public enum Bewoelkung {

	SONNIG{
		
		/**
		 * @return Gibt den Namen der Wetterlage zurück 
		 */
		public String toString() {
			return "Sonnig"; 
		}
		
		/**
		 * 
		 * @return Gibt das Icon der Wetterlage zurück 
		 */
		public BufferedImage getIcon() {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("Sonnig.jpg"));
			} catch (IOException e) {
			}
			return img; 
		}
	}, 
	BEWOELKT{
		
		/**
		 * @return Gibt den Namen der Wetterlage zurück 
		 */
		public String toString() {
			return "Bewoelkt"; 
		}
		
		/**
		 * 
		 * @return Gibt das Icon der Wetterlage zurück 
		 */
		public BufferedImage getIcon() {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("Bewoelkt.jpg"));
			} catch (IOException e) {
			}
			return img; 
		}
	}, 
	REGEN{
		
		/**
		 * @return Gibt den Namen der Wetterlage zurück 
		 */
		public String toString() {
			return "Regnerisch"; 
		}
		
		/**
		 * 
		 * @return Gibt das Icon der Wetterlage zurück 
		 */
		public BufferedImage getIcon() {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("Regen.jpg"));
			} catch (IOException e) {
			}
			return img; 
		}
	}, 
	SCHNEE{
		
		/**
		 * @return Gibt den Namen der Wetterlage zurück 
		 */
		public String toString() {
			return "Schnee"; 
		}
		
		/**
		 * 
		 * @return Gibt das Icon der Wetterlage zurück 
		 */
		public BufferedImage getIcon() {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("Schnee.jpg"));
			} catch (IOException e) {
			}
			return img; 
		}
	}, 
	STURM{
		
		/**
		 * @return Gibt den Namen der Wetterlage zurück 
		 */
		public String toString() {
			return "Sturm"; 
		}
		
		/**
		 * 
		 * @return Gibt das Icon der Wetterlage zurück 
		 */
		public BufferedImage getIcon() {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("Sturm.jpg"));
			} catch (IOException e) {
			}
			return img; 
		}
	}, 
	LEICHT_BEWOELKT{
		
		/**
		 * @return Gibt den Namen der Wetterlage zurück 
		 */
		public String toString() {
			return "Leicht Bewoelkt"; 
		}
		
		/**
		 * 
		 * @return Gibt das Icon der Wetterlage zurück 
		 */
		public BufferedImage getIcon() {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("LeichtBewoelkt.jpg"));
			} catch (IOException e) {
			}
			return img; 
		}
	};
	

}