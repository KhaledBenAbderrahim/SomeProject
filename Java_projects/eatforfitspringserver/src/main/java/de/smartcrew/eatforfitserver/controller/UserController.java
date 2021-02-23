package de.smartcrew.eatforfitserver.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.smartcrew.eatforfitserver.constants.Sex;
import de.smartcrew.eatforfitserver.constants.UserRestURIConstants;
import de.smartcrew.eatforfitserver.constants.UserStatus;
import de.smartcrew.eatforfitserver.payload.MessageResponse;
import de.smartcrew.eatforfitserver.entity.User;
import de.smartcrew.eatforfitserver.entity.UserProfile;
import de.smartcrew.eatforfitserver.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

/**
 * Datum : Dec. 12-2015
 * Die klasse ist als RestController deklariert. Jede Anforderungsbehandlungsmethode der ProfilController-Klasse serialisiert automatisch Rückkehrobjekte in HttpResponse
 * @author EatForFitTeam
 * @version 1.0
 *
 */
@RestController
@Log4j2
public class UserController {
	
	/**
     * Ein objekt userRepository wird erzeugt, um ein automatische Abfragen aus den Methodennamen zu generieren möglich wird.
     * Die Annotation @Autowired wird für die automatische Abhängigkeitsinjektion verwendet.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Diese Methode zeigt die User Liste die in der DatenBank gespeichert sind
     * @return eine Liste mit Generics User
     */
    @GetMapping(value = UserRestURIConstants.GET_All_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {

        List<User> usersList = userRepository.findAll();
        return usersList;
    }

    /**
     * Diese Methode zeigt ein bestimmte User aus der Daten Bank durch userKey-Eingabe
     * @param user von type User
     * @return ein Objekt von typ User
     */
    @PostMapping(value = UserRestURIConstants.GET_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByKey(@RequestBody Map<String, String> userKey) {
        Optional<User> user = userRepository.findUserByuserKey(userKey.get("userKey"));

        if (!user.isPresent()) {
            System.out.println("User ist leer");
            return null;
        }

        return user.get();
    }

    @GetMapping(value = UserRestURIConstants.INSERT_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public User insertUser() {
        String userKey = UUID.randomUUID().toString();

        String userName = "Guest-" + userKey;

        User newUser = new User(userKey, userName, UserStatus.GUEST, null, null, null);

        UserProfile profile = new UserProfile("Vorname", "Nachname", new Date(567893169), Sex.NOTSET);

        newUser.setUserProfile(profile);
        profile.setUser(newUser);

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @PostMapping(value = UserRestURIConstants.SIGNUP_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {

        // Abfrage der Authentifizierungsmethode
        // möglich sind nickname oder email

        if (userRepository.existsBynickName(request.get("nickname"))) {
            return ResponseEntity
                .status(600)
                .body(new MessageResponse("Fehler: Benutzer existiert bereits"));
        }

        if (userRepository.existsByeMail(request.get("email"))) {
            return ResponseEntity
                .status(601)
                .body(new MessageResponse("Fehler: E-Mail wird bereits verwendet"));
        }

        //Optional<User> authUser = userRepository.findUserByuserKey(request.get("userkey"));

        return ResponseEntity.ok(new MessageResponse("Alles OK: Benutzer ist im System nicht vorhanden"));
    }

    @PostMapping(value = UserRestURIConstants.AUTH_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authUser(@RequestBody Map<String, String> request) {

        if (request.get("authmethod").equalsIgnoreCase("nickname") && !userRepository.existsBynickName(request.get("inputName"))) {
            return ResponseEntity.status(602).body(new MessageResponse("Fehler: Benutzer existiert nicht"));
        }

        if (request.get("authmethod").equalsIgnoreCase("email") && !userRepository.existsByeMail(request.get("inputName"))) {
            return ResponseEntity.status(603).body(new MessageResponse("Fehler: E-Mail ist nicht im System gespeichert"));
        }


        // Objektdeklaration Rückgabewert
        Optional<User> authUser;

        // Abfrage der Authentifizierungsmethode
        // möglich sind nickName oder eMail
        if (request.get("authmethod").equalsIgnoreCase("nickname")) {
            authUser = userRepository.findUserBynickName(request.get("inputName"));
        } else {
            authUser = userRepository.findUserByeMail(request.get("inputName"));
        }

        // Methode gibt leeren User zurück, wenn übergebenes Passwort mit gespeichertem
        // Passwort nicht übereinstimmt
        if (!authUser.get().getPassword().equalsIgnoreCase(request.get("password"))) {
            return ResponseEntity
                .status(604)
                .body(new MessageResponse("Fehler: Passwörter stimmen nicht überein"));
        }

        // Gibt in der Datenbank gespeicherten Benutzer zurück
        return ResponseEntity.ok(authUser.get());
    }

    /**TODO */
    @PostMapping(value = UserRestURIConstants.SAVE_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user) {
        user.getUserProfile().setUser(user);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    /**
     * Diese Methode löscht ein User aus der DatenBank
     * @param profile von typ userProfile
     * 
     */
    @DeleteMapping(value = UserRestURIConstants.DELETE_USER)
    public void deleteUser(@RequestBody User user) {
        userRepository.delete(user);
    }
}
