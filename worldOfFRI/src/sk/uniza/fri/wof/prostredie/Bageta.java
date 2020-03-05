/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prostredie;


public class Bageta implements IPredmet {

    @Override
    public String getNazov() {
        return "bageta";
    }

    @Override
    public void pouziSa() {
        System.out.println("Chrum chrum, mnam mnam");
    }
    
}
