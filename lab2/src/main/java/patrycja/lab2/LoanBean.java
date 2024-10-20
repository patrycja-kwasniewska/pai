/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author patrycjakwasniewska
 */


package patrycja.lab2;

import java.io.Serializable;

public class LoanBean implements Serializable {
    private double kwota;
    private double oprocentowanie;
    private int liczbaRat;

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public double getOprocentowanie() {
        return oprocentowanie;
    }

    public void setOprocentowanie(double oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }

    public int getLiczbaRat() {
        return liczbaRat;
    }

    public void setLiczbaRat(int liczbaRat) {
        this.liczbaRat = liczbaRat;
    }

    public double getRata() {
        double p = oprocentowanie / 12 / 100;  // Miesięczne oprocentowanie
        return (kwota * p) / (1 - Math.pow(1 + p, -liczbaRat));
    }
}
