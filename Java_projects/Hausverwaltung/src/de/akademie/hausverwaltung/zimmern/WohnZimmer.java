package de.akademie.hausverwaltung.zimmern;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Sub-Klasse WohnZimmer erzeugt ein neues Zimmer vom Typ WohnZimmer.<br>
 * Die Klasse erbt von der Superklasse {@link Zimmer}
 * 
 * @author Akademie für Weiterbildung
 *
 */

public class WohnZimmer extends Zimmer {

	private boolean sofa;
	
	/**
	 * Der Standard Konstruktor zum Anlegen eines neuen Wohnzimmers.<br>
	 * Dieser Konstruktor kommt zum Einsatz, wenn ein neues Wohnzimmer angelegt wird<br>
	 * Bis auf den Parameter <code>spuele</code> werden alle Parameter an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * @param feldUnter1Meter		Die Anzahl von Feldern [cm²] mit einer Deckenhöhe max. 1 m
	 * @param feldUnter2Meter		Die Anzahl von Feldern [cm²] mit einer Deckenhöhe min. 1 m und max. 2 m
	 * @param feldVolleHoehe		Die Anzahl von Feldern [cm²] mit einer Deckenhöhe min. 2 m
	 * @param leange				Die Länge eines neuen Zimmers [m]
	 * @param breit					Die Breite eines neuen Zimmers [m]
	 * @param sofa					Ist ein Sofa vorhanden, oder nicht
	 */
	
	public WohnZimmer(final int feldUnter1Meter, 
			final int feldUnter2Meter, 
			final int feldVolleHoehe, 
			final double leange, 
			final double breit,
			final boolean sofa) {
		super(feldUnter1Meter, feldUnter2Meter, feldVolleHoehe, leange, breit);
		
		this.sofa = sofa;
	}
	
	/**
	 * Copy-Konstruktor für ein neues Wohnzimmer auf der Basis eines bereits vorhandenen Wohnzimmers.<br>
	 * Hierbei werden alle Attribute eines existierenden Wohnzimmers in ein neues Objekt vom Typ WohnZimmer kopiert.<br>
	 * Durch das Erzeugen einer Kopie werden ungewollte Seiteneffekte verhindert.<br>
	 * Der Parameter <code>sofa</code> wird über den Methodenaufruf {@link getSofa()} aus dem übergebenen Objekt an das neu erzeugte Objekt weitergegeben.<br>
	 * Das übergebene Objekt wird über den Methodenaufruf <code>super()</code> an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * Die Abfrage <code>{@link Objects}.requireNonNull(T, String)</code> verhindert, dass ein WohnZimmer mit einem Wert <code>null</code> an den Konstruktor übergeben wird.
	 * 
	 * @param zimmer					Ein bereits bestehendes Objekt WohnZimmer
	 * @throws NullPointerException		Sollte das übergebene Objekt vom Typ WohnZimmer <code>null</code> sein
	 */

	public WohnZimmer(WohnZimmer zimmer) {
		super(zimmer);
		
		this.sofa = zimmer.getSofa();
	}
	
	/**
	 * Methode zum Auslesen ob ein Sofa vorhanden ist
	 * 
	 * @return		Gibt <code>true</code> zurück, wenn ein Sofa vorhanden ist und <code>false</code>, wenn nicht
	 */

	public boolean getSofa() {
	
		return this.sofa;
	}
	
	/**
	 * Methode erzeugt einen einzigartigen HashCode und gibt diesen als Typ <code>int</code> zurück.<
	 * 
	 * @return		Gibt einen objektspezifischen HashCode als <code>int</code> zurück
	 */

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (sofa ? 1231 : 1237);
		return result;
	}
	
	/**
	 * Methode zum Vergleich von zwei Objekten des Typs {@link WohnZimmer}.<br>
	 * Gibt einen boole'schen Ausdruck <code>true</code> bei Gleichheit, oder <code>false</code> bei Ungleichheit zurück.
	 * 
	 * @return		Gibt <code>true</code> bei Gleichheit, oder <code>false</code> bei Ungleichheit zurück
	 */

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof WohnZimmer))
			return false;
		WohnZimmer other = (WohnZimmer) obj;
		if (sofa != other.sofa)
			return false;
		return true;
	}
	
	/**
	 * Methode zur Formatierung der Ausgabe eines Objektes vom Typ {@link WohnZimmer}.<br>
	 * Ausgabe erfolgt in einem formatierten {@link String}.
	 * 
	 * @return 		Gibt einen formatierten {@link String} eines Objektes vom Typ {@link WohnZimmer} zurück.
	 */

	@Override
	public String toString() {
		return String.format(
				"WohnZimmer [sofa=%s, getLeange()=%s, getBreit()=%s, getFleache()=%s, getFeldQuadratMeter()=%s, getFeldUnter1Meter()=%s, getFeldUnter2Meter()=%s, getFeldVolleHoehe()=%s]",
				sofa,getLeange(), getBreit(), getFleache(), getFeldQuadratMeter(), getFeldUnter1Meter(),
				getFeldUnter2Meter(), getFeldVolleHoehe());
	}
		
}
