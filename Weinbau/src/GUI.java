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
		JButton button = new JButton("�ffnen");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				GUI.initialisiere2(w);
			}
		});
		panel.add(button, BorderLayout.SOUTH);

		// WARNUNGEN HIER HINZUF�GEN (BorderLayout CENTER)
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
		Color background = Color.WHITE;
		c.setBackground(background);

		Wetter wetter = Datenbank.getWetter(datum, weinberg); // Datum �ndern

		JPanel eins, zwei, drei, vier;

		// Panel 1 Uberschrift + Status + evtl Warnungen + Hinweise
		//
		//
		//
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
		//
		//
		//
		zwei = new JPanel(new BorderLayout());

		// �berschrift
		JLabel wwetter = new JLabel("Wetter:");
		wwetter.setFont(new Font("Serim", Font.BOLD, 20));
		zwei.add(wwetter, BorderLayout.NORTH);

		JPanel splitmeinhalf = new JPanel(new GridLayout(1, 2));

		JPanel leftside = new JPanel(new GridLayout(5, 1));
		// Wetterdaten auf linke seite
		leftside.add(new JLabel("Temperatur: " + wetter.getTemp()));
		leftside.add(new JLabel("Bewoelkung: " + wetter.getBewoelkung()));
		leftside.add(new JLabel("Windgeschwindigkeit: " + wetter.getWind()));
		leftside.add(new JLabel("Sonnenstunden: " + wetter.getSonnenstunden()));
		leftside.setBackground(background);
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
		splitmeinhalf.setBackground(background);
		zwei.add(splitmeinhalf, BorderLayout.CENTER);
		// Panel 3 Niederschlag
		//
		//
		//
		drei = new JPanel(new GridLayout(1, 2));
		JPanel niederschlaglinks = new JPanel(new GridLayout(5, 1));

		// Ueberschrift
		JLabel wniederschlag = new JLabel("Niederschlag:");
		wniederschlag.setFont(new Font("Serim", Font.BOLD, 20));
		niederschlaglinks.add(wniederschlag);
		// Daten
		niederschlaglinks
				.add(new JLabel("Regenwahrscheinlichkeit heute: " + wetter.getRegenwahrscheinlichkeit() + "%"));
		niederschlaglinks.add(new JLabel("Niederschlag: " + wetter.getNiederschlag()));
		drei.add(niederschlaglinks);

		// Der Versuch eines Diagramms
		JPanel diagramm = new JPanel() {

			int last = 0;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i = 7; i >= 0; i--) {

					Wetter wetterx = Datenbank.getWetter(datum - 7 + i, weinberg);
					int regen = wetterx.getNiederschlag() / 100;
					int temp = (int) wetterx.getTemp() * 4;
					g.setColor(Color.BLUE);
					g.fillRect(i * 50, 175 - regen, 20, regen);
					g.setColor(Color.RED);
					if (last != 0)
						g.drawLine(i * 50 + 50, 200 - last, i * 50, 200 - temp);
					;
					last = temp;
				}
			}
		};
		drei.add(diagramm);

		vier = new JPanel(new GridLayout(3, 2));
		vier.add(new JLabel("Mineraliengehalt"));
		vier.add(new JButton("D�ngen"));
		vier.add(new JLabel("Zuckergehalt"));
		vier.add(new JLabel("WERT ZUCKER"));

		vier.add(new JLabel("Gr��e der Pflanzen"));
		vier.add(new JLabel("WERT GRoessE"));

		eins.setBackground(background);
		zwei.setBackground(background);
		drei.setBackground(background);
		vier.setBackground(background);

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
