/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.klietkanazviera;

/**
 *
 * @param E typ zvierata v klietke
 * @author janik
 */
class Klietka<E> {

    private E zviera;

    public Klietka() {
        this.zviera = null;
    }
    
    void vloz(E zviera) {
        this.zviera = zviera;
    }

    @Override
    public String toString() {
        return "Klietka{" + "zviera=" + zviera + '}';
    }
    
}
