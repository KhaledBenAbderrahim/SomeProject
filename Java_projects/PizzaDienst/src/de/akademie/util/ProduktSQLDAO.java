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

import de.akademie.pizzadienst.belaege.Belag;
import de.akademie.pizzadienst.dao.ProduktDAO;
import de.akademie.pizzadienst.produkte.Baguette;
import de.akademie.pizzadienst.produkte.Pizza;
import de.akademie.pizzadienst.produkte.Produkt;

/**
 * @author Simme
 *
 */
public class ProduktSQLDAO implements ProduktDAO {

	Connection database;
	
	public ProduktSQLDAO() {
		
		this.database = DatabaseAccess.connectToDatabase();	
	}
	
	@Override
	public List<Belag> getAllBelaege(Produkt produkt) {
		
		List<Belag> allBelaege = new ArrayList<Belag>();

		try {
			
			PreparedStatement pStmt = database.prepareStatement("SELECT * FROM produktbelaege as a "
					+ "LEFT JOIN belaege AS b "
					+ "ON (a.belagID = b.belagID) "
					+ "WHERE a.produktID = ?");
			
			pStmt.setInt(1, produkt.getProduktNummer());
			ResultSet dbResult = pStmt.executeQuery();
			
			while(dbResult.next()) {
				allBelaege.add(new Belag(dbResult.getString("name"), dbResult.getInt("preis"), dbResult.getInt("belagID")));
			}
		}
		catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return allBelaege;
		
	}

	@Override
	public Belag addNewBelag(Belag belag) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Produkt addNewProdukt(Produkt produkt) {
		
		Produkt neuesProdukt = null;
		
		if(produkt instanceof Pizza) {
			neuesProdukt = new Pizza((Pizza)produkt, saveProdukt(produkt));	
		}else if(produkt instanceof Baguette){
			neuesProdukt = new Baguette((Baguette) produkt, saveProdukt(produkt));
		}
		
		return neuesProdukt;
	}

	@Override
	public int saveProdukt(Produkt produkt) {
		try {
			
			PreparedStatement insertStmt, insertStmt2;
			
			if(produkt.getProduktNummer() == 0) {
				
				insertStmt = database.prepareStatement("INSERT INTO produkte "
						+ "(produktName) "
						+ "VALUES (?)", 
						PreparedStatement.RETURN_GENERATED_KEYS);
				
				insertStmt.setString(1, produkt.getProduktName());
				
				insertStmt.executeUpdate();
				ResultSet rs = insertStmt.getGeneratedKeys();
				
				int produktID = rs.next() ? rs.getInt(1) : 0;
				insertStmt.close();
				
				if(produkt instanceof Pizza) {
					
					// save in pizza table and produktbelage
					if(produktID != 0) {
						
						// insert into pizza table
						insertStmt = database.prepareStatement("INSERT INTO pizza "
								+ "(Pizza_Groesse, produktID) "
								+ "VALUES (?, ?)", 
								PreparedStatement.RETURN_GENERATED_KEYS);
						
						insertStmt.setInt(1, ((Pizza) produkt).getGroesseID());
						insertStmt.setInt(2, produktID);
						
						insertStmt.executeUpdate();
						insertStmt.close();
						
						// insert into pizza produktbelaege table
						
						for(Belag belag : produkt.getBelaege()) {
							
							insertStmt = database.prepareStatement("INSERT INTO produktbelaege "
									+ "(produktID, belagID) "
									+ "VALUES (?, ?)", 
									PreparedStatement.RETURN_GENERATED_KEYS);
						
							insertStmt.setInt(1, produktID);
							insertStmt.setInt(2, belag.getBelagNummer());
							
							insertStmt.executeUpdate();		
							insertStmt.close();
							
						}
						
					}
					
				}else if(produkt instanceof Baguette){
					
					// save in pizza table and produktbelage
					if(produktID != 0) {
						
						// insert into pizza table
						insertStmt2 = database.prepareStatement("INSERT INTO baguette "
								+ "(Baguette_Groesse, produktID) "
								+ "VALUES (?, ?)", 
								PreparedStatement.RETURN_GENERATED_KEYS);
						
						insertStmt2.setInt(1, ((Baguette) produkt).getGroesseID());
						insertStmt2.setInt(2, produktID);
						
						insertStmt2.executeUpdate();
						insertStmt2.close();
						
						// insert into pizza produktbelaege table
						
						for(Belag belag : produkt.getBelaege()) {
							
							insertStmt2 = database.prepareStatement("INSERT INTO produktbelaege "
									+ "(produktID, belagID) "
									+ "VALUES (?, ?)", 
									PreparedStatement.RETURN_GENERATED_KEYS);
						
							insertStmt2.setInt(1, produktID);
							insertStmt2.setInt(2, belag.getBelagNummer());
							

							
							insertStmt2.executeUpdate();
							insertStmt2.close();
							
						}
						
					}
					
				}
				
				return produktID;
			} else {
				
				PreparedStatement updateStmt = database.prepareStatement("UPDATE produkte SET "
						+ "produktName = ? "
						+ "WHERE produktID = ?");
				updateStmt.setInt(1, produkt.getProduktNummer());
				
				updateStmt.executeUpdate();
				updateStmt.close();
				return produkt.getProduktNummer();
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public boolean deleteProdukt(Produkt produkt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Produkt> getProduktByProduktNr(int produktNr) {
		
		Optional<Produkt> produkt = null;
		
		try {
			
			PreparedStatement pStmt = database.prepareStatement("SELECT * FROM baguette WHERE produktID = ?");
			
			pStmt.setInt(1, produktNr);
			ResultSet dbResult = pStmt.executeQuery();	
			if(dbResult.next()) {
				//////// Baguette
				//System.out.println("Baguette");
				//produkt = new Baguette(produktName, groesseID, belaege, produktNummer);
			}else {
				//////// Pizza
				//System.out.println("Pizza");
				
				//produkt = new Pizza(produktName, groesseID, belaege, produktNummer);
				
			}
				
		}
		catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return produkt;
	}

	@Override
	public String getProduktType(Produkt produkt) {
		if(produkt instanceof Pizza) {
			return "Pizza";
		}else if(produkt instanceof Baguette) {
			return "Bageutte";
		}
		return null;
	}

	@Override
	public int getGesamtPreis(Produkt produkt) {
		// TODO Auto-generated method stub
		return 0;
	}

}
