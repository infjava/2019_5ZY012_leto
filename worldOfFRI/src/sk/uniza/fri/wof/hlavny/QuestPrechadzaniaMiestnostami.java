/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.hlavny;

/**
 *
 * @author janik
 */
public class QuestPrechadzaniaMiestnostami {

    private final int pocet;

    public QuestPrechadzaniaMiestnostami(int pocet) {
        this.pocet = pocet;
    }

    
    String getNazov() {
        return String.format("Prejdi %d miestnosti", this.pocet);
    }

    String getPopis() {
        return String.format("Stary Dezo od teba chce, aby si sa riadne nachodil. Prejdi pre neho aspon %d miestnosti.", this.pocet);
    }
    
}
