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
public class Obchodnik extends Npc {

    private final ArrayList<IPredmet> tovar;

    public Obchodnik(String meno, IPredmet... tovar) {
        super(meno);
        
        this.tovar = new ArrayList<IPredmet>();
        for (IPredmet predmet : tovar) {
            this.tovar.add(predmet);
        }
    }

    public void zobrazTovar() {
        int cislo = 1;
        for (IPredmet predmet : this.tovar) {
            System.out.format("%d) %s%n", cislo, predmet.getNazov());
            cislo++;
        }
    }

    public IPredmet vymenPredmet(int cisloTovaru, IPredmet predmetZInventara) {
        IPredmet tovar = this.tovar.get(cisloTovaru - 1);
        this.tovar.set(cisloTovaru - 1, predmetZInventara);
        return tovar;
    }
    
}
