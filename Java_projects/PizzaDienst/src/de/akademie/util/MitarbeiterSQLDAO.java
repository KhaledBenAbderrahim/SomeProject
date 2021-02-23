/**
 * 
 */
package de.akademie.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;



import de.akademie.personen.Mitarbeiter;
import de.akademie.personen.Person;
import de.akademie.pizzadienst.dao.MitarbeiterDAO;

/**
 * @author Simme
 *
 */
public class MitarbeiterSQLDAO implements MitarbeiterDAO {

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	@Override
	public Set<Mitarbeiter> getAllMitarbeiter() throws SQLException {
		Set<Mitarbeiter> mitarbeiter = new HashSet<>();
		try {

			connection = DatabaseAccess.connectToDatabase();
			statement = connection.createStatement();
			if (statement != null) {
				ResultSet resultSet = statement.executeQuery("SELECT * from mitarbeiter");

				while (resultSet.next()) {
					mitarbeiter.add(new Mitarbeiter(Person.geschlecht.valueOf(resultSet.getString("geschlecht")),
							resultSet.getString("vorname"), resultSet.getString("nachname"),
							resultSet.getString("adresse"), resultSet.getInt("postleitzahl"),
							resultSet.getString("wohnort"), resultSet.getString("telefon_1"),
							resultSet.getString("telefon_2"), resultSet.getInt("mitarbeiterNummer")));
				}
			}

		} finally {
			statement.close();
			
			connection.close();

		}

		return mitarbeiter;
	}

	@Override
	public Mitarbeiter addNewMitarbeiter(Mitarbeiter t) throws SQLException {
		try {
			connection = DatabaseAccess.connectToDatabase();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO mitarbeiter(geschlecht, vorname, nachname, adresse, postleitzahl, wohnort, telefon_1, telefon_2) VALUES (?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, null);
			preparedStatement.setString(2, t.getVorname());
			preparedStatement.setString(3, t.getNachname());
			preparedStatement.setString(4, t.getAdresse());
			preparedStatement.setInt(5, t.getPostlaitzahl());
			preparedStatement.setString(6, t.getWohnort());
			preparedStatement.setString(7, t.getMobilnummer());
			preparedStatement.setString(8, t.getFestnetznummer());

//			int sqlCodeErgebnisse = preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			int mitarbeiterNr = resultSet.next() ? resultSet.getInt(1) : 0;
			preparedStatement.close();
			return new Mitarbeiter(t.getGeschlecht(), t.getVorname(), t.getNachname(), t.getAdresse(),
					t.getPostlaitzahl(), t.getWohnort(), t.getFestnetznummer(), t.getMobilnummer(), mitarbeiterNr);

		} finally {
			preparedStatement.close();
			connection.close();
			

		}

	}

	@Override
	public int saveMitarbeiter(Mitarbeiter mitarbeiter) {

		return 0;
	}

	@Override
	public void deleteMitarbeiter(Mitarbeiter mitarbeiter) throws SQLException {
		String deleteSQL = "DELETE FROM mitarbeiter WHERE vorname = '"+mitarbeiter.getVorname()+"'";
		
		System.out.println("delete " +  mitarbeiter);
		excute(deleteSQL);
	
	}

	@Override
	public Optional<Mitarbeiter> getMitarbeiterByMitarbeiterNr(int mitarbeiterNr) throws SQLException {
		String sqlQuery ="SELECT * for mitarbeiter WHERE mitarbeiterNummer = " + mitarbeiterNr ;
		Mitarbeiter m = null;
		try {
			connection = DatabaseAccess.connectToDatabase();
			statement = connection.createStatement();
			if (statement != null) {
				resultSet = statement.executeQuery(sqlQuery);
				while (resultSet.next()) {
					 m = new Mitarbeiter(Person.geschlecht.valueOf(resultSet.getString("geschlecht")),
							resultSet.getString("vorname"), resultSet.getString("nachname"),
							resultSet.getString("adresse"), resultSet.getInt("postleitzahl"),
							resultSet.getString("wohnort"), resultSet.getString("telefon_1"),
							resultSet.getString("telefon_2"), resultSet.getInt("mitarbeiterNummer"));
					System.out.println(m);
				}
			}
			
			
		}finally {
			statement.close();
			resultSet.close();
			connection.close();
		}
		return Optional.ofNullable(m);
	}

	@Override
	public Optional<Mitarbeiter> getMitarbeiterByName(String vorname, String nachname) throws SQLException {
		String sqlMitarbeiterbyName = "SELECT * FROM mitarbeiter WHERE vorname = '" + vorname + "'"
				+"AND nachname = '" + nachname + "'"  ;
				
		
		showMitarbeiter(sqlMitarbeiterbyName);
		
	

		return null;
	}

	@Override
	public Optional<Mitarbeiter> getMitarbeiterByAdresse(String adresse, int postleitzahl, String wohnort) throws SQLException {
		String sqlMitarbeiterbyAdresse = "SELECT *  FROM mitarbeiter WHERE adresse = " + 
				adresse + " AND posleitzahl = " + postleitzahl + " AND wohnort = " + wohnort;
		
		showMitarbeiter(sqlMitarbeiterbyAdresse);
		
		return null;
	}

	@Override
	public Optional<Mitarbeiter> getMitarbeiterByTelefonNr(String nummer) throws SQLException {
		String sqlMitarbeiterbyTelefonNr = "SELECT * FROM mitarbeiter WHERE telefon_1 = " 
				+ nummer + " telefon_2 = " + nummer;
		showMitarbeiter(sqlMitarbeiterbyTelefonNr);
		
		return null;
	}

	private void excute(String sqlBefehl) throws SQLException {
		try {
			connection = DatabaseAccess.connectToDatabase();
			statement = connection.createStatement();
			if (statement != null) {
				statement.execute(sqlBefehl);
			}
		} finally {
			statement.close();
			connection.close();
		}
	}
	
	private void showMitarbeiter(String sqlbefehl) throws SQLException {
		Set<Mitarbeiter> mitarbeiter = new HashSet<>();
		try {

			connection = DatabaseAccess.connectToDatabase();
			statement = connection.createStatement();
			if (statement != null) {
				ResultSet resultSet = statement.executeQuery(sqlbefehl);

				while (resultSet.next()) {
					mitarbeiter.add(new Mitarbeiter(Person.geschlecht.valueOf(resultSet.getString("geschlecht")),
							resultSet.getString("vorname"), resultSet.getString("nachname"),
							resultSet.getString("adresse"), resultSet.getInt("postleitzahl"),
							resultSet.getString("wohnort"), resultSet.getString("telefon_1"),
							resultSet.getString("telefon_2"), resultSet.getInt("mitarbeiterNummer")));
				}
				mitarbeiter.stream().forEach(System.out::println);
			}

		} finally {
			statement.close();
			
			connection.close();

		}
		
	}

}
