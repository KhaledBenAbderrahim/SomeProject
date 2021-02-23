package de.smartcrew.eatforfitserver.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

import de.smartcrew.eatforfitserver.constants.Sex;
import lombok.*;

/**
 * Datum : Dec. 12-2015
 * Die klasse UserProfile ist eine einfache übliche JPA-Entity-Klasse, welche eine Datenbanktabelle repräsentiert.
 * Um die Datenbank Daten zu manipulieren ,die klasse UserProfileRepository wurde geschrieben
 * @author EatForFitTeam
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table (name = "profiles")
public class UserProfile {
	
	/**
     * int Wert fur die userId, diese werte werden automatisch generiert in der Datenbank
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    /**
     * User Wert fuer user, jeder User hat eine OneToOne Beziehung mit ein UserPorfile
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private User user;
    /**
     * String Wert fuer firstname
     */
    private String firstname;
    /**
     * String Wert fuer lastname
     */
    private String lastname;

    //Benutzerbild später hinzufügen???
    /**
     * Date Wert fuer Birthdate
     */
    private Date birthdate;
    /**
     * Sex Wert fuer sex
     */
    @NonNull
    @Enumerated(EnumType.STRING)
    private Sex sex;

    /**
     * int Table  fuer likes von diese userProfile
     */
    private int[] likes;
    /**
     * int Tablle fuer userprofile diskles
     */
    private int[] dislikes;
    /**
     * int Wert fuer diabestis
     */
    @NonNull
    @Value("${some.key:0}")
    private int diabetis;
    /**
     * int Wert fuer lactose
     */
    @NonNull
    @Value("${some.key:0}")
    private int lactose;
    /**
     * int Wert fuer gluten
     */
    @NonNull
    @Value("${some.key:0}")
    private int gluten;
    /**
     * int Wert fuer fitness
     */
    @NonNull
    @Value("${some.key:0}")
    private int fitness;
    /**
     * int Wert fuer losweight
     */
    @NonNull
    @Value("${some.key:0}")
    private int loseweigth;
    /**
     * int wert fuer leanrcook
     */
    @NonNull
    @Value("${some.key:0}")
    private int learncook;
    
    
    /**
     * ein Objekt von typ UserProfile wird erzeugt
     * @param firstname von Type String
     * @param lastname von Type String
     * @param birthdate von Type Date
     * @param sex von Type Sex
     */
    public UserProfile(String firstname, String lastname, Date birthdate, Sex sex) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.sex = sex;
    }

    
}
