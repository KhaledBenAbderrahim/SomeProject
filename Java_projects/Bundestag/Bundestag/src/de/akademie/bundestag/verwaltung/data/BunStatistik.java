package de.akademie.bundestag.verwaltung.data;

public class BunStatistik extends Statistik {
	private int anzahlDerFraktionen;

	public BunStatistik(int anzahlDerFraktionen, Statistik statistik) {
		super(statistik);
		this.anzahlDerFraktionen = anzahlDerFraktionen;
	}

	public int getAnzahlDerFraktionen() {
		return anzahlDerFraktionen;
	}
}
