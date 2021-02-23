package de.akademie.pizzadienst;


import java.util.Optional;
import java.util.Set;

public interface DAO<T> {
	Optional<T> get(long id);

    Set<T> getAll();

    boolean save(T t);


    boolean delete(T t);
    
    boolean update(T t,String string_1,String string_2);
}
