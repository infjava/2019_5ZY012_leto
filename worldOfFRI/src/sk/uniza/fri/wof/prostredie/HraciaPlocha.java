/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import sk.uniza.fri.wof.hlavny.Hra;
import sk.uniza.fri.wof.prostredie.predmety.Navleky;
import sk.uniza.fri.wof.prostredie.predmety.Bageta;
import sk.uniza.fri.wof.prostredie.predmety.ZbytocnyPredmet;
import sk.uniza.fri.wof.prostredie.predmety.Dezo;
import sk.uniza.fri.wof.prostredie.npccka.VrcholRozhovoru;
import sk.uniza.fri.wof.prostredie.npccka.Obchodnik;
import sk.uniza.fri.wof.prostredie.npccka.NpcSRozhovorom;
import sk.uniza.fri.wof.prostredie.npccka.HranaRozhovoru;
import sk.uniza.fri.wof.prostredie.npccka.Nepriatel;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

/**
 *
 * @author janik
 */
public class HraciaPlocha {

    private Miestnost pociatocnaMiestnost;
    private final HashMap<String, Miestnost> miestnosti;

    public HraciaPlocha() {
        this.miestnosti = new HashMap<String, Miestnost>();
        this.nacitajMapu("hraciaplocha1.wofmap");
    }

    private void nacitajMapu(String nazovSuboru) {
        File suborMapy = new File(nazovSuboru);
        try (Scanner subor = new Scanner(suborMapy)) {
            
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Nenasiel sa subor s mapou", ex);
        }
    }

    public Miestnost getPociatocnaMiestnost() {
        return this.pociatocnaMiestnost;
    }
    
    public Miestnost getMiestnost(String nazov) {
        return this.miestnosti.get(nazov);
    }

    private Miestnost newMiestnost(String nazov, String popis) {
        Miestnost miestnost = new Miestnost(nazov, popis);
        this.miestnosti.put(nazov, miestnost);
        return miestnost;
    }

    public IPredmet vytvorPredmet(String nazovPredmetu) {
        switch (nazovPredmetu) {
            case "dezo":
                return new Dezo();
            case "bageta":
                return new Bageta();
            case "navleky":
                return new Navleky();
            default:
                return new ZbytocnyPredmet(nazovPredmetu);
        }
    }
}
