/**
 * 
 */
package de.akademie.pizzadienst.dao;

import java.util.List;
import java.util.Optional;

/**
 * @author Simme
 *
 */
public interface ProduktGroesseDAO {
	
	public List<Integer> getAllPizzaGroessenIDs();
	public List<Integer> getAllBaguetteGroessenIDs();
	
	public int getPizzaGroesse(int groessenID);
	public int getPizzaGroesseByName(String groessenName);
	
	public Optional<String> getBaguetteGroesse(int groessenID);	

	public int getPizzaGroessePreis(int groessenID);
	public int getBaguetteGroessePreis(int groessenID);
	
	public boolean changePizzaGroessePreis(int groessenID, int preis);
	public boolean changeBaguetteGroessePreis(int groessenID, int preis);
}
