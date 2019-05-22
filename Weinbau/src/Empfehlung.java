/**
 * 
 * @author D074003
 * @version 1.0
 *
 */
public class Empfehlung  {

	boolean		warnung;
	int			bewaesserungsmenge;
	int			duengen;
	Status		empfehlungsStatus;
	String		text;
	String		textBewaesserung;
	String		textDuenger;
	Weinberg	weinberg;			// aktueller Weinberg
	int			datum;				// aktuelles Datum
	Wetter[]	wettervorhersage;

	/**
	 * 
	 * @param weinberg
	 * @param datum    Konstruktor zieht sich Wetterdaten der naechsten 7 Tage und
	 *                 starte die Berechnung der Empfehlung
	 */
	public Empfehlung(Weinberg weinberg, int datum) {
		this.weinberg = weinberg;
		this.empfehlungsStatus = weinberg.getStatus();
		this.datum = datum;
		wettervorhersage = new Wetter[7];
		for (int i = 0; i < 7; i++) {
			wettervorhersage[i] = Datenbank.getWetter(datum + i, weinberg);
		}
		this.warnung = false;
		this.text = "";
		berechneEmpfehlung();
	}

	/**
	 * Startet Empfehlung Bewaesserung und Duengen Leitet je nach aktuellem Status
	 * des Weinberges die richtige Empfehlungsberechnung ein
	 */
	private void berechneEmpfehlung() {
		try {
			empfehlungBewaesserung();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		empfehlungDuengen();
		try {

			switch (weinberg.getStatus().getWeinbergstatus()) {
			case WINTERRUHE:
				empfehlungWinterruhe();
				break;
			case REBSCHNITT:
				empfehlungRebschnitt();
				break;
			case REBERZIEHUNG:
				empfehlungReberziehung();
				break;
			case BODENARBEIT:
				empfehlungBodenarbeit();
				break;
			case PFLANZENSCHUTZ:
				empfehlungPflanzenschutz();
				break;
			case BEFRUCHTUNG:
				empfehlungBefruchtung(); 
				break;
			case LAUBARBEIT:
				empfehlungLaubarbeit();
				break;
			case ERNTE:
				empfehlungErnte();
				break;

			}
		} catch (Exception e) {
			text = "Es ist ein Fehler aufgetreten, die Empfehlung konnte nicht Sachgemaess berechnet werden!";
		}

	}

	/**
	 * 
	 * @param wettervorhersage Wetter[] Ermoeglicht Manipulation der
	 *                         Wettervorhersage wegen Testzwecken
	 */
	protected void setWettervorhersage(Wetter[] wettervorhersage) {
		this.wettervorhersage = wettervorhersage;
	}

	/**
	 * 
	 * @return Gibt den Status der Empfohlen wird zuerueck
	 */
	public Status getEmpfehlungsstatus() {
		return this.empfehlungsStatus;
	}

	/**
	 * 
	 * @return Gibt Empfehlungstext fuer GUI zurueck
	 */
	public String getText() {
		if (this.empfehlungsStatus.equals(this.weinberg.getStatus())) {
			return text;
		} else {

			text = "Bitte in Phase " + this.empfehlungsStatus.getWeinbergstatus().toString() + " uebergehen.";

			return text;
		}

	}

	/**
	 * 
	 * @return Gibt Empfehlungstext fuer Bewaesserungsmenge zurueck
	 */
	public String getTextBewaesserung() {
		if (this.bewaesserungsmenge == 0) {
			return null;
		} else {
			this.textBewaesserung = "Einer Menge von " + this.bewaesserungsmenge + " ml bewaessern.";
			return this.textBewaesserung;
		}
	}

	/**
	 * 
	 * @return Gibt Empfehlungstext fuer Duengermenge zurueck
	 */
	public String getTextDuenger() {
		if (this.duengen == 0) {
			return null;
		} else {
			this.textDuenger = "Eine Menge von " + this.duengen + "ml duengen.";
			return this.textDuenger;
		}
	}

	/**
	 * 
	 * @return Gibt zuerueck ob Empfehlung den Status einer Warnung hat
	 */
	public boolean istWarnung() {
		return warnung;
	}

	/**
	 * 
	 * @return Menge die geduengt werden soll
	 */
	public int sollDuengen() {
		return duengen;
	}

	/**
	 * 
	 * @return Menge die bewaessert werden soll
	 */
	public int sollBewaessern() {
		return bewaesserungsmenge;
	}

	/**
	 * Berechnet die Menge die Bewaessert werden soll
	 * <code>this.bewaesserungsmenge</code>
	 * berechnen der benötigten Bewaesserungsmenge in Abhängigkeit des erwarteten
	 *  Niederschlags und der aktuellen Bodenfeuchtigkeit
	 *  und in Abhängigkeit der benötigten Menge je nach Alter der Reben
	 */
	protected void empfehlungBewaesserung() throws Exception{
		if (weinberg.getBodenfeuchtigkeit() > weinberg.getStatus().getWeinbergstatus().getMinBodenfeuchtigkeit()) {
			this.bewaesserungsmenge = 0;
			return;
		}
		if (weinberg.getBodenfeuchtigkeit() < 15) {
			this.warnung = true;
		}

		int niederschlag = 0;
		int sonnenstunden = 0;
		int temperatur = 0;

			int tage = 7;
			niederschlag = this.durchschnittNiederschlag(tage);
			sonnenstunden = this.durchschnitSonnenstunden(tage);
			temperatur = this.durchschnittTemp(tage);


		// if alter < 2 Jahre
		if (weinberg.getAlter() < 24)
			bewaesserungsmenge = (int) (((1 - weinberg.getBodenfeuchtigkeit() * 0.01) * 1500) - niederschlag);
		// if alter > 2 Jahre
		if (weinberg.getAlter() > 24)
			bewaesserungsmenge = (int) (((1 - weinberg.getBodenfeuchtigkeit() * 0.01) * 1000) - niederschlag);

		if (temperatur < 15) {
			// zu vernachlässigende Verdunstung
		} else if (temperatur < 25) {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.1);
		} else {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.3);
		}

		if (sonnenstunden < 3) {
			// vernachlässigbar
		} else if (sonnenstunden < 8) {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.1);
		} else {
			bewaesserungsmenge = (int) (bewaesserungsmenge * 1.3);
		}
		
		if (bewaesserungsmenge < 0 ) {
			bewaesserungsmenge = 0; 
		}
	}

	/**
	 * Berechnet die Menge die geduengt werden soll <code>this.duengen</code>
	 */
	protected void empfehlungDuengen() {
		int mineralien = this.weinberg.getMineraliengehalt();
		if (mineralien > weinberg.getStatus().getWeinbergstatus().getMineraliengehalt()) {
			this.duengen = 0;
			return;
		}
		if (mineralien < 15) {
			this.warnung = true;
		}
		
		this.duengen = (100 - mineralien) * 3 * (1 + this.weinberg.getPflanzen().size());
		if (duengen < 0) {
			duengen = 0; 
		}
	}
	
	/**
	 * Übergang von Winteruhe zu Rebschnitt sobald Austrieb beginnt. 
	 * ( durchschnittliche Tagestemperatur von 8 bis 10 C )
	 * @throws Exception
	 */
	protected void empfehlungWinterruhe() throws Exception {
		this.empfehlungsStatus = weinberg.getStatus(); 
		this.text = "Sobald der Austrieb beginnt sollte zum Rebschnitt übergegangen werden";
		if (weinberg.getStatus().getProzent() == 100) {
			int temperatur = this.durchschnittTemp(7);
			if (temperatur > this.weinberg.getStatus().getWeinbergstatus().getMinTemperatur() && temperatur < this.weinberg.getStatus().getWeinbergstatus().getMaxTemperatur()) {
				this.empfehlungsStatus = new Status(Weinbergstatus.REBSCHNITT);
			}
		}
	}

	/**
	 * Berechnet die Empfehlung fuer den Status Rebschnitt (Uebergang zu
	 * Reberziehung) Sobald der Rebschnitt beendet ist und das Wetter feucht genug
	 * ist wird mit der Reberziehung begonnen Das feuchte Wetter wird benoetigt um
	 * berechen der Reben vorzubeugen
	 */
	protected void empfehlungRebschnitt() throws Exception {
		this.empfehlungsStatus = weinberg.getStatus();
		if (weinberg.getStatus().getProzent() == 100) {
			int niederschlag = this.durchschnittNiederschlag(7);
			if (niederschlag > weinberg.getStatus().getWeinbergstatus().getMinNiederschlag())
				this.empfehlungsStatus = new Status(Weinbergstatus.REBERZIEHUNG);
		}
	}

	/**
	 * Berechnet die Empfehlung fuer den Status Reberziehung ( Uebergang in
	 * Bodenarbeit) Dazu darf der Boden nicht zu trocken sein aber das Wetter auch
	 * nicht zu regnerisch, da sonst die Gefahr von Bodenerosionen besteht. Der
	 * Umbruchzeitpunkt richtet sich nach den Wasserverhältnissen
	 */
	protected void empfehlungReberziehung() throws Exception {
		this.empfehlungsStatus = weinberg.getStatus();
		if (weinberg.getStatus().getProzent() < 100) {
			return;
		}
		int bodenfeuchtigkeit = this.weinberg.getBodenfeuchtigkeit();
		if (bodenfeuchtigkeit < weinberg.getStatus().getWeinbergstatus().getMinBodenfeuchtigkeit())
			this.text = "Der Boden ist noch zu trocken um zur Bodenarbeit überzugehen";
		if (bodenfeuchtigkeit > weinberg.getStatus().getWeinbergstatus().getMaxBodenfeuchtigkeit())
			this.text = "Der Boden ist noch zu  feucht um zur Bodenarbeit überzugehen";
		if (bodenfeuchtigkeit > weinberg.getStatus().getWeinbergstatus().getMinBodenfeuchtigkeit() 
				&& bodenfeuchtigkeit < weinberg.getStatus().getWeinbergstatus().getMaxBodenfeuchtigkeit()) {
			this.text = "Das Wetter ist zu regnerisch um zur Bodenarbeit überzugehen";
			boolean starkRegen = false;
			for (int i = 0; i < 7; i++) {
				Wetter wetter = wettervorhersage[i];
				if (wetter.getNiederschlag() > weinberg.getStatus().getWeinbergstatus().getMaxNiederschlag())
					starkRegen = true;
			}
			if (starkRegen == false) {
				this.empfehlungsStatus = new Status(Weinbergstatus.BODENARBEIT);
			}
		}

	}

	/**
	 * Berechnet die Empfehlung fuer den Status Bodenarbeit ( Uebergang in
	 * Pflanzenschutz)
	 * Sobald die Bodenarbeit beendet wurde und das Wetter Sonnig, Warm ist und es genug regnet beginnt der Austrieb
	 * Damit beginnt der Pflanzenschutz
	 */
	protected void empfehlungBodenarbeit() throws Exception {
		this.empfehlungsStatus = weinberg.getStatus();
		if (weinberg.getStatus().getProzent() == 100) {
			int tage = 7;
			double niederschlag = this.durchschnittNiederschlag(tage);
			double sonnenstunden = this.durchschnitSonnenstunden(tage);
			double temperatur = this.durchschnittTemp(tage);
			text="Das Wetter ist nicht geeignet um zu Pflanzenschutz ueberzugehen"; 
			if (temperatur > weinberg.getStatus().getWeinbergstatus().getMinTemperatur()) {
				if (sonnenstunden > weinberg.getStatus().getWeinbergstatus().getSonnenstunden()) {
					if (niederschlag > weinberg.getStatus().getWeinbergstatus().getMinNiederschlag()) {
						this.empfehlungsStatus = new Status(Weinbergstatus.PFLANZENSCHUTZ);
					}
				}
			}
		}
	}

	/**
	 * Berechnet die Empfehlung fuer den Status Pflanzenschutz ( Uebergang in
	 * Befruchtung)
	 * Wenn das Wetter feucht ist besteht die Gefahr vor Pilzkrankheiten. Vorbeugend wird Pflanzenschutzmittel verwendet.
	 * Es wird je nach Wetter 4-7 mal Pflanzenschutzmittel gespritzt 
	 * 
	 */
	protected void empfehlungPflanzenschutz() throws Exception{
		this.empfehlungsStatus = weinberg.getStatus();
		String hoheGefahr = "Achtung hohe Gefahr von Pilzkrankheiten";
		String gefahr = "Achtung erhöhte Gefahr von Pilzrkrankheiten";
		String kGefahr = "Keine Gefahr von Pilzkrankheiten";
		if (this.weinberg.getStatus().getProzent() >= 100) { // es wurde schon max. Anzahl gespritzt
			this.empfehlungsStatus = new Status(Weinbergstatus.BEFRUCHTUNG);
			return;
		}
		this.empfehlungsStatus = new Status(Weinbergstatus.PFLANZENSCHUTZ);
		int niederschlag = 0, sonnenstunden = 0, temperatur = 0;

			int tage = 3;
			niederschlag = this.durchschnittNiederschlag(tage);
			sonnenstunden = this.durchschnitSonnenstunden(tage);
			temperatur = this.durchschnittTemp(tage);

		int mittlereBodenfeuchtigkeit = (int) ((weinberg.getStatus().getWeinbergstatus().getMaxBodenfeuchtigkeit()+weinberg.getStatus().getWeinbergstatus().getMinBodenfeuchtigkeit())/2);
		if (weinberg.getBodenfeuchtigkeit() >= mittlereBodenfeuchtigkeit) {
			if (sonnenstunden < (weinberg.getStatus().getWeinbergstatus().getSonnenstunden()*0.5) && niederschlag > (weinberg.getStatus().getWeinbergstatus().getMaxNiederschlag()) && temperatur < (weinberg.getStatus().getWeinbergstatus().getMinTemperatur())) {
				// Achtung hohe Gefahr von Pilzkrankheiten
				this.text = hoheGefahr;
				this.warnung = true;
			} else if (sonnenstunden < (weinberg.getStatus().getWeinbergstatus().getSonnenstunden()*0.07) && niederschlag > (weinberg.getStatus().getWeinbergstatus().getMaxNiederschlag()*0.7) && temperatur < weinberg.getStatus().getWeinbergstatus().getMinTemperatur()) {
				// Achtung erhöhte Gefahr von Pilzkrankheiten
				this.text = gefahr;
			} else {
				// keine Gefahr von Pilzkrankheiten
				this.text = kGefahr;
			}

		} else {
			if (sonnenstunden < (weinberg.getStatus().getWeinbergstatus().getSonnenstunden()*0.5) && niederschlag > (weinberg.getStatus().getWeinbergstatus().getMaxNiederschlag()) && temperatur < weinberg.getStatus().getWeinbergstatus().getMinTemperatur()) {
				// Achtung hohe Gefahr von Pilzkrankheiten
				this.text = hoheGefahr;
				this.warnung = true;
			} else if (sonnenstunden < (weinberg.getStatus().getWeinbergstatus().getSonnenstunden()*0.7) && niederschlag > weinberg.getStatus().getWeinbergstatus().getMaxNiederschlag()*0.7 && temperatur < weinberg.getStatus().getWeinbergstatus().getMinTemperatur()) {
				// Achtung erhöhte Gefahr von Pilzkrankheiten
				this.text = gefahr;
			} else {
				// keine Gefahr von Pilzkrankheiten
				this.text = kGefahr;
			}
		}

	}
	/**
	 * Berechnet die Empfehlung fuer den Status Befruchtung ( Uebergang in Laubarbeit )
	 * Wechsel zu Laubarbeit bei hoeheren Windgeschwindigkeiten um Triebe festzubinden 
	 * als auch bei regnerischem Wetter um Blaetter aufzulesen um die Durchlueftung der
	 * Pflanzen zu foerdern
	 */
	
	protected void empfehlungBefruchtung() throws Exception {
		this.empfehlungsStatus = weinberg.getStatus();
		if (weinberg.getStatus().getProzent() < 100) {
			return;
		}
		int tage = 7;
		int niederschlag = this.durchschnittNiederschlag(tage);
		int sonnenstunden = this.durchschnitSonnenstunden(tage);
		if(niederschlag < this.weinberg.getStatus().getWeinbergstatus().getMinNiederschlag()) {
			text = "Niederschlag zu gering um zur Laubarbeit ueberzugehen";
		}
		if(niederschlag >= this.weinberg.getStatus().getWeinbergstatus().getMinNiederschlag() & sonnenstunden > this.weinberg.getStatus().getWeinbergstatus().getSonnenstunden()) {
			text = "Bodenfeuchtigkeit zu gering um zur Laubarbeit ueberzugehen";
		}
		if( niederschlag >= 750) {
			this.empfehlungsStatus = new Status(Weinbergstatus.LAUBARBEIT);
		}
		
		
	}
	
	/**
	 *Berechnet die Empfehlung fuer den Status Laubarbeit ( Uebergang in Ernte )
	 *Faktoren fuer den Uebergang in Ernte belaufen sich auf Zuckergehalt und Groesse der Pflanzen
	 * 
	 */
	protected void empfehlungLaubarbeit() throws Exception{
		this.empfehlungsStatus = weinberg.getStatus();
		if (weinberg.getStatus().getProzent() < 100) {
			if (this.durchschnittWind(3) > this.weinberg.getStatus().getWeinbergstatus().getWind() ) {
				this.warnung = true; 
				text ="Achtung starker Wind! Es besteht die Gefahr, dass Reben umknicken! Bitte Laubarbeit durchführen!"; 
			}
			return;
		}

		if (weinberg.getPflanzenGroesse() < this.weinberg.getStatus().getWeinbergstatus().getGroesse() ) {  
			text = "Pflanzen sind noch nicht gro� genug um geerntet zu werden";
		}
		if (weinberg.getZuckergehalt() < this.weinberg.getStatus().getWeinbergstatus().getZuckergehalt()  ) {
			text = "Zuckergehalt der Pflanzen noch nicht hoch genug";
		}
		if (weinberg.getPflanzenGroesse() >= this.weinberg.getStatus().getWeinbergstatus().getGroesse() && weinberg.getZuckergehalt() >= this.weinberg.getStatus().getWeinbergstatus().getZuckergehalt()) {
			this.empfehlungsStatus = new Status(Weinbergstatus.ERNTE);
		}

	}

	/**
	 * Berechnet die Empfehlung fuer den Status Ernte ( Uebergang in Winterruhe)
	 */
	protected void empfehlungErnte() {
		this.empfehlungsStatus = this.weinberg.getStatus(); 
		if ( this.weinberg.getStatus().getProzent()<100) {
			return; 
		}
		Wetter wetter = wettervorhersage[1];
		if (wetter.getTemp() > this.weinberg.getStatus().getWeinbergstatus().getMaxTemperatur()) { // Temperatur noch offen
			text = "Fuer optimale Winterruhe sollte die Temperatur nicht ueber "+this.weinberg.getStatus().getWeinbergstatus().getMaxTemperatur()+"C betragen";
		} else {
			this.empfehlungsStatus = new Status(Weinbergstatus.WINTERRUHE);
		}
	}

	/**
	 * 
	 * @param tage Muss zwischen 1 und 7 liegen
	 * @return Durchschnittlicher Niederschlag fuer <code>tage</code>
	 *         unterberuecksichtigung der Regenwahrscheinlichkeit
	 * 
	 * @throws Exception falls Tage nicht zwischen 1und 7 liegen
	 */
	protected int durchschnittNiederschlag(int tage) throws Exception {
		if (tage < 1 || tage > 7) {
			throw new RuntimeException("Tage muessen zwischen 1 und 7 liegen");
		}
		double niederschlag = 1.0;
		for (int i = 0; i < tage; i++) {
			Wetter wetter = wettervorhersage[i];
			// System.out.println(wetter.toString());
			// niederschlag = (80*0.01);
			niederschlag = niederschlag + wetter.getNiederschlag() * (wetter.getRegenwahrscheinlichkeit() * 0.01);
		}
		return (int) (niederschlag / tage);
	}
	
	/**
	 * 
	 * @param tage Muss zwischen 1 und 7 liegen
	 * @return Durchschnittlicher Wind fuer <code>tage</code>
	 *         
	 * 
	 * @throws Exception falls Tage nicht zwischen 1und 7 liegen
	 */
	protected int durchschnittWind(int tage) throws Exception {
		if (tage < 1 || tage > 7) {
			throw new RuntimeException("Tage muessen zwischen 1 und 7 liegen");
		}
		double wind = 1.0;
		for (int i = 0; i < tage; i++) {
			Wetter wetter = wettervorhersage[i];
			// System.out.println(wetter.toString());
			wind = wind + wetter.getWind();
		}
		return (int) (wind / tage);
	}

	/**
	 * 
	 * @param tage Muss zwischen 1 und 7 liegen
	 * @return Durchschnittliche Temperatur fuer <code>tage</code>
	 * 
	 * @throws Exception falls Tage nicht zwischen 1und 7 liegen
	 */
	protected int durchschnittTemp(int tage) throws Exception {
		if (tage < 1 || tage > 7) {
			throw new RuntimeException("Tage muessen zwischen 1 und 7 liegen");
		}
		double temp = 0;
		for (int i = 0; i < tage; i++) {
			Wetter wetter = wettervorhersage[i];
			temp = temp + wetter.getTemp();
		}
		return (int) temp / tage;
	}
	
	/**
	 * 
	 * @param tage Muss zwischen 1 und 7 liegen
	 * @return Durchschnittliche Sonnenstunden fuer <code>tage</code>
	 * 
	 * @throws Exception falls Tage nicht zwischen 1und 7 liegen
	 */
	protected int durchschnitSonnenstunden(int tage) throws Exception {
		if (tage < 1 || tage > 7) {
			throw new RuntimeException("Tage muessen zwischen 1 und 7 liegen");
		}
		double temp = 0;
		for (int i = 0; i < tage; i++) {
			Wetter wetter = wettervorhersage[i];
			temp = temp + wetter.getSonnenstunden();
		}
		return (int) temp / tage;
	}
	

}
