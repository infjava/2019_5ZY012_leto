/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.zoznamstudentov;

import java.awt.event.KeyEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author janik
 */
public class HlavneOkno extends javax.swing.JFrame {

    private final DefaultListModel<Student> zoznam;

    /**
     * Creates new form HlavneOkno
     */
    public HlavneOkno() {
        this.zoznam = new DefaultListModel<Student>();
        this.initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        lstZoznamStudentov = new javax.swing.JList<>();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        txtMeno = new javax.swing.JTextField();
        txtPriezvisko = new javax.swing.JTextField();
        btnPridaj = new javax.swing.JButton();
        btnOprav = new javax.swing.JButton();
        btnOdstan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout());

        lstZoznamStudentov.setModel(this.zoznam);
        lstZoznamStudentov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lstZoznamStudentovKeyPressed(evt);
            }
        });
        lstZoznamStudentov.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstZoznamStudentovValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstZoznamStudentov);

        getContentPane().add(jScrollPane1);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        txtMeno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                studentFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                studentFocusLost(evt);
            }
        });
        jPanel1.add(txtMeno);

        txtPriezvisko.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                studentFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                studentFocusLost(evt);
            }
        });
        jPanel1.add(txtPriezvisko);

        btnPridaj.setText("Pridaj");
        btnPridaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPridajActionPerformed(evt);
            }
        });
        jPanel1.add(btnPridaj);

        btnOprav.setText("Oprav");
        btnOprav.setEnabled(false);
        btnOprav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpravActionPerformed(evt);
            }
        });
        jPanel1.add(btnOprav);

        btnOdstan.setText("Odstráň");
        btnOdstan.setEnabled(false);
        btnOdstan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOdstanActionPerformed(evt);
            }
        });
        jPanel1.add(btnOdstan);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPridajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPridajActionPerformed
        if (this.txtMeno.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Musis zadat meno");
            this.txtMeno.grabFocus();
            return;
        }
        if (this.txtPriezvisko.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Musis zadat priezvisko");
            this.txtPriezvisko.grabFocus();
            return;
        }
        
        Student student = new Student(this.txtMeno.getText(), this.txtPriezvisko.getText());
        this.zoznam.addElement(student);
        
        this.txtMeno.setText("");
        this.txtPriezvisko.setText("");
        this.txtMeno.grabFocus();
    }//GEN-LAST:event_btnPridajActionPerformed

    private void btnOdstanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOdstanActionPerformed
        int idx = this.lstZoznamStudentov.getSelectedIndex();
        this.zoznam.removeElementAt(idx);
    }//GEN-LAST:event_btnOdstanActionPerformed

    private void lstZoznamStudentovValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstZoznamStudentovValueChanged
        Student student = this.lstZoznamStudentov.getSelectedValue();
        if (student == null) {
            this.btnOdstan.setEnabled(false);
            this.btnOprav.setEnabled(false);
            this.txtMeno.setText("");
            this.txtPriezvisko.setText("");
        } else {
            this.btnOdstan.setEnabled(true);
            this.btnOprav.setEnabled(true);
            
            this.txtMeno.setText(student.getMeno());
            this.txtPriezvisko.setText(student.getPriezvisko());
        }
    }//GEN-LAST:event_lstZoznamStudentovValueChanged

    private void btnOpravActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpravActionPerformed
        int idx = this.lstZoznamStudentov.getSelectedIndex();
        
        if (this.txtMeno.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Musis zadat meno");
            this.txtMeno.grabFocus();
            return;
        }
        if (this.txtPriezvisko.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Musis zadat priezvisko");
            this.txtPriezvisko.grabFocus();
            return;
        }
        
        Student student = new Student(this.txtMeno.getText(), this.txtPriezvisko.getText());
        this.zoznam.setElementAt(student, idx);
    }//GEN-LAST:event_btnOpravActionPerformed

    private void studentFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentFocusGained
        int idx = this.lstZoznamStudentov.getSelectedIndex();
        if (idx == -1) {
            this.getRootPane().setDefaultButton(this.btnPridaj);
        } else {
            this.getRootPane().setDefaultButton(this.btnOprav);
        }
    }//GEN-LAST:event_studentFocusGained

    private void studentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentFocusLost
        this.getRootPane().setDefaultButton(null);
    }//GEN-LAST:event_studentFocusLost

    private void lstZoznamStudentovKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstZoznamStudentovKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int idx = this.lstZoznamStudentov.getSelectedIndex();
            if (idx != -1) {
                this.zoznam.removeElementAt(idx);
            }
        }
    }//GEN-LAST:event_lstZoznamStudentovKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOdstan;
    private javax.swing.JButton btnOprav;
    private javax.swing.JButton btnPridaj;
    private javax.swing.JList<Student> lstZoznamStudentov;
    private javax.swing.JTextField txtMeno;
    private javax.swing.JTextField txtPriezvisko;
    // End of variables declaration//GEN-END:variables
}
