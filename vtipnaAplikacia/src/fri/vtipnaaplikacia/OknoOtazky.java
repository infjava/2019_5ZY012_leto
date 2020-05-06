/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.vtipnaaplikacia;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        this.tlacitko1 = this.vytvorTlacitko("áno", true);
        tlacidla.add(this.tlacitko1);
        this.tlacitko2 = this.vytvorTlacitko("nie", false);
        tlacidla.add(this.tlacitko2);
        this.okno.add(tlacidla, BorderLayout.CENTER);
        this.okno.pack();
    }
    
    private JButton vytvorTlacitko(String text, boolean maFocus) {
        final JButton tlacitko = new JButton(text);
        
        tlacitko.addActionListener(
            (ActionEvent ae) -> {
                JOptionPane.showMessageDialog(null, "To som si teda o tebe nemyslel :(");
                System.exit(0);
            }
        );
        
        tlacitko.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    if (((JButton)me.getComponent()).getText().equals("áno")) {
                        return;
                    }

                    switch (OknoOtazky.this.tlacitko1.getText()) {
                        case "áno":
                            OknoOtazky.this.tlacitko1.setText("nie");
                            OknoOtazky.this.tlacitko1.setFocusable(false);
                            OknoOtazky.this.tlacitko2.setText("áno");
                            OknoOtazky.this.tlacitko2.setFocusable(true);
                            OknoOtazky.this.tlacitko2.grabFocus();
                            break;
                        case "nie":
                            OknoOtazky.this.tlacitko1.setText("áno");
                            OknoOtazky.this.tlacitko1.setFocusable(true);
                            OknoOtazky.this.tlacitko1.grabFocus();
                            OknoOtazky.this.tlacitko2.setText("nie");
                            OknoOtazky.this.tlacitko2.setFocusable(false);
                            break;
                        default:
                            throw new AssertionError();
                    }
                }
            }
        );
        
        if (maFocus) {
            tlacitko.setFocusable(true);
            tlacitko.grabFocus();
        } else {
            tlacitko.setFocusable(false);
        }
        
        return tlacitko;
    }

    void zobraz() {
        this.okno.setVisible(true);
    }
    
}
