package FRAFPA.reproduction;

import FRAFPA.Poisson;
import FRAFPA.enums.Sexe;

public class HermaphroditeAge implements ReproductionStrategie {

    @Override
    public void majSexeSelonAge(Poisson poisson) {
        if (poisson.getAge() < 10) {
            poisson.setSexe(Sexe.MALE);
        } else {
            poisson.setSexe(Sexe.FEMELLE);
        }
    }

    @Override
    public boolean peutSeReproduireAvec(Poisson p1, Poisson p2) {
        return p1.getSexe() != p2.getSexe();
    }
}