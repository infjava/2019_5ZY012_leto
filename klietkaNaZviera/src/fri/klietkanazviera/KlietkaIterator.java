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
public class KlietkaIterator<E> implements Iterator<E> {

    private E zviera;

    public KlietkaIterator(E zviera) {
        this.zviera = zviera;
    }

    @Override
    public boolean hasNext() {
        return this.zviera != null;
    }

    @Override
    public E next() {
        E vracaneZviera = this.zviera;
        this.zviera = null;
        return vracaneZviera;
    }
    
}
