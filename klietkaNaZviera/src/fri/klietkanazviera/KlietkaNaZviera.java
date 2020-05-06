/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.klietkanazviera;

import java.util.Iterator;

/**
 *
 * @author janik
 */
public class KlietkaNaZviera {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Klietka<Lev> klietkaNaLeva = new Klietka<Lev>();
        klietkaNaLeva.vloz(new Lev());
        klietkaNaLeva.vlozPotravu(new Mys());
        System.out.println(klietkaNaLeva);
        
        Klietka<Mys> klietkaNaMys = new Klietka<Mys>();
        //klietkaNaMys.vloz(new Mys());
        //klietkaNaMys.vlozPotravu(new Syr());
        System.out.println(klietkaNaMys);
        
        System.out.println("Vypis cez foreach:");
        for (Mys zviera : klietkaNaMys) {
            System.out.println(zviera);
        }
        
        System.out.println("Vypis cez iterator:");
        Iterator<Mys> prst = klietkaNaMys.iterator();
        while (prst.hasNext()) {
            Mys zviera = prst.next();
            System.out.println(zviera);
        }
    }
    
}
