package FRAFPA;

import java.util.Random;

import FRAFPA.enums.Sex;

public class Poisson extends EtreVivant {
    private String nom;
    private Sex sex;

    public Poisson(String nom, Sex sex) {
        super(10, 0);
        this.nom = nom;
        this.sex = sex;

           Random rand = new Random();
        this.setAge(rand.nextInt(10));
    }
    

    public String getNom() {
        return nom;
    }

    public Sex getSexe() {
        return sex;
    }

    @Override
    public String toString() {
        return "Poisson [nom=" + nom + ", sex=" + sex + "]";
    }

}
