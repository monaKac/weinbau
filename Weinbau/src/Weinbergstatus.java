
public enum Weinbergstatus {
	WINTERRUHE{
		public String toString() {
			return "Winterruhe"; 
		}
	}, REBSCHNITT{
		public String toString() {
			return "Rebschnitt"; 
		}
	}, REBERZIEHUNG{
		public String toString() {
			return "Reberziehung"; 
		}
	}, BODENARBEIT{
		public String toString() {
			return "Bodenarbeit"; 
		}
	}, PFLANZENSCHUTZ{
		public String toString() {
			return "Pflanzenschutz"; 
		}
	}, BEFRUCHTUNG{
		public String toString() {
			return "Befruchtung"; 
		}
	}, LAUBARBEIT{
		public String toString() {
			return "Laubarbeit"; 
		}
	}, ERNTE{
		public String toString() {
			return "Ernte"; 
		}
	};
}
