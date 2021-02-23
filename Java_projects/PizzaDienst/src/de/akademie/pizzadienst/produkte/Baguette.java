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
	 * Neben dem Produktname ist die Größe des baguettes als Enum vom Typ {@link BaguetteGroesse}, sowie eine Liste an Grundbelägen erforderlich.<br>
	 * Hierbei kann es sich um eine Liste an Standardbelägen, oder eine individuelle Liste handeln.<br>
	 * Weitere Beläge können später noch hinzugefügt werden.
	 * 
	 * @param 	Der übergebene Produktname
	 * @param	Die Größe des Baguettes
	 * @param 	Eine Liste an Belägen des Baguettes
	 */
	
	public Baguette(final String produktName, final int groesseID, final List<Belag> belaege) {		
		super(produktName, belaege);
		this.groesseID = groesseID;
		this.dao = new ProduktGroesseSQLDAO();
	}
	
	/**
	 * Kopie Konstruktor für SQL
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
	 * Methode zur Ausgabe der Größe eines Baguettes
	 * 
	 * @return 	Gibt die Größe zurück
	 */
	
	public String getProduktGroesse() {
		return dao.getBaguetteGroesse(groesseID).get();
	}
	
	/**
	 * Methode zur Ausgabe der Größe einer Pizza
	 * 
	 * @return 	Gibt die Größe (Durchmesser) in cm zurück
	 */
	
	public int getGroesseID() {
		return this.groesseID;
	}
	
	/**
	 * Methode zur Ausgabe des Grundpreises eines Baguettes.<br>
	 * Der Grundpreis ist abhängig von der gewählten Größe.
	 * 
	 * @return 	Gibt den Grundpreis in Cent zurück
	 */
	
	public int getGrundpreis() {
		return dao.getBaguetteGroessePreis(groesseID);
	}
	
	/**
	 * Methode zur Berechnung des Produktpreises.<br>
	 * Hierbei werden neben dem Grundpreis die Preise des hinzugefügten Beläge aufaddiert.
	 * 
	 * @return	Gibt den Produktpreis zurück
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
	 * Zur Erzeugung wird der Name, sowie die Größe des Baguettes verwendet.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (groesseID);
		return result;
	}
	
	/**
	 * Methode für den Vergleich zweier Baguettes<br>
	 * Zur Erzeugung wird der Name, sowie die Größe des Baguettes verwendet.
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
		return String.format("Baguette: %s\nGröße: %s\nPreis: %.2f\nBeläge:\n%s", getProduktName(), dao.getBaguetteGroesse(groesseID), BerechneProduktPreis() / 100., getBelaege().toString());
	}
	
}
