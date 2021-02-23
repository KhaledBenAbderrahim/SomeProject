package de.akademie.bundestag.verwaltung.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Partei {
	private String name;
	private List<Mitglied> mitglieder;

	public Partei(String name) {
		this.name = name;
		this.mitglieder = new ArrayList<Mitglied>();
		List<Mitglied> mitglieder = Mitglied.loadMitgliederList();

		List<Abgeordneter> abgeordnete = Abgeordneter.imporData("mdb.csv");

		for (int i = 0; i < abgeordnete.size(); i++) {
			if (abgeordnete.get(i).getPartei().equals(name)) {
				this.mitglieder.add(mitglieder.get(i));
			}

		}

	}

	public String getName() {
		return name;
	}

	public List<Mitglied> getMitglieder() {
		return mitglieder;
	}

	public int size() {
		return mitglieder.size();

	}

	public boolean contains(Mitglied mitglied) {

//		for (Mitglied m : mitglieder) {
//			if (mitglied.equals(m)) {
//				return true;
//			}
//		}
//		return false;
		return this.mitglieder.contains(mitglied);
	}

	public Statistik parStatistik() {
		int anzahl = size();
		int sumalter = 0, maxAlter = 0, minAlter = 2000, alter;
		int gW = 0, gM = 0, gD = 0;

		for (Mitglied mitglied : mitglieder) {
			alter = LocalDate.now().getYear() - mitglied.getGeburtsjahr().getYear();
			sumalter += alter;
			if (alter < minAlter) {
				minAlter = alter;
			}
			if (alter > maxAlter) {
				maxAlter = alter;
			}
			if (mitglied.getGeschlecht() == Geschlecht.W) {
				gW++;
			} else if (mitglied.getGeschlecht() == Geschlecht.D) {
				gD++;
			}
		}
		if (anzahl == 0) {
			return new Statistik(0, 0, 0, 2000, 0, 0, 0);
		}
		int dAlter = sumalter / anzahl;
		double dGW = Math.round(100 * 100 * gW / (double) anzahl) / 100d;
		double dGD = Math.round(100 * 100 * gD / (double) anzahl) / 100d;
		double dGM = 100 - dGD - dGW;

		return new Statistik(anzahl, dAlter, maxAlter, minAlter, dGM, dGW, dGD);
	}

	public boolean addMitglied(Mitglied mitglied) {
		if (this.mitglieder.contains(mitglied)) {
			return false;
		}
		mitglieder.add(mitglied);
		return true;
	}

	public boolean removeMitglied(Mitglied mitglied) {
		return mitglieder.remove(mitglied);
	}

	@Override
	public String toString() {
		String str = name + " " + size() + "\n**************";
		for (Mitglied mitglied : mitglieder) {
			str += mitglied.toString();
		}
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Partei))
			return false;
		Partei other = (Partei) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
