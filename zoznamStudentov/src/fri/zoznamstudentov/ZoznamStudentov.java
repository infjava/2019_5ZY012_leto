/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.zoznamstudentov;

import javax.swing.UIManager;

/**
 *
 * @author janik
 */
public class ZoznamStudentov {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        
        HlavneOkno okno = new HlavneOkno();
        okno.setVisible(true);
    }
    
}
