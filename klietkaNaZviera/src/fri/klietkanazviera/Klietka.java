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
class Klietka {

    private Lev lev;

    public Klietka() {
        this.lev = null;
    }
    
    void vloz(Lev lev) {
        this.lev = lev;
    }

    @Override
    public String toString() {
        return "Klietka{" + "lev=" + lev + '}';
    }
    
}
