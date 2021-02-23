/**
 * 
 */
package de.akademie.pizzadienst.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import de.akademie.bestellungen.Bestellungen;
import de.akademie.pizzadienst.produkte.Produkt;

/**
 * @author Simme
 *
 */
public interface BestellungenDAO {
	
	public List<Bestellungen> getAllBestellungen();
	public Bestellungen addNewBestellung(Bestellungen bestellung);
	public Bestellungen addNewProdukt(Bestellungen bestellung, Produkt produkt);
	public int saveBestellung(Bestellungen bestellung);
	public boolean changeBestellungsStatus(Bestellungen bestellung);
	
	public Optional<Bestellungen> getBestellungenByBestellNr(int bestellNr);
	public Optional<Bestellungen> getBestellungenByBestellZeit(LocalDate bestellZeit);
	public Optional<List<Bestellungen>> getBestellungenByMitarbeiterNr(int mitarbeiterNr);
	public Optional<List<Bestellungen>> getBestellungenByKundenNr(int kundenNummer);
	public Optional<List<Produkt>> getProdukteAusBestellung(Bestellungen bestellung);
	public int getGesamtPreis(Bestellungen bestellung);
}
