/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.hlavny.Hrac;


public class Bageta implements IPredmet {

    private int pocetZahryznuti;

    public Bageta() {
        this.pocetZahryznuti = 0;
    }

    @Override
    public String getNazov() {
        return "bageta";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.println("Chrum chrum, mnam mnam");
        this.pocetZahryznuti++;
        if (this.pocetZahryznuti < 7) {
            System.out.format("Zostava ti %.2f%% bagety%n", (7 - this.pocetZahryznuti) * 100.0 / 7);
        } else {
            System.out.println("Pazravec");
            hrac.odstranPredmet(this);
        }
    }

    @Override
    public boolean daSaPolozit() {
        return true;
    }
    
}
