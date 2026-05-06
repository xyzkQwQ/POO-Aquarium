package FRAFPA.poissons;

import FRAFPA.PoissonHerbivore;
import FRAFPA.enums.Sexe;
import FRAFPA.reproduction.HermaphroditeAge;

public class Bar extends PoissonHerbivore {

    public Bar(String nom, Sexe sexe) {
        super(nom, sexe, new HermaphroditeAge());
    }
}