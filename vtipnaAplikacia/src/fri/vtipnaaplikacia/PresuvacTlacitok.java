/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.vtipnaaplikacia;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author janik
 */
public class PresuvacTlacitok extends MouseAdapter {

    private final JButton tlacitko1;
    private final JButton tlacitko2;

    PresuvacTlacitok(JButton tlacitko1, JButton tlacitko2) {
        this.tlacitko1 = tlacitko1;
        this.tlacitko2 = tlacitko2;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (((JButton)me.getComponent()).getText().equals("áno")) {
            return;
        }
        
        String zaloha = this.tlacitko1.getText();
        this.tlacitko1.setText(this.tlacitko2.getText());
        this.tlacitko2.setText(zaloha);
    }
    
}
