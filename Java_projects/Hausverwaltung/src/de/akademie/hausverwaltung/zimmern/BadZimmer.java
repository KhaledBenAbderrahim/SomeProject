package de.akademie.hausverwaltung.zimmern;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Sub-Klasse BadZimmer erzeugt ein neues Zimmer vom Typ BadZimmer.<br>
 * Die Klasse erbt von der Superklasse {@link Zimmer}<br>
 * 
 * @author Akademie f�r Weiterbildung
 *
 */

public class BadZimmer extends Zimmer {

	private boolean dusche;
	
	/**
	 * Der Standard Konstruktor zum Anlegen eines neuen Badezimmers.<br>
	 * Dieser Konstruktor kommt zum Einsatz, wenn ein neues Badezimmer angelegt wird<br>
	 * Bis auf den Parameter <code>dusche</code> werden alle Parameter an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * @param feldUnter1Meter		Die Anzahl von Feldern [cm�] mit einer Deckenh�he max. 1 m
	 * @param feldUnter2Meter		Die Anzahl von Feldern [cm�] mit einer Deckenh�he min. 1 m und max. 2 m
	 * @param feldVolleHoehe		Die Anzahl von Feldern [cm�] mit einer Deckenh�he min. 2 m
	 * @param leange				Die L�nge eines neuen Zimmers [m]
	 * @param breit					Die Breite eines neuen Zimmers [m]
	 * @param dusche				Ist eine Dusche vorhanden, oder nicht
	 */
	
	public BadZimmer(final int feldUnter1Meter, 
			final int feldUnter2Meter, 
			final int feldVolleHoehe, 
			final double leange, 
			final double breit,
			final boolean dusche) {
		super(feldUnter1Meter, feldUnter2Meter, feldVolleHoehe, leange, breit);
		
		this.dusche = dusche;
	}
	
	/**
	 * Copy-Konstruktor f�r ein neues Badezimmer auf der Basis eines bereits vorhandenen Badezimmers.<br>
	 * Hierbei werden alle Attribute eines existierenden Badezimmers in ein neues Objekt vom Typ Badezimmer kopiert.<br>
	 * Durch das Erzeugen einer Kopie werden ungewollte Seiteneffekte verhindert.<br>
	 * Der Parameter <code>dusche</code> wird �ber den Methodenaufruf {@link getDusche()} aus dem �bergebenen Objekt an das neu erzeugte Objekt weitergegeben.<br>
	 * Das �bergebene Objekt wird �ber den Methodenaufruf <code>super()</code> an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * Die Abfrage <code>{@link Objects}.requireNonNull(T, String)</code> verhindert, dass ein Badezimmer mit einem Wert <code>null</code> an den Konstruktor �bergeben wird.
	 * 
	 * @param zimmer					Ein bereits bestehendes Objekt Badezimmer
	 * @throws NullPointerException		Sollte das �bergebene Objekt vom Typ Badezimmer <code>null</code> sein
	 */

	public BadZimmer(BadZimmer zimmer) {
		super(zimmer);
		this.dusche = zimmer.getDusche();
	}
	
	/**
	 * Methode zum Auslesen ob eine Dusche vorhanden ist
	 * 
	 * @return		Gibt <code>true</code> zur�ck, wenn eine Dusche vorhanden ist und <code>false</code>, wenn nicht
	 */

	public boolean getDusche() {
	
		return this.dusche;
	}
	
	/**
	 * Methode erzeugt einen einzigartigen HashCode und gibt diesen als Typ <code>int</code> zur�ck.<br>
	 * 
	 * @return		Gibt einen objektspezifischen HashCode als <code>int</code> zur�ck
	 */

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (dusche ? 1231 : 1237);
		return result;
	}
	
	/**
	 * Methode zum Vergleich von zwei Objekten des Typs {@link BadZimmer}.<br>
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
		if (!(obj instanceof BadZimmer))
			return false;
		BadZimmer other = (BadZimmer) obj;
		if (dusche != other.dusche)
			return false;
		return true;
	}
	
	/**
	 * Methode zur Formatierung der Ausgabe eines Objektes vom Typ {@link BadZimmer}.<br>
	 * Ausgabe erfolgt in einem formatierten {@link String}.
	 * 
	 * @return 		Gibt einen formatierten {@link String} eines Objektes vom Typ {@link BadZimmer} zur�ck.
	 */

	@Override
	public String toString() {
		return String.format(
				"BadZimmer [dusche=%s, getLeange()=%s, getBreit()=%s, getFleache()=%s, getFeldQuadratMeter()=%s, getFeldUnter1Meter()=%s, getFeldUnter2Meter()=%s, getFeldVolleHoehe()=%s]",
				dusche, getLeange(), getBreit(), getFleache(), getFeldQuadratMeter(), getFeldUnter1Meter(),
				getFeldUnter2Meter(), getFeldVolleHoehe());
	}
		
}
