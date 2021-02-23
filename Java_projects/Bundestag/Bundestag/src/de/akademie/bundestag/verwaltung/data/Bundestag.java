package de.akademie.bundestag.verwaltung.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bundestag {
	private final int MIN_FRAKTION_SIZE = 5;
	private final int ERFOLG = 0;
	private final int FRAKTION_SCHON_VORHANDEN = 1;
	private final int MINDHEIT_NICHT_ERFUELLT = 2;

	private List<Fraktion> fraktionen = new ArrayList<Fraktion>();
	private Mitglied praesident;
	private Map<String, Mitglied> vizepraesidenten = new HashMap<String, Mitglied>();

	public List<Fraktion> getFraktionen() {
		return fraktionen;
	}

	public Mitglied getPraesident() {
		return praesident;
	}

	public Map<String, Mitglied> getVizepraesidenten() {
		return vizepraesidenten;
	}

	public int addFraktion(Fraktion fraktion) {
		if (fraktionen.contains(fraktion)) {
			return FRAKTION_SCHON_VORHANDEN;
		}
		if (!fraktion.equals(new Fraktion("Parteilos")) && fraktion.size() < MIN_FRAKTION_SIZE) {
			return MINDHEIT_NICHT_ERFUELLT;
		}
		fraktionen.add(fraktion);
		return ERFOLG;
	}

	public void changePraesident(Mitglied praesident) {
		this.praesident = praesident;
	}

	public void changeVizepraesident(Mitglied vizepraesident, Fraktion fraktion) {
		vizepraesidenten.put(fraktion.getName(), vizepraesident);
	}

	public boolean removeVizepraesident(Mitglied vizepraesident, Fraktion fraktion) {
		if (!vizepraesidenten.containsKey(fraktion.getName())) {
			return false;
		}
		if (!vizepraesidenten.containsValue(vizepraesident)) {
			return false;
		}
		vizepraesidenten.put(fraktion.getName(), null);
		return true;
	}

	public Partei parteiVonMitglied(Mitglied mitglied) {
		for (Fraktion fraktion : fraktionen) {
			for (Partei partei : fraktion.getParteien()) {
				for (Mitglied m : partei.getMitglieder()) {
					if (m.equals(mitglied)) {
						return partei;
					}
				}
			}
		}
		return null;
	}

	public int anzahlDerParteien() {
		int sum = 0;
		for (Fraktion fraktion : fraktionen) {
			if (!fraktion.getName().equals("Parteilos")) {
				sum += fraktion.getParteien().size();
			}
		}
		return sum;
	}

	public int anzahlDerParteilosen() {
		int sum = 0;
		for (Fraktion fraktion : fraktionen) {
			if (fraktion.getName().equals("Parteilos")) {
				sum = fraktion.size();
				break;
			}
		}
		return sum;
	}

	public BunStatistik bunStatistik() {
		int anzahlDerFraktionen = fraktionen.size() - 1;// Fraktionlos ist keine Fraktion....
		int maxAlter = 0, minAlter = 2000;
		int gW = 0, gD = 0;
		int sumAlter = 0;
		int anzahl = 0;
		FraStatistik frS;
		for (Fraktion fraktion : fraktionen) {
			anzahl += fraktion.size();
			frS = fraktion.fraStatistik();
			if (maxAlter < frS.getMaxAlter()) {
				maxAlter = frS.getMaxAlter();
			}
			if (minAlter > frS.getMinAlter()) {
				minAlter = frS.getMinAlter();
			}
			sumAlter += frS.getDurchAlter();
			gW += frS.getGeschlechterW();
			gD += frS.getGeschlechterD();
		}
		int dAlter = sumAlter / anzahlDerFraktionen;
		double dGW = Math.round(100 * gW / (double) anzahlDerFraktionen) / 100d;
		double dGD = Math.round(100 * gD / (double) anzahlDerFraktionen) / 100d;
		double dGM = 100 - dGD - dGW;
		return new BunStatistik(anzahlDerFraktionen, new Statistik(anzahl, dAlter, maxAlter, minAlter, dGM, dGW, dGD));
	}

	@Override
	public String toString() {
		BunStatistik bStatistik = bunStatistik();
		StringBuilder sb = new StringBuilder("\t\t\tBundestag\n");
		sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		sb.append(String.format("%-30s", "Anzahl der Abgeordneten: ") + String.format("%-10d", bStatistik.getAnzahl())
				+ "\n");
		sb.append(String.format("%-30s", "Anzahl der Fraktionen: ")
				+ String.format("%-10d", bStatistik.getAnzahlDerFraktionen()) + "\n");
		sb.append(String.format("%-30s", "Anzahl der Parteien: ") + String.format("%-10d", anzahlDerParteien()) + "\n");
		sb.append(String.format("%-30s", "Anzahl der Parteilosen: ") + String.format("%-10d", anzahlDerParteilosen())
				+ "\n");
		sb.append(String.format("%-30s", "Präsident: ") + String.format("%-10s", getPraesident()) + " ("
				+ parteiVonMitglied(praesident).getName() + ")" + "\n");
		sb.append("Vizepräsidenten:" + "\n");
		for (Map.Entry<String, Mitglied> entry : vizepraesidenten.entrySet()) {
			if (!entry.getKey().equals("Parteilos")) {
				sb.append(String.format("%-30s", " ") + String.format("%-10s", entry.getValue()) + " (" + entry.getKey()
						+ ")" + "\n");
			}
		}
		sb.append(String.format("%-30s", "Durchnittsalter: ") + String.format("%-10d", bunStatistik().getDurchAlter())
				+ "\n");
		sb.append(String.format("%-30s", "Maxalter: ") + String.format("%-10d", bunStatistik().getMaxAlter()) + "\n");
		sb.append(String.format("%-30s", "Minalter: ") + String.format("%-10d", bunStatistik().getMinAlter()) + "\n");
		sb.append(String.format("%-30s", "Frauen: ") + String.format("%-10.2f", bunStatistik().getGeschlechterW())
				+ "%\n");
		sb.append(String.format("%-30s", "Männer: ") + String.format("%-10.2f", bunStatistik().getGeschlechterM())
				+ "%\n");
		sb.append(String.format("%-30s", "Divers: ") + String.format("%-10.2f", bunStatistik().getGeschlechterD())
				+ "%\n");
		// ********************Fraktionen*******************************

		sb.append("\n\n\t\t\t\tFraktionen\n");
		sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		sb.append(String.format("%-10s", "Name"));
		sb.append(String.format("%-15s", "an. Parteien"));
		sb.append(String.format("%-20s", "an. Abgeordneten"));
		sb.append(String.format("%-10s", "Frauen(%)"));
		sb.append(String.format("%-10s", "Männer(%)"));
		sb.append(String.format("%-10s", "Divers(%)"));
		sb.append(String.format("%-10s", "d. Alter"));
		sb.append(String.format("%-10s", "max Alter"));
		sb.append(String.format("%-10s", "min Alter") + "\n");

		sb.append(String.format("%-10s", "-----"));
		sb.append(String.format("%-15s", "------------"));
		sb.append(String.format("%-20s", "----------------"));
		sb.append(String.format("%-10s", "---------"));
		sb.append(String.format("%-10s", "---------"));
		sb.append(String.format("%-10s", "---------"));
		sb.append(String.format("%-10s", "--------"));
		sb.append(String.format("%-10s", "--------"));
		sb.append(String.format("%-10s", "--------") + "\n");

		for (Fraktion fraktion : fraktionen) {
//			if (!fraktion.getName().equals("Parteilos")) {
			if (fraktion.size() == 0) {
				continue;
			}
			sb.append(String.format("%-10s", fraktion.getName()));
			sb.append(String.format("%-15d", fraktion.anzahlDerParteien()));
			sb.append(String.format("%-20d", fraktion.size()));
			sb.append(String.format("%-10.2f", fraktion.fraStatistik().getGeschlechterW()));
			sb.append(String.format("%-10.2f", fraktion.fraStatistik().getGeschlechterM()));
			sb.append(String.format("%-10.2f", fraktion.fraStatistik().getGeschlechterD()));
			sb.append(String.format("%-10d", fraktion.fraStatistik().getDurchAlter()));
			sb.append(String.format("%-10d", fraktion.fraStatistik().getMaxAlter()));
			sb.append(String.format("%-10d", fraktion.fraStatistik().getMinAlter()));
			sb.append("\n");
//			}
		}
		// ********************Fraktionen*******************************

		sb.append("\n\n\t\t\t\tParteien\n");
		sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		sb.append(String.format("%-10s", "Name"));
		sb.append(String.format("%-20s", "an. Abgeordneten"));
		sb.append(String.format("%-10s", "Frauen(%)"));
		sb.append(String.format("%-10s", "Männer(%)"));
		sb.append(String.format("%-10s", "Divers(%)"));
		sb.append(String.format("%-10s", "d. Alter"));
		sb.append(String.format("%-10s", "max Alter"));
		sb.append(String.format("%-10s", "min Alter") + "\n");

		sb.append(String.format("%-10s", "-----"));
		sb.append(String.format("%-20s", "----------------"));
		sb.append(String.format("%-10s", "---------"));
		sb.append(String.format("%-10s", "---------"));
		sb.append(String.format("%-10s", "---------"));
		sb.append(String.format("%-10s", "--------"));
		sb.append(String.format("%-10s", "--------"));
		sb.append(String.format("%-10s", "--------") + "\n");

		for (Fraktion fraktion : fraktionen) {
			if (fraktion.size() == 0) {
				continue;
			}
			for (Partei partei : fraktion.getParteien()) {
				sb.append(String.format("%-10s", partei.getName()));
				sb.append(String.format("%-20d", partei.size()));
				sb.append(String.format("%-10.2f", partei.parStatistik().getGeschlechterW()));
				sb.append(String.format("%-10.2f", partei.parStatistik().getGeschlechterM()));
				sb.append(String.format("%-10.2f", partei.parStatistik().getGeschlechterD()));
				sb.append(String.format("%-10d", partei.parStatistik().getDurchAlter()));
				sb.append(String.format("%-10d", partei.parStatistik().getMaxAlter()));
				sb.append(String.format("%-10d", partei.parStatistik().getMinAlter()));
				sb.append("\n");
			}

		}
		// ***************************************************
		return sb.toString();
	}
}
