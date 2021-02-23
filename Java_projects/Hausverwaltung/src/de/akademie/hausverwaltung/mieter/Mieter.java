package de.akademie.hausverwaltung.mieter;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Die Klasse Mieter erzeugt einen neuen Mieter, der bestehenden Wohnungen zugewiesen werden kann.<br>
 * Als einzigartig werden bei einem Mieter Vor- und Nachname, Geburtstag vom Typ {@link LocalDate}, und seine Ausweisnummer angesehen.
 * 
 * @author Akademie für Weiterbildung
 *
 */

public class Mieter {

	private String vorname;
	private String nachname;
	private String emailAdresse;
	private String strasse;
	private LocalDate geburst;
	private String ausweis;
	private long handy;
	
	/**
	 * Der Standard Konstruktor zum Anlegen eines neuen Mieters.<br>
	 * Dieser Konstruktor kommt zum Einsatz, wenn ein neuer Mieter angelegt werden muss
	 * 
	 * @param vorname			Vorname des neuen Mieters
	 * @param nachname			Nachname des neuen Mieters
	 * @param geburst			Der Geburstag des neuen Mieters
	 * @param emailAdresse		Die E-Mailadresse des neuen Mieters
	 * @param strasse			Die Anschrift des neuen Mieters
	 * @param ausweis			Die Ausweisnummer zur eindeutigen Identifizierung
	 * @param handy				Die Telefonnummer für eine bessere und direktere Erreichbarkeit
	 */
	
	public Mieter(final String vorname, 
			final String nachname, 
			final LocalDate geburst, 
			final String emailAdresse, 
			final String strasse,
			int i, int j, final String ausweis,
			final long handy) {
		
		this.vorname 		= vorname;
		this.nachname 		= nachname;
		this.geburst 		= geburst;
		this.emailAdresse 	= emailAdresse;
		this.strasse 		= strasse;
		this.ausweis		= ausweis;
		this.handy			= handy;
	}

	/**
	 * Copy-Konstruktor für einen neuen Mieter auf der Basis eines bereits vorhandenen Mieters.<br>
	 * Hierbei werden alle Attribute eines existierenden Mieters in ein neues Objekt vom Typ Mieter kopiert.<br>
	 * Durch das Erzeugen einer Kopie werden ungewollte Seiteneffekte verhindert.<br><br>
	 * 
	 * Die Abfrage <code>{@link Objects}.requireNonNull(T, String)</code> verhindert, dass ein Mieter mit einem Wert <code>null</code> an den Konstruktor übergeben wird.
	 * 
	 * @param mieter					Ein bereits bestehendes Objekt Mieter
	 * @throws NullPointerException		Sollte das übergebene Objekt vom Typ Mieter <code>null</code> sein
	 */
	
	public Mieter(Mieter mieter) {
		
		Objects.requireNonNull(mieter, "Mieter sollte nicht null sein.");
		
		this.vorname 		= mieter.getVorname();
		this.nachname 		= mieter.getNachname();
		this.geburst 		= mieter.getGeburst();
		this.emailAdresse 	= mieter.getEmailAdresse();
		this.strasse 		= mieter.getStrasse();
		this.ausweis		= mieter.getAusweis();
		this.handy			= mieter.getHandy();
	}
	
	/**
	 * Methode zum Auslesen des Vornames eines Mieters
	 * @return		Gibt den Vornamen eines Mieters zurück
	 */

	public String getVorname() {
		
		return this.vorname;
	}
	
	/**
	 * Methode zum Auslesen des Nachnames eines Mieters
	 * @return		Gibt den Nachnamen eines Mieters zurück
	 */

	public String getNachname() {
		
		return this.nachname;
	}
	
	/**
	 * Methode zum Auslesen des Geburtstags eines Mieters
	 * @return		Gibt den Geburtstag eines Mieters zurück
	 */
	
	public LocalDate getGeburst() {
		
		return this.geburst;
	}
	
	/**
	 * Methode zum Auslesen der E-Mailadresse eines Mieters
	 * @return		Gibt die E-Mailadresse eines Mieters zurück
	 */


	public String getEmailAdresse() {
		
		return this.emailAdresse;
	}
	
	/**
	 * Methode zum Auslesen des Wohnortes eines Mieters
	 * @return		Gibt die Wohnanschrift eines Mieters zurück
	 */

	public String getStrasse() {
		
		return this.strasse;
	}
	
	/**
	 * Methode zum Auslesen der Ausweisnummer eines Mieters
	 * @return		Gibt die Ausweisnummer eines Mieters zurück
	 */
	
	public String getAusweis() {
		
		return this.ausweis;
	}
	
	/**
	 * Methode zum Auslesen der Telefonnummer eines Mieters
	 * @return		Gibt die Telefonnummer eines Mieters zurück
	 */
	
	public long getHandy() {
		
		return this.handy;
	}
	
	/**
	 * Methode erzeugt einen einzigartigen HashCode und gibt diesen als Typ <code>int</code> zurück.<br>
	 * Zur Erzeugung werden die unveränderlichen Attribute Vor- und Nachname, Geburtstag und Ausweisnummer eines Mieters verwendet.
	 * 
	 * @return		Gibt einen objektspezifischen HashCode als <code>int</code> zurück
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ausweis == null) ? 0 : ausweis.hashCode());
		result = prime * result + ((geburst == null) ? 0 : geburst.hashCode());
		result = prime * result + ((nachname == null) ? 0 : nachname.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
		return result;
	}
	
	/**
	 * Methode zum Vergleich von zwei Objekten des Typs {@link Mieter}.<br>
	 * Gibt einen boole'schen Ausdruck <code>true</code> bei Gleichheit, oder <code>false</code> bei Ungleichheit zurück.<br>
	 * Zur Ermittlung, ob zwei Objekte gleich sind, wird Vor- und Nachname, Geburtstag und Ausweisnummer eines Mieters verwendet.
	 * 
	 * @return		Gibt <code>true</code> bei Gleichheit, oder <code>false</code> bei Ungleichheit zurück
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Mieter))
			return false;
		Mieter other = (Mieter) obj;
		if (ausweis == null) {
			if (other.ausweis != null)
				return false;
		} else if (!ausweis.equals(other.ausweis))
			return false;
		if (geburst == null) {
			if (other.geburst != null)
				return false;
		} else if (!geburst.equals(other.geburst))
			return false;
		if (nachname == null) {
			if (other.nachname != null)
				return false;
		} else if (!nachname.equals(other.nachname))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}
	
	/**
	 * Methode zur Formatierung der Ausgabe eines Objektes vom Typ {@link Mieter}.<br>
	 * Ausgabe erfolgt in einem formatierten {@link String}.
	 * 
	 * @return 		Gibt einen formatierten {@link String} eines Objektes vom Typ {@link Mieter} zurück.
	 */

	@Override
	public String toString() {
		return String.format(
				"Mieter [vorname=%s, nachname=%s, emailAdresse=%s, strasse=%s, geburst=%s, ausweis=%s, handy=%s]",
				vorname, nachname, emailAdresse, strasse, geburst, ausweis, handy);
	}
	
}
