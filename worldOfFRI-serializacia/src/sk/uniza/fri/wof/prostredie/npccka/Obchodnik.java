/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie.npccka;

import java.io.Serializable;
import java.util.ArrayList;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

/**
 *
 * @author janik
 */
public class Obchodnik extends Npc implements Serializable {

    private final IPredmet[] tovar;

    public Obchodnik(String meno, IPredmet... tovar) {
        super(meno);
        
        this.tovar = tovar;
    }

    public void zobrazTovar() {
        int cislo = 1;
        for (IPredmet predmet : this.tovar) {
            System.out.format("%d) %s%n", cislo, predmet.getNazov());
            cislo++;
        }
    }

    public IPredmet vymenPredmet(int cisloTovaru, IPredmet predmetZInventara) {
        IPredmet tovar = this.tovar[cisloTovaru - 1];
        this.tovar[cisloTovaru - 1] = predmetZInventara;
        return tovar;
    }
    
}
