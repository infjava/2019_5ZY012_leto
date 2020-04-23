/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.hlavny;

/**
 *
 * @author janik
 */
public class Verzie {
    /*
    Obsahuje len hlavicku a nazov miestnosti hraca
    */
    public static final int PRVA_VERZIA = 1;
    
    /*
    Pridany inventar hraca
    */
    public static final int UKLADANIE_INVENTARA = 2;
    
    /*
    Ku kazdemu predmetu sa uklada aj jeho stav
    */
    public static final int UKLADANIE_STAVOV_PREDMETOV = 3;
    
    public static final int POSLEDNA = UKLADANIE_STAVOV_PREDMETOV;
}
