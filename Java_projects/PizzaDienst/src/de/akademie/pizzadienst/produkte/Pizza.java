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
	 * Neben dem Produktname ist die Gr��e der Pizza als Enum vom Typ {@link PizzaGroesse}, sowie eine Liste an Grundbel�gen erforderlich.<br>
	 * Hierbei kann es sich um eine Liste an Standardbel�gen, oder eine individuelle Liste handeln.<br>
	 * Weitere Bel�ge k�nnen sp�ter noch hinzugef�gt werden.
	 * 
	 * @param 	Der �bergebene Produktname
	 * @param	Die Gr��e der Pizza
	 * @param 	Eine Liste an Bel�gen der Pizza
	 */
	
	public Pizza(final String produktName, final int groesseID, final List<Belag> belaege) {		
		super(produktName, belaege);
		this.groesseID = groesseID;
		dao = new ProduktGroesseSQLDAO();
	}
	
	/**
	 * Kopie Konstruktor f�r SQL
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
	 * Methode zur Ausgabe der Gr��e einer Pizza
	 * 
	 * @return 	Gibt die Gr��e (Durchmesser) in cm zur�ck
	 */
	
	public int getProduktGroesse() {
		return dao.getPizzaGroesse(groesseID);
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
	 * Methode zur Ausgabe des Grundpreises einer Pizza.<br>
	 * Der Grundpreis ist abh�ngig von der gew�hlten Gr��e.
	 * 
	 * @return 	Gibt den Grundpreis in Cent zur�ck
	 */
	
	public int getGrundpreis() {
		return dao.getPizzaGroessePreis(groesseID);
	}
	
	/**
	 * Methode zur Berechnung des Produktpreises.<br>
	 * Hierbei werden neben dem Grundpreis die Preise des hinzugef�gten Bel�ge aufaddiert.
	 * 
	 * @return	Gibt den Produktpreis in Cent zur�ck
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
	 * Zur Erzeugung wird der Name, sowie die Gr��e der Pizza verwendet.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (groesseID);
		return result;
	}
	
	/**
	 * Methode f�r den Vergleich zweier Pizzen<br>
	 * Zur Erzeugung wird der Name, sowie die Gr��e der Pizza verwendet.
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
		return String.format("Pizza: %s\nGr��e: %d\nPreis: %.2f\nBel�ge:\n%s", getProduktName(), dao.getPizzaGroesse(groesseID), BerechneProduktPreis() / 100., belagText);
	}
	
	
}
