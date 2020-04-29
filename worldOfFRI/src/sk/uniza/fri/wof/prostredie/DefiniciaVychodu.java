/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.vynimky.MiestnostNenajdenaException;

/**
 *
 * @author janik
 */
class DefiniciaVychodu {

    private final Miestnost miestnost;
    private final String smer;
    private final String ciel;

    DefiniciaVychodu(Miestnost miestnost, String smer, String ciel) {
        this.miestnost = miestnost;
        this.smer = smer;
        this.ciel = ciel;
        
    }

    void vytvor(HraciaPlocha hraciaPlocha) throws MiestnostNenajdenaException {
        this.miestnost.nastavVychod(this.smer, hraciaPlocha.getMiestnost(this.ciel));
    }
    
}
