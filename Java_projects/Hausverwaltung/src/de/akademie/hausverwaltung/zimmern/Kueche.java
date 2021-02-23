package de.akademie.hausverwaltung.zimmern;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Sub-Klasse Kueche erzeugt ein neues Zimmer vom Typ Kueche.<br>
 * Die Klasse erbt von der Superklasse {@link Zimmer}
 * 
 * @author Akademie für Weiterbildung
 *
 */

public class Kueche extends Zimmer {

	private boolean spuele;
		
	/**
	 * Der Standard Konstruktor zum Anlegen einer neuen Küche.<br>
	 * Dieser Konstruktor kommt zum Einsatz, wenn eine neue Küche angelegt wird<br>
	 * Bis auf den Parameter <code>spuele</code> werden alle Parameter an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * @param feldUnter1Meter		Die Anzahl von Feldern [cm²] mit einer Deckenhöhe max. 1 m
	 * @param feldUnter2Meter		Die Anzahl von Feldern [cm²] mit einer Deckenhöhe min. 1 m und max. 2 m
	 * @param feldVolleHoehe		Die Anzahl von Feldern [cm²] mit einer Deckenhöhe min. 2 m
	 * @param leange				Die Länge eines neuen Zimmers [m]
	 * @param breit					Die Breite eines neuen Zimmers [m]
	 * @param spuele				Ist eine Spüle vorhanden, oder nicht
	 */
	
	public Kueche(final int feldUnter1Meter, 
			final int feldUnter2Meter, 
			final int feldVolleHoehe, 
			final double leange, 
			final double breit,
			final boolean spuele) {
		super(feldUnter1Meter, feldUnter2Meter, feldVolleHoehe, leange, breit);
		
		this.spuele = spuele;
	}
	
	/**
	 * Copy-Konstruktor für eine neue Küche auf der Basis einer bereits vorhandenen Küche.<br>
	 * Hierbei werden alle Attribute einer existierenden Küche in ein neues Objekt vom Typ Kueche kopiert.<br>
	 * Durch das Erzeugen einer Kopie werden ungewollte Seiteneffekte verhindert.<br>
	 * Der Parameter <code>spuele</code> wird über den Methodenaufruf {@link getSpuele()} aus dem übergebenen Objekt an das neu erzeugte Objekt weitergegeben.<br>
	 * Das übergebene Objekt wird über den Methodenaufruf <code>super()</code> an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * Die Abfrage <code>{@link Objects}.requireNonNull(T, String)</code> verhindert, dass ein Kueche mit einem Wert <code>null</code> an den Konstruktor übergeben wird.
	 * 
	 * @param zimmer					Ein bereits bestehendes Objekt Kueche
	 * @throws NullPointerException		Sollte das übergebene Objekt vom Typ Kueche <code>null</code> sein
	 */

	public Kueche(Kueche zimmer) {
		super(zimmer);
		this.spuele = zimmer.getSpuele();
	}

	/**
	 * Methode zum Auslesen ob eine Spüle vorhanden ist
	 * 
	 * @return		Gibt <code>true</code> zurück, wenn eine Spüle vorhanden ist und <code>false</code>, wenn nicht
	 */
	
	public boolean getSpuele() {
	
		return this.spuele;
	}

	/**
	 * Methode erzeugt einen einzigartigen HashCode und gibt diesen als Typ <code>int</code> zurück.
	 * 
	 * @return		Gibt einen objektspezifischen HashCode als <code>int</code> zurück
	 */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (spuele ? 1231 : 1237);
		return result;
	}
	
	/**
	 * Methode zum Vergleich von zwei Objekten des Typs {@link Kueche}.<br>
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
		if (!(obj instanceof Kueche))
			return false;
		Kueche other = (Kueche) obj;
		if (spuele != other.spuele)
			return false;
		return true;
	}
	
	/**
	 * Methode zur Formatierung der Ausgabe eines Objektes vom Typ {@link Kueche}.<br>
	 * Ausgabe erfolgt in einem formatierten {@link String}.
	 * 
	 * @return 		Gibt einen formatierten {@link String} eines Objektes vom Typ {@link Kueche} zurück.
	 */

	@Override
	public String toString() {
		return String.format(
				"Kueche [spuele=%s, getLeange()=%s, getBreit()=%s, getFleache()=%s, getFeldQuadratMeter()=%s, getFeldUnter1Meter()=%s, getFeldUnter2Meter()=%s, getFeldVolleHoehe()=%s]",
				spuele, getLeange(), getBreit(), getFleache(), getFeldQuadratMeter(), getFeldUnter1Meter(),
				getFeldUnter2Meter(), getFeldVolleHoehe());
	}
		
}
