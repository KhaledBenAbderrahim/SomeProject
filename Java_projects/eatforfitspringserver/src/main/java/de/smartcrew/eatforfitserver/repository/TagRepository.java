package de.smartcrew.eatforfitserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.smartcrew.eatforfitserver.entity.Tag;

/**
 * Datum : Dec. 12-2015
 * TagRepository schnitstelle vererbt die attribute und Operationen aus JpaRepository
 * durch diese Schnittstelle erhalten wird die relevansten JpaRepositoryMethoden für den standarddatenzugriff,die standardmässig in eine DAO verfügbar sind.
 * @author EatForFitTeam
 * @version 1.0
 *
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
	/**
     * neue benutzerdefinierte Zugriffsmethode: ein Tag in der DatenBank suchen durch ID-Eingabe
     * @param key von typ int
     * @return Optional mit Generics "Tag"
     */
	Optional<Tag> findTagByTagID(int key);

}