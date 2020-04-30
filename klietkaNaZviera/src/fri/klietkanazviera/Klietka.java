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

    private Zviera zviera;

    public Klietka() {
        this.zviera = null;
    }
    
    void vloz(Zviera zviera) {
        this.zviera = zviera;
    }

    @Override
    public String toString() {
        return "Klietka{" + "zviera=" + zviera + '}';
    }
    
}
