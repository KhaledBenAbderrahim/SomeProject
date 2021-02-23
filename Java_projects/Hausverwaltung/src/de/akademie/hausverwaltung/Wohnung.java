package de.akademie.hausverwaltung;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import de.akademie.hausverwaltung.mieter.Mieter;
import de.akademie.hausverwaltung.zimmern.*;

/**
 * Die Klasse Wohnung erzeugt eine neue Wohnung, der bereits angelegte {@link Mieter} zugewiesen werden können.<br>
 * Jede Wohnung kann maximal einen {@link Mieter} besitzen.
 * 
 * @author Akademie für Weiterbildung
 *
 */

public class Wohnung{
	
	private static double preisProMeter = 10.28;
	
	private List<Zimmer> zimmer = new ArrayList<Zimmer>();
	private Mieter mieter;
	private int hausNummer;
	private double wohnungFlaeche;
	private double kaltMiete = 0.0;
	private double nebenKosten;
	private boolean leer;
	
	/**
	 * Der Standard Konstruktor zum Anlegen einer neuen Wohnung.<br>
	 * Dieser Konstruktor kommt zum Einsatz, wenn eine neue Wohnung angelegt werden muss<br>
	 * Jede Wohnung muss aus mindestens 1 {@link Kueche}, 1 {@link WohnZimmer}, 1 {@link BadZimmer} und 1 {@link Flur} bestehen.<br>
	 * Bei der Instanzierung einer neuen Wohnung müssen passende Objekte übergeben werden.<br>
	 * Die Abfrage <code>{@link Objects}.requireNonNull(T, String)</code> verhindert, dass {@link Zimmer} mit einem Wert <code>null</code> an den Konstruktor übergeben werden.<br><br>
	 * 
	 * @param wohnZimmer		Ein Objekt vom Typ {@link WohnZimmer}
	 * @param kueche			Ein Objekt vom Typ {@link Kueche}
	 * @param badZimmer			Ein Objekt vom Typ {@link BadZimmer}
	 * @param flur				Ein Objekt vom Typ {@link Flur}
	 * @param hausNummer		Die Hausnummer der Wohnung
	 * @param nebenKosten		Die Nebenkosten der Wohnung [cent]
	 * @throws IllegalArgumentException		Sollte ein Wert kleiner 0 für die <code>nebenKosten</code> angegeben werden
	 */
	
	public Wohnung(final WohnZimmer wohnZimmer,
			final Kueche kueche,
			final BadZimmer badZimmer,
			final Flur flur,
			final int hausNummer,
			final double nebenKosten) {
		
		Objects.requireNonNull(wohnZimmer, "Wohnzimmer sollte nicht null sein.");
		Objects.requireNonNull(kueche, "Küche sollte nicht null sein.");
		Objects.requireNonNull(badZimmer, "Badzimmer sollte nicht null sein.");
		Objects.requireNonNull(flur, "Flur sollte nicht null sein.");
		
		if(nebenKosten < 0) {
			throw new IllegalArgumentException("Nebenkosten sollte positiv sein.");
		}
				
		this.zimmer.add(new WohnZimmer(wohnZimmer));
		this.zimmer.add(new Kueche(kueche));
		this.zimmer.add(new BadZimmer(badZimmer));
		this.zimmer.add(new Flur(flur));
		
		for(int i = 0; i < zimmer.size();i++) {
			this.kaltMiete += zimmer.get(i).getFeldQuadratMeter() / 10000 * preisProMeter;
			this.wohnungFlaeche += zimmer.get(i).getFleache();
		}
		
		this.hausNummer 	= hausNummer;
		this.nebenKosten 	= nebenKosten;
		this.leer			= true; 
	}
	
	/**
	 * Copy-Konstruktor für eine neue Wohnung auf der Basis einer bereits vorhandenen Wohnung.<br>
	 * Hierbei werden alle Attribute eines existierenden Mieters in ein neues Objekt vom Typ Wohnung kopiert.<br>
	 * Durch das Erzeugen einer Kopie werden ungewollte Seiteneffekte verhindert.<br><br>
	 * 
	 * Die Abfrage <code>{@link Objects}.requireNonNull(T, String)</code> verhindert, dass eine Wohnung mit einem Wert <code>null</code> an den Konstruktor übergeben wird.
	 * 
	 * @param wohnung					Ein bereits bestehendes Objekt Wohnung
	 * @throws NullPointerException		Sollte das übergebene Objekt vom Typ Wohnung <code>null</code> sein
	 */
	
	Wohnung(Wohnung wohnung){
		
		Objects.requireNonNull(wohnung, "Wohnung sollte nicht null sein.");
		
		this.zimmer			= wohnung.getZimmern();
		this.mieter 		= wohnung.getMieter();
		this.hausNummer 	= wohnung.getHausNummer();
		this.nebenKosten 	= wohnung.getNebenKosten();
		this.wohnungFlaeche	= wohnung.getWohnungFlaeche();
		this.kaltMiete		= wohnung.getKaltMiete();
		this.leer			= wohnung.getLeer();
	}
	
	/**
	 * Methode fügt einer Wohnung ein neues Zimmer hinzu.<br>
	 * Zur Vermeidung von Seiteneffekten werden von übergebenen Objekten immer Kopien erzeugt.
	 * 
	 * @param object		Das einzufügende Zimmer. <br>
	 * 						Muss vom Typ <code>WOHNZIMMER / BADEZIMMER / KUECHE / FLUR</code> sein
	 * @throws NullPointerException		Wenn übergebenes Zimmer den Wert <code>null</code> besitzt
	 */
	
	public void addZimmer(Object object) {
		
		Objects.requireNonNull(object, "Zimmer sollte nicht null sein.");

		Zimmer zimmerKopie;
		
		if(object instanceof WohnZimmer) {
			zimmerKopie = new WohnZimmer((WohnZimmer)object);
		}else if(object instanceof BadZimmer) {
			zimmerKopie = new BadZimmer((BadZimmer)object);
		}else if(object instanceof Kueche) {
			zimmerKopie = new Kueche((Kueche)object);
		}else if(object instanceof Flur) {
			zimmerKopie = new Flur((Flur)object);
		}else {
			throw new NoSuchElementException("Zimmer Typ existiert nicht.");
		}
		
		this.zimmer.add(zimmerKopie);
		this.kaltMiete 		+= zimmerKopie.getFeldQuadratMeter() / 10000 * preisProMeter;
		this.wohnungFlaeche += zimmerKopie.getFleache();
		
	}
	
	/**
	 * Methode zum Ausgeben der in der Wohnung enthaltenen Zimmer.<br>
	 * Rückgabewert ist eine Liste aller Zimmer.
	 * @return		Gibt eine Liste aller Zimmer zurück
	 */
	
	public List<Zimmer> getZimmern(){
				
		return List.copyOf(this.zimmer);
	}
	
	/**
	 * Methode zur Ausgabe des Mieters der Wohnung.<br>
	 * Gibt eine Kopie des Mieters der Wohnung aus.<br>
	 * Steht die Wohnung leer, gibt die Methode <code>null</code> zurück.
	 * @return		Gibt den aktuellen Mieter der Wohnung als Objekt vom Typ {@link Mieter} zurück.<br>
	 * 				Ist die Wohnung leerstehend, wird <code>null</code> zurückgegeben.
	 */
	
	
	public Mieter getMieter() {
		
		if(this.leer == true) { // Wohnung ist leer
			return null;
		}
		
		return new Mieter(this.mieter);
	}
	
	/**
	 * Methode zum Hinzufügen eines neuen Mieters für eine existierende Wohnung.<br>
	 * Es muss ein Objekt vom Typ {@link Mieter} übergeben werden.<br>
	 * Methode überprüft zunächst, ob Wohnung leer steht und gibt <code>false</code> zurück, sollte sie nicht leer sein.
	 * 
	 * @param mieter		Ein gültiges Objekt (darf nicht <code>null</code> sein) vom Typ {@link Mieter}
	 * @return				Gibt <code>false</code> zurück, wenn Wohnung nicht leer ist.<br>
	 * 						Gibt <code>true</code> zurück, wenn Mieter der Wohnung zugewiesen wurde.
	 * @throws NullPointerException		Wenn übergebenes Objekt <code>null</code> ist
	 */
	
	public boolean addMieter(Mieter mieter) {
		
		if(this.leer == true) {
			
			Objects.requireNonNull(mieter, "Mieter sollte nicht null sein.");

			this.mieter = new Mieter(mieter);
			this.leer = false;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Methode zum Entfernen eines Mieters aus einer Wohnung.<br>
	 * Der Zustand der Wohnung wird auf leer gesetzt und der Mieter der Wohnung auf <code>null</code>
	 * 
	 */
	
	public boolean removeMieter() {
		
		this.leer = true;
		this.mieter = null;
		
		return true;
	}
	
	/**
	 * Methode zum Ändern eines Mieters einer Wohnung in einen anderen Mieter.<br>
	 * Als Parameter muss ein Objekt vom Typ {@link Mieter} übergeben werden.<br><br>
	 * Die Methode ruft zunächst die Methode <code>removeMieter()</code> auf, um den aktuellen Mieter zu entfernen.<br>
	 * Anschließend wird mittels der Methode <code>addMieter(E)</code>, welche ein Objekt vom Typ {@link Mieter} erfordert, ein neuer Mieter der Wohnung zugewiesen.
	 * 
	 * @param mieter					Ein Objekt vom Typ {@link Mieter}
	 * @throws NullPointerException 	Wenn übergebenes Objekt mit <code>null</code> übergeben wurde 
	 */
	
	public void changeMieter(Mieter mieter) {
		
		Objects.requireNonNull(mieter, "Mieter sollte nicht null sein.");
		
		removeMieter();
		addMieter(mieter);
	}
	
	public static double getPreisProMeter() {
		
		return Wohnung.preisProMeter;
	}
	
	public static void changePreis(final double newPreis) {
		
		if(newPreis < 0) {
			throw new IllegalArgumentException("Neupreis sollte positiv sein.");
		}
		
		Wohnung.preisProMeter = newPreis;
	}

	public int getHausNummer() {
		
		return this.hausNummer;
	}

	public double getWohnungFlaeche() {
		
		return this.wohnungFlaeche;
	}

	public double getKaltMiete() {
		
		return this.kaltMiete;
	}

	public double getNebenKosten() {
		
		return this.nebenKosten;
	}
	
	public boolean getLeer() {
		
		return this.leer;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + hausNummer;
		long temp;
		temp = Double.doubleToLongBits(wohnungFlaeche);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((zimmer == null) ? 0 : zimmer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (!(obj instanceof Wohnung))
			return false;
		Wohnung other = (Wohnung) obj;
		if (hausNummer != other.hausNummer)
			return false;
		if (Double.doubleToLongBits(wohnungFlaeche) != Double.doubleToLongBits(other.wohnungFlaeche))
			return false;
		if (zimmer == null) {
			if (other.zimmer != null)
				return false;
		} else if (!zimmer.equals(other.zimmer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return String.format(
				"Wohnung [zimmern=%s, mieter=%s, hausNummer=%s, wohnungFlaeche=%sM², kaltMiete=€%.02f, nebenKosten=€%.02f, brutto=€%.02f]",
				zimmer, mieter, hausNummer, wohnungFlaeche / 10000, kaltMiete, nebenKosten, nebenKosten + kaltMiete);
	}
	
}
