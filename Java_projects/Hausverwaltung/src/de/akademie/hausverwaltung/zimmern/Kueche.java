package de.akademie.hausverwaltung.zimmern;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Sub-Klasse Kueche erzeugt ein neues Zimmer vom Typ Kueche.<br>
 * Die Klasse erbt von der Superklasse {@link Zimmer}
 * 
 * @author Akademie f�r Weiterbildung
 *
 */

public class Kueche extends Zimmer {

	private boolean spuele;
		
	/**
	 * Der Standard Konstruktor zum Anlegen einer neuen K�che.<br>
	 * Dieser Konstruktor kommt zum Einsatz, wenn eine neue K�che angelegt wird<br>
	 * Bis auf den Parameter <code>spuele</code> werden alle Parameter an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * @param feldUnter1Meter		Die Anzahl von Feldern [cm�] mit einer Deckenh�he max. 1 m
	 * @param feldUnter2Meter		Die Anzahl von Feldern [cm�] mit einer Deckenh�he min. 1 m und max. 2 m
	 * @param feldVolleHoehe		Die Anzahl von Feldern [cm�] mit einer Deckenh�he min. 2 m
	 * @param leange				Die L�nge eines neuen Zimmers [m]
	 * @param breit					Die Breite eines neuen Zimmers [m]
	 * @param spuele				Ist eine Sp�le vorhanden, oder nicht
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
	 * Copy-Konstruktor f�r eine neue K�che auf der Basis einer bereits vorhandenen K�che.<br>
	 * Hierbei werden alle Attribute einer existierenden K�che in ein neues Objekt vom Typ Kueche kopiert.<br>
	 * Durch das Erzeugen einer Kopie werden ungewollte Seiteneffekte verhindert.<br>
	 * Der Parameter <code>spuele</code> wird �ber den Methodenaufruf {@link getSpuele()} aus dem �bergebenen Objekt an das neu erzeugte Objekt weitergegeben.<br>
	 * Das �bergebene Objekt wird �ber den Methodenaufruf <code>super()</code> an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * Die Abfrage <code>{@link Objects}.requireNonNull(T, String)</code> verhindert, dass ein Kueche mit einem Wert <code>null</code> an den Konstruktor �bergeben wird.
	 * 
	 * @param zimmer					Ein bereits bestehendes Objekt Kueche
	 * @throws NullPointerException		Sollte das �bergebene Objekt vom Typ Kueche <code>null</code> sein
	 */

	public Kueche(Kueche zimmer) {
		super(zimmer);
		this.spuele = zimmer.getSpuele();
	}

	/**
	 * Methode zum Auslesen ob eine Sp�le vorhanden ist
	 * 
	 * @return		Gibt <code>true</code> zur�ck, wenn eine Sp�le vorhanden ist und <code>false</code>, wenn nicht
	 */
	
	public boolean getSpuele() {
	
		return this.spuele;
	}

	/**
	 * Methode erzeugt einen einzigartigen HashCode und gibt diesen als Typ <code>int</code> zur�ck.
	 * 
	 * @return		Gibt einen objektspezifischen HashCode als <code>int</code> zur�ck
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
	 * Gibt einen boole'schen Ausdruck <code>true</code> bei Gleichheit, oder <code>false</code> bei Ungleichheit zur�ck.
	 * 
	 * @return		Gibt <code>true</code> bei Gleichheit, oder <code>false</code> bei Ungleichheit zur�ck
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
	 * @return 		Gibt einen formatierten {@link String} eines Objektes vom Typ {@link Kueche} zur�ck.
	 */

	@Override
	public String toString() {
		return String.format(
				"Kueche [spuele=%s, getLeange()=%s, getBreit()=%s, getFleache()=%s, getFeldQuadratMeter()=%s, getFeldUnter1Meter()=%s, getFeldUnter2Meter()=%s, getFeldVolleHoehe()=%s]",
				spuele, getLeange(), getBreit(), getFleache(), getFeldQuadratMeter(), getFeldUnter1Meter(),
				getFeldUnter2Meter(), getFeldVolleHoehe());
	}
		
}
