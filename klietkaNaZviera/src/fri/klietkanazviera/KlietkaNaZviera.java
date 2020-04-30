/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.klietkanazviera;

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
        System.out.println(klietkaNaLeva);
        
        Klietka<Mys> klietkaNaMys = new Klietka<Mys>();
        klietkaNaMys.vloz(new Mys());
        System.out.println(klietkaNaMys);
    }
    
}
