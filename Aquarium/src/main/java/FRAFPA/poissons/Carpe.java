package FRAFPA.poissons;

import FRAFPA.PoissonHerbivore;
import FRAFPA.enums.Sexe;
import FRAFPA.reproduction.Monosexue;

public class Carpe extends PoissonHerbivore {

    public Carpe(String nom, Sexe sexe) {
        super(nom, sexe, new Monosexue());
    }
}