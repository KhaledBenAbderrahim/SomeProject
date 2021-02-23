/**
 * 
 */
package de.akademie.pizzadienst.dao;

import java.util.List;
import java.util.Optional;

import de.akademie.personen.Kunde;

/**
 * @author Simme
 *
 */
public interface KundeDAO {
	
	public List<Kunde> getAllKunden();
	public Kunde addNewKunde(Kunde kunde);
	public int saveKunde(Kunde kunde);
	public boolean deleteKunde(Kunde kunde);
	
	public Optional<Kunde> getKundeByKundenNr(int kundenNr);
	public Optional<Kunde> getKundeByName(String vorname, String nachname);
	public Optional<Kunde> getKundeByAdresse(String adresse, int postleitzahl, String wohnort);
	public Optional<Kunde> getKundeByTelefonNr(String nummer);	
}
