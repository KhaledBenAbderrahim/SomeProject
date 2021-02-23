package de.akademie.bundestag.verwaltung.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mitglied {
	private String vorname;
	private String nachname;
	private Geschlecht geschlecht;
	private LocalDate geburtsjahr;

	public Mitglied(String vorname, String nachname, Geschlecht geschlecht, LocalDate geburtsjahr) {
		this.vorname = vorname.trim();
		this.nachname = nachname.trim();
		this.geschlecht = geschlecht;
		this.geburtsjahr = geburtsjahr;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public LocalDate getGeburtsjahr() {
		return geburtsjahr;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	@Override
	public String toString() {
		return vorname + " " + nachname + " " + geschlecht + " " + geburtsjahr;
	}

	/**
	 * gibt ein List von Mitglieder aus. sie ersetzt Geschlecht als String durch
	 * Geschlecht als Enum
	 * 
	 * @param abgeordnete
	 * @return eine Kette von Mitglieder...
	 */
	public static List<Mitglied> loadMitgliederList() {
		List<Abgeordneter> abgeordnete = Abgeordneter.imporData("mdb.csv");

		List<Mitglied> mitglieder = new ArrayList<Mitglied>();
		Geschlecht geschlecht;
		for (Abgeordneter abgeordneter : abgeordnete) {
			if (abgeordneter.getGeschlecht().equals("m")) {
				geschlecht = Geschlecht.M;
			} else if (abgeordneter.getGeschlecht().equals("w")) {
				geschlecht = Geschlecht.W;
			} else {
				geschlecht = Geschlecht.D;
			}
			mitglieder.add(new Mitglied(abgeordneter.getVorname(), abgeordneter.getNachname(), geschlecht,
					abgeordneter.getGeburtsjahr()));
		}

		return mitglieder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geburtsjahr == null) ? 0 : geburtsjahr.hashCode());
		result = prime * result + ((nachname == null) ? 0 : nachname.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Mitglied))
			return false;
		Mitglied other = (Mitglied) obj;
		if (geburtsjahr == null) {
			if (other.geburtsjahr != null)
				return false;
		} else if (!geburtsjahr.equals(other.geburtsjahr))
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

}
