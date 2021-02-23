/**
 * 
 */
package de.akademie.pizzadienst.produkte;

import java.util.ArrayList;
import java.util.List;
import de.akademie.pizzadienst.belaege.Belag;


/**
 * Klasse definiert Objekte vom Typ Produkt.<br>
 * Jedes Produkt hat eine einzigartige Produktnummer, einen Produktnamen und eine Liste von Bel�gen.
 *  
 * @author Gruppe2
 *
 */
public abstract class Produkt {
	
	private int produktNummer;
	private String produktName;
	private List<Belag> belaege = new ArrayList<>();
	
	/**
	 * Standard-Konstruktor zur Erzeugung eines neuen Produkts.<br>
	 * Die Produktnummer wird automatisch erzeugt.<br>
	 * Neben dem Produktname ist eine Liste an Grundbel�gen erforderlich.<br>
	 * Hierbei kann es sich um eine Liste an Standardbel�gen des jeweiligen Produktes, oder eine individuelle Liste handeln.<br>
	 * Weitere Bel�ge k�nnen sp�ter noch hinzugef�gt werden.
	 * 
	 * @param 	Der �bergebene Produktname
	 * @param 	Eine Liste an Bel�gen des Produktes
	 */
	
	public Produkt(final String produktName, final List<Belag> belaege) {
		
		this.produktNummer 	= 0;
		this.produktName 	= produktName;
		
		for(Belag belag : belaege) {
			AddBelag(belag);
		}
	}

	
	/**
	 * Kopie Konstruktor f�r SQL
	 * @param produktName
	 * @param belaege
	 * @param produktNummer
	 */
	public Produkt(String produktName, List<Belag> belaege, int produktNummer) {
		
		this.produktNummer 	= produktNummer;
		this.produktName 	= produktName;
		
		for(Belag belag : belaege) {
			AddBelag(belag);
		}
	}

	/**
	 * Methode zur Ausgabe der einzigartigen Produktnummer
	 * 
	 * @return 	Gibt die Produktnummer zur�ck
	 */
	
	public int getProduktNummer() {
		
		return produktNummer;
	}

	/**
	 * Methode zur Ausgabe des Produktnamens
	 * 
	 * @return	Gibt den Produktnamen zur�ck
	 */
	
	public String getProduktName() {
		
		return produktName;
	}

	/**
	 * Methode zur R�ckgabe der Belagsliste.
	 * 
	 * @return	Gibt die Liste aller Bel�ge zur�ck
	 */
	
	public List<Belag> getBelaege() {
		
		return List.copyOf(belaege);
	}
	
	/**
	 * Methode zur Berechnung des Produktpreises.<br>
	 * Methode ist abstrakt und muss daher von allen vererbten Klassen implementiert werden.
	 * 
	 * @return	Gibt den Produktpreis zur�ck
	 */
	
	public abstract int BerechneProduktPreis();
	
	/**
	 * Methode zum Hinzuf�gen eines Belags.<br>
	 * Bel�ge k�nnen mehrfach hinzugef�gt werden.
	 * 
	 * @param 	Der hinzuzuf�gende Belag
	 */
	
	public void AddBelag(Belag belag) {
		
		this.belaege.add(belag);
	}
	
	/**
	 * Methode zum Entfernen eines Belags.
	 * 
	 * @param 	Der zu entfernende Belag
	 */
	
	public void removeBelag(Belag belag) {
		
		if(!belaege.contains(belag)) {
			return;
		}
		
		this.belaege.remove(belag);
	}
	
	/**
	 * Methode zur Erzeugung eines einzigartigen Hashcodes.<br>
	 * Zur Erzeugung wird der Name des Produktes verwendet.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produktName == null) ? 0 : produktName.hashCode());
		return result;
	}
	
	/**
	 * Methode f�r den Vergleich zweier Produkte<br>
	 * Zur Erzeugung wird der Name des Produktes verwendet.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Produkt))
			return false;
		Produkt other = (Produkt) obj;
		if (produktName == null) {
			if (other.produktName != null)
				return false;
		} else if (!produktName.equals(other.produktName))
			return false;
		return true;
	}
	
	/**
	 * ToString-Methode
	 */

	@Override
	public String toString() {
		return String.format("Produkt [produktNummer=%s, produktName=%s, belaege=%s]", produktNummer, produktName,
				belaege);
	}
	
}
