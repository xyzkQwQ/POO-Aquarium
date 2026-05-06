package FRAFPA.poissons;

import FRAFPA.PoissonCarnivore;
import FRAFPA.enums.Sexe;
import FRAFPA.reproduction.HermaphroditeAge;

public class Merou extends PoissonCarnivore {

    public Merou(String nom, Sexe sexe) {
        super(nom, sexe, new HermaphroditeAge());
    }
}