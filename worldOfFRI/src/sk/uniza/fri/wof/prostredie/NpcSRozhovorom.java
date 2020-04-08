/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import java.util.Scanner;


public class NpcSRozhovorom extends Npc {

    public NpcSRozhovorom(String meno) {
        super(meno);
    }

    public void spustiRozhovor() {
        Scanner vstup = new Scanner(System.in);
        
        System.out.println("1) kde najdem ucitela?");
        System.out.println("2) nemate toaletny papier?");
        System.out.println("3) kde to som?");
        System.out.print("Vyber si[1-3]: ");
        int moznost = vstup.nextInt();
        switch (moznost) {
            case 1:
                System.out.println("Ktory?");
                System.out.println("1) Janech");
                System.out.println("2) dekan");
                System.out.print("Vyber si[1-2]: ");
                moznost = vstup.nextInt();
                switch (moznost) {
                    case 1:
                        System.out.println("Ma cvicenie");
                        break;
                    case 2:
                        System.out.println("Netusim");
                        break;
                    default:
                        throw new AssertionError();
                }
                break;
            case 2:
                System.out.println("Mam, ale nedam. Kup si v Tescu.");
                break;
            case 3:
                System.out.println("Pouzi prikaz pomoc");
                break;
            default:
                throw new AssertionError();
        }
    }
    
}
