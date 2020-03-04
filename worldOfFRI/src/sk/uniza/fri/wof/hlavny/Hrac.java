/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.hlavny;

import java.util.ArrayList;
import java.util.HashMap;
import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.Predmet;

/**
 *
 * @author janik
 */
public class Hrac {
    private Miestnost aktualnaMiestnost;
    private final HashMap<String, Predmet> inventar;

    public Hrac(Miestnost pociatocnaMiestnost) {
        this.aktualnaMiestnost = pociatocnaMiestnost;
        this.inventar = new HashMap<String, Predmet>();
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public boolean chodSmerom(String smer) {
        Miestnost novaMiestnost = this.aktualnaMiestnost.getVychod(smer);

        if (novaMiestnost == null) {
            return false;
        }
        
        this.aktualnaMiestnost = novaMiestnost;
        return true;
    }

    public void dvihniPredmet(String nazovPredmetu) {
        Predmet predmet = this.aktualnaMiestnost.vyberPredmet(nazovPredmetu);
        this.inventar.put(predmet.getNazov(), predmet);
    }

    public void zobrazInventar() {
        if (this.inventar.isEmpty()) {
            System.out.println("Inventar je prazdny");
        } else {
            System.out.println("V inventari mas:");
            for (String nazov : this.inventar.keySet()) {
                System.out.format("- %s%n", nazov);
            }
        }
    }

    public void zahodPredmet(String nazovPredmetu) {
        Predmet predmet = this.inventar.remove(nazovPredmetu);
        this.aktualnaMiestnost.vlozPredmet(predmet);
    }
}
