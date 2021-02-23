package de.smartcrew.eatforfitserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.smartcrew.eatforfitserver.entity.User;
import de.smartcrew.eatforfitserver.constants.UserStatus;

/**
 * Datum : Dec. 12-2015
 * UserRepository schnitstelle vererbt die attribute und Operationen aus JpaRepository
 * durch diese Schnittstelle erhalten wird die relevansten JpaRepositoryMethoden für den standarddatenzugriff,die standardmässig in eine DAO verfügbar sind.
 * @author EatForFitTeam
 * @version 1.0
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    
	/**
     * neue benutzerdefinierte Zugriffsmethode:Ein user suchen in der DB durch ein ID-Eingabe
     * @param userID von typ int
     * @return Optional mit Generics "User"
     */
	Optional<User> findUserByuserID(int userID);
	/**
     * neue benutzerdefinierte Zugriffsmethode:Ein user suchen in der DB durch ein userKey-Eingabe
     * @param userKey von typ String
     * @return Optional mit Generics "User"
     */
	Optional<User> findUserByuserKey(String userKey);    
	/**
     * neue benutzerdefinierte Zugriffsmethode:Ein user suchen in der DB durch ein nickName-Eingabe
     * @param nickName von typ String
     * @return Optional mit Generics "User"
     */
	Optional<User> findUserBynickName(String nickName);    
	/**
     * neue benutzerdefinierte Zugriffsmethode:Ein user suchen in der DB durch ein eMail-Eingabe
     * @param eMail von typ String
     * @return Optional mit Generics "User"
     */
	Optional<User> findUserByeMail(String eMail);    
	/**
     * neue benutzerdefinierte Zugriffsmethode:Ein user suchen in der DB durch ein userStatus-Eingabe
     * @param userStatus von typ UserStatus
     * @return Optional mit Generics "User"
     */
    Optional<User> findUserByuserStatus(UserStatus userStatus);
    
    Boolean existsBynickName(String nickName);

    Boolean existsByeMail(String eMail);
}
