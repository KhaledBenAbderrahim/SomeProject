package de.akademie.hausverwaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Etage{

	private List<Wohnung> wohnungen = new ArrayList<Wohnung>();
	
	private int etageNummer;
	
	Etage(final int etageNummer, final Wohnung wohnungLinks, final Wohnung wohnungRechts){
		
		Objects.requireNonNull(wohnungLinks,"Wohnung sollte nicht null sein.");
		Objects.requireNonNull(wohnungRechts,"Wohnung sollte nicht null sein.");
		
		Wohnung wohnungLinksKopie 	= new Wohnung(wohnungLinks);
		Wohnung wohnungRechtsKopie 	= new Wohnung(wohnungRechts);
		
		if(this.wohnungen.contains(wohnungLinksKopie) || this.wohnungen.contains(wohnungRechtsKopie)){
			throw new IllegalArgumentException("Mindestens eine der Wohnungen ist bereits vorhanden");
		}
		if(!wohnungLinksKopie.equals(wohnungRechtsKopie)) {
			this.wohnungen.add(wohnungLinksKopie);
			this.wohnungen.add(wohnungRechtsKopie);
			this.etageNummer = etageNummer;			
		}else {
			throw new IllegalArgumentException("Wohnungen sollen unterschiedliche sein.");
		}

	}
	
	/**
	 * Copy-Konstruktor<br>
	 * Erzeugt eine Deep-Copy der Liste Etage, da mit {@link List}.copyOf(E) nur eine Kopie der ersten Ordnung erzeugt wird.<br>
	 * Die Inhalte der kopierten Liste sind dennoch Referenzwerte auf dieselben Inhalte der Ursprungsliste.
	 * 
	 * @param etage		Die Etage, in der sich die Wohnung befindet
	 * @throws NullPointerException		Wenn Etage mit <code>null</code> übergeben wird
	 */
	Etage(Etage etage){
		
		Objects.requireNonNull(etage,"Etage sollte nicht null sein.");
		
		List<Wohnung> wohnungenKopie 	= new ArrayList<Wohnung>();
		
	    for (Wohnung wohnung : etage.wohnungen) {
	    	wohnungenKopie.add(new Wohnung(wohnung));
	    }
		
		this.wohnungen 					= wohnungenKopie;
		this.etageNummer 				= etage.getEtageNummer();
		
	}
	
	/**
	 * Mit dieser Methode können wir eine Wohnung auf eine Etage einfügen.<br>
	 * @param wohnung	Die Wohnung, die einfügen wollen.	
	 * @throws NullPointerException		 Wenn Wohnung mit <code>null</code> übergeben wird. 
	 * @throws IllegalArgumentException  Wenn die gleiche Wohnung nochmal übergeben wird
	 */
	public void addWohnung(Wohnung wohnung) {
		
		Objects.requireNonNull(wohnung,"Wohnung sollte nicht null sein.");
		
		if(!this.wohnungen.contains(wohnung)) {
			this.wohnungen.add(new Wohnung(wohnung));
		}else{
			throw new IllegalArgumentException("Wohnung kann nur einmal vorhanden sein.");
		}
		
	}
	
	/**
	 * Mit dieser Methode können wir eine Liste bekommen, die aus Wohnungen bestehen.<br>
	 * @return kopiertente Liste aus Wohnungen
	 */
	public List<Wohnung> getWohnungen(){
		
		return List.copyOf(wohnungen);
	}
	
	/**
	 * Mit dieser Methode können wir die Nummer der Etage bekommen, wo die Wohnung sich befindet.<br> 
	 * @return Nummer der Etage
	 */
	public int getEtageNummer() {
		
		return this.etageNummer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + etageNummer;
		result = prime * result + ((wohnungen == null) ? 0 : wohnungen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Etage))
			return false;
		Etage other = (Etage) obj;
		if (etageNummer != other.etageNummer)
			return false;
		if (wohnungen == null) {
			if (other.wohnungen != null)
				return false;
		} else if (!wohnungen.equals(other.wohnungen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Etage [wohnungen=%s, etageNummer=%s]", wohnungen, etageNummer);
	}
	
}
