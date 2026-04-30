package FRAFPA;

import FRAFPA.enums.Sex;

public abstract class PoissonHerbivore extends Poisson {

    public PoissonHerbivore(String nom, Sex sex) {
        super(nom, sex);
        //TODO Auto-generated constructor stub
    }

    public void mangerAlgue(Algue algueProie) {

        int newHPHerbivore = this.getPv() + 3;
        this.setPv(newHPHerbivore);
        
        System.out.println("predateur " + this.getNom() + " à mangé une algue");
                                
        System.err.println( this.getNom() + " à récupéré " + "3 PV, il a maintenant : " + this.getPv() + " PV");
    }

}

