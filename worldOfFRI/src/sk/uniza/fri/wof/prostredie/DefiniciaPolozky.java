/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import java.util.ArrayList;

/**
 *
 * @author janik
 */
class DefiniciaPolozky {

    private final String polozka;
    private final ArrayList<DefiniciaPolozky> podpolozky;

    DefiniciaPolozky(String polozka) {
        this.polozka = polozka;
        this.podpolozky = new ArrayList<DefiniciaPolozky>();
    }

    public String getPolozka() {
        return this.polozka;
    }

    void pridaj(int urovenZoznamu, DefiniciaPolozky definiciaPolozky) {
        if (urovenZoznamu == 1) {
            this.podpolozky.add(definiciaPolozky);
        } else {
            this.podpolozky.get(this.podpolozky.size() - 1).pridaj(urovenZoznamu - 1, definiciaPolozky);
        }
    }

    int pocetPodpoloziek() {
        return this.podpolozky.size();
    }

    DefiniciaPolozky getPodpolozka(int index) {
        return this.podpolozky.get(index);
    }

    void vypisDebug(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(this.polozka);
        for (DefiniciaPolozky definiciaPolozky : this.podpolozky) {
            definiciaPolozky.vypisDebug(level + 1);
        }
    }
    
}
