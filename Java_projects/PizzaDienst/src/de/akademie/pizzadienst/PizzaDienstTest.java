package de.akademie.pizzadienst;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import de.akademie.bestellungen.Bestellungen;
import de.akademie.personen.Kunde;
import de.akademie.personen.Mitarbeiter;
import de.akademie.personen.Person;
import de.akademie.pizzadienst.belaege.Belag;
import de.akademie.pizzadienst.dao.BestellungenDAO;
import de.akademie.pizzadienst.dao.KundeDAO;
import de.akademie.pizzadienst.produkte.Baguette;
import de.akademie.util.BelagSQLDAO;
import de.akademie.util.BestellungenSQLDAO;
import de.akademie.util.KundeSQLDAO;

import de.akademie.util.MitarbeiterSQLDAO;

import de.akademie.util.ProduktSQLDAO;

import de.akademie.pizzadienst.produkte.Pizza;
import de.akademie.pizzadienst.produkte.Produkt;

/**
 * @author Gruppe 2
 *
 */

public class PizzaDienstTest {
	
	public static void main(String[] args) {
		//Connection conn = DatabaseAccess.connectToDatabase();		

		
		////////////////////////////////////////////////
		/////// Kunden
		/////////////////////////////////////////////////


		System.out.println("==========================");
		System.out.println("====== Kunden ======");
		System.out.println("==========================");
		
		Kunde kunde1 = new Kunde(Person.geschlecht.W, "Melinda", "Soffice", "Helmer 4", 28359, "Bremen", "0421108765", "017698654689");
		Kunde kunde2 = new Kunde(Person.geschlecht.M, "Gelsomina", "Felice", "Helmer 20", 28359, "Bremen", "0421108444", "017698655589");


		//System.out.println(kunde1);
		//System.out.println(kunde2);
//		
		KundeDAO kDao = new KundeSQLDAO();
		List<Kunde> kunden = kDao.getAllKunden();
		//System.out.println("Kundenanzahl: " + kunden.size());
		
		
		//kDao.deleteKunde(kunde1);

		kunde1 = kDao.addNewKunde(kunde1);
		kunde2 = kDao.addNewKunde(kunde2);
		
		//System.out.println(kunde1.getKundennummer());
		
		kunde1.changeAdresse("Helmer 6");
		kDao.saveKunde(kunde1);
		
		kunde1 = kDao.getKundeByKundenNr(kunde1.getKundennummer()).get();
		//System.out.println(kunde1);
		
		kunden = kDao.getAllKunden();
		
		for(Kunde kunde : kunden) {
			System.out.println(kunde);
		}
		
		//System.out.println("Kundenanzahl: " + kunden.size());

		
		/////////////////////////////////////////////////
		/////// Mitarbeitern
		/////////////////////////////////////////////////

		System.out.println("==========================");
		System.out.println("====== Mitarbeiter ======");
		System.out.println("==========================");
		
		Mitarbeiter mitarbeiter1 = new Mitarbeiter(Person.geschlecht.M, "Antonio", "Greco", "Helmer 22", 28359, "Bremen", "042", "0176", 44466609 );
		Mitarbeiter mitarbeiter2 = new Mitarbeiter(Person.geschlecht.M, "Franco", "Greco", "Helmer 202", 28359, "Bremen", "042", "01769", 44466688 );
		MitarbeiterSQLDAO mitarbeiterSQLDAO = new MitarbeiterSQLDAO();
		
		                                   /*===ADD==*/
//		try {
//			mitarbeiterSQLDAO.addNewMitarbeiter(mitarbeiter2); //Funktioniert nur bei klein zahlen
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
		
		                                  /*==DELETE==*/
//		try {
//			mitarbeiterSQLDAO.deleteMitarbeiter(mitarbeiter2);//Funktioniert
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
		                             /*==getmitarbeiterByName*/
		try {
			mitarbeiterSQLDAO.getMitarbeiterByName("Antonio", "Greco");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		//System.out.println(mitarbeiter1);
		//System.out.println(mitarbeiter2);
		
		/////////////////////////////////////////////////
		/////// Beläge
		/////////////////////////////////////////////////

		
		System.out.println("==========================");
		System.out.println("====== Beläge ======");
		System.out.println("==========================");
		
		Belag tomatten 	= new Belag("Tomatten", 500);
		Belag kaesse 	= new Belag("Kässe", 150);
		Belag zwiebel 	= new Belag("Zwiebel", 100);
		
		BelagSQLDAO BelagDao = new BelagSQLDAO();
		
		tomatten 	= BelagDao.addNewBelag(tomatten);
		kaesse 		= BelagDao.addNewBelag(kaesse);
		zwiebel 	= BelagDao.addNewBelag(zwiebel);
		
		System.out.println("------ Preise ------");
		System.out.println(tomatten);		
		System.out.println(kaesse);
		System.out.println(zwiebel);
		
		List<Belag> tomattenundkaeseBelag = new ArrayList<Belag>();
		
		tomattenundkaeseBelag.add(tomatten);
		tomattenundkaeseBelag.add(kaesse);
		
		List<Belag> nurkaesseBelag = new ArrayList<Belag>();
		nurkaesseBelag.add(kaesse);
		
		/////////////////////////////////////////////////
		/////// Produkte
		/////////////////////////////////////////////////
		

		System.out.println("==========================");
		System.out.println("====== Produkte ======");
		System.out.println("==========================");
		
		Produkt pizza1 		= new Pizza("Aglio e olio", 3, tomattenundkaeseBelag);
		Produkt pizza2 		= new Pizza("Margarita", 2, nurkaesseBelag);
		Produkt baguette1 	= new Baguette("Kässe Baguette", 1, nurkaesseBelag);
				
		ProduktSQLDAO pDao = new ProduktSQLDAO();
		
		pizza1 		= pDao.addNewProdukt(pizza1);
		pizza2 		= pDao.addNewProdukt(pizza2);
		baguette1 	= pDao.addNewProdukt(baguette1);
		
		List<Belag> allPizzaBelaege = pDao.getAllBelaege(pizza1);
		
		System.out.println("------ Get Alle Beläge ------");
		System.out.println(pizza1.getProduktName());
		System.out.println(allPizzaBelaege);
		
		pDao.getProduktByProduktNr(pizza1.getProduktNummer());
		
		//System.out.println(pizza1);
		//System.out.println(pizza2);
		//System.out.println(baguette1);
		

		/////////////////////////////////////////////////
		/////// Bestellungen
		/////////////////////////////////////////////////
		
		System.out.println("==========================");
		System.out.println("====== Bestellungen ======");
		System.out.println("==========================");
		System.out.println();
		
		BestellungenDAO bDao = new BestellungenSQLDAO();
		
		Bestellungen bestellung1 = new Bestellungen(kunde1, mitarbeiter1);
		
		
		bestellung1.add(pizza1);
		bestellung1.add(pizza1);
		bestellung1.add(pizza2);
		bestellung1.add(baguette1);
		bestellung1.add(baguette1);
		
		System.out.println("Before Database:\n" + bestellung1);
		bestellung1 = bDao.addNewBestellung(bestellung1);
		System.out.println();
		System.out.println("After Database:\n" + bestellung1);
		//bestellung1.changeAuslieferungZeit(LocalDateTime.now());
		//bestellung1.changeAbnahmeDatum(LocalDateTime.now().plusMinutes(60));
		

		Bestellungen bestellung2 = new Bestellungen(kunde2, mitarbeiter2);
		
		bestellung2 = bDao.addNewBestellung(bestellung2);
		System.out.println("Before Database:\n" + bestellung2);
		bestellung2 = bDao.addNewProdukt(bestellung2, pizza1);
		bestellung2.add(pizza1);
		/*for(Produkt produkt : bDao.getProdukteAusBestellung(bestellung2).get()) {
			bestellung2.add(produkt);
		}*/
		System.out.println();
		System.out.println("After Database:\n" + bestellung2);
		
		System.out.println(bDao.getGesamtPreis(bestellung1));
		
		//bestellung2 = bDao.addNewBestellung(bestellung2);


	}

}
