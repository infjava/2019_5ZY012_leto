/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

import java.util.ArrayList;
import sk.uniza.fri.wof.prostredie.npccka.HranaRozhovoru;
import sk.uniza.fri.wof.prostredie.npccka.NpcSRozhovorom;
import sk.uniza.fri.wof.prostredie.npccka.Obchodnik;
import sk.uniza.fri.wof.prostredie.npccka.VrcholRozhovoru;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

/**
 *
 * @author janik
 */
class DefiniciaNpc {

    private final TypDefinicieNpc typNpc;
    private final String nazov;
    private final DefiniciaPolozky polozky;

    DefiniciaNpc(TypDefinicieNpc typNpc, String nazov) {
        this.typNpc = typNpc;
        this.nazov = nazov;
        this.polozky = new DefiniciaPolozky(null);
        
    }

    void pridajPolozku(int urovenZoznamu, String polozka) {
        this.polozky.pridaj(urovenZoznamu, new DefiniciaPolozky(polozka));
    }

    void vytvor(Miestnost miestnost, HraciaPlocha hraciaPlocha) {
        switch (this.typNpc) {
            case OBCHODNIK:
                IPredmet[] tovar = new IPredmet[this.polozky.pocetPodpoloziek()];
                for (int i = 0; i < tovar.length; i++) {
                    tovar[i] = hraciaPlocha.vytvorPredmet(this.polozky.getPodpolozka(i).getPolozka());
                }
                //miestnost.postavNpc(new Obchodnik(this.nazov, tovar.toArray(new IPredmet[0])));
                miestnost.postavNpc(new Obchodnik(this.nazov, tovar));
                break;
            case ROZHOVOR:
                miestnost.postavNpc(new NpcSRozhovorom(this.nazov, this.vytvorVrcholRozhovoru(true, this.polozky)));
                break;
            default:
                throw new AssertionError();
        }
    }

    private VrcholRozhovoru vytvorVrcholRozhovoru(boolean jePrvy, DefiniciaPolozky polozky) {
        int prvyIndex;
        String replikaNpc;
        
        if (jePrvy) {
            prvyIndex = 0;
            replikaNpc = null;
        } else {
            prvyIndex = 1;
            replikaNpc = polozky.getPodpolozka(0).getPolozka();
        }
        
        int pocet = polozky.pocetPodpoloziek() - prvyIndex;
        HranaRozhovoru[] hrany = new HranaRozhovoru[pocet];
        
        for (int i = 0; i < pocet; i++) {
            DefiniciaPolozky polozka = polozky.getPodpolozka(i + prvyIndex);
            hrany[i] = new HranaRozhovoru(polozka.getPolozka(), this.vytvorVrcholRozhovoru(false, polozka));
        }
        
        return new VrcholRozhovoru(replikaNpc, hrany);
    }
    
}
