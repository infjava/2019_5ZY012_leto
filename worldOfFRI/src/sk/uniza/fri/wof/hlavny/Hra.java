package sk.uniza.fri.wof.hlavny;

import sk.uniza.fri.wof.vynimky.NuspesnyLoadException;
import sk.uniza.fri.wof.vynimky.SaveNenajdenyException;
import java.io.DataInputStream;
import sk.uniza.fri.wof.vynimky.NuspesnySaveException;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import sk.uniza.fri.wof.vynimky.HracZomrelException;
import sk.uniza.fri.wof.prostredie.HraciaPlocha;
import sk.uniza.fri.wof.hlavny.ovladanie.Parser;
import sk.uniza.fri.wof.hlavny.ovladanie.Prikaz;
import sk.uniza.fri.wof.hlavny.ovladanie.VykonavacPrikazov;

/**
 * Trieda Hra je hlavna trieda aplikacie "World of FRI".
 * "World of FRI" je velmi jednoducha textova hra - adventura. 
 * Hrac sa moze prechadzat po niektorych priestoroch - miestnostiach fakulty. 
 * To je v tejto verzii vsetko. Hru treba skutocne zancne rozsirit,
 * aby bola zaujimava.
 * 
 * Ak chcete hrat "World of FRI", vytvorte instanciu triedy Hra (hra) 
 * a poslite jej spravu hraj.
 * 
 * Hra vytvori a inicializuje vsetky potebne objekty:
 * vytvori vsetky miestnosti, vytvori parser a zacne hru. Hra tiez vyhodnocuje
 * a vykonava prikazy, ktore vrati parser.
 * 
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
*/
 
public class Hra  {
    private static final int IDENTIFIKACIA_SAVE = 0xFFAA3387;
    private static final int VERZIA_SAVE = 3;
    
    private Parser parser;
    private final HraciaPlocha hraciaPlocha;
    private final Hrac hrac;
    private final VykonavacPrikazov vykonavacPrikazov;
    
    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() {
        this.hraciaPlocha = new HraciaPlocha();
        this.hrac = new Hrac(this.hraciaPlocha.getPociatocnaMiestnost());
        this.vykonavacPrikazov = new VykonavacPrikazov(this);
        this.parser = new Parser(this.vykonavacPrikazov);
    }

    /**
     *  Hlavna metoda hry.
     *  Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void hraj() {            
        this.vypisPrivitanie();

        // Vstupny bod hlavneho cyklu.
        // Opakovane nacitava prikazy hraca
        // vykonava ich kym hrac nezada prikaz na ukoncenie hry.
                
        boolean jeKoniec;
        
        try {
            do {
                Prikaz prikaz = this.parser.nacitajPrikaz();
                jeKoniec = this.vykonavacPrikazov.vykonajPrikaz(this.hrac, prikaz);
            } while (!jeKoniec);
            
            System.out.println("Maj sa fajn!");
        } catch (HracZomrelException e) {
            System.out.println("Tak sa si mrtvy. Co teraz?");
        }
    }

    /**
     * Vypise privitanie hraca do terminaloveho okna.
     */
    private void vypisPrivitanie() {
        System.out.println();
        System.out.println("Vitaj v hre World of FRI!");
        System.out.println("World of FRI je nova, neuveritelne nudna adventura.");
        System.out.println("Zadaj 'pomoc' ak potrebujes pomoc.");
        System.out.println();
        this.hrac.getAktualnaMiestnost().vypisInfoOMiestnosti();
    }

    public void ulozPoziciu(String nazovPozicie) throws NuspesnySaveException {
        File suborPozicie = new File(nazovPozicie + ".save");
        
        try (DataOutputStream pozicia = new DataOutputStream(new FileOutputStream(suborPozicie))) {
            pozicia.writeInt(Hra.IDENTIFIKACIA_SAVE);
            pozicia.writeInt(Hra.VERZIA_SAVE);
            
            this.hrac.ulozPoziciu(pozicia);
        } catch (IOException ex) {
            throw new NuspesnySaveException();
        }
    }

    public void nacitajPoziciu(String nazovPozicie) throws SaveNenajdenyException, NuspesnyLoadException {
        File suborPozicie = new File(nazovPozicie + ".save");
        
        try (DataInputStream pozicia = new DataInputStream(new FileInputStream(suborPozicie))) {
            if (pozicia.readInt() != Hra.IDENTIFIKACIA_SAVE) {
                throw new NuspesnyLoadException();
            }
            
            final int verziaSave = pozicia.readInt();
            if (verziaSave > Hra.VERZIA_SAVE) {
                throw new NuspesnyLoadException();
            }
            
            this.hrac.nacitajPoziciu(pozicia, this.hraciaPlocha, verziaSave);
        } catch (FileNotFoundException ex) {
            throw new SaveNenajdenyException();
        } catch (IOException ex) {
            throw new NuspesnyLoadException();
        }
    }
}
