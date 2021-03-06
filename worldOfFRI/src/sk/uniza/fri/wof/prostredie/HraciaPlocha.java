/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.vynimky.MiestnostNenajdenaException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import sk.uniza.fri.wof.hlavny.Hra;
import sk.uniza.fri.wof.prostredie.predmety.Navleky;
import sk.uniza.fri.wof.prostredie.predmety.Bageta;
import sk.uniza.fri.wof.prostredie.predmety.ZbytocnyPredmet;
import sk.uniza.fri.wof.prostredie.predmety.Dezo;
import sk.uniza.fri.wof.prostredie.npccka.VrcholRozhovoru;
import sk.uniza.fri.wof.prostredie.npccka.Obchodnik;
import sk.uniza.fri.wof.prostredie.npccka.NpcSRozhovorom;
import sk.uniza.fri.wof.prostredie.npccka.HranaRozhovoru;
import sk.uniza.fri.wof.prostredie.npccka.Nepriatel;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

/**
 *
 * @author janik
 */
public class HraciaPlocha {

    private Miestnost pociatocnaMiestnost;
    private final HashMap<String, Miestnost> miestnosti;

    public HraciaPlocha() {
        this.miestnosti = new HashMap<String, Miestnost>();
        this.nacitajMapu("hraciaplocha.wofmap");
    }

    private void nacitajMapu(String nazovSuboru) {
        File suborMapy = new File(nazovSuboru);
        try (Scanner subor = new Scanner(suborMapy)) {
            Miestnost posledna = null;
            SekciaDefinicieMiestnosti sekcia = null;
            
            ArrayList<DefiniciaVychodu> vychody = new ArrayList<DefiniciaVychodu>();
            DefiniciaNpc npc = null;
            
            while (subor.hasNextLine()) {                
                String riadokString = subor.nextLine();
                Scanner riadok = new Scanner(riadokString);
                
                if (!riadok.hasNext()) {
                    continue;
                }
                
                final String prikaz = riadok.next();
                
                if (!this.suHviezdicky(prikaz) && npc != null) {
                    npc.vytvor(posledna, this);
                    npc = null;
                }
                
                switch (prikaz) {
                    case "Miestnost":
                        posledna = this.newMiestnost(riadok.next(), riadok.nextLine().strip());
                        break;
                    case "Vychody:":
                        sekcia = SekciaDefinicieMiestnosti.VYCHODY;
                        break;
                    case "Npc:":
                        sekcia = SekciaDefinicieMiestnosti.NPC;
                        break;
                    case "Predmety:":
                        sekcia = SekciaDefinicieMiestnosti.PREDMETY;
                        break;
                    case "Start":
                        this.pociatocnaMiestnost = posledna;
                        break;
                    case "-":
                        switch (sekcia) {
                            case VYCHODY:
                                vychody.add(new DefiniciaVychodu(posledna, riadok.next(), riadok.next()));
                                break;
                            case NPC:
                                switch (riadok.next()) {
                                    case "obchodnik":
                                        npc = new DefiniciaNpc(TypDefinicieNpc.OBCHODNIK, riadok.next());
                                        break;
                                    case "nepriatel":
                                        posledna.postavNpc(new Nepriatel(riadok.next()));
                                        break;
                                    case "rozhovor":
                                        npc = new DefiniciaNpc(TypDefinicieNpc.ROZHOVOR, riadok.next());
                                        break;
                                    default:
                                        throw new AssertionError();
                                }
                                break;
                            case PREDMETY:
                                posledna.vlozPredmet(this.vytvorPredmet(riadok.next()));
                                break;
                            default:
                                throw new AssertionError();
                        }
                        break;

                    default:
                        if (this.suHviezdicky(prikaz)) {
                            npc.pridajPolozku(prikaz.length(), riadok.nextLine().strip());
                        } else {
                            throw new AssertionError();
                        }
                }
            }
            
            for (DefiniciaVychodu vychod : vychody) {
                vychod.vytvor(this);
            }
            
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Nenasiel sa subor s mapou", ex);
        } catch (MiestnostNenajdenaException ex) {
            throw new RuntimeException("Nenasla sa miestnost", ex);
        }
        
        if (this.pociatocnaMiestnost == null) {
            throw new RuntimeException("V mape chyba definicia pociatocnej miestnosti");
        }
    }

    public Miestnost getPociatocnaMiestnost() {
        return this.pociatocnaMiestnost;
    }
    
    public Miestnost getMiestnost(String nazov) throws MiestnostNenajdenaException {
        Miestnost miestnost = this.miestnosti.get(nazov);
        if (miestnost == null) {
            throw new MiestnostNenajdenaException();
        }
        return miestnost;
    }

    private Miestnost newMiestnost(String nazov, String popis) {
        Miestnost miestnost = new Miestnost(nazov, popis);
        this.miestnosti.put(nazov, miestnost);
        return miestnost;
    }

    public IPredmet vytvorPredmet(String nazovPredmetu) {
        switch (nazovPredmetu) {
            case "dezo":
                return new Dezo();
            case "bageta":
                return new Bageta();
            case "navleky":
                return new Navleky();
            default:
                return new ZbytocnyPredmet(nazovPredmetu);
        }
    }

    private boolean suHviezdicky(String prikaz) {
        for (int i = 0; i < prikaz.length(); i++) {
            if (prikaz.charAt(i) != '*') {
                return false;
            }
        }
        
        return true;
    }
}
