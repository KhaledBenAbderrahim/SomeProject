/**
 * 
 */
package de.akademie.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.akademie.bestellungen.Bestellungen;
import de.akademie.pizzadienst.dao.BestellungenDAO;
import de.akademie.pizzadienst.dao.KundeDAO;
import de.akademie.pizzadienst.dao.MitarbeiterDAO;
import de.akademie.pizzadienst.dao.ProduktDAO;
import de.akademie.pizzadienst.produkte.Produkt;

/**
 * @author Simme
 *
 */
public class BestellungenSQLDAO implements BestellungenDAO {

	Connection database;
	
	public BestellungenSQLDAO() {
		this.database = DatabaseAccess.connectToDatabase();
	}
	
	@Override
	public List<Bestellungen> getAllBestellungen() {
		List<Bestellungen> returnList = new ArrayList<>();
		try {
			PreparedStatement stmt = database.prepareStatement("SELECT * FROM bestellungen");
			ResultSet dbResult = stmt.executeQuery();			
			while(dbResult.next()) {
				if(dbResult.getRow() == 0) {
					return null;
				}
				KundeDAO kDao = new KundeSQLDAO(); 
				MitarbeiterDAO mDao = new MitarbeiterSQLDAO(); 
				Bestellungen neueBestellung = 
					new Bestellungen(dbResult.getInt("bestellNummer"), 
					kDao.getKundeByKundenNr(dbResult.getInt("kundenNummer")).get(), 
					mDao.getMitarbeiterByMitarbeiterNr(dbResult.getInt("Mitarbeiter_mitarbeiterNummer")).get(), 
					dbResult.getTimestamp("bestellZeit").toLocalDateTime(), 
					dbResult.getTimestamp("auslieferZeit").toLocalDateTime(), 
					dbResult.getTimestamp("abnahmeZeit").toLocalDateTime());
				returnList.add(neueBestellung);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return returnList;
	}

	@Override
	public Bestellungen addNewBestellung(Bestellungen bestellung) {
		Bestellungen neueBestellung = new Bestellungen(bestellung, saveBestellung(bestellung));
		return neueBestellung;
	}

	@Override
	public Bestellungen addNewProdukt(Bestellungen bestellung, Produkt produkt) {
		try {
			PreparedStatement stmt = database.prepareStatement("SELECT id FROM einzelbestellung WHERE bestellNummer = ? AND produktID = ?");
			stmt.setInt(1, bestellung.getBestellungsNummer());
			stmt.setInt(2, produkt.getProduktNummer());
			
			ResultSet dbResult = stmt.executeQuery();
			dbResult.next();
			if(dbResult.getRow() == 0) {
				PreparedStatement insertStmt = database.prepareStatement("INSERT INTO einzelbestellung(bestellNummer, produktID, menge, einzelpreis, gesamtpreis) VALUES (?,?,1,?,?)");
				insertStmt.setInt(1, bestellung.getBestellungsNummer());
				insertStmt.setInt(2, produkt.getProduktNummer());
				insertStmt.setInt(3, produkt.BerechneProduktPreis());
				insertStmt.setInt(4, produkt.BerechneProduktPreis());
				
				insertStmt.executeUpdate();
				insertStmt.close();
				//System.out.println(produkt);
				//bestellung.add(produkt);
				return bestellung;
			}
			else {
				PreparedStatement updateStmt = database.prepareStatement("UPDATE einzelbestellung SET menge = menge + 1, gesamtpreis = gesamtpreis + ? WHERE id = ?");
				updateStmt.setInt(1, produkt.BerechneProduktPreis());
				updateStmt.setInt(2, dbResult.getInt("id"));
				updateStmt.executeUpdate();
				//bestellung.add(produkt);
				return bestellung;
			}
					
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return bestellung;	
	}

	@Override
	public int saveBestellung(Bestellungen bestellung) {
		try {			
			if(bestellung.getBestellungsNummer() == 0) {
				PreparedStatement insertStmt = database.prepareStatement("INSERT INTO bestellungen(bestellZeit, auslieferZeit, abnahmeZeit, Mitarbeiter_mitarbeiterNummer, kundenNummer) "
						+ "VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
				insertStmt.setTimestamp(1, Timestamp.valueOf(bestellung.getBestellungZeit()));
				insertStmt.setTimestamp(2, Timestamp.valueOf(bestellung.getAuslieferungZeit()));
				insertStmt.setTimestamp(3, Timestamp.valueOf(bestellung.getAbnahmeZeit()));
				insertStmt.setInt(4, 1/*bestellung.getMitarbeiter().getMitarbeiternummer()*/);
				insertStmt.setInt(5, bestellung.getKunde().getKundennummer());
				
				insertStmt.executeUpdate();
				ResultSet dbResult = insertStmt.getGeneratedKeys();

				int bestellNr = dbResult.next() ? dbResult.getInt(1) : 0;
				insertStmt.close();	
				
				Bestellungen tempBestellung = new Bestellungen(bestellung, bestellNr);
				if(tempBestellung.getBestellungen().size() > 0)
					for(Produkt produkt : tempBestellung.getBestellungen()) {
						addNewProdukt(tempBestellung, produkt);
					}
				return bestellNr;
			}
			else {
				PreparedStatement updateStmt = database.prepareStatement("UPDATE kunde SET bestellZeit = ?, auslieferZeit = ?, abnahmeZeit = ?, Mitarbeiter_mitarbeiterNummer = ?, kundenNummer = ? WHERE bestellNummer = ?");
				updateStmt.setTimestamp(1, Timestamp.valueOf(bestellung.getBestellungZeit()));
				updateStmt.setTimestamp(2, Timestamp.valueOf(bestellung.getAuslieferungZeit()));
				updateStmt.setTimestamp(3, Timestamp.valueOf(bestellung.getAbnahmeZeit()));
				updateStmt.setInt(4, bestellung.getMitarbeiter().getMitarbeiternummer());
				updateStmt.setInt(5, bestellung.getKunde().getKundennummer());
				updateStmt.setInt(6, bestellung.getBestellungsNummer());
				
				updateStmt.executeUpdate();
				updateStmt.close();
				return bestellung.getBestellungsNummer();
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean changeBestellungsStatus(Bestellungen bestellung) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Bestellungen> getBestellungenByBestellNr(int bestellNr) {
		try {
			PreparedStatement stmt = database.prepareStatement("SELECT * FROM bestellungen WHERE bestellNummer = ?");
			stmt.setInt(1, bestellNr);
			ResultSet dbResult = stmt.executeQuery();
			dbResult.next();
			if(dbResult.getRow() == 0) {
				return null;
			}
			else {
				KundeDAO kDao = new KundeSQLDAO(); 
				MitarbeiterDAO mDao = new MitarbeiterSQLDAO(); 
				return Optional.ofNullable(new Bestellungen(dbResult.getInt("bestellNummer"), 
						kDao.getKundeByKundenNr(dbResult.getInt("kundenNummer")).get(), 
						mDao.getMitarbeiterByMitarbeiterNr(dbResult.getInt("Mitarbeiter_mitarbeiterNummer")).get(), 
						dbResult.getTimestamp("bestellZeit").toLocalDateTime(), 
						dbResult.getTimestamp("auslieferZeit").toLocalDateTime(), 
						dbResult.getTimestamp("abnahmeZeit").toLocalDateTime()));
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Optional<Bestellungen> getBestellungenByBestellZeit(LocalDate bestellZeit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Bestellungen>> getBestellungenByMitarbeiterNr(int mitarbeiterNr) {
		List<Bestellungen> returnList = new ArrayList<>();
		try {
			PreparedStatement stmt = database.prepareStatement("SELECT * FROM bestellungen WHERE Mitarbeiter_mitarbeiterNummer = ?");
			stmt.setInt(1, mitarbeiterNr);
			ResultSet dbResult = stmt.executeQuery();			
			while(dbResult.next()) {
				if(dbResult.getRow() == 0) {
					return null;
				}
				KundeDAO kDao = new KundeSQLDAO(); 
				MitarbeiterDAO mDao = new MitarbeiterSQLDAO(); 
				Bestellungen neueBestellung = 
					new Bestellungen(dbResult.getInt("bestellNummer"), 
					kDao.getKundeByKundenNr(dbResult.getInt("kundenNummer")).get(), 
					mDao.getMitarbeiterByMitarbeiterNr(dbResult.getInt("Mitarbeiter_mitarbeiterNummer")).get(), 
					dbResult.getTimestamp("bestellZeit").toLocalDateTime(), 
					dbResult.getTimestamp("auslieferZeit").toLocalDateTime(), 
					dbResult.getTimestamp("abnahmeZeit").toLocalDateTime());
				returnList.add(neueBestellung);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return Optional.ofNullable(returnList);
	}

	@Override
	public Optional<List<Bestellungen>> getBestellungenByKundenNr(int kundenNummer) {
		List<Bestellungen> returnList = new ArrayList<>();
		try {
			PreparedStatement stmt = database.prepareStatement("SELECT * FROM bestellungen WHERE kundenNummer = ?");
			stmt.setInt(1, kundenNummer);
			ResultSet dbResult = stmt.executeQuery();			
			while(dbResult.next()) {
				if(dbResult.getRow() == 0) {
					return null;
				}
				KundeDAO kDao = new KundeSQLDAO(); 
				MitarbeiterDAO mDao = new MitarbeiterSQLDAO(); 
				Bestellungen neueBestellung = 
					new Bestellungen(dbResult.getInt("bestellNummer"), 
					kDao.getKundeByKundenNr(dbResult.getInt("kundenNummer")).get(), 
					mDao.getMitarbeiterByMitarbeiterNr(dbResult.getInt("Mitarbeiter_mitarbeiterNummer")).get(), 
					dbResult.getTimestamp("bestellZeit").toLocalDateTime(), 
					dbResult.getTimestamp("auslieferZeit").toLocalDateTime(), 
					dbResult.getTimestamp("abnahmeZeit").toLocalDateTime());
				returnList.add(neueBestellung);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return Optional.ofNullable(returnList);
	}

	@Override
	public Optional<List<Produkt>> getProdukteAusBestellung(Bestellungen bestellung) {
		List<Produkt> returnList = new ArrayList<>();
		try {
			PreparedStatement stmt = database.prepareStatement("SELECT produktID, menge FROM einzelbestellung WHERE bestellNummer = ?");
			stmt.setInt(1, bestellung.getBestellungsNummer());
			ResultSet dbResult = stmt.executeQuery();			
			while(dbResult.next()) {
				if(dbResult.getRow() == 0) {
					return null;
				}
				ProduktDAO pDao = new ProduktSQLDAO();				
				for(int i = 0; i < dbResult.getInt("menge"); i++) {
					returnList.add(pDao.getProduktByProduktNr(dbResult.getInt("produktID")).get());
				}
				
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return Optional.ofNullable(returnList);
	}

	@Override
	public int getGesamtPreis(Bestellungen bestellung) {
		int sum = 0;
		try {
			PreparedStatement stmt = database.prepareStatement("SELECT gesamtpreis FROM einzelbestellung WHERE bestellNummer = ?");
			stmt.setInt(1, bestellung.getBestellungsNummer());
			ResultSet dbResult = stmt.executeQuery();
			while(dbResult.next()) {
				sum += dbResult.getInt("gesamtpreis");
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return sum;
	}

}
