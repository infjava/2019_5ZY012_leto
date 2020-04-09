/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.hlavny;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import sk.uniza.fri.wof.prostredie.Bageta;
import sk.uniza.fri.wof.prostredie.IPredmet;
import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.Npc;
import sk.uniza.fri.wof.prostredie.NpcSRozhovorom;
import sk.uniza.fri.wof.prostredie.Obchodnik;
import sk.uniza.fri.wof.prostredie.ZbytocnyPredmet;

/**
 *
 * @author janik
 */
public class Hrac {
    private Miestnost aktualnaMiestnost;
    private final HashMap<String, IPredmet> inventar;
    private final Questbook questbook;

    public Hrac(Miestnost pociatocnaMiestnost) {
        this.aktualnaMiestnost = pociatocnaMiestnost;
        this.inventar = new HashMap<String, IPredmet>();
        this.questbook = new Questbook();
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public boolean chodSmerom(String smer) {
        Miestnost novaMiestnost = this.aktualnaMiestnost.getVychod(smer);

        if (novaMiestnost == null) {
            return false;
        }
        
        this.aktualnaMiestnost = novaMiestnost;
        
        this.questbook.getRiadic().hracSaPohol();
        
        return true;
    }

    public boolean dvihniPredmet(String nazovPredmetu) {
        IPredmet predmet = this.aktualnaMiestnost.vyberPredmet(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        this.inventar.put(predmet.getNazov(), predmet);
        return true;
    }

    public void zobrazInventar() {
        if (this.inventar.isEmpty()) {
            System.out.println("Inventar je prazdny");
        } else {
            System.out.println("V inventari mas:");
            for (String nazov : this.inventar.keySet()) {
                System.out.format("- %s%n", nazov);
            }
        }
    }

    public boolean zahodPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.remove(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        if (!predmet.daSaPolozit()) {
            return false;
        }
        
        this.aktualnaMiestnost.vlozPredmet(predmet);
        return true;
    }

    public boolean pouziPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.get(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        predmet.pouziSa(this);
        return true;
    }

    public void odstranPredmet(IPredmet predmet) {
        this.inventar.remove(predmet.getNazov());
    }

    public Questbook getQuestbook() {
        return this.questbook;
    }

    public boolean oslovNpc(String menoNpc) {
        Npc npc = this.aktualnaMiestnost.getNpc(menoNpc);
        
        if (npc instanceof NpcSRozhovorom) {
            ((NpcSRozhovorom) npc).spustiRozhovor();
            return true;
        }
        
        return false;
    }

    public boolean nakupOdNpc(String menoNpc) {
        Npc npc = this.aktualnaMiestnost.getNpc(menoNpc);
        
        if (npc instanceof Obchodnik) {
            final Scanner vstup = new Scanner(System.in);
            final Obchodnik obchodnik = (Obchodnik) npc;
            
            obchodnik.zobrazTovar();
            
            if (this.inventar.isEmpty()) {
                return true;
            }
            
            System.out.print("Co chces kupit [0 ak nic]: ");
            int cisloTovaru = vstup.nextInt();
            
            if (cisloTovaru == 0) {
                return true;
            }
            
            System.out.println("V inventari mas:");
            int cislo = 1;
            ArrayList<String> zoznamNazvovPredmetov = new ArrayList<String>();
            for (String nazov : this.inventar.keySet()) {
                System.out.format("%d) %s%n", cislo, nazov);
                zoznamNazvovPredmetov.add(nazov);
                cislo++;
            }
            
            System.out.print("Za co chces vymenit [0 ak nic]: ");
            int cisloPredmetuZInventara = vstup.nextInt();
            
            if (cisloPredmetuZInventara == 0) {
                return true;
            }
            
            IPredmet predmetZInventara = this.inventar.remove(
                zoznamNazvovPredmetov.get(cisloPredmetuZInventara - 1)
            );
            
            IPredmet tovar = obchodnik.vymenPredmet(cisloTovaru, predmetZInventara);
            this.inventar.put(tovar.getNazov(), tovar);
            
            return true;
        }
        
        return false;
    }
}
