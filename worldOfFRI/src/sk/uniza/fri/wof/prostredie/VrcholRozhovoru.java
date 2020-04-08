/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;

/**
 *
 * @author janik
 */
public class VrcholRozhovoru {

    private final String replikaNpc;
    private final HranaRozhovoru[] hrany;

    public VrcholRozhovoru(String replikaNpc, HranaRozhovoru... hrany) {
        this.replikaNpc = replikaNpc;
        this.hrany = hrany;
    }

    public String getReplikaNpc() {
        return this.replikaNpc;
    }

    public void vypis() {
        if (this.replikaNpc != null) {
            System.out.println(this.replikaNpc);
        }
        
        int cislo = 1;
        for (HranaRozhovoru hrana : this.hrany) {
            System.out.format("%d) %s%n", cislo, hrana.getReplikaHraca());
            cislo++;
        }
        
        if (this.hrany.length > 0) {
            System.out.format("Vyber si[1-%d]: ", this.hrany.length);
        }
    }

    VrcholRozhovoru getNasledujuci(int moznost) {
        return this.hrany[moznost - 1].getCielovyVrchol();
    }

    boolean jeKoniecRozhovoru() {
        return this.hrany.length == 0;
    }
}
