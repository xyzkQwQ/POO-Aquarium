package FRAFPA;

import FRAFPA.enums.Sexe;
import FRAFPA.reproduction.ReproductionStrategie;

public abstract class PoissonHerbivore extends Poisson {

    public PoissonHerbivore(String nom, Sexe sexe, ReproductionStrategie strategie) {
        super(nom, sexe, strategie);
    }

    public void mangerAlgue(Algue algueProie) {

        if (algueProie == null)
            return;

        this.setPv(this.getPv() + 3);

        System.out.println("poisson " + this.getNom() + " a mangé une algue");
        System.out.println(this.getNom() + " récupère 3 PV -> " + this.getPv() + " PV");
    }
}