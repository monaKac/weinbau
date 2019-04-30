import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

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
	}
	
public static void initialisiere2(Weinberg weinberg) {
		gui = new GUI(weinberg);
		gui.setVisible(true);
		gui.setSize(800,800);
	}

public JPanel createPanel(Weinberg w) {
	JPanel panel = new JPanel(new BorderLayout());
	panel.add(new JLabel("Weinberg"+ w.getId()), BorderLayout.NORTH);
	JButton button = new JButton("Öffnen");
	button.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			GUI.initialisiere2(w);
		}
	});
	panel.add(button,BorderLayout.SOUTH);
	
	// WARNUNGEN HIER HINZUFÜGEN (BorderLayout CENTER)
	Empfehlung empfehlung = new Empfehlung(w, 10); 
	if(empfehlung.getText()!= null ) {
		panel.add(new JLabel(empfehlung.getText()), BorderLayout.CENTER);
	}
	 
	
	return panel; 
}

	public void fenster1(Winzer winzer) {
		
		c.removeAll();
		c.setLayout(new GridLayout(2,8));
		
		for (Weinberg w: winzer.getWeinberge()) {
			c.add(createPanel(w));
			
		}
	
		

		JPanel option2add = new JPanel(new FlowLayout());
		JButton neuerWeinberg = new JButton("Neuen Weinberg hinzufï¿½gen");
		
		option2add.add(neuerWeinberg);
		
		

		
	
		c.add(option2add);
		
		
	}
	

	public void fenster2(Weinberg weinberg) {
		
		c.removeAll();
		c.setLayout(new GridLayout(4,1,50,5));
		
		JPanel eins,zwei,drei,vier;

		eins = new JPanel(new GridLayout(2,1/*AnzahlWarnungen*/)); // ï¿½berschriften /Warnungen...
		eins.add(new JLabel("Weinberg "+ weinberg.getId()));
		eins.add(new JLabel("Warnung 1"));

		zwei = new JPanel(new BorderLayout()); // Wetter und so / also variable von Weinberg?
		zwei.add(new JLabel("Wetter"), BorderLayout.NORTH);
		zwei.add(new JLabel("Hier steht das aktuelle Wetter"),BorderLayout.CENTER);
		
		drei = new JPanel(new BorderLayout()); // Niederschlag
		drei.add(new JLabel("Niederschlag:"), BorderLayout.NORTH);
		drei.add(new JButton("Ich bin ein Niederschlagsdiagramm"),BorderLayout.CENTER);
		drei.add(new JButton("Bewï¿½sserung starten"),BorderLayout.SOUTH);
		
		vier = new JPanel(new GridLayout(3,2)); // Daten Durchschnitt der Pflanzen ???
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
