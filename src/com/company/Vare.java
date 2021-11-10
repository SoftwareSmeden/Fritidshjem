package com.company;

import java.io.Serializable;

public class Vare implements Serializable {

    //Opg. 1 ---> Opret en klasse VareData, med konstruktør, set og get funktioner.

    private int stk;
    private String navn;
    private double pris;
    private double totalPrisRabat;
    private double totalPris;

    public Vare() {
    }

    public Vare(int stk, String navn, double pris, double totalPrisRabat, double totalPris) {
        this.stk = stk;
        this.navn = navn;
        this.pris = pris;
        this.totalPrisRabat = totalPrisRabat;
        this.totalPris = totalPris;
    }

    @Override
    public String toString() {
        return "Vare{" +
                "stk=" + stk +
                ", navn='" + navn + '\'' +
                ", pris=" + pris +
                ", totalPrisRabat=" + totalPrisRabat +
                ", totalPris=" + totalPris +
                '}';
    }

    //Getters og setters
    public int getStk() {
        return stk;
    }

    public void setStk(int stk) {
        this.stk = stk;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public double getTotalPrisRabat() {
        return totalPrisRabat;
    }

    public void setTotalPrisRabat(double totalPrisRabat) {
        this.totalPrisRabat = totalPrisRabat;
    }

    public void setTotalPris(double totalPris) {
        this.totalPris = totalPris;
    }

    public double getTotalPris() {
        return totalPris;
    }
}
