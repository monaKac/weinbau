import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class GUI extends JFrame {

	Container c = getContentPane();

	public GUI() {
		Weinberg weinberg = new Weinberg();
		this.fenster2(weinberg);

	}

	public static void main(String[] args) {
		GUI gui = new GUI();

		gui.setVisible(true);
		gui.setSize(800 /* Weinbergliste.length *125 */ , 1000);
	}

	public void fenster1() {
		
		c.removeAll();
		
		
		// in Weinberg Klasse ?
		JPanel weinberg1 = new JPanel(new BorderLayout());
		JLabel überschrift1 = new JLabel("Weinberg 1");
		JButton gehezu1 = new JButton("WARNUNGEN UND SO");
		weinberg1.add(überschrift1, BorderLayout.NORTH);
		weinberg1.add(gehezu1, BorderLayout.CENTER);
		
		// for Weinbergliste : weinbergnr --> c.add(weinbergnr.panel)
		c.add(weinberg1);
		

		JPanel option2add = new JPanel(new FlowLayout());
		JButton neuerWeinberg = new JButton("Neuen Weinberg hinzufügen");
		
		option2add.add(neuerWeinberg);
		
		JPanel slot3 = new JPanel(new BorderLayout());
		JPanel slot4 = new JPanel(new BorderLayout());

		c.setLayout(new GridLayout(2 /* Weinbergliste.length /2 */, 2  ));
	
		c.add(option2add);
		c.add(slot3);
		c.add(slot4);
		
	}

	public void fenster2(Weinberg weinberg) {
		
		c.removeAll();
		c.setLayout(new GridLayout(4,1,50,5));
		
		JPanel eins,zwei,drei,vier;
		
		eins = new JPanel(new GridLayout(2,1/*AnzahlWarnungen*/)); // Überschriften /Warnungen...
		eins.add(new JLabel("Weinberg 1"));
		eins.add(new JLabel("Warnung 1"));
		
		zwei = new JPanel(new BorderLayout()); // Wetter und so
		zwei.add(new JLabel("Wetter"), BorderLayout.NORTH);
		zwei.add(new JLabel("Hier steht das aktuelle Wetter"),BorderLayout.CENTER);
		
		drei = new JPanel(new BorderLayout()); // Niederschlag
		drei.add(new JLabel("Niederschlag"), BorderLayout.NORTH);
		drei.add(new JButton("Ich bin ein Niederschlagsdiagramm"),BorderLayout.CENTER);
		drei.add(new JButton("Bewässerung starten"),BorderLayout.SOUTH);
		
		vier = new JPanel(new GridLayout(3,2)); // Daten
		vier.add(new JLabel("Mineraliengehalt"));
		vier.add(new JButton("Düngen"));
		vier.add(new JLabel("Zuckergehalt"));
		vier.add(new JLabel("WERT ZUCKER"));
		vier.add(new JLabel("Größe der Pflanzen"));
		vier.add(new JLabel("WERT GRÖßE"));
		
		eins.setBackground(Color.lightGray);
		zwei.setBackground(Color.cyan);
		drei.setBackground(Color.lightGray);
		vier.setBackground(Color.cyan);
		
		c.add(eins);
		c.add(zwei);
		c.add(drei);
		c.add(vier);
		
		
		
		

	}
}
