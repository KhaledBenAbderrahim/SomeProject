/**
 * 
 */
package de.akademie.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.akademie.personen.Kunde;
import de.akademie.personen.Person;
import de.akademie.pizzadienst.belaege.Belag;
import de.akademie.pizzadienst.dao.BelagDAO;
import de.akademie.pizzadienst.produkte.Produkt;

/**
 * @author Simme, Ingrid
 *
 */
public class BelagSQLDAO implements BelagDAO {
	
	Connection database;
	
	public BelagSQLDAO() {
		this.database = DatabaseAccess.connectToDatabase();
	}

	@Override
	public List<Belag> getAllBelaege(Produkt produkt) {
		List<Belag> allBelaege = new ArrayList<Belag>();
		try {
			PreparedStatement pStmt = database.prepareStatement("SELECT * FROM belaege LIMIT 100");
			ResultSet dbResult = pStmt.executeQuery();
			while(dbResult.next()) {
				allBelaege.add(new Belag( 
						dbResult.getString("belagName"), 
						dbResult.getInt("preis")));
				}
		}
		catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return allBelaege;
	}
	
	
	
	@Override
	public Belag addNewBelag(Belag belag) {
		Belag neuerBelag = new Belag(belag, saveBelag(belag));
		return neuerBelag;
	}

	@Override
	public int saveBelag(Belag belag) {
		try {			
			if(belag.getBelagNummer() == 0) {
				PreparedStatement insertStmt = database.prepareStatement("INSERT INTO belaege(name, preis) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
				insertStmt.setString(1, belag.getBelagName());
				insertStmt.setInt(2, belag.getPreis());
				
				insertStmt.executeUpdate();
				ResultSet rs = insertStmt.getGeneratedKeys();

				int belagNummer = rs.next() ? rs.getInt(1) : 0;
				insertStmt.close();	
				return belagNummer;
			}
			else {
				PreparedStatement updateStmt = database.prepareStatement("UPDATE belaege SET name = ?, preis = ? WHERE belagID = ?");
				updateStmt.setString(1, belag.getBelagName());
				updateStmt.setInt(2, belag.getPreis());
				updateStmt.setInt(3, belag.getBelagNummer());
				
				updateStmt.executeUpdate();
				updateStmt.close();
				return belag.getBelagNummer();
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
				
		return 0;
	}

	@Override
	public boolean deleteBelag(Belag belag) {
		
		try {
			PreparedStatement pStmt = database.prepareStatement("DELETE FROM belaege WHERE belagID = ? LIMIT 1");
			pStmt.setInt(1, belag.getBelagNummer());
			
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
	public Optional<Belag> getBelagByBelagID(int belagID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Belag> getBelagByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Belag>> getBelagByPreis(int preis) {
		// TODO Auto-generated method stub
		return null;
	}

}
