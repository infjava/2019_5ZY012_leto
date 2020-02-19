/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janik
 */
public class SpravaStudentov {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student s = new Student("1001120", "Jan", "Janech");
        Skupina sk = new Skupina("5zy012");
        sk.pridajStudenta(s);
        sk.vypisStudentov();
    }
    
}
