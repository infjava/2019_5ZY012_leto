/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie.predmety;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import sk.uniza.fri.wof.hlavny.Hrac;
import sk.uniza.fri.wof.prostredie.HraciaPlocha;

/**
 *
 * @author janik
 */
public interface IPredmet {

    String getNazov();

    void pouziSa(Hrac hrac);
    
    boolean daSaPolozit();

    void ulozPoziciu(DataOutputStream pozicia) throws IOException;

    void nacitajPoziciu(DataInputStream pozicia, HraciaPlocha hraciaPlocha, int verziaSave) throws IOException;
}
