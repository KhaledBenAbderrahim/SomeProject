package de.akademie.hausverwaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.akademie.hausverwaltung.mieter.Mieter;
import de.akademie.hausverwaltung.zimmern.Zimmer;

public class HausVerwaltung {

	private List<Immobiele> immobilien = new ArrayList<Immobiele>();
	private List<Mieter> mietern = new ArrayList<Mieter>();
	
	private String name;
	
	HausVerwaltung(final String name){
		this.name = name;
	}
	
	public void addImmobiele(Immobiele gebeude){
		
		Objects.requireNonNull(gebeude, "Gebeude sollte nicht null sein.");
		
		if(immobilien.contains(gebeude)) {
			
			throw new IllegalArgumentException("Gebeude kann nicht dupliziert sein.");
			
		}
		
		Immobiele gebeudeKopie = new Immobiele(gebeude);
		
		immobilien.add(gebeudeKopie);	
		
	}

	public void addMieter(Mieter mieter){
		
		Objects.requireNonNull(mieter, "Mieter sollte nicht null sein.");
		
		if(mietern.contains(mieter)) {
			
			throw new IllegalArgumentException("Mieter kann nicht dupliziert sein.");	
			
		}
		
		Mieter mieterKopie = new Mieter(mieter);
		
		mietern.add(mieterKopie);
		
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public List<Immobiele> getImmobilienListe(){
		
		List<Immobiele> immobilienKopie = new ArrayList<Immobiele>(this.immobilien);
		
		return immobilienKopie;
	}

	public List<Mieter> getMieternListe(){
		
		List<Mieter> mieternKopie = new ArrayList<Mieter>(this.mietern);
		
		return mieternKopie;
	}

	public void vermieten(int postleitzahl, String stadtName, String strassenName, int gebaeudeNummer, int hausNummer, Mieter mieter) {
		
		for(Immobiele gebeude : immobilien) {

			if((gebeude.getStrassenName().equals(strassenName)) 
				&& (gebeude.getPostleitzahl() == postleitzahl)
				&& (gebeude.getStadtName().equals(stadtName))
				&& (gebeude.getGebaeudeNummer() == gebaeudeNummer)) {
				
				gebeude.addMieter(mieter, hausNummer);
				
			}
			
		}
		
	}
	
	public void unvermieten(int postleitzahl, String stadtName, String strassenName, int gebaeudeNummer, int hausNummer) {

		for(Immobiele gebeude : immobilien) {

			if((gebeude.getStrassenName().equals(strassenName)) 
				&& (gebeude.getPostleitzahl() == postleitzahl)
				&& (gebeude.getStadtName().equals(stadtName))
				&& (gebeude.getGebaeudeNummer() == gebaeudeNummer)) {
				
				gebeude.removeMieter(hausNummer);
				
			}
			
		}
		
	}

	public int getLeereWohnungen() {
		
		int leer = 0;
		for(Immobiele gebeude : immobilien) {
			for(Etage etage : gebeude.getEtagen()) {
				for(Wohnung wohnung: etage.getWohnungen()) {
					if(wohnung.getLeer()) {
						leer++;
					}
				}
			}
		}
		
		return leer;
		
	}

	public int getVermieteteWohnungen() {
		
		int belegt = 0;
		for(Immobiele gebeude : immobilien) {
			for(Etage etage : gebeude.getEtagen()) {
				for(Wohnung wohnung: etage.getWohnungen()) {
					if(!wohnung.getLeer()) {
						belegt++;
					}
				}
			}
		}
		
		return belegt;
		
	}

	public int getAlleWohnungen() {
		
		return getLeereWohnungen() + getVermieteteWohnungen();
		
	}
	
public String print() {
		
		String str = "";
		
		for(Immobiele immobiele : immobilien) {
			
			str += "\n===================================================================================\n";
			str += "Gebeude: " + immobiele.getStrassenName() + " " + immobiele.getGebaeudeNummer();
			str += "\n===================================================================================\n";
			
			for(Etage etage : immobiele.getEtagen()) {
				str += "Etage: " + etage.getEtageNummer();
				str += "\n-----------------------------------------------------------------------------------\n";
				
				for(Wohnung wohnung : etage.getWohnungen()) {
					str += "\tWohnung: " + wohnung.getHausNummer();
					str += ", " + wohnung.getWohnungFlaeche()/10000 + "m²";
					str += ", K.m.:" + String.format("€%.02f", wohnung.getKaltMiete());
					str += ", N.k.:" + String.format("€%.02f", wohnung.getNebenKosten());
					str += ", G.m.:" + String.format("€%.02f", wohnung.getKaltMiete() + wohnung.getNebenKosten());
					str += ", Leer:" + (wohnung.getLeer()?"Ja":"Nein");
					str += "\n";
					
					if(wohnung.getLeer() == false) {
						str += "\t\tMieter: " + wohnung.getMieter().getVorname();
						str += " " + wohnung.getMieter().getNachname();
						str += ", " + wohnung.getMieter().getGeburst();
						str += ", " + wohnung.getMieter().getAusweis();
						str += ", " + wohnung.getMieter().getHandy();
						str += "\n";
					}

					for(Zimmer zimmer : wohnung.getZimmern()) {
						String fullClassName = zimmer.getClass().toString();
						String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
						str += "\t\tZimmer: " + simpleClassName;
						str += ", " + zimmer.getFleache()/10000 + "m²\n";	
					}
				}
			}
		}
		
		return str;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((immobilien == null) ? 0 : immobilien.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof HausVerwaltung))
			return false;
		HausVerwaltung other = (HausVerwaltung) obj;
		if (immobilien == null) {
			if (other.immobilien != null)
				return false;
		} else if (!immobilien.equals(other.immobilien))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("HausVerwaltung [immobilien=%s, name=%s]", immobilien, name);
	}
	
}
