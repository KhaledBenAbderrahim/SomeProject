package de.smartcrew.eatforfitserver.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.smartcrew.eatforfitserver.constants.ReceipeRestURIConstants;
import de.smartcrew.eatforfitserver.entity.Receipe;
import de.smartcrew.eatforfitserver.repository.ReceipeRepository;
/**
 * Datum : Dec. 12-2015
 * Die klasse ist als RestController deklariert. Jede Anforderungsbehandlungsmethode der ReceipeController-Klasse serialisiert automatisch Rückkehrobjekte in HttpResponse
 * @author EatForFitTeam
 * @version 1.0
 *
 */

@RestController
public class ReceipeController {
	
	
    /**
     * Ein objekt receipeRepository wird erzeugt, um ein automatische Abfragen aus den Methodennamen zu generieren möglich wird.
     * Die Annotation @Autowired wird für die automatische Abhängigkeitsinjektion verwendet.
     */
    @Autowired
    ReceipeRepository receipeRepository;
    
    
    /**
     * Diese Methode speichert eine Rezept in unsere DatenBank
     * @param receipe von type Receipe
     * @return eine objekt von typ Receipe
     */
    @PostMapping(value = ReceipeRestURIConstants.INSERT_RECEIPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Receipe insertReceipe(@RequestBody Receipe receipe) {

        System.out.println(receipe);

        Receipe savedReceipe = receipeRepository.save(receipe);
        return savedReceipe;
    }

    /**
     * Diese Methode zeigt die Rezept Liste die in der DatenBank gespeichert sind
     * @return eine Liste mit Generics Receipe
     */
    @GetMapping(value = ReceipeRestURIConstants.GET_All_RECEIPES, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Receipe> getReceipes() {

        List<Receipe> receipesList = receipeRepository.findAll();
        return receipesList;
    }

    /**
     * Diese Methode zeigt ein bestimmt Rezept aus der Daten Bank durch ID-Eingabe
     * @param id von type int
     * @return ein Objekt von typ Receipe
     */
    @GetMapping(value = ReceipeRestURIConstants.GET_RECEIPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Receipe getReceipeById(@RequestBody int id) {
        Optional<Receipe> receipe = receipeRepository.findReceipeByReceipeId(id);
        if (!receipe.isPresent())
            return null;

        return receipe.get();
    }

    /**
     * Diese Methode speichert ein Rezept in der Datenbank
     * @param receipe von typ Receipe
     * @return ein Objekt von typ Receipe
     */
    @PostMapping(value = ReceipeRestURIConstants.SAVE_RECEIPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Receipe saveReceipe(@RequestBody Receipe receipe) {
        Receipe savedReceipe = receipeRepository.save(receipe);
        return savedReceipe;
    }

    /**
     * Diese Methode löscht ein Rezept falls es in der DatenBank vorhanden ist
     * @param receipe
     */
    @PostMapping(value = ReceipeRestURIConstants.DELETE_RECEIPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReceipe(Receipe receipe) {
        receipeRepository.delete(receipe);
    }
}
