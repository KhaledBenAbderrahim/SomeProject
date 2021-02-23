package de.akademie.personen;
import java.util.Objects;

import lombok.Data;

/**
 * Die Klasse Kunde beschreibt eine Kunde die den Bestellungservice verwendet.
 * @author Ingrid
 *
 */

public class Kunde extends Person{
	
	private int kundennummer;
	
	/**
	 * 
	 * @param kundennummer die Nummer der Kunde
	 * 
	 */

	public Kunde(final geschlecht personGeschlecht, final String vorname, final String nachname, final String adresse, final int postlaitzahl, final String wohnort, final String festnetznummer,
			final String mobilnummer, final int kundennummer) {
		super(personGeschlecht, vorname, nachname, adresse, postlaitzahl, wohnort, festnetznummer, mobilnummer);
		this.kundennummer = kundennummer;
	}
	
	public Kunde(final geschlecht personGeschlecht, final String vorname, final String nachname, final String adresse, final int postlaitzahl, final String wohnort, final String festnetznummer,
			final String mobilnummer) {
		super(personGeschlecht, vorname, nachname, adresse, postlaitzahl, wohnort, festnetznummer, mobilnummer);
		this.kundennummer = 0;
	}
	
	public Kunde(Kunde kunde, final int kundenNummer) {
		super(kunde.getGeschlecht(), 
				kunde.getVorname(), 
				kunde.getNachname(), 
				kunde.getAdresse(), 
				kunde.getPostlaitzahl(), 
				kunde.getWohnort(), 
				kunde.getFestnetznummer(), 
				kunde.getMobilnummer());
		this.kundennummer = kundenNummer;
	}
	
	/**
	 * @return die Kundenummer
	 */

	public int getKundennummer() {
		return kundennummer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(kundennummer);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Kunde))
			return false;
		Kunde other = (Kunde) obj;
		return kundennummer == other.kundennummer;
	}

	@Override
	public String toString() {
		String geschlecht = getGeschlecht().toString().equals("M") ? "Herr" : "Frau";
		
		StringBuilder builder = new StringBuilder();
		builder.append("------------------------------------------------------------- \n");
		builder.append("Kundennummer: "  +kundennummer+ "\n");
		builder.append("" +geschlecht);
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
