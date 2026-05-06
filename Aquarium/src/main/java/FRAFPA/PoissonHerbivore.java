package FRAFPA;

import FRAFPA.enums.Sexe;

public abstract class PoissonHerbivore extends Poisson {

    public PoissonHerbivore(String nom, Sexe sexe) {
        super(nom, sexe);
        // TODO Auto-generated constructor stub
    }

    public void mangerAlgue(Algue algueProie) {

        int newHPHerbivore = this.getPv() + 3;
        this.setPv(newHPHerbivore);

        System.out.println("predateur " + this.getNom() + " à mangé une algue");
        System.err.println(this.getNom() + " à récupéré " + "3 PV, il a maintenant : " + this.getPv() + " PV");
        System.out.println();
    }

}
