package de.smartcrew.eatforfitserver.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Datum : Dec. 12-2015
 * Die klasse Tag ist eine einfache übliche JPA-Entity-Klasse, welche eine Datenbanktabelle repräsentiert.
 * Um die Datenbank Daten zu manipulieren ,die klasse TagRepository wurde geschrieben
 * @author EatForFitTeam
 * @version 1.0
 *
 */

@Entity
@Table(name = "Tags")
@Data
@NoArgsConstructor
public class Tag {
	
	
    /**
     * int Wert für die tagId, diese werte werden automatisch generiert in der Datenbank
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagID;
    /**
     * String Wert für den Tagsname
     */
    private String name;

    /**
     * Liste mit Generic Receipe enthält die verschiendene rezepte die zu ein Tag gehört, Tag und rezepte haben ManyToMany beziehung in der Datenbank
     */
    @ManyToMany(mappedBy = "tags")
    private List<Receipe> receipes = new ArrayList<>();

    /**
     *Liste mit Generics 'Ingredient enthält veschiedene Ingredient die zu Tag gehört, Tag und Inmgredient haben many to many beziehung in der Datenbank
     *Tag_Ingredient ist die  matching Tabelle zwischen ingredient und tag
     */
    @ManyToMany
    @JoinTable(  name = "Tag_Ingredient" ,
                 joinColumns = {@JoinColumn(name = "tagid")},
                 inverseJoinColumns = {@JoinColumn(name = "ingredientid")})
    private List<Ingredient> ingredients = new ArrayList<>();
    
}
