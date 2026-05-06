package FRAFPA.poissons;

import FRAFPA.PoissonCarnivore;
import FRAFPA.enums.Sexe;
import FRAFPA.reproduction.Monosexue;

public class Thon extends PoissonCarnivore {

    public Thon(String nom, Sexe sexe) {
        super(nom, sexe, new Monosexue());
    }
}