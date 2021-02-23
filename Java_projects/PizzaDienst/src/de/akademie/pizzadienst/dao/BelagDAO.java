/**
 * 
 */
package de.akademie.pizzadienst.dao;

import java.util.List;
import java.util.Optional;

import de.akademie.pizzadienst.belaege.Belag;
import de.akademie.pizzadienst.produkte.Produkt;

/**
 * @author Simme
 *
 */
public interface BelagDAO {
	
	public List<Belag> getAllBelaege(Produkt produkt);
	public Belag addNewBelag(Belag belag);
	public int saveBelag(Belag belag);
	public boolean deleteBelag(Belag belag);
	
	public Optional<Belag> getBelagByBelagID(int belagID);
	public Optional<Belag> getBelagByName(String name);
	public Optional<List<Belag>> getBelagByPreis(int preis);
}
