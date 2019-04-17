
public class Empfehlung {
	
	boolean warnung;
	int bewaessern; 
	int duengen; 
	int pflanzenschutz; 
	String text; 
	Weinberg weinberg; //aktueller Weinberg
	int datum;  // aktuelles Datum 
	
	public Empfehlung(Weinberg weinberg, int datum) {
		this.weinberg = weinberg; 
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
            break;
        case PFLANZEN: 
            //DO
        } 
		
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
		return bewaessern; 
	}
	
	public int sollPflanzenschutz() {
		return pflanzenschutz; 
	}
	
	private void empfehlungBewaesserung(){
		if(weinberg.getBodenfeuchtigkeit()>50) {
			return;
		}
		double niederschlag = 0.0; 
		double regenwahrscheinlichkeit = 0.0; 
		for(int i = 0; i < 7 ; i++) {
			Wetter wetter = Datenbank.getWetter(i, weinberg);
			niederschlag = niederschlag + wetter.getNiederschlag();
			regenwahrscheinlichkeit = regenwahrscheinlichkeit + wetter.getRegenwahrscheinlichkeit();
		}
		niederschlag = niederschlag / 7; 
		regenwahrscheinlichkeit = regenwahrscheinlichkeit / 7 ; 
//		if() {
//			
//		}else if() {
//			
//		}else {
//			
//		}
		
		
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
	
	

}
