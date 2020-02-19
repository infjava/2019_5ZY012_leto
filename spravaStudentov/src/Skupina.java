
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janik
 */
public class Skupina {

    private final String cislo;
    private final ArrayList<Student> zoznam;

    public Skupina(String cislo) {
        this.cislo = cislo;
        this.zoznam = new ArrayList<Student>();
    }

    public String getCislo() {
        return this.cislo;
    }

    void pridajStudenta(Student novy) {
        this.zoznam.add(novy);
    }

    void vypisStudentov() {
        for (Student student : this.zoznam) {
            System.out.println(student);
        }
    }
}
