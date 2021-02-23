/**
 * 
 */
package de.akademie.pizzadienst.belaege;

/**
 * Die Klasse erstellt einen Belag mit einem gewünschten Namen und einem festgelegten Preis.<br>
 * Zusätzlich erhält jeder Belag eine feste ID für eine einfachere Nutzung.
 * 
 * @author Gruppe2
 *
 */
public class Belag {
	
	private int belagNummer;
	private String belagName;
	private int preis;
	
	/**
	 * Der Standardkonstruktor.<br>
	 * Benötigt den Namen und den Preis des Belags.<br>
	 * die Belag-ID wird automatisch generiert.
	 * 
	 * @param 	Der Name des Belags
	 * @param 	Der Preis des Belags
	 */
	
	public Belag(final String belagName, final int preis) {
		this.belagNummer 	= 0;
		this.belagName 		= belagName;
		this.preis 			= preis;
	}

	public Belag(final String belagName, final int preis, final int belagNummer) {
		this.belagNummer 	= belagNummer;
		this.belagName 		= belagName;
		this.preis 			= preis;
	}
	
	public Belag(Belag belag, int belagNummer) {
		
		this.belagNummer 	= belagNummer;
		this.belagName 		= belag.getBelagName();
		this.preis 			= belag.getPreis();
	}

	/**
	 * Methode zur Ausgabe der Belags-ID
	 * 
	 * @return Gibt die einzigartige ID zurück.
	 */
	public int getBelagNummer() {
		return this.belagNummer;
	}

	/**
	 * Methode zur Ausgabe des Belagnamens
	 * 
	 * @return Gibt den Belagnamen zurück
	 */
	public String getBelagName() {
		return belagName;
	}

	/**
	 * Methode zur Ausgabe des Belagpreises
	 * 
	 * @return Gibt den Preis des Belags in Cent zurück
	 */
	public int getPreis() {
		return preis;
	}
	
	/**
	 * Methode zur Erzeugung eines einzigartigen Hashcodes.<br>
	 * Zur Erzeugung wird der Name des Belags verwendet.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((belagName == null) ? 0 : belagName.hashCode());
		return result;
	}
	
	/**
	 * Methode für den Vergleich zweier Beläge<br>
	 * Zur Erzeugung wird der Name des Belags verwendet.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Belag))
			return false;
		Belag other = (Belag) obj;
		if (belagName == null) {
			if (other.belagName != null)
				return false;
		} else if (!belagName.equals(other.belagName))
			return false;
		return true;
	}
	
	/**
	 * ToString-Methode
	 */

	@Override
	public String toString() {
		return String.format("Belag: %s, Preis: %.2f", belagName, preis/100.);
	}
	
	
}
