/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.hlavny;

import java.util.ArrayList;
import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.Predmet;

/**
 *
 * @author janik
 */
public class Hrac {
    private Miestnost aktualnaMiestnost;
    private final ArrayList<Predmet> inventar;

    public Hrac(Miestnost pociatocnaMiestnost) {
        this.aktualnaMiestnost = pociatocnaMiestnost;
        this.inventar = new ArrayList<Predmet>();
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
        this.inventar.add(predmet);
    }

    public void zobrazInventar() {
        if (this.inventar.isEmpty()) {
            System.out.println("Inventar je prazdny");
        } else {
            System.out.println("V inventari mas:");
            for (Predmet predmet : this.inventar) {
                System.out.format("- %s%n", predmet.getNazov());
            }
        }
    }

    public void zahodPredmet(String nazovPredmetu) {
        for (Predmet predmet : this.inventar) {
            if (predmet.getNazov().equals(nazovPredmetu)) {
                this.inventar.remove(predmet);
                this.aktualnaMiestnost.vlozPredmet(predmet);
                return;
            }
        }
    }
}
