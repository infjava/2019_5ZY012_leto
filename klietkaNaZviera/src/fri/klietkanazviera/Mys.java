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
public class Mys extends Zviera implements IPotrava<Lev> {

    @Override
    public String toString() {
        return "Mys";
    }

    @Override
    public void zjedz(IPotrava potrava) {
        System.out.println("Myska postupne schrumkala " + potrava);
    }

    
}
