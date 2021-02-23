package de.akademie.hausverwaltung;

import java.time.LocalDate;

import de.akademie.hausverwaltung.mieter.*;
import de.akademie.hausverwaltung.zimmern.*;

public class HausverwaltungTest {

	public static void main(String[] args) {
		
		Mieter mieter1 = new Mieter("Max", "Mustermann", LocalDate.of(1979, 2, 1), "max@mustermann.de", "Musterstraﬂe", 28217, 122 ,"ID111212", 05712232123);
		Mieter mieter2 = new Mieter("Sven", "M¸ller", LocalDate.of(1980, 9, 25), "Sven@musterdomains.de", "Musterstraﬂe", 28217, 123 ,"ID111213", 05712232124);
		Mieter mieter3 = new Mieter("Sarah", "Otto", LocalDate.of(1984, 3, 3), "Sarah@domainsmuster.de", "Musterstraﬂe", 28217, 124 ,"ID111214", 05712232125);

		System.out.println("\n======= Mietern ========\n");
		
		System.out.println(mieter1);
		System.out.println(mieter2);
		System.out.println(mieter3);
		
		System.out.println("\n======= Zimmern ========\n");
		
		WohnZimmer zimmer1 	= new WohnZimmer(0, 0, 240000, 400.0, 600.0, true);
		Kueche zimmer2 		= new Kueche(0, 50000, 100000, 500.0, 300.0, true);
		BadZimmer zimmer3 	= new BadZimmer(20000, 0, 100000, 300.0, 400.0, true);
		Flur zimmer4 		= new Flur(30000, 0, 0, 100.0, 300.0, true);
		
		
		try{
			//WohnZimmer zimmerTest 	= new WohnZimmer(-1, 3, 4, 0., 0., true);	
		}catch(IllegalArgumentException ex) {
			System.err.println("Illegalargument: " + ex.getMessage());
		}
		
		System.out.println(zimmer1);
		System.out.println(zimmer2);
		System.out.println(zimmer3);
		System.out.println(zimmer4);
		
		WohnZimmer zimmerExtra 	= new WohnZimmer(0, 0, 120000, 400.0, 300.0, false);
		
		System.out.println("\n======= Wohnungen ========\n");
	
		Wohnung wohnung1 = new Wohnung(zimmer1, zimmer2, zimmer3, zimmer4, 1001, 69.00);
		Wohnung wohnung2 = new Wohnung(zimmer1, zimmer2, zimmer3, zimmer4, 1002, 69.00);
		Wohnung wohnung3 = new Wohnung(zimmer1, zimmer2, zimmer3, zimmer4, 1003, 30.00);
		Wohnung wohnung4 = new Wohnung(zimmer1, zimmer2, zimmer3, zimmer4, 1004, 30.00);
		Wohnung wohnung5 = new Wohnung(zimmer1, zimmer2, zimmer3, zimmer4, 1005, 70.00);
		
		wohnung1.addZimmer(zimmerExtra); // extrazimmer
		wohnung2.addZimmer(zimmer3); // badzimmer
		wohnung3.addZimmer(zimmer2); // kuche
		wohnung4.addZimmer(zimmerExtra); // kuche
		wohnung4.addZimmer(zimmer3); // kuche

		try {
			//wohnung1.addZimmer(null);
		}catch(NullPointerException ex) {
			System.err.println("Nullpointer: " + ex.getMessage());
		}

		try {
			//Wohnung wohnungTest = new Wohnung(zimmer1, zimmer2, zimmer3, zimmer4, 1005, -70.00);
		}catch(IllegalArgumentException ex) {
			System.err.println("Illegalargument: " + ex.getMessage());
		}
		
		System.out.println(wohnung1);
		System.out.println(wohnung2);
		System.out.println(wohnung3);
		System.out.println(wohnung4);
		System.out.println(wohnung5);
		
		System.out.println("\n======= Etagen ========\n");
		
		Etage etage1 = new Etage(1, wohnung1, wohnung2);
		Etage etage2 = new Etage(2, wohnung3, wohnung4);

		try {
			//Etage etage1Test = new Etage(1, wohnung1, wohnung1);
		}catch(IllegalArgumentException ex) {
			System.err.println("Illegalargument: " + ex.getMessage());
		}
		
		try {
			//etage1.addWohnung(wohnung1);
		}catch(IllegalArgumentException ex) {
			System.err.println("Illegalargument: " + ex.getMessage());
		}
		
		etage1.addWohnung(wohnung5);
		
		System.out.println(etage1);
		System.out.println(etage2);
		
		System.out.println("\n======= Immobiele ========\n");
		
		Immobiele gebeude1 = new Immobiele(etage1, 13, 28325, "Bremen", "Musterheerstraﬂe");
		//gebeude1.addEtage(etage2);
		
		Immobiele gebeude2 = new Immobiele(etage1, 14, 28325, "Bremen", "Musterheerstraﬂe");
		gebeude2.addEtage(etage2);
		
		Immobiele gebeude3 = new Immobiele(etage1, 15, 28325, "Bremen", "Musterheerstraﬂe");
		
		System.out.println(gebeude1);
		
		HausVerwaltung hw = new HausVerwaltung("Gold & Glitzer GmbH");
		
		hw.addImmobiele(gebeude1);
		hw.addImmobiele(gebeude2);
		hw.addImmobiele(gebeude3);
		
		hw.addMieter(mieter1);
		hw.addMieter(mieter2);
		hw.addMieter(mieter3);
				
		System.out.println("\n======= Wohnung Miete ========\n");
		
		hw.vermieten(28325, "Bremen", "Musterheerstraﬂe", 13, 1001, mieter1);
		// TODO change mieter adress
				
		System.out.println("leere Wohnungen: " + hw.getLeereWohnungen());
		System.out.println("belegte Wohnungen: " + hw.getVermieteteWohnungen());
		System.out.println("alle Wohnungen: " + hw.getAlleWohnungen());
		
		// TODO list in eine Adresse
		// TODO zust‰ndigkeit ‰nderen removeImmobilie
		// TODO Zimmern mehere Eingenschaften haben
	
		hw.unvermieten(28325, "Bremen", "Musterheerstraﬂe", 13, 1001);
		
		System.out.println("\n" + gebeude1 + "\n");

		System.out.println("leere Wohnungen: " + hw.getLeereWohnungen());
		System.out.println("belegte Wohnungen: " + hw.getVermieteteWohnungen());
		System.out.println("alle Wohnungen: " + hw.getAlleWohnungen());
		
		System.out.println("\n======= Hausverwaltung ========\n");
		
		System.out.println(hw.print());
	}
}
