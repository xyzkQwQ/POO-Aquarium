package FRAFPA.reproduction;

import FRAFPA.Poisson;
import FRAFPA.enums.Sexe;

public class HermaphroditeOpportuniste implements ReproductionStrategie {

    @Override
    public void majSexeSelonAge(Poisson poisson) {
        // rien basé sur l'âge
    }

    @Override
    public boolean peutSeReproduireAvec(Poisson p1, Poisson p2) {

        if (p1.getSexe() == p2.getSexe()) {
            // changement de sexe du premier poisson
            if (p1.getSexe() == Sexe.MALE) {
                p1.setSexe(Sexe.FEMELLE);
            } else {
                p1.setSexe(Sexe.MALE);
            }
        }

        return p1.getSexe() != p2.getSexe();
    }
}
