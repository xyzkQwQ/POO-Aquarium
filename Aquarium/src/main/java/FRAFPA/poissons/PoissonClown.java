package FRAFPA.poissons;

import FRAFPA.PoissonCarnivore;
import FRAFPA.enums.Sexe;
import FRAFPA.reproduction.HermaphroditeOpportuniste;

public class PoissonClown extends PoissonCarnivore {

    public PoissonClown(String nom, Sexe sexe) {
        super(nom, sexe, new HermaphroditeOpportuniste());
    }
}