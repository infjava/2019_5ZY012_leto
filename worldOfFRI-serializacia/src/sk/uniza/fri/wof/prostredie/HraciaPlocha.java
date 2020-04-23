/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import java.io.Serializable;
import java.util.HashMap;
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
public class HraciaPlocha implements Serializable {

    private final Miestnost pociatocnaMiestnost;
    private final HashMap<String, Miestnost> miestnosti;

    public HraciaPlocha() {
        this.miestnosti = new HashMap<String, Miestnost>();
        
        Miestnost bus = this.newMiestnost("autobus", "ide sa domov");
        Miestnost bufet = this.newMiestnost("bufet", "tu ti daju bagetu a pizzu");
        Miestnost parkovisko = this.newMiestnost("parkovisko", "plne aut, bicyklov, kolobeziek, tiav a koni");
        Miestnost rc = this.newMiestnost("rc", "chodba v podzemi");
        Miestnost ra006 = this.newMiestnost("ra006", "miestnost plna pilne programujucich studentov");
        Miestnost ra = this.newMiestnost("ra", "chodba s vytrinkami plnymi historickych artefaktov");
        Miestnost vratnica = this.newMiestnost("vratnica", "tu na nas ciha pani Vratnicka");
        Miestnost rb = this.newMiestnost("rb", "temna chodba");
        Miestnost chillZone = this.newMiestnost("chillzona", "mozte sa tu schladit v prijemnom prostredi huciacich pocitacov");
        Miestnost ic = this.newMiestnost("ic", "kniznica");
        
        bufet.vlozPredmet(new ZbytocnyPredmet("krabica"));
        
        bus.nastavVychod("vychod", parkovisko);
        
        bufet.nastavVychod("sever", parkovisko);
        bufet.nastavVychod("vychod", rc);
        bufet.postavNpc(new Obchodnik("kucharka",
            new ZbytocnyPredmet("pizza"),
            new Bageta(),
            new Navleky()
        ));
        
        parkovisko.nastavVychod("zapad", bus);
        parkovisko.nastavVychod("hore", vratnica);
        parkovisko.nastavVychod("sever", bufet);
        
        rc.nastavVychod("zapad", bufet);
        rc.nastavVychod("hore", ra);
        
        ra006.nastavVychod("vychod", ra);
        
        ra.nastavVychod("zapad", ra006);
        ra.nastavVychod("dole", rc);
        ra.nastavVychod("juh", vratnica);
        
        vratnica.nastavVychod("juh", rb);
        vratnica.nastavVychod("sever", ra);
        vratnica.nastavVychod("vychod", ic);
        vratnica.nastavVychod("dole", parkovisko);
        
        vratnica.vlozPredmet(new ZbytocnyPredmet("klavesnica"));
        vratnica.vlozPredmet(new Dezo());
        
        VrcholRozhovoru zaciatocny = new VrcholRozhovoru(null,
            new HranaRozhovoru("kde najdem ucitela?", new VrcholRozhovoru("Ktory?",
                new HranaRozhovoru("Janech", new VrcholRozhovoru("Ma cvicenie")),
                new HranaRozhovoru("dekan", new VrcholRozhovoru("Netusim"))
            )),
            new HranaRozhovoru("nemate toaletny papier?", new VrcholRozhovoru("Mam, ale nedam. Kup si v Tescu.")),
            new HranaRozhovoru("kde to som?", new VrcholRozhovoru("Pouzi prikaz pomoc"))
        );
        
        vratnica.postavNpc(new NpcSRozhovorom("upratovacka", zaciatocny));
        vratnica.postavNpc(new Nepriatel("ucitel"));
        
        rb.nastavVychod("sever", vratnica);
        rb.nastavVychod("juh", chillZone);
        
        chillZone.nastavVychod("sever", rb);
        
        ic.nastavVychod("zapad", vratnica);

        this.pociatocnaMiestnost = vratnica;
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
