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
        Random rand = new Random(); // ✅ un seul Random

        // Création des algues
        for (int x = 0; x < 10; x++) {
            algues.add(new Algue());
        }

        // Création des poissons
        poissons.add(new Merou("Maxime", Sex.MALE));
        poissons.add(new Bar("Josua", Sex.MALE));
        poissons.add(new Carpe("Esteban", Sex.MALE));
        poissons.add(new Sole("Enzo", Sex.MALE));
        poissons.add(new Thon("Adrien", Sex.MALE));
        poissons.add(new PoissonClown("Kevin", Sex.MALE));

        poissons.add(new Merou("Maximette", Sex.FEMELLE));
        poissons.add(new Bar("Josuette", Sex.FEMELLE));
        poissons.add(new Carpe("Estebanette", Sex.FEMELLE));
        poissons.add(new Sole("Enzette", Sex.FEMELLE));
        poissons.add(new Thon("Adrienette", Sex.FEMELLE));
        poissons.add(new PoissonClown("Kevinette", Sex.FEMELLE));

        boolean over = false;
        Poisson poissonPredateurGagnant = null;

        for (int tour = 1; tour < 31; tour++) {

            System.out.println("---------------");
            System.out.println("TOUR NUMÉRO " + tour);
            System.out.println();

            // parcours à l'envers pour éviter les bugs de suppression
            for (int i = poissons.size() - 1; i >= 0; i--) {

                Poisson poissonPredateur = poissons.get(i);

                // incrément de l'âge correctement
                poissonPredateur.setAge(poissonPredateur.getAge() + 1);

                System.out.println(poissonPredateur.getNom() + " a " + poissonPredateur.getAge() + " ANS");

                // mort par vieillesse
                if (poissonPredateur.getAge() >= 20) {
                    System.out.println(poissonPredateur.getNom() + " est mort de vieillesse");
                    poissons.remove(i);
                    continue;
                }

                System.out.println("C'est au tour de " + poissonPredateur.getNom() +
                        ". Il a actuellement : " + poissonPredateur.getPv() + " PV");

                // perte de PV
                poissonPredateur.setPv(poissonPredateur.getPv() - 1);

                System.out.println(poissonPredateur.getNom() + " a maintenant "
                        + poissonPredateur.getPv() + " PV");
                System.out.println();

                // si faible vie → manger
                if (poissonPredateur.getPv() <= 5) {

                    if (poissonPredateur instanceof PoissonCarnivore carnivore) {

                        if (poissons.size() <= 1) continue;

                        int indexProie = rand.nextInt(poissons.size());
                        Poisson poissonProie = poissons.get(indexProie);
                        
                        if (poissonPredateur == poissonProie) {
                            System.out.println("Impossible de se manger soi-même");
                            continue;
                        }

                        if (poissonPredateur.getClass() == poissonProie.getClass()) {
                            System.out.println("Même espèce → impossible de manger");
                            continue;
                        }

                        System.out.println(poissonProie.getNom() + " avait " + poissonProie.getPv() + " PV");

                        carnivore.mangerPoisson(poissonProie);

                        System.out.println(poissonProie.getNom()
                                + " a été attaqué et a maintenant "
                                + poissonProie.getPv() + " PV");

                        // test après attaque
                        if (poissonProie.getPv() <= 0) {
                            System.out.println(poissonProie.getNom() + " est mort");
                            poissons.remove(poissonProie);
                        }

                    } else if (poissonPredateur instanceof PoissonHerbivore herbivore) {

                        if (algues.isEmpty()) continue;

                        int indexAlgue = rand.nextInt(algues.size());
                        Algue algue = algues.get(indexAlgue);

                        herbivore.mangerAlgue(algue);
                    }
                }
            }

            // condition de fin
            if (poissons.size() == 1) {
                poissonPredateurGagnant = poissons.get(0);
                over = true;
            }

            if (over) {
                System.out.println("Fin du jeu ! Le gagnant est : "
                        + poissonPredateurGagnant.getNom());
                break;
            }
        }
    }
}
    