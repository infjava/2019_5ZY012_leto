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
        Klietka klietka = new Klietka();
        klietka.vloz(new Lev());
        System.out.println(klietka);
    }
    
}
