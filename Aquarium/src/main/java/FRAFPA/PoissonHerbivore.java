package FRAFPA;

import FRAFPA.enums.Sex;

public abstract class PoissonHerbivore extends Poisson {

    public PoissonHerbivore(String nom, Sex sex) {
        super(nom, sex);
        //TODO Auto-generated constructor stub
    }

    public void mangerAlgue(Algue algueProie) {
        System.out.println("predateur " + this.getNom() + " à mangé une algue");
    }

}

