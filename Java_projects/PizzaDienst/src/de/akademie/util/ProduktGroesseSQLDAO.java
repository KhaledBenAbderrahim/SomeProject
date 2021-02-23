/**
 * 
 */
package de.akademie.util;

import java.util.List;
import java.util.Optional;

import de.akademie.pizzadienst.dao.ProduktGroesseDAO;

/**
 * @author Simme
 *
 */
public class ProduktGroesseSQLDAO implements ProduktGroesseDAO {

	@Override
	public List<Integer> getAllPizzaGroessenIDs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllBaguetteGroessenIDs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPizzaGroesse(int groessenID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPizzaGroesseByName(String groessenName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<String> getBaguetteGroesse(int groessenID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPizzaGroessePreis(int groessenID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBaguetteGroessePreis(int groessenID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean changePizzaGroessePreis(int groessenID, int preis) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeBaguetteGroessePreis(int groessenID, int preis) {
		// TODO Auto-generated method stub
		return false;
	}

}
