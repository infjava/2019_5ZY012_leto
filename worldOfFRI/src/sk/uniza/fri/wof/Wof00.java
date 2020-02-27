package sk.uniza.fri.wof;

/*
timer
ziskavanie bodov
- spawnovane v miestnostiach
npc
- dekan
- ucitel
- nahnevany student
- upratovacka
- robot
- akcie:
  - rozhovor
  - suboj - papier, kamen, noznice
  - obchod
questy
- kupit bagetu pre npc, aby otvorilo dvere
- robot da hadanku
- robot nevie vypocitat, potrebuje kalkulacku
predmety
- bageta
- kalkulacka
- navleky
- ploskacka/tapka
- index
- isic
*/

import sk.uniza.fri.wof.hlavny.Hra;

/**
 * Hlavna trieda hry WoF s metodou main - spustanie v NB
 * 
 * @author Lubomir Sadlon
 * @version 21.2.2012
 */
public class Wof00 {

    /**
     * @param args parametre programu
     */
    public static void main(String[] args) {
        Hra hra = new Hra();
        hra.hraj();
    }
}
