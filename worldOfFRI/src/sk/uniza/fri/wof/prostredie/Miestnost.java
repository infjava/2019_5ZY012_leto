package sk.uniza.fri.wof.prostredie;


import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Trieda Miestnost realizuje jednu miestnost/priestor v celom priestore hry.
 * Kazda "miestnost" je z inymi miestnostami spojena vychodmi. 
 * Vychody z miestnosti su oznacovane svetovymi stranami sever, vychod, juh
 * a zapad. Pre kazdy vychod si miestnost pamata odkaz na susednu miestnost
 * alebo null, ak tym smerom vychod nema.
 *
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Miestnost {
    private String popisMiestnosti;
    private TreeMap<String, Miestnost> vychody;
    private final ArrayList<Predmet> predmety;

    /**
     * Vytvori miestnost popis ktorej je v parametrom.
     * Po vytvoreni miestnost nema ziadne vychody. Popis miesnost strucne 
     * charakterizuje.
     * 
     * @param popis text popisu miestnosti.
     */
    public Miestnost(String popis) {
        this.popisMiestnosti = popis;
        this.vychody = new TreeMap<String, Miestnost>();
        this.predmety = new ArrayList<Predmet>();
    }

    /**
     * Nastavi vychody z miestnosti. Kazdy vychod je urceny bud odkazom 
     * na miestnost alebo hodnotou null, ak vychod tym smerom neexistuje.
     * 
     * @param smer nazov smeru pre dany vychod.
     * @param vychod miestnost danym smerom.
     */
    public void nastavVychod(String smer, Miestnost vychod) {
        this.vychody.put(smer, vychod);
    }

    /**
     * @return textovy popis miestnosti.
     */
    public String getPopis() {
        return this.popisMiestnosti;
    }

    public void vypisInfoOMiestnosti() {
        System.out.println("Teraz si v miestnosti " + this.getPopis());
        System.out.print("Vychody: ");
        for (String smer : this.vychody.keySet()) {
            System.out.print(smer + " ");
        }
        System.out.println();
        if (!this.predmety.isEmpty()) {
            System.out.print("Predmety: ");
            for (Predmet predmet : this.predmety) {
                System.out.format("%s ", predmet.getNazov());
            }
            System.out.println();
        }
    }

    public Miestnost getVychod(String smer) {
        return this.vychody.get(smer);
    }

    void vlozPredmet(Predmet predmet) {
        this.predmety.add(predmet);
    }

    public Predmet vyberPredmet(String nazovPredmetu) {
        for (Predmet predmet : this.predmety) {
            if (predmet.getNazov().equals(nazovPredmetu)) {
                this.predmety.remove(predmet);
                return predmet;
            }
        }
        
        return null;
    }
}
