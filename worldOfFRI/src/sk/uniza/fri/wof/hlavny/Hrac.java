/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.hlavny;

import java.io.DataOutputStream;
import sk.uniza.fri.wof.vynimky.HracZomrelException;
import sk.uniza.fri.wof.vynimky.NpcNespravnehoTypuException;
import sk.uniza.fri.wof.vynimky.NpcNenajdeneException;
import sk.uniza.fri.wof.questy.Questbook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import sk.uniza.fri.wof.prostredie.predmety.Bageta;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;
import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.npccka.Nepriatel;
import sk.uniza.fri.wof.prostredie.npccka.Npc;
import sk.uniza.fri.wof.prostredie.npccka.NpcSRozhovorom;
import sk.uniza.fri.wof.prostredie.npccka.Obchodnik;
import sk.uniza.fri.wof.prostredie.npccka.Utok;
import sk.uniza.fri.wof.prostredie.predmety.ZbytocnyPredmet;

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

    public void oslovNpc(String menoNpc) throws NpcNespravnehoTypuException, NpcNenajdeneException {
        Npc npc = this.aktualnaMiestnost.getNpc(menoNpc);
        
        if (!(npc instanceof NpcSRozhovorom)) {
            throw new NpcNespravnehoTypuException();
        }
        
        ((NpcSRozhovorom) npc).spustiRozhovor();
    }

    public void nakupOdNpc(String menoNpc) throws NpcNenajdeneException, NpcNespravnehoTypuException {
        Npc npc = this.aktualnaMiestnost.getNpc(menoNpc);
        
        if (!(npc instanceof Obchodnik)) {
            throw new NpcNespravnehoTypuException();
        }
        
        final Scanner vstup = new Scanner(System.in);
        final Obchodnik obchodnik = (Obchodnik) npc;

        obchodnik.zobrazTovar();

        if (this.inventar.isEmpty()) {
            return;
        }

        System.out.print("Co chces kupit [0 ak nic]: ");
        int cisloTovaru = vstup.nextInt();

        if (cisloTovaru == 0) {
            return;
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
            return;
        }

        IPredmet predmetZInventara = this.inventar.remove(
            zoznamNazvovPredmetov.get(cisloPredmetuZInventara - 1)
        );

        IPredmet tovar = obchodnik.vymenPredmet(cisloTovaru, predmetZInventara);
        this.inventar.put(tovar.getNazov(), tovar);
    }

    public void utocNaNpc(String menoNpc) throws NpcNenajdeneException, NpcNespravnehoTypuException {
        Npc npc = this.aktualnaMiestnost.getNpc(menoNpc);
        
        if (!(npc instanceof Nepriatel)) {
            throw new NpcNespravnehoTypuException();
        }
        
        System.out.print("Ako chces zautocit [papier/kamen/noznice]");
        Utok utok = Utok.valueOf(new Scanner(System.in).nextLine().toUpperCase());

        switch (((Nepriatel) npc).utok(utok)) {
            case VYHRAL_HRAC:
                System.out.println("Vyhral si");
                break;
            case VYHRAL_NEPRIATEL:
                System.out.format("Vyhral %s%n", menoNpc);
                throw new HracZomrelException();
            case REMIZA:
                System.out.println("Bola remiza");
                break;
        }
    }
}
