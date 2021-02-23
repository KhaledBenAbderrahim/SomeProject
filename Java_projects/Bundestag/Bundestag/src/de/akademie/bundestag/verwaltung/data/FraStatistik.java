package de.akademie.bundestag.verwaltung.data;

public class FraStatistik extends Statistik {
	private int anzahlDerParteien;

	public FraStatistik(int anzahlDerParteien, Statistik statistik) {
		super(statistik);
		this.anzahlDerParteien = anzahlDerParteien;
	}

	public int getAnzahlDerParteien() {
		return anzahlDerParteien;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Anzahl der Parteien: " + anzahlDerParteien + ", " + super.toString();
	}
}
