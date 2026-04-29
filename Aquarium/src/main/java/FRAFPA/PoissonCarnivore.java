package FRAFPA;

import FRAFPA.enums.Sex;

public abstract class PoissonCarnivore extends Poisson {

    public PoissonCarnivore(String nom, Sex sex) {
        super(nom, sex);
        // TODO Auto-generated constructor stub
    }

    public void mangerPoisson(Poisson poissonProie) {
        System.out.println("predateur " + this.getNom() + " à mangé " + poissonProie.getNom());
    }
}
