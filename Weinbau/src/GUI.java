import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {

	int datum = 10;
	Container c = getContentPane();
	static GUI gui;

	public GUI(Winzer winzer) {

		fenster1(winzer);

	}

	public GUI(Weinberg weinberg) {
		fenster2(weinberg);
	}

	public static void initialisiere(Winzer winzer) {
		gui = new GUI(winzer);

		gui.setVisible(true);
		gui.setSize(800 /* Weinbergliste.length *125 */ , 800);
		gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void initialisiere2(Weinberg weinberg) {
		gui = new GUI(weinberg);
		gui.setVisible(true);
		gui.setSize(800, 800);
	}

	public JPanel createPanel(Weinberg w) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Weinberg" + w.getId()), BorderLayout.NORTH);
		JButton button = new JButton("Öffnen");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				GUI.initialisiere2(w);
			}
		});
		panel.add(button, BorderLayout.SOUTH);

		// WARNUNGEN HIER HINZUFÜGEN (BorderLayout CENTER)
		Empfehlung empfehlung = new Empfehlung(w, 10);
		if (empfehlung.getText() != null) {
			panel.add(new JLabel(empfehlung.getText()), BorderLayout.CENTER);
		}

		return panel;
	}

	public void fenster1(Winzer winzer) {

		c.removeAll();
		c.setLayout(new GridLayout(2, 8));

		for (Weinberg w : winzer.getWeinberge()) {
			c.add(createPanel(w));

		}

		JPanel option2add = new JPanel(new FlowLayout());
		JButton neuerWeinberg = new JButton("Neuen Weinberg hinzufuegen");

		option2add.add(neuerWeinberg);

		c.add(option2add);

	}

	public void fenster2(Weinberg weinberg) {

		c.removeAll();
		c.setLayout(new GridLayout(4, 1, 50, 5));

		Wetter wetter = Datenbank.getWetter(datum, weinberg); // Datum ändern

		JPanel eins, zwei, drei, vier;

		// Panel 1 Uberschrift + Status + evtl Warnungen + Hinweise
		eins = new JPanel(new GridLayout(5, 1));

		// Uberschrift/Name der Weinbergs
		JLabel wname = new JLabel("Weinberg: " + weinberg.getName());
		wname.setFont(new Font("Serim", Font.BOLD, 30));
		eins.add(wname);

		// Status
		JLabel wstatus = new JLabel("Status: " + weinberg.getStatus().status.toString());
		wstatus.setFont(new Font("Serim", Font.PLAIN, 20));
		eins.add(wstatus);

		// Kommentar Feldarbeiter
		eins.add(new JLabel("Kommentar der Feldarbeiter: " + weinberg.getKommentar()));

		// Panel 2 Wetterdaten
		zwei = new JPanel(new BorderLayout());

		// Überschrift
		JLabel wwetter = new JLabel("Wetter");
		wwetter.setFont(new Font("Serim", Font.PLAIN, 20));
		zwei.add(wwetter, BorderLayout.NORTH);

		JPanel splitmeinhalf = new JPanel(new GridLayout(1, 2));

		JPanel leftside = new JPanel(new GridLayout(5, 1));
		// Wetterdaten auf linke seite
		leftside.add(new JLabel("Temperatur: " + wetter.getTemp()));
		leftside.add(new JLabel("Bewoelkung: " + wetter.getBewoelkung()));
		leftside.add(new JLabel("Windgeschwindigkeit: " + wetter.getWind()));
		leftside.add(new JLabel("Sonnenstunden: " + wetter.getSonnenstunden()));
		leftside.add(new JLabel("Regenwahrscheinlichkeit: " + wetter.getRegenwahrscheinlichkeit()));
		splitmeinhalf.add(leftside);

		// Wettericon auf rechte seite
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(getClass().getResource(findmyicon(wetter.getBewoelkung())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		splitmeinhalf.add(picLabel);
		zwei.add(splitmeinhalf, BorderLayout.CENTER);

		drei = new JPanel(new BorderLayout()); // Niederschlag
		drei.add(new JLabel("Niederschlag:"), BorderLayout.NORTH);
		drei.add(new JButton("Ich bin ein Niederschlagsdiagramm"), BorderLayout.CENTER);
		drei.add(new JButton("Bewaesserung starten"), BorderLayout.SOUTH);

		vier = new JPanel(new GridLayout(3, 2)); // Daten Durchschnitt der Pflanzen ???
		vier.add(new JLabel("Mineraliengehalt"));
		vier.add(new JButton("Düngen"));
		vier.add(new JLabel("Zuckergehalt"));
		vier.add(new JLabel("WERT ZUCKER"));

		vier.add(new JLabel("Größe der Pflanzen"));
		vier.add(new JLabel("WERT GRoessE"));

		/*
		 * eins.setBackground(Color.lightGray); zwei.setBackground(Color.cyan);
		 * drei.setBackground(Color.lightGray); vier.setBackground(Color.cyan);
		 */
		c.add(eins);
		c.add(zwei);
		c.add(drei);
		c.add(vier);
	}

	
	
	public String findmyicon(Bewoelkung eingabe) {

		switch (eingabe) {
		case BEWOELKT:
			return "bewoelkt.jpg";
		case LEICHT_BEWOELKT:
			return "leichtBewoelkt.jpg";
		case REGEN:
			return "regen.jpg";
		case SCHNEE:
			return "schnee.jpg";
		case SONNIG:
			return "sonnig.jpg";
		case STURM:
			return "sturm.jpg";

		default:
			break;
		}
		
		return null;

	}

}
