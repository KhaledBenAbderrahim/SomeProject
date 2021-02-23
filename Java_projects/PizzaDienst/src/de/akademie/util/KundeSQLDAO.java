
package de.akademie.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.mysql.cj.xdevapi.Statement;

import de.akademie.personen.Kunde;
import de.akademie.personen.Mitarbeiter;
import de.akademie.personen.Person;
import de.akademie.pizzadienst.dao.KundeDAO;

/**
 * @author Simme
 *
 */
public class KundeSQLDAO implements KundeDAO {
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	
	public KundeSQLDAO() {
		this.connection = DatabaseAccess.connectToDatabase();
	}
	
	@Override
	public List<Kunde> getAllKunden() {
		List<Kunde> allKunden = new ArrayList<Kunde>();
		
		try {
			PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM kunde LIMIT 100");
			ResultSet dbResult = pStmt.executeQuery();
			while(dbResult.next()) {
				allKunden.add(new Kunde(Person.geschlecht.valueOf(dbResult.getString("geschlecht")), 
						dbResult.getString("vorname"), 
						dbResult.getString("nachname"), 
						dbResult.getString("adresse"), 
						dbResult.getInt("postleitzahl"), 
						dbResult.getString("wohnort"), 
						dbResult.getString("telefon_1"), 
						dbResult.getString("telefon_2"), 
						dbResult.getInt("kundenNummer")));
			}
		}
		catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return allKunden;
	}

	@Override
	public Optional<Kunde> getKundeByKundenNr(int kundenNr) {
		try {
			PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM kunde WHERE kundenNummer = ? LIMIT 1");
			pStmt.setInt(1, kundenNr);
			ResultSet dbResult = pStmt.executeQuery();			
			while(dbResult.next()) {
				if(dbResult.getRow() == 0) {
					return null;
				}
				
				Kunde neuerKunde = new Kunde(Person.geschlecht.valueOf(dbResult.getString("geschlecht")), 
						dbResult.getString("vorname"), 
						dbResult.getString("nachname"), 
						dbResult.getString("adresse"), 
						dbResult.getInt("postleitzahl"), 
						dbResult.getString("wohnort"), 
						dbResult.getString("telefon_1"), 
						dbResult.getString("telefon_2"), 
						dbResult.getInt("kundenNummer"));
				return Optional.ofNullable(neuerKunde);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}	
	
	@Override
	public Kunde addNewKunde(Kunde kunde) {
		Kunde neuerKunde = new Kunde(kunde, saveKunde(kunde));
		return neuerKunde;
	}

	@Override
	public int saveKunde(Kunde kunde) {
		try {			
			if(kunde.getKundennummer() == 0) {
				PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO kunde(geschlecht, vorname, nachname, adresse, postleitzahl, wohnort, telefon_1, telefon_2) VALUES (?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
				insertStmt.setString(1, kunde.getGeschlecht().toString());
				insertStmt.setString(2, kunde.getVorname());
				insertStmt.setString(3, kunde.getNachname());
				insertStmt.setString(4, kunde.getAdresse());
				insertStmt.setInt(5, kunde.getPostlaitzahl());
				insertStmt.setString(6, kunde.getWohnort());
				insertStmt.setString(7, kunde.getFestnetznummer());
				insertStmt.setString(8, kunde.getMobilnummer());
				
				insertStmt.executeUpdate();
				ResultSet rs = insertStmt.getGeneratedKeys();

				int kundenNr = rs.next() ? rs.getInt(1) : 0;
				insertStmt.close();	
				return kundenNr;
			}
			else {
				PreparedStatement updateStmt = connection.prepareStatement("UPDATE kunde SET geschlecht = ?, vorname = ?, nachname = ?, adresse = ?, postleitzahl = ?, wohnort = ?, telefon_1 = ?, telefon_2 = ? WHERE kundenNummer = ?");
				updateStmt.setString(1, kunde.getGeschlecht().toString());
				updateStmt.setString(2, kunde.getVorname());
				updateStmt.setString(3, kunde.getNachname());
				updateStmt.setString(4, kunde.getAdresse());
				updateStmt.setInt(5, kunde.getPostlaitzahl());
				updateStmt.setString(6, kunde.getWohnort());
				updateStmt.setString(7, kunde.getFestnetznummer());
				updateStmt.setString(8, kunde.getMobilnummer());
				updateStmt.setInt(9, kunde.getKundennummer());
				
				updateStmt.executeUpdate();
				updateStmt.close();
				return kunde.getKundennummer();
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean deleteKunde(Kunde kunde) {
		try {
			PreparedStatement pStmt = connection.prepareStatement("DELETE FROM kunde WHERE kundenNummer = ? LIMIT 1");
			pStmt.setInt(1, kunde.getKundennummer());
			
			int executed = pStmt.executeUpdate();
			pStmt.close();
			
			if(executed != 0) {				
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Optional<Kunde> getKundeByName(String vorname, String nachname) {
		String sqlMitarbeiterbyName = "SELECT * FROM kunden WHERE vorname = '" + vorname + "'"
				+"AND nachname = '" + nachname + "'"  ;
		
		try {
			showKunden(sqlMitarbeiterbyName );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Optional<Kunde> getKundeByAdresse(String adresse, int postleitzahl, String wohnort) {
		String sqlKundenByAdresse = "SELECT * FROM kunden WHERE adresse = '" + adresse + "'"
				+"AND wohnort= '" + wohnort + "'"  ;
		
		try {
			showKunden(sqlKundenByAdresse);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Optional<Kunde> getKundeByTelefonNr(String nummer) {
		String sqlKundenBytelefoneNr = "SELECT * FROM kunden WHERE telefon_1 = '" + nummer + "'"  ;
		
		try {
			showKunden(sqlKundenBytelefoneNr);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	private void showKunden(String sqlbefehl) throws SQLException {
		Set<Kunde> kunden = new HashSet<>();
		try {

			connection = DatabaseAccess.connectToDatabase();
			PreparedStatement pStmt = connection.prepareStatement(sqlbefehl);
			if (statement != null) {
				ResultSet resultSet = pStmt.executeQuery();			

				while (resultSet.next()) {
					kunden.add(new Kunde(Person.geschlecht.valueOf(resultSet.getString("geschlecht")),
							resultSet.getString("vorname"), resultSet.getString("nachname"),
							resultSet.getString("adresse"), resultSet.getInt("posleitzahl"),
							resultSet.getString("wohnort"), resultSet.getString("telefone_1"),
							resultSet.getString("telefone_2"), resultSet.getInt("mitarbeiterNummer")));
				}
				kunden.stream().forEach(System.out::println);
			}

		} finally {
			
		
			connection.close();

		}
	}
		
	
	

}
