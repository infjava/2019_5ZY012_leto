package sk.uniza.fri.wof.prostredie;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
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
    private final HashMap<String, IPredmet> predmety;
    private final HashMap<String, Npc> npccka;

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
        this.predmety = new HashMap<String, IPredmet>();
        this.npccka = new HashMap<String, Npc>();
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
        this.vypisInfoOPolozkach("Vychody", this.vychody.keySet());
        this.vypisInfoOPolozkach("Predmety", this.predmety.keySet());
        this.vypisInfoOPolozkach("Npc", this.npccka.keySet());
    }

    private void vypisInfoOPolozkach(String nadpis, Set<String> polozky) {
        if (!polozky.isEmpty()) {
            System.out.print(nadpis + ": ");
            for (String meno : polozky) {
                System.out.format("%s ", meno);
            }
            System.out.println();
        }
    }

    public Miestnost getVychod(String smer) {
        return this.vychody.get(smer);
    }

    public void vlozPredmet(IPredmet predmet) {
        this.predmety.put(predmet.getNazov(), predmet);
    }

    public IPredmet vyberPredmet(String nazovPredmetu) {
        return this.predmety.remove(nazovPredmetu);
    }

    void postavNpc(Npc npc) {
        this.npccka.put(npc.getMeno(), npc);
    }

    public Npc getNpc(String menoNpc) {
        return this.npccka.get(menoNpc);
    }
}
