package de.smartcrew.eatforfitserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.smartcrew.eatforfitserver.entity.UserProfile;
import de.smartcrew.eatforfitserver.entity.User;


/**
 * Datum : Dec. 12-2015
 * UserProfileRepository schnitstelle vererbt die attribute und Operationen aus JpaRepository
 * durch diese Schnittstelle erhalten wird die relevansten JpaRepositoryMethoden für den standarddatenzugriff,die standardmässig in eine DAO verfügbar sind.
 * @author EatForFitTeam
 * @version 1.0
 *
 */
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
	/**
     * neue benutzerdefinierte Zugriffsmethode: ein User Profil suchen in der DatenBank durch ein User-Eingabe
     * @param user von typ User
     * @return Optional mit Generics "UserProfile"
     */
	Optional<UserProfile> findUserProfileByUser(User user);
}
