package de.akademie.bundestag.verwaltung.data;

public class Statistik {
	private int anzahl;
	private int durchAlter;
	private int maxAlter;
	private int minAlter;
	private double geschlechterM;
	private double geschlechterW;
	private double geschlechterD;

	public Statistik(int anzahl, int durchAlter, int maxAlter, int minAlter, double geschlechterM, double geschlechterW,
			double geschlechterD) {
		this.anzahl = anzahl;
		this.durchAlter = durchAlter;
		this.maxAlter = maxAlter;
		this.minAlter = minAlter;
		this.geschlechterM = geschlechterM;
		this.geschlechterW = geschlechterW;
		this.geschlechterD = geschlechterD;
	}

	public Statistik(Statistik statistik) {
		this(statistik.anzahl, statistik.durchAlter, statistik.maxAlter, statistik.minAlter, statistik.geschlechterM,
				statistik.geschlechterW, statistik.geschlechterD);
	}

	public int getAnzahl() {
		return anzahl;
	}

	public int getDurchAlter() {
		return durchAlter;
	}

	public int getMaxAlter() {
		return maxAlter;
	}

	public int getMinAlter() {
		return minAlter;
	}

	public double getGeschlechterM() {
		return geschlechterM;
	}

	public double getGeschlechterW() {
		return geschlechterW;
	}

	public double getGeschlechterD() {
		return geschlechterD;
	}

	@Override
	public String toString() {
		return "Anzahl= " + anzahl + ", Durchschnittsalter: " + durchAlter + ", Minalter: " + minAlter + ", Maxalter: "
				+ maxAlter + ", Geschlechter W(%)= " + geschlechterW + ", M(%)= " + geschlechterM + ", D(%)= "
				+ geschlechterD;
	}
}
