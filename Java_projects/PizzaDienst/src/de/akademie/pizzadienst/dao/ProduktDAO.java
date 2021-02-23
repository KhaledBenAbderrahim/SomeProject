/**
 * 
 */
package de.akademie.pizzadienst.dao;

import java.util.List;
import java.util.Optional;

import de.akademie.personen.Kunde;
import de.akademie.pizzadienst.belaege.Belag;
import de.akademie.pizzadienst.produkte.Produkt;

/**
 * @author Simme
 *
 */
public interface ProduktDAO {
	
	public List<Belag> getAllBelaege(Produkt produkt);
	public Belag addNewBelag(Belag belag);
	public Produkt addNewProdukt(Produkt produkt);
	public int saveProdukt(Produkt produkt);
	public boolean deleteProdukt(Produkt produkt);
	
	public Optional<Produkt> getProduktByProduktNr(int produktNr);
	public String getProduktType(Produkt produkt);
	public int getGesamtPreis(Produkt produkt);
}
