package de.akademie.bundestag.verwaltung.data;

import java.util.ArrayList;
import java.util.List;

public class Fraktion {
	private String name;
	private List<Partei> parteien;

	public Fraktion(String name) {
		this.name = name;
		parteien = new ArrayList<Partei>();
	}

	public String getName() {
		return name;
	}

	public List<Partei> getParteien() {
		return parteien;
	}

	public int anzahlDerParteien() {
		return parteien.size();
	}

	/**
	 * gibt Anzahl der Mitglieder aller Parteien der Fraktion zurück
	 * 
	 * @return int
	 */
	public int size() {
		int sum = 0;

		for (Partei partei : parteien) {
			sum += partei.size();
		}

		return sum;
	}

	public boolean contains(Partei partei) {
		return parteien.contains(partei);
	}

	public boolean addPartei(Partei partei) {
		if (contains(partei)) {
			return false;
		}
		parteien.add(partei);
		return true;
	}

	public boolean removePartei(Partei partei) {
		return parteien.remove(partei);
	}

	public FraStatistik fraStatistik() {
		int anzahl = parteien.size();
		int size = size();
		int maxAlter = 0, minAlter = 2000;
		double gW = 0, gM = 0, gD = 0;
		double alter = 0;
		for (Partei partei : parteien) {
			alter += partei.parStatistik().getDurchAlter();
			gW += partei.parStatistik().getGeschlechterW();
			gM += partei.parStatistik().getGeschlechterM();
			gD += partei.parStatistik().getGeschlechterD();

			if (partei.parStatistik().getMaxAlter() > maxAlter) {
				maxAlter = partei.parStatistik().getMaxAlter();
			}
			if (partei.parStatistik().getMinAlter() < minAlter) {
				minAlter = partei.parStatistik().getMinAlter();
			}
		}
		int dAlter = (int) alter / anzahl;
		double dGW = Math.round(100 * gW / anzahl) / 100d;
		double dGM = Math.round(100 * gM / anzahl) / 100d;
		double dGD = Math.round(100 * gD / anzahl) / 100d;
		return new FraStatistik(anzahl, new Statistik(size, dAlter, maxAlter, minAlter, dGM, dGW, dGD));
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
		if (!(obj instanceof Fraktion))
			return false;
		Fraktion other = (Fraktion) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
