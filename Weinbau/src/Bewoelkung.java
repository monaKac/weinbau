import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Bewoelkung {
	SONNIG{
		public String toString() {
			return "Sonnig"; 
		}
		
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
		public String toString() {
			return "Bewoelkt"; 
		}
		
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
		public String toString() {
			return "Regnerisch"; 
		}
		
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
		public String toString() {
			return "Schnee"; 
		}
		
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
		public String toString() {
			return "Sturm"; 
		}
		
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
		public String toString() {
			return "Leicht Bewoelkt"; 
		}
		
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