package FRAFPA.poissons;

import FRAFPA.PoissonHerbivore;
import FRAFPA.enums.Sexe;
import FRAFPA.reproduction.HermaphroditeOpportuniste;

public class Sole extends PoissonHerbivore {

    public Sole(String nom, Sexe sexe) {
        super(nom, sexe, new HermaphroditeOpportuniste());
    }
}