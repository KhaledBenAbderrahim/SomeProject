package de.akademie.hausverwaltung.zimmern;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Sub-Klasse Flur erzeugt ein neues Zimmer vom Typ Flur.<br>
 * Die Klasse erbt von der Superklasse {@link Zimmer}
 * 
 * @author Akademie f�r Weiterbildung
 *
 */

public class Flur extends Zimmer {

	private boolean teppich;
	
	/**
	 * Der Standard Konstruktor zum Anlegen eines neuen Flures.<br>
	 * Dieser Konstruktor kommt zum Einsatz, wenn ein neuer Flur angelegt wird<br>
	 * Bis auf den Parameter <code>teppich</code> werden alle Parameter an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * @param feldUnter1Meter		Die Anzahl von Feldern [cm�] mit einer Deckenh�he max. 1 m
	 * @param feldUnter2Meter		Die Anzahl von Feldern [cm�] mit einer Deckenh�he min. 1 m und max. 2 m
	 * @param feldVolleHoehe		Die Anzahl von Feldern [cm�] mit einer Deckenh�he min. 2 m
	 * @param leange				Die L�nge eines neuen Zimmers [m]
	 * @param breit					Die Breite eines neuen Zimmers [m]
	 * @param teppich				Ist ein Teppich vorhanden, oder nicht
	 */
	
	public Flur(final int feldUnter1Meter, 
			final int feldUnter2Meter, 
			final int feldVolleHoehe, 
			final double leange, 
			final double breit,
			final boolean teppich) {
		super(feldUnter1Meter, feldUnter2Meter, feldVolleHoehe, leange, breit);
		
		this.teppich = teppich;
	}

	/**
	 * Copy-Konstruktor f�r einen neuen Flur auf der Basis eines bereits vorhandenen Flures.<br>
	 * Hierbei werden alle Attribute eines existierenden Flures in ein neues Objekt vom Typ Flur kopiert.<br>
	 * Durch das Erzeugen einer Kopie werden ungewollte Seiteneffekte verhindert.<br>
	 * Der Parameter <code>teppich</code> wird �ber den Methodenaufruf {@link getTeppich()} aus dem �bergebenen Objekt an das neu erzeugte Objekt weitergegeben.<br>
	 * Das �bergebene Objekt wird �ber den Methodenaufruf <code>super()</code> an den Konstruktor der Superklasse {@link Zimmer} weitergeleitet.
	 * 
	 * Die Abfrage <code>{@link Objects}.requireNonNull(T, String)</code> verhindert, dass ein Flur mit einem Wert <code>null</code> an den Konstruktor �bergeben wird.
	 * 
	 * @param zimmer					Ein bereits bestehendes Objekt Flur
	 * @throws NullPointerException		Sollte das �bergebene Objekt vom Typ Flur <code>null</code> sein
	 */
	
	public Flur(Flur zimmer) {
		super(zimmer);
		this.teppich = zimmer.getTeppich();
	}
	
	/**
	 * Methode zum Auslesen ob ein Teppich vorhanden ist
	 * 
	 * @return		Gibt <code>true</code> zur�ck, wenn ein Teppich vorhanden ist und <code>false</code>, wenn nicht
	 */

	public boolean getTeppich() {
	
		return this.teppich;
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
		result = prime * result + (teppich ? 1231 : 1237);
		return result;
	}
	
	/**
	 * Methode zum Vergleich von zwei Objekten des Typs {@link Flur}.<br>
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
		if (!(obj instanceof Flur))
			return false;
		Flur other = (Flur) obj;
		if (teppich != other.teppich)
			return false;
		return true;
	}
	
	/**
	 * Methode zur Formatierung der Ausgabe eines Objektes vom Typ {@link Flur}.<br>
	 * Ausgabe erfolgt in einem formatierten {@link String}.
	 * 
	 * @return 		Gibt einen formatierten {@link String} eines Objektes vom Typ {@link Flur} zur�ck.
	 */

	@Override
	public String toString() {
		return String.format(
				"Flur [teppich=%s, getLeange()=%s, getBreit()=%s, getFleache()=%s, getFeldQuadratMeter()=%s, getFeldUnter1Meter()=%s, getFeldUnter2Meter()=%s, getFeldVolleHoehe()=%s]",
				teppich, getLeange(), getBreit(), getFleache(), getFeldQuadratMeter(), getFeldUnter1Meter(),
				getFeldUnter2Meter(), getFeldVolleHoehe());
	}
		
}
