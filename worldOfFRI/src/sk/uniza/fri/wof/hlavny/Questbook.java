/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.hlavny;

import java.util.ArrayList;

/**
 *
 * @author janik
 */
public class Questbook {

    private final ArrayList<QuestPrechadzaniaMiestnostami> questy;

    public Questbook() {
        this.questy = new ArrayList<QuestPrechadzaniaMiestnostami>();
    }
    
    public void pridajQuest(QuestPrechadzaniaMiestnostami quest) {
        this.questy.add(quest);
    }
    
    public void vypisQuesty() {
        if (this.questy.isEmpty()) {
            System.out.println("Nemas questy");
        } else {
            System.out.println("Aktivne questy:");
            for (QuestPrechadzaniaMiestnostami quest : this.questy) {
                System.out.format("- %s%n", quest.getNazov());
                System.out.format("  %s%n", quest.getPopis());
            }
        }
    }
}
