package de.smartcrew.eatforfitserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import de.smartcrew.eatforfitserver.constants.ReceipeUnits;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datum : Dec. 12-2015
 * Die klasse Ingredient ist eine einfache übliche JPA-Entity-Klasse, welche eine Datenbanktabelle repräsentiert.
 * Um Ingredient Datenbank Daten zu manipulieren ,die klasse IngredientRepository wurde geschrieben
 * @author EatForFitTeam
 * @version 1.0
 *
 */
@Entity
@Table(name = "Ingredient")
@Data
@NoArgsConstructor
public class Ingredient {

    /**
     * int Wert für die ingredientId, diese werte werden automatisch generiert in der Datenbank
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientid;
    /**
     *String Wert  für Ingredientsname
     */
    private String name;
    /**
     *int Wert für Ingredientsmenge, diese Wert ist unveränderlich
     */
    private final int quantity = 100;
    /**
     * Enum Wert für Ingredientsmenge Einheit
     */
    private ReceipeUnits unit;
    /**
     *int Wert für die Kalorie Wert das diese Ingredient enthält
     */
    private int kcal;
    /**
     * int Wert für , wie viele Kohlenhydrate hat ein Ingredient
     */
    private int kohlenhydrate;
    /**
     * int wert für, wie viel Eiweiss hat diese Ingredient
     */
    private int eiweiss;
    /**
     * int wert für, wie viele Ballast hat diese Ingredient
     */
    private int ballast;

    /**
     * Eine Liste mit generics Receipe, enthält eine list mit Rezepte,jeder Ingredient hat many Rezepte, und eine Rezept hat viele Ingredient(ManyToMany Beziehung)
     */
    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    private List<Receipe> receipes = new ArrayList<>();
    
    /**
     * Eine Liste mit Generics Step, enthält eine List mit step, ein Ingredient ist in Viele Step, ein Step hat viele Ingredient.(ManyToMany Beziehung)
     */
    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    private List<Step> steps = new ArrayList<>();
    /**
     * Eine Liste mit Generics Tag, enthält eine Liste mit step, ein Ingredient hat Viele Tags, ein Tag hat viele Ingredient.(ManyToMany Beziehung)
     */
    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    private List<Tag> tags = new ArrayList<>();

}
