package de.akademie.hausverwaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.akademie.hausverwaltung.mieter.Mieter;
import de.akademie.hausverwaltung.zimmern.Zimmer;

public class Immobiele{

	private List<Etage> etagen = new ArrayList<Etage>();
	
	private int gebaeudeNummer;
	private int postleitzahl;
	private String stadtName;
	private String strassenName;
	
	Immobiele(
			final Etage etage,
			final int gebaeudeNummer,
			final int postleitzahl,
			final String stadtName, 
			final String strassenName){
		
		Objects.requireNonNull(etage, "Etage sollte nicht null sein.");
		
		Etage etageKopie 	= new Etage(etage);
		this.etagen.add(etageKopie);
		
		this.gebaeudeNummer = gebaeudeNummer;
		this.postleitzahl	= postleitzahl;
		this.stadtName		= stadtName;
		this.strassenName	= strassenName;
	}
	
	// Kopie Constructor
	Immobiele(final Immobiele gebeude){
		
		this.gebaeudeNummer = gebeude.getGebaeudeNummer();
		this.postleitzahl 	= gebeude.getPostleitzahl();
		this.stadtName 		= gebeude.getStadtName();
		this.strassenName 	= gebeude.getStrassenName();
		this.etagen 		= List.copyOf(gebeude.getEtagen());
	}

	private Wohnung searchWohnung(int hausNummer) {
		
		for(Etage etage : this.etagen){
			
			List<Wohnung> wohnungenList = new ArrayList<Wohnung>(etage.getWohnungen());
	
			for(Wohnung wohnung : wohnungenList) {				
				if(wohnung.getHausNummer() == hausNummer) {
					return wohnung;
				}
			}	
		}
		
		throw new NullPointerException("Keine Wohungsnummer #" + hausNummer + " gefunden");
	}
	
	public List<Etage> getEtagen() {
		List<Etage> etagenKopie = new ArrayList<Etage>(this.etagen);
		return etagenKopie;
	}

	public int getGebaeudeNummer() {
		
		return this.gebaeudeNummer;
	}

	public int getPostleitzahl() {
		
		return this.postleitzahl;
	}

	public String getStadtName() {
		
		return this.stadtName;
	}

	public String getStrassenName() {
		
		return this.strassenName;
	}

	public void addEtage(Etage newEtage) {
		
		Objects.requireNonNull(newEtage, "Neuetage sollte nicht null sein.");
		
		Etage etageKopie = new Etage(newEtage);
		this.etagen.add(etageKopie);
	}
	
	public boolean addMieter(Mieter mieter, int hausNummer){
		
		Objects.requireNonNull(mieter, "Neumieter sollte nicht null sein");
		
		Wohnung wohnung = this.searchWohnung(hausNummer);
		
		if(wohnung.getLeer() == true) {
			Mieter mieterKopie = new Mieter(mieter);
			wohnung.addMieter(mieterKopie);
			return true;
		}
		
		throw new IllegalArgumentException("Wohnung ist nicht leer, aktulllen Mieter ist: " + wohnung.getMieter());
	}

	public boolean removeMieter(int hausNummer){
			
		Wohnung wohnung = this.searchWohnung(hausNummer);
		
		if(wohnung.removeMieter()) {
			return true;
		}
		
		return false;
	}

	public String print() {
		
		String str = "";
		
		str += "\n===================================================================================\n";
		str += "Gebeude: " + this.strassenName + " " + this.gebaeudeNummer;
		str += "\n===================================================================================\n";
		
		for(Etage etage : this.etagen) {
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
		
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etagen == null) ? 0 : etagen.hashCode());
		result = prime * result + gebaeudeNummer;
		result = prime * result + postleitzahl;
		result = prime * result + ((stadtName == null) ? 0 : stadtName.hashCode());
		result = prime * result + ((strassenName == null) ? 0 : strassenName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Immobiele))
			return false;
		Immobiele other = (Immobiele) obj;
		if (etagen == null) {
			if (other.etagen != null)
				return false;
		} else if (!etagen.equals(other.etagen))
			return false;
		if (gebaeudeNummer != other.gebaeudeNummer)
			return false;
		if (postleitzahl != other.postleitzahl)
			return false;
		if (stadtName == null) {
			if (other.stadtName != null)
				return false;
		} else if (!stadtName.equals(other.stadtName))
			return false;
		if (strassenName == null) {
			if (other.strassenName != null)
				return false;
		} else if (!strassenName.equals(other.strassenName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Immobiele [etagen=%s, gebaeudeNummer=%s, postleitzahl=%s, stadtName=%s, strassenName=%s]",
				etagen, gebaeudeNummer, postleitzahl, stadtName, strassenName);
	}
	
}
