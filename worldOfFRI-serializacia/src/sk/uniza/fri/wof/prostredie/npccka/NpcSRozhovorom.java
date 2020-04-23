/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie.npccka;

import java.io.Serializable;
import java.util.Scanner;


public class NpcSRozhovorom extends Npc implements Serializable {

    private final VrcholRozhovoru zaciatokRozhovoru;

    public NpcSRozhovorom(String meno, VrcholRozhovoru zaciatokRozhovoru) {
        super(meno);
        this.zaciatokRozhovoru = zaciatokRozhovoru;
    }

    public void spustiRozhovor() {
        Scanner vstup = new Scanner(System.in);
        VrcholRozhovoru aktualny = this.zaciatokRozhovoru;
        
        do {
            aktualny.vypis();
            
            int moznost;
            try {
                moznost = Integer.parseInt(vstup.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            
            try {
                aktualny = aktualny.getNasledujuci(moznost);
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        } while (!aktualny.jeKoniecRozhovoru());
        
        aktualny.vypis();
    }
    
}
