package FRAFPA;

import FRAFPA.enums.Sexe;
import FRAFPA.reproduction.ReproductionStrategie;

public abstract class PoissonCarnivore extends Poisson {

    public PoissonCarnivore(String nom, Sexe sexe, ReproductionStrategie strategie) {
        super(nom, sexe, strategie);
    }

    public void mangerPoisson(Poisson poissonProie) {

        if (poissonProie == null || poissonProie == this)
            return;

        // gain PV prédateur
        this.setPv(this.getPv() + 5);

        // perte PV proie
        poissonProie.setPv(poissonProie.getPv() - 4);

        System.out.println("prédateur " + this.getNom() + " a mangé " + poissonProie.getNom());
        System.out.println(this.getNom() + " récupère 5 PV -> " + this.getPv() + " PV");
    }
}