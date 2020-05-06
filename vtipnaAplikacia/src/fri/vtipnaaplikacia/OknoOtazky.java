/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.vtipnaaplikacia;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        this.okno.setLayout(new BorderLayout());
        this.okno.add(new JLabel("Chceš z predmetu Informatika 2 známku Fx?"), BorderLayout.NORTH);
        this.okno.add(new JButton("áno"), BorderLayout.WEST);
        this.okno.add(new JButton("nie"), BorderLayout.CENTER);
        this.okno.pack();
    }
    

    void zobraz() {
        this.okno.setVisible(true);
    }
    
}
