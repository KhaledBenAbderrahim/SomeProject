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
public class Pizza extends Produkt{
	
	private int groesseID;
	private ProduktGroesseDAO dao;
	
	/**
	 * Standard-Konstruktor zur Erzeugung einer neuen Pizza.<br>
	 * Die Produktnummer wird automatisch erzeugt.<br>
	 * Neben dem Produktname ist die Größe der Pizza als Enum vom Typ {@link PizzaGroesse}, sowie eine Liste an Grundbelägen erforderlich.<br>
	 * Hierbei kann es sich um eine Liste an Standardbelägen, oder eine individuelle Liste handeln.<br>
	 * Weitere Beläge können später noch hinzugefügt werden.
	 * 
	 * @param 	Der übergebene Produktname
	 * @param	Die Größe der Pizza
	 * @param 	Eine Liste an Belägen der Pizza
	 */
	
	public Pizza(final String produktName, final int groesseID, final List<Belag> belaege) {		
		super(produktName, belaege);
		this.groesseID = groesseID;
		dao = new ProduktGroesseSQLDAO();
	}
	
	/**
	 * Kopie Konstruktor für SQL
	 * @param produktName
	 * @param groesseID
	 * @param belaege
	 * @param produktNummer
	 */
	public Pizza(final String produktName, final int groesseID, final List<Belag> belaege, final int produktNummer) {		
		super(produktName, belaege, produktNummer);
		this.groesseID = groesseID;
		dao = new ProduktGroesseSQLDAO();
	}
	
	/**
	 * Kopie Konstruktor
	 * @param pizza
	 * @param produktNummer
	 */
	
	public Pizza(final Pizza pizza, final int produktNummer) {		
		super(pizza.getProduktName(), pizza.getBelaege(), produktNummer);
		this.groesseID = pizza.getGroesseID();
		dao = new ProduktGroesseSQLDAO();
	}

	/**
	 * Methode zur Ausgabe der Größe einer Pizza
	 * 
	 * @return 	Gibt die Größe (Durchmesser) in cm zurück
	 */
	
	public int getProduktGroesse() {
		return dao.getPizzaGroesse(groesseID);
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
	 * Methode zur Ausgabe des Grundpreises einer Pizza.<br>
	 * Der Grundpreis ist abhängig von der gewählten Größe.
	 * 
	 * @return 	Gibt den Grundpreis in Cent zurück
	 */
	
	public int getGrundpreis() {
		return dao.getPizzaGroessePreis(groesseID);
	}
	
	/**
	 * Methode zur Berechnung des Produktpreises.<br>
	 * Hierbei werden neben dem Grundpreis die Preise des hinzugefügten Beläge aufaddiert.
	 * 
	 * @return	Gibt den Produktpreis in Cent zurück
	 */
	
	public int BerechneProduktPreis() {
		int gesamtPreis = 0;
		
		gesamtPreis += dao.getPizzaGroessePreis(groesseID);
		for(Belag belag : getBelaege()) {
			gesamtPreis += belag.getPreis();
		}
		
		return gesamtPreis;
	}
	
	/**
	 * Methode zur Erzeugung eines einzigartigen Hashcodes.<br>
	 * Zur Erzeugung wird der Name, sowie die Größe der Pizza verwendet.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (groesseID);
		return result;
	}
	
	/**
	 * Methode für den Vergleich zweier Pizzen<br>
	 * Zur Erzeugung wird der Name, sowie die Größe der Pizza verwendet.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Pizza))
			return false;
		Pizza other = (Pizza) obj;
		if (groesseID != other.groesseID)
			return false;
		return true;
	}
	
	/**
	 * ToString-Methode
	 */

	@Override
	public String toString() {
		String belagText = "";
		for(Belag belag : getBelaege()) {
			belagText += belag + "\n";
		}
		return String.format("Pizza: %s\nGröße: %d\nPreis: %.2f\nBeläge:\n%s", getProduktName(), dao.getPizzaGroesse(groesseID), BerechneProduktPreis() / 100., belagText);
	}
	
	
}
