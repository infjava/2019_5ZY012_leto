/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.vtipnaaplikacia;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author janik
 */
class OknoOtazky {

    private final JFrame okno;

    public OknoOtazky() {
        this.okno = new JFrame("Otázka");
        this.okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.okno.setSize(200, 100);
    }
    

    void zobraz() {
        this.okno.setVisible(true);
    }
    
}
