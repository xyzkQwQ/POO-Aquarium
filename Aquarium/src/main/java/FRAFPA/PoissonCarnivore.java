package FRAFPA;

import FRAFPA.enums.Sex;

public abstract class PoissonCarnivore extends Poisson {

    public PoissonCarnivore(String nom, Sex sex) {
        super(nom, sex);
        // TODO Auto-generated constructor stub
    }

    public void mangerPoisson(Poisson poissonProie) {

        // 1 - gain de HP pour le poisson prédateur
        int newHPCarnivore = this.getPv() + 5;
        this.setPv(newHPCarnivore);

        // 2 - perte de HP pour la proie
        int newProieHP = poissonProie.getPv() - 4;
        poissonProie.setPv(newProieHP);
        
        System.out.println("predateur " + this.getNom() + " à mangé " + poissonProie.getNom());

        System.err.println( this.getNom() + " à récupéré " + "5 PV, il a maintenant : " + this.getPv() + " PV");
    }
}
