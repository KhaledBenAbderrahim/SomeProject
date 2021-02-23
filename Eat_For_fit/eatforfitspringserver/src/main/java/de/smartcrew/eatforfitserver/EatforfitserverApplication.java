package de.smartcrew.eatforfitserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * Datum : Dec. 12-2015
 * Diese Klasse vererbt die attribute und Operationen aus SpringBootServletInitializer
 * in diese Klasse Kann unser SpringBoot Projekt Starten.
 * @author EatForFitTeam
 * @version 1.0
 *
 */
@SpringBootApplication
public class EatforfitserverApplication extends SpringBootServletInitializer {

	/**In Diese Main Methode wird die Spring-BootAnwendung gestartet über SpringApplication.run(),und es wird SpringBootServletInitzializer.run() ausgeführt.
	 * @param args
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(EatforfitserverApplication.class, args);
	}

}
