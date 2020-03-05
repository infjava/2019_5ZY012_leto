/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.hlavny.Hrac;

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
    
}
