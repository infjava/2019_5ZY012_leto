package sk.uniza.fri.wof.hlavny.ovladanie;

import sk.uniza.fri.wof.hlavny.Hra;
import sk.uniza.fri.wof.hlavny.Hrac;
import sk.uniza.fri.wof.vynimky.NpcNenajdeneException;
import sk.uniza.fri.wof.vynimky.NpcNespravnehoTypuException;
import sk.uniza.fri.wof.vynimky.NuspesnyLoadException;
import sk.uniza.fri.wof.vynimky.NuspesnySaveException;
import sk.uniza.fri.wof.vynimky.SaveNenajdenyException;

/**
 * Trieda NazvyPrikazov udrzuje zoznam nazvov platnych prikazov hry. 
 * Za ulohu ma rozpoznavat platne prikazy.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */

public class VykonavacPrikazov {
    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "chod", "ukonci", "pomoc", "dvihni", "inventar",
        "zahod", "pouzi", "questbook", "dobryden",
        "masnieco", "utoc", "save", "load"
    };
    private final Hra hra;

    public VykonavacPrikazov(Hra hra) {
        this.hra = hra;
    }

    /**
     * Kontroluje, ci nazov v parametri je platny prikaz.
     *  
     * @return true ak je parameter platny prikaz,
     * false inak.
     */
    public boolean jePrikaz(String nazov) {
        for (int i = 0; i < VykonavacPrikazov.PLATNE_PRIKAZY.length; i++) {
            if (VykonavacPrikazov.PLATNE_PRIKAZY[i].equals(nazov)) {
                return true;
            }
        }
        // ak algoritmus dosiahne tento bod, parameter nie je platny prikaz
        return false;
    }

    /**
     * Prevezne prikaz a vykona ho.
     * 
     * @param hrac hrac, ktory prikaz vykonava.
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    public boolean vykonajPrikaz(Hrac hrac, Prikaz prikaz) {
        boolean jeKoniec = false;

        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        String nazovPrikazu = prikaz.getNazov();
        
        switch (nazovPrikazu) {
            case "pomoc":
                this.vypisNapovedu();
                return false;
            case "chod":
                this.chodDoMiestnosti(hrac, prikaz);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            case "dvihni":
                this.dvihniPredmet(hrac, prikaz);
                return false;
            case "zahod":
                this.zahodPredmet(hrac, prikaz);
                return false;
            case "inventar":
                this.zobrazInventar(hrac);
                return false;
            case "pouzi":
                this.pouziPredmet(hrac, prikaz);
                return false;
            case "questbook":
                this.zobrazQuestbook(hrac);
                return false;
            case "dobryden":
                this.oslovNpc(hrac, prikaz);
                return false;
            case "masnieco":
                this.nakupOdNpc(hrac, prikaz);
                return false;
            case "utoc":
                this.utocNaNpc(hrac, prikaz);
                return false;
            case "save":
                this.ulozPoziciu(hrac, prikaz);
                return false;
            case "load":
                this.nacitajPoziciu(hrac, prikaz);
                return false;
            default:
                return false;
        }
    }

    // implementacie prikazov:

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        for (String prikaz : VykonavacPrikazov.PLATNE_PRIKAZY) {
            System.out.format("- %s%n", prikaz);
        }
    }

    /** 
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     */
    private void chodDoMiestnosti(Hrac hrac, Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();
        
        if (hrac.chodSmerom(smer)) {
            hrac.getAktualnaMiestnost().vypisInfoOMiestnosti();
        } else {
            System.out.println("Tam nie je vychod!");
        }
    }

    /** 
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * Prikaz ukoncenia nema parameter.
     * 
     * @return true, if this command quits the game, false otherwise.
     * @return true, ak prikaz konci hru, inak false.
     */
    private boolean ukonciHru(Prikaz prikaz) {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }

    private void dvihniPredmet(Hrac hrac, Prikaz prikaz) {
        String nazovPredmetu = prikaz.getParameter();
        if (!hrac.dvihniPredmet(nazovPredmetu)) {
            System.out.format("Nepodarilo sa dvihnut predmet %s%n", nazovPredmetu);
        }
    }

    private void zobrazInventar(Hrac hrac) {
        hrac.zobrazInventar();
    }

    private void zahodPredmet(Hrac hrac, Prikaz prikaz) {
        String nazovPredmetu = prikaz.getParameter();
        if (!hrac.zahodPredmet(nazovPredmetu)) {
            System.out.format("Nepodarilo sa zahodit predmet %s%n", nazovPredmetu);
        }
    }

    private void pouziPredmet(Hrac hrac, Prikaz prikaz) {
        String nazovPredmetu = prikaz.getParameter();
        if (!hrac.pouziPredmet(nazovPredmetu)) {
            System.out.format("Nepodarilo sa pouzit predmet %s%n", nazovPredmetu);
        }
    }

    private void zobrazQuestbook(Hrac hrac) {
        hrac.getQuestbook().vypisQuesty();
    }

    private void oslovNpc(Hrac hrac, Prikaz prikaz) {
        String menoNpc = prikaz.getParameter();
        try {
            hrac.oslovNpc(menoNpc);
        } catch (NpcNespravnehoTypuException ex) {
            System.out.format("Npc %s sa s tebou rozpravat nechce%n", menoNpc);
        } catch (NpcNenajdeneException ex) {
            System.out.format("Npc %s nikde nevidis%n", menoNpc);
        }
    }

    private void nakupOdNpc(Hrac hrac, Prikaz prikaz) {
        String menoNpc = prikaz.getParameter();
        try {
            hrac.nakupOdNpc(menoNpc);
        } catch (NpcNespravnehoTypuException ex) {
            System.out.format("Npc %s ti nema co ponuknut%n", menoNpc);
        } catch (NpcNenajdeneException ex) {
            System.out.format("Npc %s nikde nevidis%n", menoNpc);
        }
    }

    private void utocNaNpc(Hrac hrac, Prikaz prikaz) {
        String menoNpc = prikaz.getParameter();
        try {
            hrac.utocNaNpc(menoNpc);
        } catch (NpcNespravnehoTypuException ex) {
            System.out.format("Npc %s s tebou bojovat nechce%n", menoNpc);
        } catch (NpcNenajdeneException ex) {
            System.out.format("Npc %s nikde nevidis%n", menoNpc);
        }
    }

    private void ulozPoziciu(Hrac hrac, Prikaz prikaz) {
        String nazovPozicie = prikaz.getParameter();
        try {
            this.hra.ulozPoziciu(nazovPozicie);
        } catch (NuspesnySaveException ex) {
            System.out.println("Nepodarilo sa ulozit save, skus znovu");
        }
    }

    private void nacitajPoziciu(Hrac hrac, Prikaz prikaz) {
        String nazovPozicie = prikaz.getParameter();
        try {
            this.hra.nacitajPoziciu(nazovPozicie);
            
            this.hra.getHrac().getAktualnaMiestnost().vypisInfoOMiestnosti();
        } catch (SaveNenajdenyException ex) {
            System.out.println("Asi si zadal zly nazov save, taky nepoznam");
        } catch (NuspesnyLoadException ex) {
            System.out.println("Nepodarilo sa nacitat data savu");
        }
    }
}
