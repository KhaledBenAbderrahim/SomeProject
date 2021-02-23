package de.akademie.hausverwaltung.zimmern;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Klasse Zimmer erzeugt ein neues Zimmer, das einer bestehenden Wohnung zugewiesen werden kann.<br>
 * Jedes Zimmer besitzt eine Länge und Breite, mit der die Gesamtfläche berechnet werden kann.<br>
 * Je nach Deckenhöhe variiert die errechnete Fläche.<br>
 * Flächen mit einer Deckenhöhe kleiner 1 m werden nicht zur Fläche gezählt.<br>
 * Flächen mit einer Deckenhöhe zwischen 1 und 2 m wird nur zur Hälfte zur Fläche gezählt.
 * 
 * @author Akademie für Weiterbildung
 *
 */

public abstract class Zimmer {
	
	private double leange; // als Info
	private double breit; // als Info
	private double fleache; // Gezamte flaeche ihne decken hoehe  
	private double feldQuadratMeter;
	private int feldUnter1Meter; // im cm 0%
	private int feldUnter2Meter;  // in cm 50%
	private int feldVolleHoehe; // in cm 100%
	
	/**
	 * Der Standard Konstruktor zum Anlegen eines neuen Zimmers.<br>
	 * Dieser Konstruktor kommt zum Einsatz, wenn ein neues Zimmer angelegt werden muss
	 * 
	 * @param feldUnter1Meter		Die Anzahl von Feldern [cm²] mit einer Deckenhöhe max. 1 m
	 * @param feldUnter2Meter		Die Anzahl von Feldern [cm²] mit einer Deckenhöhe min. 1 m und max. 2 m
	 * @param feldVolleHoehe		Die Anzahl von Feldern [cm²] mit einer Deckenhöhe min. 2 m
	 * @param leange				Die Länge eines neuen Zimmers [m]
	 * @param breit					Die Breite eines neuen Zimmers [m]
	 */
	
	Zimmer(final int feldUnter1Meter, 
			final int feldUnter2Meter, 
			final int feldVolleHoehe, 
			final double leange, 
			final double breit){
		
		if(feldUnter1Meter < 0 || feldUnter2Meter < 0 || feldVolleHoehe < 0) {
			throw new IllegalArgumentException("Felder sollte groser als 0 sein.");
		}

		if((feldUnter1Meter + feldUnter2Meter + feldVolleHoehe) == 0) {
			throw new IllegalArgumentException("Die summe von Feldern sollte groser als 0 sein.");
		}
		
		if(leange < 0 || breit < 0) {
			throw new IllegalArgumentException("Laenge oder Breit sollen groser als 0 sein.");
		}
		
		this.feldUnter1Meter 	= feldUnter1Meter;
		this.feldUnter2Meter	= feldUnter2Meter;
		this.feldVolleHoehe		= feldVolleHoehe;
		this.leange				= leange;
		this.breit				= breit;
		
		this.fleache			= feldUnter1Meter + feldUnter2Meter + feldVolleHoehe; // cm
		this.feldQuadratMeter	= (feldUnter2Meter * 0.5 + feldVolleHoehe); // cm
	}
	
	/**
	 * Copy-Konstruktor für ein neues Zimmer auf der Basis eines bereits vorhandenen Zimmers.<br>
	 * Hierbei werden alle Attribute eines existierenden Zimmers in ein neues Objekt vom Typ Zimmer kopiert.<br>
	 * Durch das Erzeugen einer Kopie werden ungewollte Seiteneffekte verhindert.<br><br>
	 * 
	 * Die Abfrage <code>{@link Objects}.requireNonNull(T, String)</code> verhindert, dass ein Zimmer mit einem Wert <code>null</code> an den Konstruktor übergeben wird.
	 * 
	 * @param zimmer					Ein bereits bestehendes Objekt vom Typ Zimmer
	 * @throws NullPointerException		Sollte das übergebene Objekt vom Typ Zimmer <code>null</code> sein
	 */
	
	Zimmer(final Zimmer zimmer){
		
		Objects.requireNonNull(zimmer, "Zimmer sollte nicht null sein.");
		
		this.feldUnter1Meter 	= zimmer.getFeldUnter1Meter(); 
		this.feldUnter2Meter	= zimmer.getFeldUnter2Meter();
		this.feldVolleHoehe		= zimmer.getFeldVolleHoehe();
		this.leange				= zimmer.getLeange();
		this.breit				= zimmer.getBreit();
		this.fleache			= zimmer.getFleache();
		this.feldQuadratMeter	= zimmer.getFeldQuadratMeter();
	}
	
	/**
	 * Methode zum Auslesen der Länge eines Zimmers
	 * 
	 * @return		Gibt die Länge eines Zimmers zurück
	 */

	public double getLeange() {
		return leange;
	}
	
	/**
	 * Methode zum Auslesen der Breite eines Zimmers
	 * 
	 * @return		Gibt die Breite eines Zimmers zurück
	 */

	public double getBreit() {
		return breit;
	}
	
	/**
	 * Methode zum Auslesen der Gesamtfläche eines Zimmers.
	 * Die Deckenhöhe wird hierbei nicht berücksichtigt.
	 * 
	 * @return		Gibt die Fläche eines Zimmers (ohne Bezug zur Deckenhöhe) in cm² zurück
	 */

	public double getFleache() {
		return fleache;
	}
	
	/**
	 * Methode zum Auslesen der Fläche unter der Berücksichtigung der Deckenhöhe eines Zimmers
	 * 
	 * @return		Gibt die Fläche eines Zimmers (mit Bezug zur Deckenhöhe) in cm² zurück
	 */

	public double getFeldQuadratMeter() {
		return feldQuadratMeter;
	}
	
	/**
	 * Methode zum Auslesen der Fläche mit einer Deckenhöhe kleiner 1 m eines Zimmers
	 * 
	 * @return		Gibt die Fläche mit einer Deckenhöhe kleiner 1 m eines Zimmers in cm² zurück
	 */

	public int getFeldUnter1Meter() {
		return feldUnter1Meter;
	}
	
	/**
	 * Methode zum Auslesen der Fläche mit einer Deckenhöhe zwischen 1 und 2 m eines Zimmers
	 * 
	 * @return		Gibt die Fläche mit einer Deckenhöhe zwischen 1  und 2 m eines Zimmers in cm² zurück
	 */

	public int getFeldUnter2Meter() {
		return feldUnter2Meter;
	}
	
	/**
	 * Methode zum Auslesen der Fläche mit einer Deckenhöhe größer 2 m eines Zimmers
	 * 
	 * @return		Gibt die Fläche mit einer Deckenhöhe größer 2 m eines Zimmers in cm² zurück
	 */

	public int getFeldVolleHoehe() {
		return feldVolleHoehe;
	}
	
	/**
	 * Methode erzeugt einen einzigartigen HashCode und gibt diesen als Typ <code>int</code> zurück.<br>
	 * 
	 * @return		Gibt einen objektspezifischen HashCode als <code>int</code> zurück
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(breit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(feldQuadratMeter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + feldUnter1Meter;
		result = prime * result + feldUnter2Meter;
		result = prime * result + feldVolleHoehe;
		temp = Double.doubleToLongBits(fleache);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(leange);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	/**
	 * Methode zum Vergleich von zwei Objekten des Typs {@link Zimmer}.<br>
	 * Gibt einen boole'schen Ausdruck <code>true</code> bei Gleichheit, oder <code>false</code> bei Ungleichheit zurück.<br>
	 * Zwei Objekte werden als gleich betrachtet, wenn sämtliche Attribute gleich sind.
	 * 
	 * @return		Gibt <code>true</code> bei Gleichheit, oder <code>false</code> bei Ungleichheit zurück
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Zimmer))
			return false;
		Zimmer other = (Zimmer) obj;
		if (Double.doubleToLongBits(breit) != Double.doubleToLongBits(other.breit))
			return false;
		if (Double.doubleToLongBits(feldQuadratMeter) != Double.doubleToLongBits(other.feldQuadratMeter))
			return false;
		if (feldUnter1Meter != other.feldUnter1Meter)
			return false;
		if (feldUnter2Meter != other.feldUnter2Meter)
			return false;
		if (feldVolleHoehe != other.feldVolleHoehe)
			return false;
		if (Double.doubleToLongBits(fleache) != Double.doubleToLongBits(other.fleache))
			return false;
		if (Double.doubleToLongBits(leange) != Double.doubleToLongBits(other.leange))
			return false;
		return true;
	}

	/**
	 * Methode zur Formatierung der Ausgabe eines Objektes vom Typ {@link Zimmer}.<br>
	 * Ausgabe erfolgt in einem formatierten {@link String}.
	 * 
	 * @return 		Gibt einen formatierten {@link String} eines Objektes vom Typ {@link Zimmer} zurück.
	 */
	
	@Override
	public String toString() {
		return String.format(
				"Zimmer [leange=%s, breit=%s, fleache=%s, feldQuadratMeter=%s, feldUnter1Meter=%s, feldUnter2Meter=%s, feldVolleHoehe=%s]",
				leange, breit, fleache, feldQuadratMeter, feldUnter1Meter, feldUnter2Meter, feldVolleHoehe);
	}
	
}