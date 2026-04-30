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

        poissons.add(new Merou("Maxouchoubidou", Sex.FEMELLE));
        poissons.add(new Bar("Josualagrossefolle", Sex.FEMELLE));
        poissons.add(new Carpe("Estebannette", Sex.FEMELLE));
        poissons.add(new Sole("Enzounette", Sex.FEMELLE));
        poissons.add(new Thon("Adrienne", Sex.FEMELLE));
        poissons.add(new PoissonClown("Kevinnette", Sex.FEMELLE));

        boolean over = false;
        Poisson poissonPredateurGagnant = null;

        for (int nombreTour = 1; nombreTour < 30; nombreTour++) {

            System.out.println("Tour : " + nombreTour);
            System.out.println();

            for (int iPoisson = 0; iPoisson < poissons.size(); iPoisson++) {

                Poisson poissonPredateur = poissons.get(iPoisson); // Tous les poissons
                int poissonPredateurHP = poissonPredateur.getPv();

                poissonPredateur.incrementAge(); // ajoute 1 an au poisson
                if (poissonPredateur.getAge() > 20) {
                    System.out
                            .println(poissonPredateur.getNom() + " est mort à l'age de : " + poissonPredateur.getAge());
                    poissons.remove(poissonPredateur);
                    continue;
                }

                System.out.println(
                        poissonPredateur.getNom() + " est entrain de jouer, va perdre 1 PV, il a actuellement : "
                                + poissonPredateur.getPv() + " PV et a : " + poissonPredateur.getAge() + " ans");

                poissonPredateurHP = poissonPredateurHP - 1;
                poissonPredateur.setPv(poissonPredateurHP);

                System.out.println(poissonPredateur.getNom() + " a " + poissonPredateur.getPv() + " PV");

                if (poissonPredateur.getPv() <= 5) {

                    // if (poissons.get(iPoisson) instanceof PoissonCarnivore poissonPredateur)
                    if (poissonPredateur instanceof PoissonCarnivore poissonPredateurCarnivore) {

                        Random rand = new Random();
                        int randomPoissonProieIndex = rand.nextInt(poissons.size());

                        Poisson poissonProie = poissons.get(randomPoissonProieIndex);
                        int poissonProieHP = poissonProie.getPv();

                        if (iPoisson == randomPoissonProieIndex) {
                            System.out
                                    .println(poissonPredateur.getNom() + " Impossible de manger "
                                            + poissonProie.getNom());

                        } else if (poissonPredateur.getClass() == poissonProie.getClass()) {

                            System.out.println("PEUT PAS MANGER UN POISSON DE LA MEME ESPECE");

                        } else {

                            System.err.println(poissonProie.getNom() + " avait " + poissonProie.getPv() + " PV");

                            poissonPredateurCarnivore.mangerPoisson(poissonProie);

                            System.out.println(poissonProie.getNom()
                                    + " s'est fait manger et à perdu 4 PV, il a donc maintenant : "
                                    + poissonProie.getPv() + " PV");

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
