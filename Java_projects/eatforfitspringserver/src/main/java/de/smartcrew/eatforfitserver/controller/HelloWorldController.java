package de.smartcrew.eatforfitserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Datum : Dec. 12-2015
 * Die klasse HalloWorldController ist zum Testen 
 * @author EatForFitTeam
 * @version 1.0
 *
 */
@RestController
public class HelloWorldController {
    
    @RequestMapping("/HelloWorld")
    public String sayHello(){
        return "<h1>Hello World</h1>";
    }

}
