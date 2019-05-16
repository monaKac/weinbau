import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		this.setTitle("Feldarbeiter Anwendung");
		Container c = this.getContentPane();
		
		JComboBox comboWeinberg = new JComboBox();
		winzer.getWeinberge().forEach(weinberg -> comboWeinberg.addItem(weinberg));
		selectedWeinberg = winzer.getWeinberge().get(0); 
		
		c.setLayout(new GridLayout(12, 1));
		
		JLabel title = new JLabel("Weinberg");
		title.setFont(new Font("Serim", Font.BOLD, 25));
		c.add(title);
		c.add(comboWeinberg);
		
		JLabel lablStatus = new JLabel("Aktueller Status : " + selectedWeinberg.getStatus().getWeinbergstatus().toString()+ " "+ selectedWeinberg.getStatus().getProzent()+" %");
		c.add(lablStatus);
		
		JPanel panlStatus = new JPanel();
		JTextField inStatus = new JTextField(); 
		inStatus.setVisible(false);
		inStatus.setEditable(false);
		JButton btnStatus = new JButton("Prozent anpassen");
		btnStatus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(inStatus.isEditable()) {
					int prozent = Integer.parseInt(inStatus.getText());
					selectedWeinberg.getStatus().setProzent(prozent);
					inStatus.setEditable(false);
					inStatus.setVisible(false);
					btnStatus.setText("Prozent anpassen");
					inStatus.setText("");
				}else {
					inStatus.setText(String.valueOf(selectedWeinberg.getStatus().getProzent()));
					inStatus.setEditable(true);
					btnStatus.setText("Prozent speichern");
					inStatus.setVisible(true);
				}
			}
				});
		
		panlStatus.add(inStatus);
		panlStatus.add(btnStatus);
		c.add(panlStatus);
		
		JLabel lablBodenfeuchtigkeit = new JLabel("Bodenfeuchtigkeit : "+ selectedWeinberg.getBodenfeuchtigkeit());
		JLabel lablMineraliengehalt = new JLabel("Mineraliengehalt : "+ selectedWeinberg.getMineraliengehalt());
		JLabel lablGroesse = new JLabel("Groesse : "+selectedWeinberg.getPflanzenGroesse()); 
		JLabel lablZuckergehalt = new JLabel("Zuckergehalt : "+selectedWeinberg.getZuckergehalt());
		JLabel lablKrankheit = new JLabel("Krankheit : "+selectedWeinberg.isKrank()); 
		c.add(lablBodenfeuchtigkeit);
		c.add(lablMineraliengehalt);
		c.add(lablGroesse); 
		c.add(lablZuckergehalt);
		c.add(lablKrankheit);
		
		JTextField inKommentar = new JTextField();
		JButton btnKommentar = new JButton("Kommentar beareiten");
		inKommentar.setText(selectedWeinberg.getKommentar());
		inKommentar.setEditable(false);
		btnKommentar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(inKommentar.isEditable()) {
					inKommentar.setEditable(false);
					btnKommentar.setText("Kommentar bearbeiten");
					selectedWeinberg.setKommentar(inKommentar.getText()); 
				}else {
					inKommentar.setEditable(true);
					btnKommentar.setText("Kommentar speichern");
				}
			}
		});

		
		c.add(new JLabel("Kommentar"));
		c.add(inKommentar);
		c.add(btnKommentar);

		
		ItemListener itemListener = new ItemListener() {
		      public void itemStateChanged(ItemEvent itemEvent) {
		        int state = itemEvent.getStateChange();
		        if(state == ItemEvent.SELECTED) {
		        	selectedWeinberg = (Weinberg) itemEvent.getItem(); 
		        	System.out.println(selectedWeinberg);
		        	lablStatus.setText("Aktueller Status : "+ selectedWeinberg.getStatus().getWeinbergstatus().toString()+ " "+ selectedWeinberg.getStatus().getProzent()+" %");
		        	 lablBodenfeuchtigkeit.setText("Bodenfeuchtigkeit : "+selectedWeinberg.getBodenfeuchtigkeit());
		    		 lablMineraliengehalt.setText("Mineraliengehalt : "+ selectedWeinberg.getMineraliengehalt());
		    		 lablGroesse.setText("Groesse : "+selectedWeinberg.getPflanzenGroesse());
		    		 lablZuckergehalt.setText("Zuckergehalt : "+ selectedWeinberg.getZuckergehalt());
		    		 lablKrankheit.setText("Krankheit : "+selectedWeinberg.isKrank());
		    		 inKommentar.setText(selectedWeinberg.getKommentar());
		    		 inKommentar.setEditable(false);
		    		 btnKommentar.setText("Kommentar bearbeiten");
		    		 inStatus.setVisible(false);
					btnStatus.setText("Prozent anpassen");
		        	
		        }

		      }
		    };
		    comboWeinberg.addItemListener(itemListener);
		
		Timer timerKommentar = new Timer();
		timerKommentar.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if (inKommentar.isEditable()==false)
					inKommentar.setText(selectedWeinberg.getKommentar());
				if (inStatus.isVisible()==false)
					lablStatus.setText("Aktueller Status : " + selectedWeinberg.getStatus().getWeinbergstatus().toString()+ " "+ selectedWeinberg.getStatus().getProzent()+" %");
				
			}
		}, 0, 1000);
		    
		this.setVisible(true);
		
	}
}
