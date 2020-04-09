/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import java.util.Random;

/**
 *
 * @author janik
 */
public class Nepriatel extends Npc {

    public Nepriatel(String meno) {
        super(meno);
    }

    public VysledokUtoku utok(Utok utokHraca) {
        Utok[][] vyhry = {
            {Utok.PAPIER, Utok.KAMEN},
            {Utok.KAMEN, Utok.NOZNICE},
            {Utok.NOZNICE, Utok.PAPIER}
        };
        Random nahoda = new Random();
        int nahodnyTah = nahoda.nextInt(Utok.values().length);
        Utok utokNepriatela = Utok.values()[nahodnyTah];
        
        System.out.format("Utok: %s (hrac) <> %s (%s)%n", utokHraca, utokNepriatela, this.getMeno());
        
        for (Utok[] kombinacia : vyhry) {
            if (kombinacia[0].equals(utokHraca) && kombinacia[1].equals(utokNepriatela)) {
                return VysledokUtoku.VYHRAL_HRAC;
            }
        }
        
        if (utokHraca.equals(utokNepriatela)) {
            return VysledokUtoku.REMIZA;
        }
        
        return VysledokUtoku.VYHRAL_NEPRIATEL;
    }
    
}
