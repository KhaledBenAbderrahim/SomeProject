package de.akademie.bundestag.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.akademie.bundestag.verwaltung.data.Abgeordneter;
import de.akademie.bundestag.verwaltung.data.Bundestag;
import de.akademie.bundestag.verwaltung.data.Fraktion;
import de.akademie.bundestag.verwaltung.data.Geschlecht;
import de.akademie.bundestag.verwaltung.data.Mitglied;
import de.akademie.bundestag.verwaltung.data.Partei;

public class BundestagTest {

	public static void main(String[] args) {

		List<Abgeordneter> abgeordnete = Abgeordneter.imporData("mdb.csv");
//		System.out.println(abgeordnete.size());
//		System.out.println(abgeordnete);
//		for (Abgeordneter abgeordneter : abgeordnete) {
//			if (abgeordneter.getPartei().equals("Bündnis 90")) {
//				System.out.println(abgeordneter);
//			}
//		}

		List<Mitglied> mitglieder = Mitglied.loadMitgliederList();
//		System.out.println(mitglieder);
//                                   0      1      2      3      4         5          6          7
		String[] parteienNamen = { "CDU", "CSU", "SPD", "AfD", "FDP", "DIE LINKE", "GRÜNE", "Parteilos" };

		List<Partei> parteien = new ArrayList<Partei>();
		for (String pName : parteienNamen) {
			parteien.add(new Partei(pName));
		}

//		Partei pLos = parteien.get(7);
//		pLos.addMitglied(new Mitglied("Max", "Musterman", Geschlecht.M, LocalDate.of(1980, 1, 1)));
//		pLos.addMitglied(new Mitglied("Soldan", "Wolfgan", Geschlecht.M, LocalDate.of(1975, 1, 1)));
//		pLos.addMitglied(new Mitglied("Simon", "Reiter", Geschlecht.M, LocalDate.of(1977, 1, 1)));
//		pLos.addMitglied(new Mitglied("Lisa", "Musterfrau", Geschlecht.W, LocalDate.of(1982, 1, 1)));
//		pLos.addMitglied(new Mitglied("Lax", "Muster", Geschlecht.D, LocalDate.of(1977, 1, 1)));

//		for (Partei partei : parteien) {
//			System.out.println(partei.getName() + "\n" + partei.parStatistik());
//
//		}

//		for (Mitglied m : parteien.get(0).getMitglieder()) {
//			System.out.println(m);
//		}
//		Mitglied m = new Mitglied("Paul", "Ziemiak", Geschlecht.M, LocalDate.of(1985, 1, 1));
//		System.out.println(parteien.get(0).contains(m));

		Fraktion cdu_csuFraktion = new Fraktion("CDU/CSU");
		cdu_csuFraktion.addPartei(parteien.get(0));
		cdu_csuFraktion.addPartei(parteien.get(1));
//		System.out.println(cdu_csuFraktion.addPartei(parteien.get(0)));
//		System.out.println(cdu_csuFraktion.addPartei(parteien.get(0)));
//		System.out.println(cdu_csuFraktion.addPartei(parteien.get(1)));
//
//		System.out.println("Name der Fraktion: " + cdu_csuFraktion.getName() + "\n" + cdu_csuFraktion.fraStatistik());

		Fraktion spdFraktion = new Fraktion("SPD");
		spdFraktion.addPartei(parteien.get(2));

		Fraktion afdFraktion = new Fraktion("AfD");
		afdFraktion.addPartei(parteien.get(3));

		Fraktion fdpFraktion = new Fraktion("FDP");
		fdpFraktion.addPartei(parteien.get(4));

		Fraktion linkeFraktion = new Fraktion("DIE LINKE");
		linkeFraktion.addPartei(parteien.get(5));

		Fraktion grueneFraktion = new Fraktion("GRÜNE");
		grueneFraktion.addPartei(parteien.get(6));

		Fraktion parteilosFraktion = new Fraktion("Parteilos");
		parteilosFraktion.addPartei(parteien.get(7));

//		System.out
//				.println("Name der Fraktion: " + parteilosFraktion.getName() + "\n" + parteilosFraktion.fraStatistik());

		Bundestag bundestag = new Bundestag();
		bundestag.addFraktion(cdu_csuFraktion);
		bundestag.addFraktion(spdFraktion);
		bundestag.addFraktion(afdFraktion);
		bundestag.addFraktion(fdpFraktion);
		bundestag.addFraktion(linkeFraktion);
		bundestag.addFraktion(grueneFraktion);
		bundestag.addFraktion(parteilosFraktion);
//		System.out.println(bundestag.addFraktion(cdu_csuFraktion));
//		System.out.println(bundestag.addFraktion(cdu_csuFraktion));
//		System.out.println(bundestag.addFraktion(spdFraktion));
//		System.out.println(bundestag.addFraktion(afdFraktion));
//		System.out.println(bundestag.addFraktion(fdpFraktion));
//		System.out.println(bundestag.addFraktion(linkeFraktion));
//		System.out.println(bundestag.addFraktion(grueneFraktion));
//		System.out.println(bundestag.addFraktion(parteilosFraktion));

		bundestag.changePraesident(new Mitglied("Wolfgang", "Schäuble", Geschlecht.M, LocalDate.of(1942, 1, 1)));
		bundestag.changeVizepraesident(new Mitglied("Hans-Peter", "Friedrich", Geschlecht.M, LocalDate.of(1957, 1, 1)),
				cdu_csuFraktion);
		bundestag.changeVizepraesident(new Mitglied("Thomas", "Oppermann", Geschlecht.M, LocalDate.of(1954, 1, 1)),
				spdFraktion);
		bundestag.changeVizepraesident(new Mitglied("Wolfgang", "Kubicki", Geschlecht.M, LocalDate.of(1952, 1, 1)),
				fdpFraktion);
		bundestag.changeVizepraesident(new Mitglied("Petra", "Pau", Geschlecht.W, LocalDate.of(1963, 1, 1)),
				linkeFraktion);
		bundestag.changeVizepraesident(new Mitglied("Claudia", "Roth", Geschlecht.W, LocalDate.of(1955, 1, 1)),
				linkeFraktion);

		// for (Fraktion fr : bundestag.getFraktionen()) {
//			if (fr.getName().equals("Parteilos")) {
//				continue;
//			}
//			if (fr.getName().equals("CDU/CSU")) {
//				bundestag.addVizepraesident(fr.getParteien().get(0).getMitglieder().get(1), fr);
//			} else
//				bundestag.addVizepraesident(fr.getParteien().get(0).getMitglieder().get(0), fr);
//		}
//		System.out.println(bundestag.bunStatistik());
//		System.out.println(bundestag.getPraesident());
//		System.out.println(bundestag.getVizepraesidenten());
//		Fraktion fTest = new Fraktion("Test");
//		Partei pTest = new Partei("XY");
//		pTest.addMitglied(new Mitglied("a1", "b1", Geschlecht.M, LocalDate.of(1982, 1, 1)));
//		pTest.addMitglied(new Mitglied("a2", "b2", Geschlecht.M, LocalDate.of(1982, 1, 1)));
//		pTest.addMitglied(new Mitglied("a3", "b3", Geschlecht.M, LocalDate.of(1982, 1, 1)));
//		pTest.addMitglied(new Mitglied("a4", "b4", Geschlecht.M, LocalDate.of(1982, 1, 1)));
//		pTest.addMitglied(new Mitglied("a5", "b5", Geschlecht.M, LocalDate.of(1982, 1, 1)));
//		fTest.addPartei(pTest);
//		System.out.println(bundestag.addFraktion(fTest));
		System.out.println(bundestag);
	}

}
