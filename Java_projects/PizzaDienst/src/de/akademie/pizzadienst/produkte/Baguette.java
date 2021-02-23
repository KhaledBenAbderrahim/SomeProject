/**
 * 
 */
package de.akademie.pizzadienst.produkte;

import java.util.List;

import de.akademie.pizzadienst.belaege.Belag;
import de.akademie.pizzadienst.dao.ProduktGroesseDAO;
import de.akademie.util.ProduktGroesseSQLDAO;

/**
 * @author Gruppe2
 *
 */
public class Baguette extends Produkt{
	
	private int groesseID;
	private ProduktGroesseDAO dao;
	
	/**
	 * Standard-Konstruktor zur Erzeugung eines neuen Baguettes.<br>
	 * Die Produktnummer wird automatisch erzeugt.<br>
	 * Neben dem Produktname ist die Gr��e des baguettes als Enum vom Typ {@link BaguetteGroesse}, sowie eine Liste an Grundbel�gen erforderlich.<br>
	 * Hierbei kann es sich um eine Liste an Standardbel�gen, oder eine individuelle Liste handeln.<br>
	 * Weitere Bel�ge k�nnen sp�ter noch hinzugef�gt werden.
	 * 
	 * @param 	Der �bergebene Produktname
	 * @param	Die Gr��e des Baguettes
	 * @param 	Eine Liste an Bel�gen des Baguettes
	 */
	
	public Baguette(final String produktName, final int groesseID, final List<Belag> belaege) {		
		super(produktName, belaege);
		this.groesseID = groesseID;
		this.dao = new ProduktGroesseSQLDAO();
	}
	
	/**
	 * Kopie Konstruktor f�r SQL
	 * @param produktName
	 * @param groesseID
	 * @param belaege
	 * @param produktNummer
	 */
	
	public Baguette(final String produktName, final int groesseID, final List<Belag> belaege, final int produktNummer) {		
		super(produktName, belaege, produktNummer);
		this.groesseID = groesseID;
		this.dao = new ProduktGroesseSQLDAO();
	}

	/**
	 * Kopie Konstruktor
	 * @param baguette
	 * @param produktNummer
	 */
	
	public Baguette(final Baguette baguette, final int produktNummer) {		
		super(baguette.getProduktName(), baguette.getBelaege(), produktNummer);
		this.groesseID = baguette.getGroesseID();
		dao = new ProduktGroesseSQLDAO();
	}
	
	/**
	 * Methode zur Ausgabe der Gr��e eines Baguettes
	 * 
	 * @return 	Gibt die Gr��e zur�ck
	 */
	
	public String getProduktGroesse() {
		return dao.getBaguetteGroesse(groesseID).get();
	}
	
	/**
	 * Methode zur Ausgabe der Gr��e einer Pizza
	 * 
	 * @return 	Gibt die Gr��e (Durchmesser) in cm zur�ck
	 */
	
	public int getGroesseID() {
		return this.groesseID;
	}
	
	/**
	 * Methode zur Ausgabe des Grundpreises eines Baguettes.<br>
	 * Der Grundpreis ist abh�ngig von der gew�hlten Gr��e.
	 * 
	 * @return 	Gibt den Grundpreis in Cent zur�ck
	 */
	
	public int getGrundpreis() {
		return dao.getBaguetteGroessePreis(groesseID);
	}
	
	/**
	 * Methode zur Berechnung des Produktpreises.<br>
	 * Hierbei werden neben dem Grundpreis die Preise des hinzugef�gten Bel�ge aufaddiert.
	 * 
	 * @return	Gibt den Produktpreis zur�ck
	 */
	
	public int BerechneProduktPreis() {
		int gesamtPreis = 0;
		
		gesamtPreis += dao.getBaguetteGroessePreis(groesseID);
		for(Belag belag : getBelaege()) {
			gesamtPreis += belag.getPreis();
		}
		
		return gesamtPreis;
	}
	
	/**
	 * Methode zur Erzeugung eines einzigartigen Hashcodes.<br>
	 * Zur Erzeugung wird der Name, sowie die Gr��e des Baguettes verwendet.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (groesseID);
		return result;
	}
	
	/**
	 * Methode f�r den Vergleich zweier Baguettes<br>
	 * Zur Erzeugung wird der Name, sowie die Gr��e des Baguettes verwendet.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Baguette))
			return false;
		Baguette other = (Baguette) obj;
		if (groesseID != other.groesseID)
			return false;
		return true;
	}
	
	/**
	 * ToString-Methode
	 */

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Belag belag : getBelaege()) {
			builder.append(belag);
			builder.append("\n");
		}
		return String.format("Baguette: %s\nGr��e: %s\nPreis: %.2f\nBel�ge:\n%s", getProduktName(), dao.getBaguetteGroesse(groesseID), BerechneProduktPreis() / 100., getBelaege().toString());
	}
	
}
