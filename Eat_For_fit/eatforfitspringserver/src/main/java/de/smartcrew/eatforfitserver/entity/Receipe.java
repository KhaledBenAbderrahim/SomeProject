package de.smartcrew.eatforfitserver.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datum : Dec. 12-2015
 * Die klasse Receipe ist eine einfache übliche JPA-Entity-Klasse, welche eine Datenbanktabelle repräsentiert.
 * Um die Datenbank Daten zu manipulieren ,die klasse ReceipeRepository wurde geschrieben
 * @author EatForFitTeam
 * @version 1.0
 *
 */

@Entity
@Table(name = "receipes")
@Data
@NoArgsConstructor
public class Receipe {
	
	/**
     * int Wert für die receipeId, diese werte werden automatisch generiert in der Datenbank
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receipeId;
    /**
     * String Wert fuer die RezeptName
     */
    @NonNull
    @Column(name = "name")
    private String name;
    /**
     * int Wert fuer die Kochzeit
     */
    @NonNull
    @Column(name = "time")
    private int time;
    /**
     * int Array fuer die Rezeptcategories
     */
    @Column(name = "categories")
    private int[] categories;
    /**
     * Eine Liste mit generics Ingredient, enthaelt eine list mit ingredient,ein Ingredient hat many Rezepte, und eine Rezept hat viele Ingredient(ManyToMany Beziehung)
     */
    @ManyToMany
    @JoinTable(name = "Receipe_Ingredient", joinColumns = { @JoinColumn(name = "receipeid") }, inverseJoinColumns = {
            @JoinColumn(name = "ingredientid") })
    private List<Ingredient> ingredients = new ArrayList<>();
    /**
     * Eine Liste mit generics Step, enthält eine list mit steps,ein Rezept hat many Steps, ein Step hat viele Ingredient(OneToMany Beziehung)
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sr_fid", referencedColumnName = "receipeID")
    private List<Step> steps = new ArrayList<>();
    /**
     * Eine Liste mit generics Tag, enthält eine list mit tags,ein Rezept hat many tags,ein tag Rezept hat viele Rezepte(ManyToMany Beziehung)
     */
    @ManyToMany
    @JoinTable(name = "Receipe_Tags", joinColumns = { @JoinColumn(name = "receipeid") }, inverseJoinColumns = {
            @JoinColumn(name = "tagid") })
    private List<Tag> tags = new ArrayList<>();
    /**
     * Objekt Rezept Erzeugen(constructor)
     * @param name String value
     * @param time int value
     * @param categories int[ ] value
     */
    public Receipe(String name, int time, int[] categories) {
        this.name = name;
        this.time = time;
        this.categories = categories;
    }

}
