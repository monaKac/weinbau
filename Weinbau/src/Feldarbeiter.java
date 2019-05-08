import java.awt.Container;
import java.awt.GridLayout;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Feldarbeiter extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Weinberg selectedWeinberg;

	public Feldarbeiter(Winzer winzer) {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		Container c = this.getContentPane();
		
		JComboBox comboWeinberg = new JComboBox();
		winzer.getWeinberge().forEach(weinberg -> comboWeinberg.addItem(weinberg));
		selectedWeinberg = winzer.getWeinberge().get(0); 
		
		ItemListener itemListener = new ItemListener() {
		      public void itemStateChanged(ItemEvent itemEvent) {
		        int state = itemEvent.getStateChange();
		        if(state == ItemEvent.SELECTED) {
		        	selectedWeinberg = (Weinberg) itemEvent.getItem(); 
		        	System.out.println(selectedWeinberg);
		        	
		        }

		      }
		    };
		    comboWeinberg.addItemListener(itemListener);

		c.setLayout(new GridLayout(12, 1));
		c.add(new JLabel("Weinberg"));
		c.add(comboWeinberg);
		
		JLabel lablStatus = new JLabel("Aktueller Status : " + selectedWeinberg.getStatus().getWeinbergstatus().toString());
		c.add(lablStatus);
		//JButton btnStatus = new JButton("Naechster Status vorschlagen");
		
		JLabel lablBodenfeuchtigkeit = new JLabel("Bodenfeuchtigkeit : "+ selectedWeinberg.getBodenfeuchtigkeit());
		JLabel lablMineraliengehalt = new JLabel("Mineraliengehalt : "+ selectedWeinberg.getMineraliengehalt());
		JLabel lablZuckergehalt = new JLabel("Zuckergehalt : "+selectedWeinberg.getZuckergehalt());
		c.add(lablBodenfeuchtigkeit);
		c.add(lablMineraliengehalt);
		c.add(lablZuckergehalt);
		
		JTextField inKommentar = new JTextField();
		JButton btnKommentar = new JButton("Kommentar beareiten");
		inKommentar.setText(selectedWeinberg.getKommentar());
		inKommentar.setEnabled(false);
		
		c.add(new JLabel("Kommentar"));
		c.add(inKommentar);
		c.add(btnKommentar);
		
		
		
		JTextField inGroesse = new JTextField();
		JButton btnGroesse = new JButton("Groesse bearbeiten");

		c.add(inGroesse);
		c.add(new JLabel("Status"));

		this.setVisible(true);
	}
}
