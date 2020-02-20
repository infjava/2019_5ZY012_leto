/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author janik
 */
public class SpravaStudentovTest {

    @Test
    public void vytvorenieStudenta() {
        Student s = new Student("123", "Fero", "Mrkva");
        Assert.assertEquals("123", s.getOsobneCislo());
        Assert.assertEquals("Fero", s.getMeno());
        Assert.assertEquals("Mrkva", s.getPriezvisko());
    }

    @Test
    public void pridanieStudentaDoSkupiny() {
        Student s = new Student("123", "Fero", "Mrkva");
        Skupina sk = new Skupina("1");
        
        Assert.assertNull(sk.getStudent("123"));
        
        sk.pridajStudenta(s);
        
        Assert.assertSame(s, sk.getStudent("123"));
    }

    @Test
    public void ziskanieStudentovejSkupiny() {
        Student s = new Student("123", "Fero", "Mrkva");
        
        Assert.assertNull(s.getCisloSkupiny());
        
        Skupina sk = new Skupina("1");
        sk.pridajStudenta(s);
        
        Assert.assertEquals("1", s.getCisloSkupiny());
    }
}
