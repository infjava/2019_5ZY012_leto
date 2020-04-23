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
public class Navleky implements IPredmet {

    private boolean obute;

    public Navleky() {
        this.obute = false;
    }

    @Override
    public String getNazov() {
        return "navleky";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        this.obute = !this.obute;
        
        if (this.obute) {
            System.out.println("Obul si si navleky, mozes vstupit do labaku");
        } else {
            System.out.println("Vyzul si si navleky, drz sa od labaku dalej");
        }
    }
    
    @Override
    public boolean daSaPolozit() {
        return !this.obute;
    }

    @Override
    public void ulozPoziciu(DataOutputStream pozicia) throws IOException {
        pozicia.writeBoolean(this.obute);
    }

    @Override
    public void nacitajPoziciu(DataInputStream pozicia, HraciaPlocha hraciaPlocha, int verziaSave) throws IOException {
        if (verziaSave >= 3) { // mame aj stav predmetu
            this.obute = pozicia.readBoolean();
        }
    }
    
}
