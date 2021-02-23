/**
 * 
 */
package de.akademie.personen;
import java.util.Objects;

/**
 * Die Klasse Person beschreibt eine generische Person.
 * @author Ingrid
 *
 */
public abstract class Person {
	
	public static enum geschlecht {M, W};
	
	private geschlecht personGeschlecht;
	private String vorname;
	private String nachname;
	private String adresse;
	private int postlaitzahl;
	private String wohnort;
	private String festnetznummer;
	private String mobilnummer;
	
	/**
	 * 
	 * @param vorname die Vorname der Person
	 * @param nachname die Nachname der Person
	 * @param adresse die Adresse der Person
	 * @param postleitzahl die Postleitzahl der Person
	 * @param wohnort die Wohnort de Person
	 * @param festnetznummer die Festnetznummer der Person
	 * @param mobilnummer die Mobilnummer der Person
	 */
	
	public Person(final geschlecht personGeschlecht, final String vorname, final String nachname, final String adresse, final int postlaitzahl, final String wohnort, final String festnetznummer,
			final String mobilnummer) {
		this.personGeschlecht = personGeschlecht;
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
		this.postlaitzahl = postlaitzahl;
		this.wohnort = wohnort;
		this.festnetznummer = festnetznummer;
		this.mobilnummer = mobilnummer;
	}

	public geschlecht getGeschlecht() {
		return this.personGeschlecht;
	}
	
	public  String getVorname() {
		return vorname;
	}
	
	/**
	 * @return Vorname
	 */

	public String getNachname() {
		return nachname;
	}
	
	/**
	 * @return Nachname
	 */

	public String getAdresse() {
		return adresse;
	}
	
	/**
	 * @return die Adresse
	 */

	public int getPostlaitzahl() {
		return postlaitzahl;
	}
	
	/**
	 * @return die Postleitzahl
	 */

	public String getWohnort() {
		return wohnort;
	}
	
	/**
	 * @return den Wohnort
	 */

	public String getFestnetznummer() {
		return festnetznummer;
	}
	
	/**
	 * @return die feste Telefonnummer
	 */

	public String getMobilnummer() {
		return mobilnummer;
	}
	
	
	
	/**
	 * @param adresse the adresse to set
	 */
	public void changeAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return die Handynummer
	 */

	@Override
	public int hashCode() {
		return Objects.hash(nachname, vorname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		return Objects.equals(nachname, other.nachname) && Objects.equals(vorname, other.vorname);
	}


	


}
