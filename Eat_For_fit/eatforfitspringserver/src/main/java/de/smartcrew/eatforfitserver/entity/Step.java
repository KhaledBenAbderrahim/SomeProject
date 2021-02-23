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
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datum : Dec. 12-2015
 * Die klasse Step ist eine einfache übliche JPA-Entity-Klasse, welche eine Datenbanktabelle repräsentiert.
 * Um die Datenbank Daten zu manipulieren ,die klasse StepRepository wurde geschrieben
 * @author EatForFitTeam
 * @version 1.0
 *
 */
@Entity
@Table(name = "Step")
@Data
@NoArgsConstructor
public class Step {
	
	/**
     * int Wert fuer die StepId, diese werte werden automatisch generiert in der Datenbank
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stepId;
    /**
     * int Wert fuer die für die nummer der Step bei eine Rezept
     */
    @Column(name = "receipestep")
    private int receipeStep;
    /**
     * String Wert fuer die Step Beschreibunrg
     */
    @Column(name = "description")
    private String description;
    
    /**
     * Eine Liste mit generics Ingredient, enthält eine list mit Ingredients,ein Ingredient ist in many Step,ein Step hat viele Ingredients(ManyToMany Beziehung)
     */
    @ManyToMany
    @JoinTable(name = "step_ingredient", joinColumns = { @JoinColumn(name = "step_id") }, inverseJoinColumns = {
    @JoinColumn(name = "ingredient_id") })
    private List<Ingredient> ingredients = new ArrayList<>();
    /**
     * Objekt Step Erzeugen(constructor)
     * @param receipeStep int value
     * @param description String value
     */
    public Step(int receipeStep, String description) {
        this.receipeStep = receipeStep;
        this.description = description;
    }

}
