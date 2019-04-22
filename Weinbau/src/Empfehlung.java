
public class Empfehlung {
	
	boolean warnung;
	int bewaesserungsmenge; 
	int duengen; 
	int pflanzenschutz; 
	Weinbergstatus empfehlungsStatus; 
	String text; 
	Weinberg weinberg; //aktueller Weinberg
	int datum;  // aktuelles Datum 
	
	public Empfehlung(Weinberg weinberg, int datum) {
		this.weinberg = weinberg; 
		this.empfehlungsStatus = weinberg.getStatus().getWeinbergstatus(); 
		this.datum = datum; 
		berechneEmpfehlung(); 
	}
	
	private void berechneEmpfehlung() {
		empfehlungBewaesserung();
		empfehlungKrankheitsvorbeugung();
		empfehlungDuengen(); 
		
		switch(weinberg.getStatus().getWeinbergstatus()){
		case WINTERRUHE: 
            //DO  
			empfehlungWinterruhe(); 
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
        case PFLANZEN: 
            //DO
        } 
		
	}
	
	public Weinbergstatus getEmpfehlungsstatus() {
		return this.empfehlungsStatus; 
	}
	
	public String getText() {
		return text;
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
	
	public int sollPflanzenschutz() {
		return pflanzenschutz; 
	}
	
	private void empfehlungBewaesserung(){
		if(weinberg.getBodenfeuchtigkeit()>50) {
			return;
		}
		
		//berechnen wahrscheinlicher Niederschlag, Sonnenstunden und Temperatur Ã¼ber nÃ¤chsten 7 Tage
		double niederschlag = 0.0; 
		double regenwahrscheinlichkeit = 0.0; 
		double sonnenstunden = 0.0; 
		double temperatur = 0.0;
		for(int i = 10; i < 17 ; i++) { 	
			Wetter wetter = Datenbank.getWetter(i, weinberg);
			niederschlag = niederschlag + wetter.getNiederschlag();
			regenwahrscheinlichkeit = regenwahrscheinlichkeit + wetter.getRegenwahrscheinlichkeit();
			sonnenstunden = sonnenstunden + wetter.getSonnenstunden();
			temperatur = temperatur + wetter.getTemp(); 
		}
		niederschlag = niederschlag / 7; 
		regenwahrscheinlichkeit = regenwahrscheinlichkeit / 7 ; 
		sonnenstunden = sonnenstunden / 7; 
		temperatur = temperatur / 7; 
		
		if(regenwahrscheinlichkeit <50) { 	
			niederschlag = 0.4 * niederschlag;
		}else if(regenwahrscheinlichkeit >85) {
			niederschlag = 0.8 * niederschlag; 
		}else {
			niederschlag = 0.7 * niederschlag; 
		}
		
		//berechnen der benÃ¶tigten Niederschlagsmenge in AbhÃ¤ngigkeit des erwarteten Niederschlags und der aktuellen Bodenfeuchtigkeit
		// und in AbhÃ¤ngigkeit der benÃ¶tigten Menge je nach Alter der Reben 
		//if alter < 2 Jahre
		bewaesserungsmenge = (int) ((( weinberg.getBodenfeuchtigkeit()/100)*10000) - niederschlag); 
		//if alter > 2 Jahre 
		bewaesserungsmenge = (int) (((weinberg.getBodenfeuchtigkeit()/100)*8000) - niederschlag); 
		
		//Temperatur und Sonnenstunden einberechnen 
		if(temperatur <15) {
			// zu vernachlÃ¤ssigende Verdunstung
		}else if(temperatur <25) {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.1);
		}else {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.3 );
		}
		
		if(sonnenstunden <3) {
			//vernachlÃ¤ssigbar 
		}else if(sonnenstunden <8) {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.1 );
		}else {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.3 );
		}
		
		
	}

	private void empfehlungKrankheitsvorbeugung(){
		
	}
	
	private void empfehlungDuengen(){
		
	}
	
	private void empfehlungWinterruhe() {
		
	}
	
	private void empfehlungReberziehung() {
			
		}
	
	private void empfehlungBodenarbeit() {
		
	}
	
	private void empfehlungPflanzenschutz() {
		
	}
	
	private void empfehlungErnte() {
		empfehlungBewaesserung(); //eventuell überladene Methode um benötigte Feuchtigkeit festzulegen
		Wetter wetter = Datenbank.getWetter(1, weinberg);
		if (wetter.getTemp() > 8 ) { //Temperatur noch offen
			text = "Für optimale Winterruhe sollte die Temperatur nicht über 8°C betragen"; 
		}
		else {
			//weinberg.Weinbergstatus.next();
		}
	}


}
