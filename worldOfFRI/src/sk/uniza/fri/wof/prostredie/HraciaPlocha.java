/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

/**
 *
 * @author janik
 */
public class HraciaPlocha {

    private final Miestnost pociatocnaMiestnost;

    public HraciaPlocha() {
        Miestnost bus = new Miestnost("autobus - ide sa domov");
        Miestnost bufet = new Miestnost("bufet - tu ti daju bagetu a pizzu");
        Miestnost parkovisko = new Miestnost("parkovisko - plne aut, bicyklov, kolobeziek, tiav a koni");
        Miestnost rc = new Miestnost("rc - chodba v podzemi");
        Miestnost ra006 = new Miestnost("ra006 - miestnost plna pilne programujucich studentov");
        Miestnost ra = new Miestnost("ra - chodba s vytrinkami plnymi historickych artefaktov");
        Miestnost vratnica = new Miestnost("vratnica - tu na nas ciha pani Vratnicka");
        Miestnost rb = new Miestnost("rb - temna chodba");
        Miestnost chillZone = new Miestnost("chillzona - mozte sa tu schladit v prijemnom prostredi huciacich pocitacov");
        Miestnost ic = new Miestnost("ic - kniznica");
        
        bus.nastavVychod("vychod", parkovisko);
        
        bufet.nastavVychod("sever", parkovisko);
        bufet.nastavVychod("vychod", rc);
        bufet.vlozPredmet(new Bageta());
        
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
        vratnica.vlozPredmet(new Navleky());
        vratnica.vlozPredmet(new Dezo());
        
        vratnica.postavNpc(new Npc("upratovacka"));
        
        rb.nastavVychod("sever", vratnica);
        rb.nastavVychod("juh", chillZone);
        
        chillZone.nastavVychod("sever", rb);
        
        ic.nastavVychod("zapad", vratnica);

        this.pociatocnaMiestnost = vratnica;
    }

    public Miestnost getPociatocnaMiestnost() {
        return this.pociatocnaMiestnost;
    }
}
