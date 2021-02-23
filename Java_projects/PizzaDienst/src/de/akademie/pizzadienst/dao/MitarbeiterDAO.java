/**
 * 
 */
package de.akademie.pizzadienst.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import de.akademie.personen.Mitarbeiter;

/**
 * @author Simme
 *
 */
public interface MitarbeiterDAO {
	
	public Set<Mitarbeiter> getAllMitarbeiter() throws SQLException;
	public Mitarbeiter addNewMitarbeiter(Mitarbeiter mitarbeiter) throws SQLException;
	public int saveMitarbeiter(Mitarbeiter mitarbeiter);
	public void deleteMitarbeiter(Mitarbeiter mitarbeiter) throws SQLException;
	
	public Optional<Mitarbeiter> getMitarbeiterByMitarbeiterNr(int mitarbeiterNr) throws SQLException;
	public Optional<Mitarbeiter> getMitarbeiterByName(String vorname, String nachname) throws SQLException;
	public Optional<Mitarbeiter> getMitarbeiterByAdresse(String adresse, int postleitzahl, String wohnort) throws SQLException;
	public Optional<Mitarbeiter> getMitarbeiterByTelefonNr(String nummer) throws SQLException;	
}
