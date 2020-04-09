/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

/**
 *
 * @author janik
 */
public enum Utok {
    PAPIER("KAMEN"),
    KAMEN("NOZNICE"),
    NOZNICE("PAPIER");
    
    private final String vyhraNad;
    
    Utok(String vyhraNad) {
        this.vyhraNad = vyhraNad;
    }
    
    public boolean vyhra(Utok nad) {
        return nad.name().equals(this.vyhraNad);
    }
}
