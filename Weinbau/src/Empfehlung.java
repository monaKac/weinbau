
public class Empfehlung {
	
	boolean warnung;
	int bewaesserungsmenge; 
	int duengen; 
	int pflanzenschutz; 
	Status empfehlungsStatus; 
	String text; 
	Weinberg weinberg; //aktueller Weinberg
	int datum;  // aktuelles Datum 
	
	public Empfehlung(Weinberg weinberg, int datum) {
		this.weinberg = weinberg; 
		this.empfehlungsStatus = weinberg.getStatus(); 
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
	
	public Status getEmpfehlungsstatus() {
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
		
		//berechnen wahrscheinlicher Niederschlag, Sonnenstunden und Temperatur über nächsten 7 Tage
		double niederschlag = 0.0; 
		double regenwahrscheinlichkeit = 0.0; 
		double sonnenstunden = 0.0; 
		double temperatur = 0.0;
		for(int i = datum; i < datum+7 ; i++) { 	
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
		
		//berechnen der benötigten Niederschlagsmenge in Abhängigkeit des erwarteten Niederschlags und der aktuellen Bodenfeuchtigkeit
		// und in Abhängigkeit der benötigten Menge je nach Alter der Reben 
		//if alter < 2 Jahre
		if(weinberg.getAlter()<24)
		bewaesserungsmenge = (int) ((( weinberg.getBodenfeuchtigkeit()/100)*10000) - niederschlag); 
		//if alter > 2 Jahre 
		if(weinberg.getAlter()>24)
		bewaesserungsmenge = (int) (((weinberg.getBodenfeuchtigkeit()/100)*8000) - niederschlag); 
		
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

	private void empfehlungKrankheitsvorbeugung(){
		
	}
	
	private void empfehlungDuengen(){
		
	}
	
	private void empfehlungWinterruhe() {
		
	}
	
	private void empfehlungReberziehung() {
		// Der Umbruchzeitpunkt richtet sich nach den Wasserverhältnissen 
		//im Boden und auch danach, ob die Weinbergsflora schon Samen gebildet hat. 
		//Wenn es die Witterung erlaubt, wird der erste Arbeitsgang erst im Mai/Juni durchgeführt.
			empfehlungBewaesserung(); 
			
		}
	
	private void empfehlungBodenarbeit() {
		//Austrieb -> Beginn Pflanzenschutz 
		empfehlungBewaesserung(); 
	}
	
	private void empfehlungPflanzenschutz() {
		// je nach Wetter 4-7 mal Spritzen 
		// wenn Feucht Gefahr von Pilzkrankheiten 
		empfehlungBewaesserung(); 
		if(this.weinberg.getStatus().getProzent()>=100) { // es wurde schon max. Anzahl gespritzt
			//nextStatus
			return; 
		}
		
		double niederschlag=0, regenwahrscheinlichkeit=0, sonnenstunden=0, temperatur=0;
		for(int i = datum; i < datum+3 ; i++) { 
			Wetter wetter = Datenbank.getWetter(i, weinberg);
			niederschlag = niederschlag + (1/3)* wetter.getNiederschlag();
			regenwahrscheinlichkeit = regenwahrscheinlichkeit + (1/3)* wetter.getRegenwahrscheinlichkeit();
			sonnenstunden = sonnenstunden + (1/3)* wetter.getSonnenstunden();
			temperatur = temperatur + (1/3)* wetter.getTemp(); 
		}
		
		if(weinberg.getBodenfeuchtigkeit()>=50) {
			if(sonnenstunden < 5 && niederschlag >1000 && regenwahrscheinlichkeit >80 && temperatur < 16) {
				//Achtung hohe Gefahr von Pilzkrankheiten
			}else if(sonnenstunden < 7 && niederschlag >700 && regenwahrscheinlichkeit >60 && temperatur < 16) {
				//Achtung erhöhte Gefahr von Pilzkrankheiten
			}else {
				//keine Gefahr von Pilzkrankheiten 
			}
			
		}else {
			if(sonnenstunden < 5 && niederschlag >2000 && regenwahrscheinlichkeit >80 && temperatur < 16) {
				//Achtung hohe Gefahr von Pilzkrankheiten
			}else if(sonnenstunden < 7 && niederschlag >1500 && regenwahrscheinlichkeit >60 && temperatur < 16) {
				//Achtung erhöhte Gefahr von Pilzkrankheiten
			}else {
				//keine Gefahr von Pilzkrankheiten 
			}
		}
		
		
		
		
		
	
	}
	
	private void empfehlungErnte() {
		empfehlungBewaesserung(); //eventuell �berladene Methode um ben�tigte Feuchtigkeit festzulegen
		Wetter wetter = Datenbank.getWetter(1, weinberg);
		if (wetter.getTemp() > 8 ) { //Temperatur noch offen
			text = "F�r optimale Winterruhe sollte die Temperatur nicht �ber 8�C betragen"; 
		}
		else {
			//weinberg.Weinbergstatus.next();
		}
	}
	
}
