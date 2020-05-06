/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.vtipnaaplikacia;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author janik
 */
class OknoOtazky {

    private final JFrame okno;
    private final JButton tlacitko1;
    private final JButton tlacitko2;

    public OknoOtazky() {
        this.okno = new JFrame("Otázka");
        this.okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.okno.setLayout(new BorderLayout(10, 10));
        this.okno.add(new JLabel("Chceš z predmetu Informatika 2 známku Fx?"), BorderLayout.NORTH);
        JPanel tlacidla = new JPanel();
        tlacidla.setLayout(new GridLayout(1, 2, 10, 10));
        this.tlacitko1 = this.vytvorTlacitko("áno");
        tlacidla.add(this.tlacitko1);
        this.tlacitko2 = this.vytvorTlacitko("nie");
        tlacidla.add(this.tlacitko2);
        this.okno.add(tlacidla, BorderLayout.CENTER);
        this.okno.pack();
        
        this.tlacitko1.addMouseListener(new PresuvacTlacitok(this.tlacitko1, this.tlacitko2));
        this.tlacitko2.addMouseListener(new PresuvacTlacitok(this.tlacitko1, this.tlacitko2));
    }
    
    private JButton vytvorTlacitko(String text) {
        final JButton tlacitko = new JButton(text);
        tlacitko.addActionListener(new KliknutieNaAno());
        return tlacitko;
    }

    void zobraz() {
        this.okno.setVisible(true);
    }
    
}
