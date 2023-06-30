/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

/**
 *
 * @author Urkom
 */
public abstract class Vozilo {

    enum Pogon {
        Dizel,
        Benzin,
        Gas
    }
    protected String proizvodjac;
    protected String model;
    protected Pogon tip_pogona;
    protected double cena;
    protected int snaga;
    protected int godiste;
    protected double predjeni_kilometri;

    public Vozilo(String proizvodjac, String model, Pogon tip_pogona, double cena, int snaga, int godiste, double predjeni_kilometri) {
        this.proizvodjac = proizvodjac;
        this.model = model;
        this.tip_pogona = tip_pogona;
        this.cena = cena;
        this.snaga = snaga;
        this.godiste = godiste;
        this.predjeni_kilometri = predjeni_kilometri;
    }

    public Vozilo() {
    }

}
