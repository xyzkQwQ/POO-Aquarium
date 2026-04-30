package FRAFPA;

import FRAFPA.enums.Sex;

public class Poisson extends EtreVivant {
    private String nom;
    private Sex sex;

    public Poisson(String nom, Sex sex) {
        super(10, 0);
        this.nom = nom;
        this.sex = sex;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Poisson [nom=" + nom + ", sex=" + sex + "]";
    }

}
