package sk.uniza.fri.wof.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sk.uniza.fri.wof.hlavny.ovladanie.VykonavacPrikazov;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author janik
 */
public class NazvyPrikazovTest {

    private VykonavacPrikazov nazvyPri1;

    @Before
    public void setUp() {
        this.nazvyPri1 = new VykonavacPrikazov();
    }

    @Test
    public void jePrikaz() {
        Assert.assertTrue(this.nazvyPri1.jePrikaz("chod"));
        Assert.assertTrue(this.nazvyPri1.jePrikaz("ukonci"));
        Assert.assertTrue(this.nazvyPri1.jePrikaz("pomoc"));
        Assert.assertFalse(this.nazvyPri1.jePrikaz("ukaz"));
    }
}
