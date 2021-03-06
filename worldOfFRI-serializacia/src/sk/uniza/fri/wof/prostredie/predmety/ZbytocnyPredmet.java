/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie.predmety;

import java.io.Serializable;
import sk.uniza.fri.wof.hlavny.Hrac;

/**
 *
 * @author janik
 */
public class ZbytocnyPredmet implements IPredmet, Serializable {

    private final String nazov;

    public ZbytocnyPredmet(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.format("Pouzivas predmet %s, pouzivas, ale nist sa nedeje.%n", this.nazov);
    }

    @Override
    public boolean daSaPolozit() {
        return true;
    }
    
}
