package de.akademie.bestellungen;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.akademie.personen.Kunde;
import de.akademie.personen.Mitarbeiter;
import de.akademie.pizzadienst.produkte.Produkt;
/*
 * Diese Klasse beschreibt die Bestellung die bei der "1,2,3 Fly" gebucht ist.
 */

public class Bestellungen {
	
	/*
	 * Jeder Bestellung hat eine BestellungsNummer,BestellungsInhalt (Wie viele Pizza und Baguette),
	 * bestellungZeit,auslieferungZeit und AbnahmeZeit.
	 * Die Bestellung wurde von eine Kunde Bestellt und von "1,2,3Fly" Mitarbeiter angenommen.
	 */

	private int bestellNummer;
	private List<Produkt> bestellungen;
	private LocalDateTime bestellungZeit;
	private LocalDateTime auslieferungZeit;
	private LocalDateTime abnahmeZeit;
	private Kunde kunde;
	private Mitarbeiter mitarbeiter;
	
	/*
	 * für den Konstruktor wi brauchen die Attribute kunde und mitarbeiter als parameter.
	 * 
	 */

	public Bestellungen(Kunde kunde, Mitarbeiter mitarbeiter) {
		this.kunde = kunde;
		this.mitarbeiter = mitarbeiter;
		this.bestellungen = new ArrayList<>();
		this.bestellungZeit = LocalDateTime.now();
		bestellNummer = 0;
		//TODO implement correct time
		auslieferungZeit = LocalDateTime.now().plusMinutes(30);
		abnahmeZeit = LocalDateTime.now().plusMinutes(45);
	}
	
	public Bestellungen(int bestellNummer, Kunde kunde, Mitarbeiter mitarbeiter, LocalDateTime bestellZeit, LocalDateTime auslieferungsZeit, LocalDateTime abnahmeZeit) {
		this.bestellNummer = bestellNummer;
		this.kunde = kunde;
		this.mitarbeiter = mitarbeiter;
		this.bestellungen = new ArrayList<>();
		this.bestellungZeit = bestellZeit;		
		this.auslieferungZeit = auslieferungsZeit;
		this.abnahmeZeit = abnahmeZeit;
	}
	
	public Bestellungen(Bestellungen bestellung, int bestellNummer) {
		this.kunde = bestellung.getKunde();
		this.mitarbeiter = bestellung.getMitarbeiter();
		if(bestellung.getBestellungen().size() > 0) {
			this.bestellungen = bestellung.getBestellungen();
		}
		else {
			this.bestellungen = new ArrayList<>();
		}		
		this.bestellungZeit = bestellung.getBestellungZeit();
		this.bestellNummer = bestellNummer;
		this.auslieferungZeit = bestellung.getAuslieferungZeit();
		this.abnahmeZeit = bestellung.getAbnahmeZeit();
	}
	
	/*
	 * Eine Zügriff methode die unsere Kunde zürückliefert
	 */

	public Kunde getKunde() {
		return kunde;
	}
	
	/*
	 * Eine Zügriff methode die die Mietarbeiter zürückliefert
	 */

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}
	/*
	 * Eine Zügriff methode die die BestellNummer zürückliefert
	 */
	
	public int getBestellungsNummer() {
		return bestellNummer;
	}
	
	/*
	 * Eine Zügriff methode die die BestellungZeit zürückliefert
	 */

	public LocalDateTime getBestellungZeit() {
		return bestellungZeit;
	}

	public LocalDateTime getAuslieferungZeit() {
		return auslieferungZeit;
	}

	public LocalDateTime getAbnahmeZeit() {
		return abnahmeZeit;
	}

	public List<Produkt> getBestellungen() {
		return bestellungen;
	}

	public void add(Produkt bestellung) {
		
		Objects.requireNonNull(bestellung, "Die Bestellung sollte nicht null sein.");
		
		bestellungen.add(bestellung);

	}

	private double getPreis(List<Produkt> bestellung) {
				
		int sum = 0;
		for (Produkt p : bestellung) {
			sum += p.BerechneProduktPreis();
		}
		return sum;
	}
	
	/*
	 * Eine Zügriff methode die die AuslieferungZeit ändert
	 */

	public LocalDateTime changeAuslieferungZeit(LocalDateTime AuslieferungAenderung) {
		
		Objects.requireNonNull(AuslieferungAenderung, "Das Auslieferungdatum sollte nicht null sein.");
		
		this.auslieferungZeit = AuslieferungAenderung;
		return this.auslieferungZeit;

	}
	
	/*
	 * Eine Zügriff methode die die Abnahme Datum ändert
	 */

	public LocalDateTime changeAbnahmeDatum(LocalDateTime plusMinutes) {
		
		Objects.requireNonNull(plusMinutes, "Das Abnahmedatum sollte nicht null sein.");
		
		return plusMinutes;

	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bestellung von :  " + kunde.getVorname() + "\n");
		builder.append("Angenommen von " + mitarbeiter.getVorname() + "\n");
		builder.append("bestellungID : " + getBestellungsNummer() + "\n");
		builder.append("die bestellung : " + getBestellungen() + "\n");
		builder.append("AuslieferungDatum: " + getAuslieferungZeit() + "\n");
		builder.append("Preis: " + getPreis(bestellungen)/100 + "$\n");


		return builder.toString();
	}

}