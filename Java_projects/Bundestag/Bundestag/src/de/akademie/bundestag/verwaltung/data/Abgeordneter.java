package de.akademie.bundestag.verwaltung.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Abgeordneter {

	private final String vorname;
	private final String nachname;
	private final String geschlecht;
	private final LocalDate geburtsjahr;
	private final String partei;

	public Abgeordneter(String vorname, String nachname, String geschlecht, LocalDate geburtsjahr, String partei) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.geschlecht = geschlecht;
		this.geburtsjahr = geburtsjahr;
		this.partei = partei;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public LocalDate getGeburtsjahr() {
		return geburtsjahr;
	}

	public String getPartei() {
		return partei;
	}

	@Override
	public String toString() {
		return "[vorname=" + vorname + ", nachname=" + nachname + ", geschlecht=" + geschlecht + ", geburtsjahr="
				+ geburtsjahr + ", partei=" + partei + "]\n";
	}

	public static List<Abgeordneter> imporData(final String fileName) {
		List<Abgeordneter> abgeordnete = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");
				Abgeordneter abgeordneter = createAbgeordneterFromStringArray(attributes);

				// adding book into ArrayList
				abgeordnete.add(abgeordneter);

				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return abgeordnete;
	}

	private static Abgeordneter createAbgeordneterFromStringArray(final String[] data) {
		// Daten-Format:
		// [0]: Namenszusatz
		// [1]: Nachname
		// [2]: Vorname
		// [3]: Geschlecht
		// [4]: Geburtsjahr (ignoriert)
		// [5]: Beruf (ignoriert)
		// [6]: Partei (kurz)
		// [7]: Patei (lang)
		// [8]: ignoriert
		// [9]: Bundesland

		return new Abgeordneter(data[2].trim(), data[0].trim() + " " + data[1].trim(), data[3],
				LocalDate.of(Integer.parseInt(data[4]), 1, 1), data[6]);
	}

}
