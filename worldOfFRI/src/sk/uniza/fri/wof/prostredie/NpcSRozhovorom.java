/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import java.util.Scanner;


public class NpcSRozhovorom extends Npc {

    private final VrcholRozhovoru zaciatokRozhovoru;

    NpcSRozhovorom(String meno, VrcholRozhovoru zaciatokRozhovoru) {
        super(meno);
        this.zaciatokRozhovoru = zaciatokRozhovoru;
    }

    public void spustiRozhovor() {
        Scanner vstup = new Scanner(System.in);
        VrcholRozhovoru aktualny = this.zaciatokRozhovoru;
        
        do {
            aktualny.vypis();
            int moznost = vstup.nextInt();
            aktualny = aktualny.getNasledujuci(moznost);
        } while (!aktualny.jeKoniecRozhovoru());
        
        aktualny.vypis();
    }
    
}
