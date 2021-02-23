package de.akademie.personen;
import java.util.Objects;


/**
 * Die Kundenklasse beschreibt einen Mitarbeiter, der Kundenaufträge 
 * annimmt und bearbeitet, aber auch den Bestellservice nutzen kann.
 * 
 * @author Ingrid
 *
 */

public class Mitarbeiter extends Person{
	
	private int mitarbeiternummer;
	
	/**
	 * 
	 * @param mitarbeiternummer die Nuemmer des Mitarbeiters
	 * 
	 */

	public Mitarbeiter(final geschlecht personGeschlecht, final String vorname, final String nachname, final String adresse, final int postlaitzahl, final String wohnort,
			final String festnetznummer, final String mobilnummer, final int mitarbeiternummer) {
		super(personGeschlecht, vorname, nachname, adresse, postlaitzahl, wohnort, festnetznummer, mobilnummer);
		this.mitarbeiternummer = mitarbeiternummer;
	}

	
	

	public int getMitarbeiternummer() {
		return mitarbeiternummer;
	}
	
	/**
	 * @return die Mitarbeiternummer
	 */



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(mitarbeiternummer);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Mitarbeiter))
			return false;
		Mitarbeiter other = (Mitarbeiter) obj;
		return mitarbeiternummer == other.mitarbeiternummer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("------------------------------------------------------------- \n");
		builder.append("Mitarbeiternummer: "  +mitarbeiternummer+ "\n");
		builder.append("" +getGeschlecht());
		builder.append(" " +getVorname());
		builder.append(" " + getNachname()+ "\n");
		builder.append("" +getAdresse()+ "\n");
		builder.append("" +getWohnort());
		builder.append(" " +getPostlaitzahl()+ "\n" );
		builder.append("Festnetz: " +getFestnetznummer()+ "\n");
		builder.append("Mobil: " +getMobilnummer()+ "\n");
		builder.append("------------------------------------------------------------- \n");
		
		return builder.toString();
	}
	
	

}
