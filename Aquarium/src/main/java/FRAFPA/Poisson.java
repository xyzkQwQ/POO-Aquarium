package FRAFPA;

import java.util.Random;

import FRAFPA.enums.Sexe;

public abstract class Poisson extends EtreVivant {
    private String nom;
    private Sexe sexe;

    public Poisson(String nom, Sexe sexe) {
        super(10, 0);
        this.nom = nom;
        this.sexe = sexe;
        
        Random rand = new Random();
        this.setAge(rand.nextInt(10));
    }

    public String getNom() {
        return nom;
    }

    public Sexe getSexe() {
        return sexe;
    }

    @Override
    public String toString() {
        return "Poisson [nom=" + nom + ", sexe=" + sexe + "]";
    }

}