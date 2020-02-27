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
        // vytvorenie miestnosti
        Miestnost terasa = new Miestnost("terasa - hlavny vstup na fakultu");
        Miestnost aula = new Miestnost("aula");
        Miestnost bufet = new Miestnost("bufet");
        Miestnost labak = new Miestnost("pocitacove laboratorium");
        Miestnost kancelaria = new Miestnost("kancelaria spravcu pocitacoveho laboratoria");
        
        // inicializacia miestnosti = nastavenie vychodov
        terasa.nastavVychod("vychod", aula);
        terasa.nastavVychod("juh", labak);
        terasa.nastavVychod("zapad", bufet);
        aula.nastavVychod("zapad", terasa);
        bufet.nastavVychod("vychod", terasa);
        labak.nastavVychod("sever", terasa);
        labak.nastavVychod("vychod", kancelaria);
        kancelaria.nastavVychod("zapad", labak);

        this.pociatocnaMiestnost = terasa;
    }

    public Miestnost getPociatocnaMiestnost() {
        return this.pociatocnaMiestnost;
    }
}
