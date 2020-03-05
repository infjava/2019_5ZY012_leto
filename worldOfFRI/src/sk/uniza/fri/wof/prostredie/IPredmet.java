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
public interface IPredmet {

    String getNazov();

    void pouziSa(Hrac hrac);
    
}
