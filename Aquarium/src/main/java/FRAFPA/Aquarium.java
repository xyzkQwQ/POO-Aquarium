package FRAFPA;

import java.util.ArrayList;
import java.util.Random;

import FRAFPA.enums.Sex;
import FRAFPA.poissons.Bar;
import FRAFPA.poissons.Carpe;
import FRAFPA.poissons.Merou;
import FRAFPA.poissons.PoissonClown;
import FRAFPA.poissons.Sole;
import FRAFPA.poissons.Thon;

public class Aquarium {
    public static void main(String[] args) {
        ArrayList<Poisson> poissons = new ArrayList<>();
        ArrayList<Algue> algues = new ArrayList<>();

        for (int x = 0; x < 10; x++) {
            Algue algue = new Algue();
            algues.add(algue);
        }

        poissons.add(new Merou("Maxime", Sex.MALE));
        poissons.add(new Bar("Josua", Sex.MALE));
        poissons.add(new Carpe("Esteban", Sex.MALE));
        poissons.add(new Sole("Enzo", Sex.MALE));
        poissons.add(new Thon("Adrien", Sex.MALE));
        poissons.add(new PoissonClown("Kevin", Sex.MALE));

        poissons.add(new Merou("Maxime", Sex.FEMELLE));
        poissons.add(new Bar("Josua", Sex.FEMELLE));
        poissons.add(new Carpe("Esteban", Sex.FEMELLE));
        poissons.add(new Sole("Enzo", Sex.FEMELLE));
        poissons.add(new Thon("Adrien", Sex.FEMELLE));
        poissons.add(new PoissonClown("Kevin", Sex.FEMELLE));

        boolean over = false;
        Poisson poissonPredateurGagnant = null;

        for (int i = 0; i < 5; i++) {

            for (int iPoisson = 0; iPoisson < poissons.size(); iPoisson++) {

                Poisson poissonPredateur = poissons.get(iPoisson); // Tous les poissons
                int poissonPredateurHP = poissonPredateur.getPv();
                poissonPredateurHP = poissonPredateurHP - 1;
                poissonPredateur.setPv(poissonPredateurHP);
                System.out.println(poissonPredateur.getNom() + " a " + poissonPredateur.getPv() + " pv");

                if (poissonPredateur.getPv() <= 5) {

                    // if (poissons.get(iPoisson) instanceof PoissonCarnivore poissonPredateur)
                    if (poissonPredateur instanceof PoissonCarnivore) {

                        Random rand = new Random();
                        int randomPoissonProieIndex = rand.nextInt(poissons.size());
                        Poisson poissonProie = poissons.get(randomPoissonProieIndex);
                        int poissonProieHP = poissonProie.getPv();

                        if (iPoisson == randomPoissonProieIndex) {
                            System.out
                                    .println(poissonPredateur.getNom() + " Impossible de manger "
                                            + poissonProie.getNom());
                        } else {

                            ((PoissonCarnivore) poissonPredateur).mangerPoisson(poissonProie);
                            poissonProieHP = poissonProieHP - 4;
                            poissonProie.setPv(poissonProieHP);
                            System.out.println(poissonProie.getNom() + " a " + poissonProie.getPv() + " pv");

                            if (poissonProieHP <= 0) {
                                System.out.println(poissonProie.getNom() + " est mort");
                                poissons.remove(poissonProie);
                            }
                            // poissons.remove(poissonProie);

                            // if (poissons.size() == 1) {
                            // poissonPredateurGagnant = poissonPredateur;
                            // over = true;
                            // }
                        }

                        System.out.println("************************");

                        // if (algues.get(iAlgue) instanceof PoissonHerbivore poissonPredateur)
                    } else if (poissonPredateur instanceof PoissonHerbivore) {
                        Random rand = new Random();
                        int randomAlgueIndex = rand.nextInt(algues.size());
                        Algue algueProie = algues.get(randomAlgueIndex);
                        ((PoissonHerbivore) poissonPredateur).mangerAlgue(algueProie);
                        System.out.println("=====================");
                    }
                }
            }

            if (over) {
                System.out.println("finito il n'en reste qu'un et c'est : " + poissonPredateurGagnant.getNom());
                break;
            }
            System.out.println("-------------------------------------");
        }
    }
}
