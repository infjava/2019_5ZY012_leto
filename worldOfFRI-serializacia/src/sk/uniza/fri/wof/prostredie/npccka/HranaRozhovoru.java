/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie.npccka;

import java.io.Serializable;

/**
 *
 * @author janik
 */
public class HranaRozhovoru implements Serializable {

    private final String replikaHraca;
    private final VrcholRozhovoru cielovyVrchol;

    public HranaRozhovoru(String replikaHraca, VrcholRozhovoru cielovyVrchol) {
        this.replikaHraca = replikaHraca;
        this.cielovyVrchol = cielovyVrchol;
        
    }

    public String getReplikaHraca() {
        return this.replikaHraca;
    }

    public VrcholRozhovoru getCielovyVrchol() {
        return this.cielovyVrchol;
    }
    
    
}
