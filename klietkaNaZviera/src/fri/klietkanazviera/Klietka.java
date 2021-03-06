/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.klietkanazviera;

import java.util.Iterator;

/**
 *
 * @param E typ zvierata v klietke
 * @author janik
 */
class Klietka<E extends Zviera> implements Iterable<E> {

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

    void vlozPotravu(IPotrava<E> potrava) {
        this.zviera.zjedz(potrava);
    }

    @Override
    public Iterator<E> iterator() {
        return new KlietkaIterator<E>(this.zviera);
    }
    
}
