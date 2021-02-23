package de.smartcrew.eatforfitserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.smartcrew.eatforfitserver.entity.Receipe;


/**
 * Datum : Dec. 12-2015
 * ReceipeRepository schnitstelle vererbt die attribute und Operationen aus JpaRepository
 * durch diese Schnittstelle erhalten wird die relevansten JpaRepositoryMethoden für den standarddatenzugriff,die standardmässig in eine DAO verfügbar sind.
 * @author EatForFitTeam
 * @version 1.0
 *
 */
public interface ReceipeRepository extends JpaRepository<Receipe, Integer> {
    /**
     * neue benutzerdefinierte Zugriffsmethode : Eine Rezept in der Daten bank fnden durch ein ID
     * @param key von typ int
     * @return Optional mit Generic "Receipe"
     */
    Optional<Receipe> findReceipeByReceipeId(int key);
}
