package de.smartcrew.eatforfitserver.controller;
import org.springframework.http.MediaType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import de.smartcrew.eatforfitserver.constants.TagRestURIConstants;
import de.smartcrew.eatforfitserver.entity.Tag;
import de.smartcrew.eatforfitserver.repository.TagRepository;

/**
 * Datum : Dec. 12-2015
 * Die klasse ist als RestController deklariert. Jede Anforderungsbehandlungsmethode der TagController-Klasse serialisiert automatisch Rückkehrobjekte in HttpResponse
 * @author EatForFitTeam
 * @version 1.0
 *
 */
@RestController
public class TagController {
	

    /**
     * Ein objekt tagRepository wird erzeugt, um ein automatische Abfragen aus den Methodennamen zu generieren möglich wird.
     * Die Annotation @Autowired wird für die automatische Abhängigkeitsinjektion verwendet.
     */
    @Autowired
    TagRepository tagRepository;
    
    /**
     * Diese Methode zeigt die Tags Liste die in der DatenBank gespeichert sind
     * @return eine Liste mit Generics Receipe
     */
    @GetMapping(
        value = TagRestURIConstants.GET_All_TAGS,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Tag> getAllTags(){
        List<Tag> tags = tagRepository.findAll();
        return tags;
    }
    
    /**
     * Diese Methode zeigt ein bestimmte Tag aus der Daten Bank durch ID-Eingabe
     * @param id von type int
     * @return ein Objekt von typ Tag
     */
    @GetMapping(
        value = TagRestURIConstants.GET_TAG,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Tag getTagById(@RequestBody int id){
        Optional<Tag> tag = tagRepository.findTagByTagID(id);
        if(!tag.isPresent())
            return null;

        return tag.get();
    }

    
    /**
     * Diese Methode speichert ein Tag in der Datenbank
     * @param receipe von typ Tag
     * @return ein Objekt von typ Tag
     */
    @PostMapping(value = TagRestURIConstants.SAVE_TAG, produces = MediaType.APPLICATION_JSON_VALUE)
    public Tag saveTag(@RequestBody Tag tag) {
        Tag savedTag = tagRepository.save(tag);
        return savedTag;
    }

    /**
     * Diese Methode löscht ein Rezept aus der DatenBank
     * @param tag von typ Tag
     * 
     */
    @PostMapping(value = TagRestURIConstants.DELETE_TAG, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }
    
}
