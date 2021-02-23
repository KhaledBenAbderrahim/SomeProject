package de.smartcrew.eatforfitserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.lang.NonNull;

import de.smartcrew.eatforfitserver.constants.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datum : Dec. 12-2015
 * Die klasse User ist eine einfache übliche JPA-Entity-Klasse, welche eine Datenbanktabelle repräsentiert.
 * Um die Datenbank Daten zu manipulieren ,die klasse UserRepositoryRepository wurde geschrieben
 * @author EatForFitTeam
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    
	/**
     * int Wert fur die userId, diese werte werden automatisch generiert in der Datenbank
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

	/**
     * String Wert fuer userKey
     */
    @NonNull
    private String userKey;

    /**
     * UserProfile Wert fuer userProfile, jeder User hat ein profil (OneToOne Beziehung)
     */
    @OneToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "user")
    //@JsonIgnore
    private UserProfile userProfile;
    /**
     * String Wert fuer nickName
     */
    @NonNull
    private String nickName;   
    /**
     * UserStatus Wert fuer userStatus
     */
    @NonNull
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    /**
     * String Wert fuer eMail
     */
    private String eMail;
    @JsonIgnore
    /**
     * String Wert fuer password
     */
    private String password;
    /**
     * String Wert fuer salt
     */
    @JsonIgnore
    private String salt;
    
    
    /**
     * ein Objekt von Typ User Wird erzeugt
     * @param userKey von Type String
     * @param nickName von type String
     * @param userStatus von type UserStatus
     * @param eMail von type String
     * @param password von type String
     * @param salt von type String
     */
    public User(String userKey, String nickName, UserStatus userStatus, String eMail, String password, String salt) {
        this.userKey = userKey;
        this.nickName = nickName;
        this.userStatus = userStatus;
        this.eMail = eMail;
        this.password = password;
        this.salt = salt;
    }    
}
