/**
 * 
 * @author D074003
 * @version 1.0 
 *
 */
public class Empfehlung {
	
	boolean warnung;
	int bewaesserungsmenge; 
	int duengen; 
	Status empfehlungsStatus; 
	String text; 
	String textBewaesserung; 
	String textDuenger; 
	Weinberg weinberg; //aktueller Weinberg
	int datum;  // aktuelles Datum 
	Wetter[] wettervorhersage; 
	
	/**
	 * 
	 * @param weinberg
	 * @param datum
	 * 
	 */
	public Empfehlung(Weinberg weinberg, int datum) {
		this.weinberg = weinberg; 
		this.empfehlungsStatus = weinberg.getStatus(); 
		this.datum = datum; 
		wettervorhersage = new Wetter[7]; 
		for(int i = 0; i<7; i++) {
		wettervorhersage[i] = Datenbank.getWetter(datum+i, weinberg); 
		}
		this.warnung = false; 
		this.text = ""; 
		berechneEmpfehlung();
	}
	
	private void berechneEmpfehlung() {
		
		empfehlungBewaesserung();
		empfehlungDuengen(); 
		
		switch(weinberg.getStatus().getWeinbergstatus()){
		case WINTERRUHE: 
            //DO  
			//empfehlungWinterruhe(); 
            break; 
        case REBSCHNITT: 
            //DO
            break; 
        case REBERZIEHUNG: 
            //DO
        	empfehlungReberziehung();
            break;
        case BODENARBEIT: 
            //DO
        	empfehlungBodenarbeit();
            break;
        case PFLANZENSCHUTZ: 
            //DO
        	empfehlungPflanzenschutz(); 
            break;    
        case BEFRUCHTUNG: 
            //DO
            break;    
        case LAUBARBEIT: 
            //DO
            break;
        case ERNTE: 
            //DO
        	empfehlungErnte();
            break;
        
        } 
		
	}
	
	protected void setWettervorhersage(Wetter[] wettervorhersage) {
		this.wettervorhersage = wettervorhersage;
	}
	
	protected int getBewaesserungsmenge() {
		return this.bewaesserungsmenge; 
	}
	
	protected int getDuenger() {
		return this.duengen; 
	}
	
	
	public Status getEmpfehlungsstatus() {
		return this.empfehlungsStatus; 
	}
	
	public String getText() {
		if(this.empfehlungsStatus.equals(this.weinberg.getStatus())) {
			return text; 
		}else {
		
			text = "Bitte in Phase "+ this.empfehlungsStatus.toString()+" uebergehen.";
			
			return text;
		}
		
	}
	
	public String getTextBewaesserung() {
		if(this.bewaesserungsmenge == 0) {
			return null;
		}else {
			this.textBewaesserung = "Einer Menge von "+ this.bewaesserungsmenge +" ml bewaessern.";
		return this.textBewaesserung;
		}
	}
	
	public String getTextDuenger() {
		if(this.duengen == 0) {
			return null; 
		}else {
			this.textDuenger = "Eine Menge von "+ this.duengen+ "ml duengen."; 
		return this.textDuenger;
		}
	}
	
	public boolean istWarnung() {
		return warnung; 
	}
	
	public int sollDuengen() {
		return duengen;
	}
	
	public int sollBewaessern() {
		return bewaesserungsmenge; 
	}
	
	protected void empfehlungBewaesserung(){
		if(weinberg.getBodenfeuchtigkeit()>50) {
			this.bewaesserungsmenge = 0; 
			return;
		}
		if (weinberg.getBodenfeuchtigkeit()<15) {
			this.warnung = true; 
		}
		
		//berechnen wahrscheinlicher Niederschlag, Sonnenstunden und Temperatur über nächsten 7 Tage
		int niederschlag = 0; 
		int sonnenstunden = 0; 
		int temperatur = 0;
		try {
			int tage = 7; 
			niederschlag = this.durchschnittNiederschlag(tage);
			sonnenstunden = this.durchschnitSonnenstunden(tage);
			temperatur = this.durchschnittTemp(tage);
			
		}catch(Exception e) {
			
		}
		
		
		//berechnen der benötigten Bewaesserungsmenge in Abhängigkeit des erwarteten Niederschlags und der aktuellen Bodenfeuchtigkeit
		// und in Abhängigkeit der benötigten Menge je nach Alter der Reben 
		//if alter < 2 Jahre
		if(weinberg.getAlter()<24)
		bewaesserungsmenge = (int) ((( 1- weinberg.getBodenfeuchtigkeit()*0.01)*1500) - niederschlag); 
		//if alter > 2 Jahre 
		if(weinberg.getAlter()>24)
		bewaesserungsmenge = (int) (((1- weinberg.getBodenfeuchtigkeit()*0.01)*1000) - niederschlag); 
		
		
		
		//Temperatur und Sonnenstunden einberechnen 
		if(temperatur <15) {
			// zu vernachlässigende Verdunstung
		}else if(temperatur <25) {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.1);
		}else {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.3 );
		}
		
		if(sonnenstunden <3) {
			//vernachlässigbar 
		}else if(sonnenstunden <8) {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.1 );
		}else {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.3 );
		}
		
		
	}
	
	protected void empfehlungDuengen(){
		int mineralien = this.weinberg.getMineraliengehalt();
		if(mineralien>50) {
			this.duengen = 0; 
			return;
		}
		if(mineralien <15) {
			this.warnung = true; 
		}
		double niederschlag = 0.0; 
		
		try {
			int tage = 2; 
			niederschlag = this.durchschnittNiederschlag(tage);
			
		}
		catch(Exception e){
			
		}
		
		//if( niederschlag>800) {
			this.duengen = ( 100 - mineralien ) * 3 * (1+this.weinberg.getPflazen().size()) ;
		
		
	}
	
	
	protected void empfehlungReberziehung() {
		// Der Umbruchzeitpunkt richtet sich nach den Wasserverhältnissen 
		//im Boden und auch danach, ob die Weinbergsflora schon Samen gebildet hat. 
		//Wenn es die Witterung erlaubt, wird der erste Arbeitsgang erst im Mai/Juni durchgeführt.
			empfehlungBewaesserung(); 
			
			double bodenfeuchtigkeit = this.weinberg.getBodenfeuchtigkeit();
			
			this.empfehlungsStatus = new Status(Weinbergstatus.BODENARBEIT); 
			
		}
	
	protected void empfehlungBodenarbeit() {
		// Austrieb bei 8-10C geugend Sonne, Wasser 
		//Austrieb -> Beginn Pflanzenschutz 
		empfehlungBewaesserung(); 
		this.empfehlungDuengen();
		
		double niederschlag = 0, sonnenstunden=0, temperatur = 0; 
		try {
			int tage = 7; 
			niederschlag = this.durchschnittNiederschlag(tage);
			sonnenstunden = this.durchschnitSonnenstunden(tage);
			temperatur = this.durchschnittTemp(tage);
			
		}catch (Exception e) {
			
		}
		
		if (temperatur > 9) {
			if (sonnenstunden >6) {
				if(niederschlag>800) {
					this.empfehlungsStatus = new Status(Weinbergstatus.PFLANZENSCHUTZ); 
					this.text = "Der Austrieb steht kurz bevor"; 
				}
			}
		}
	
		
		
	}
	
	protected void empfehlungPflanzenschutz() {
		String hoheGefahr = "Achtung hohe Gefahr von Pilzkrankheiten";
		String gefahr = "Achtung erhöhte Gefahr von Pilzrkrankheiten"; 
		String kGefahr ="Keine Gefahr von Pilzkrankheiten";
		// je nach Wetter 4-7 mal Spritzen 
		// wenn Feucht Gefahr von Pilzkrankheiten 
		empfehlungBewaesserung(); 
		if(this.weinberg.getStatus().getProzent()>=100) { // es wurde schon max. Anzahl gespritzt
			//nextStatus
			this.empfehlungsStatus = new Status(Weinbergstatus.BEFRUCHTUNG); 
			return; 
		}
		this.empfehlungsStatus = new Status(Weinbergstatus.PFLANZENSCHUTZ); 
		double niederschlag =0, sonnenstunden=0, temperatur=0; 
		try {
		int tage = 3;
		 niederschlag= this.durchschnittNiederschlag(tage);
		 sonnenstunden= this.durchschnitSonnenstunden(tage);
		 temperatur= this.durchschnittTemp(tage);
		}catch(Exception e) {
			
		}
		if(weinberg.getBodenfeuchtigkeit()>=50) {
			if(sonnenstunden < 5 && niederschlag >1000  && temperatur < 16) {
				//Achtung hohe Gefahr von Pilzkrankheiten
				this.text = hoheGefahr;
				this.warnung = true; 
			}else if(sonnenstunden < 7 && niederschlag >700 && temperatur < 16) {
				//Achtung erhöhte Gefahr von Pilzkrankheiten
				this.text = gefahr; 	
			}else {
				//keine Gefahr von Pilzkrankheiten 
				this.text = kGefahr; 
			}
			
		}else {
			if(sonnenstunden < 5 && niederschlag >2000 && temperatur < 16) {
				//Achtung hohe Gefahr von Pilzkrankheiten
				this.text = hoheGefahr;
				this.warnung = true; 
			}else if(sonnenstunden < 7 && niederschlag >1500  && temperatur < 16) {
				//Achtung erhöhte Gefahr von Pilzkrankheiten
				this.text = gefahr; 
			}else {
				//keine Gefahr von Pilzkrankheiten 
				this.text = kGefahr; 
			}
		}	
	
	}
	
	protected void empfehlungErnte() {
		empfehlungBewaesserung(); //eventuell �berladene Methode um ben�tigte Feuchtigkeit festzulegen
		Wetter wetter = wettervorhersage[1];
		if (wetter.getTemp() > 8 ) { //Temperatur noch offen
			text = "Fuer optimale Winterruhe sollte die Temperatur nicht ueber 8C betragen"; 
		}
		else {
			this.empfehlungsStatus= new Status(Weinbergstatus.WINTERRUHE); 
		}
	}
	
	/**
	 * 
	 * @param tage
	 * Muss zwischen 1 und 7 liegen
	 * @return Durchschnittlicher Niederschlag fuer <code>tage</code> unterberuecksichtigung der Regenwahrscheinlichkeit
	 * 
	 * @throws Exception falls Tage nicht zwischen 1und 7 liegen 
	 */
	protected int durchschnittNiederschlag(int tage) throws Exception{
		if (tage <1 || tage >7 ) {
			throw new RuntimeException("Tage muessen zwischen 1 und 7 liegen");
		}
		double niederschlag = 1.0; 
		for (int i = 0; i< tage; i++) {
			Wetter wetter = wettervorhersage[i]; 
			//System.out.println(wetter.toString());
			//niederschlag = (80*0.01); 
			niederschlag = niederschlag + wetter.getNiederschlag()* (wetter.getRegenwahrscheinlichkeit()*0.01);
		}
		return (int) (niederschlag/tage); 
	}
	
	/**
	 * 
	  * @param tage
	 * Muss zwischen 1 und 7 liegen
	 * @return Durchschnittliche Temperatur fuer <code>tage</code> 
	 * 
	 * @throws Exception falls Tage nicht zwischen 1und 7 liegen 
	 */
	protected int durchschnittTemp(int tage) throws Exception{
		if (tage <1 || tage >7 ) {
			throw new RuntimeException("Tage muessen zwischen 1 und 7 liegen");
		}
		double temp =0; 
		for (int i = 0; i< tage; i++) {
			Wetter wetter = wettervorhersage[i]; 
			temp = temp + wetter.getTemp();
		}
		return (int) temp/tage; 
	}
	
	/**
	 * 
	  * @param tage
	 * Muss zwischen 1 und 7 liegen
	 * @return Durchschnittliche Sonnenstunden fuer <code>tage</code> 
	 * 
	 * @throws Exception falls Tage nicht zwischen 1und 7 liegen 
	 */
	protected int durchschnitSonnenstunden(int tage) throws Exception{
		if (tage <1 || tage >7 ) {
			throw new RuntimeException("Tage muessen zwischen 1 und 7 liegen");
		}
		double temp =0; 
		for (int i = 0; i< tage; i++) {
			Wetter wetter = wettervorhersage[i]; 
			temp = temp + wetter.getSonnenstunden();
		}
		return (int) temp/tage; 
	}
	
	
	
	
}
