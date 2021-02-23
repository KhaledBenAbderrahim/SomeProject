package de.smartcrew.eatforfitserver.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.smartcrew.eatforfitserver.constants.ProfileRestURIConstants;
import de.smartcrew.eatforfitserver.entity.UserProfile;
import de.smartcrew.eatforfitserver.entity.User;
import de.smartcrew.eatforfitserver.repository.UserProfileRepository;

/**
 * Datum : Dec. 12-2015
 * Die klasse ist als RestController deklariert. Jede Anforderungsbehandlungsmethode der ProfileController-Klasse serialisiert automatisch Rückkehrobjekte in HttpResponse
 * @author EatForFitTeam
 * @version 1.0
 *
 */
@RestController
public class ProfileController {
    
	
	/**
     * Ein objekt userProfileRepository wird erzeugt, um ein automatische Abfragen aus den Methodennamen zu generieren möglich wird.
     * Die Annotation @Autowired wird für die automatische Abhängigkeitsinjektion verwendet.
     */
    @Autowired
    UserProfileRepository userProfileRepository;

    /**
     * Diese Methode zeigt ein bestimmte UserProfile aus der Daten Bank durch User-Eingabe
     * @param user von type User
     * @return ein Objekt von typ Receipe
     */
    @PostMapping(
        value = ProfileRestURIConstants.GET_PROFILE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserProfile getProfileByUserId(@RequestBody User user){
        Optional<UserProfile> profile = userProfileRepository.findUserProfileByUser(user);
        if(!profile.isPresent())
            return null;

        return profile.get();
    }

    /**
     * Diese Methode speichert ein Profile in der Datenbank
     * @param profile von typ UserProfile
     * @return ein Objekt von typ UserProfile
     */
    @PostMapping(value = ProfileRestURIConstants.SAVE_PROFILE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserProfile saveProfile(@RequestBody UserProfile profile) {
        UserProfile savedProfile = userProfileRepository.save(profile);
        return savedProfile;
    }

    @DeleteMapping(value = ProfileRestURIConstants.DELETE_PROFILE)
    /**
     * Diese Methode löscht ein profile aus der DatenBank
     * @param profile von typ userProfile
     * 
     */
    public void deleteProfile(@RequestBody UserProfile profile) {
        //Adminstatus checken lassen
        
        userProfileRepository.delete(profile);
    }
}
