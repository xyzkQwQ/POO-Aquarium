package FRAFPA;

import java.util.ArrayList;
import java.util.Random;

import FRAFPA.enums.Sexe;
import FRAFPA.poissons.*;

public class Aquarium {

    public static void main(String[] args) {

        ArrayList<Poisson> poissons = new ArrayList<>();
        ArrayList<Algue> algues = new ArrayList<>();
        Random rand = new Random();

        // Initialisation algues
        for (int x = 0; x < 10; x++) {
            algues.add(new Algue());
        }

        // Initialisation poissons
        poissons.add(new Merou("Maxime", Sexe.MALE));
        poissons.add(new Bar("Josua", Sexe.MALE));
        poissons.add(new Carpe("Esteban", Sexe.MALE));
        poissons.add(new Sole("Enzo", Sexe.MALE));
        poissons.add(new Thon("Adrien", Sexe.MALE));
        poissons.add(new PoissonClown("Kevin", Sexe.MALE));

        poissons.add(new Merou("Maximette", Sexe.FEMELLE));
        poissons.add(new Bar("Josuette", Sexe.FEMELLE));
        poissons.add(new Carpe("Estebanette", Sexe.FEMELLE));
        poissons.add(new Sole("Enzette", Sexe.FEMELLE));
        poissons.add(new Thon("Adrienette", Sexe.FEMELLE));
        poissons.add(new PoissonClown("Kevinette", Sexe.FEMELLE));

        boolean over = false;
        Poisson gagnant = null;

        // Boucle principale
        for (int tour = 1; tour <= 15; tour++) {

            RapportTour rapport = new RapportTour(tour);

            // for (Poisson poisson : poissons) {

            // }

            for (int i = poissons.size() - 1; i >= 0; i--) {

                Poisson poisson = poissons.get(i);
                boolean aDejaReproduit = false;

                // Vieillissement
                // poisson.veillir();
                int ancienAge = poisson.getAge();
                poisson.setAge(poisson.getAge() + 1);
                rapport.addEvolution(poisson.getNom() + " (" + poisson.getType() + ") âge "
                        + ancienAge + " -> " + poisson.getAge());

                if (poisson.getAge() >= 20) {

                    rapport.addMort(poisson.getNom() + " (" + poisson.getType() + ") est mort de vieillesse");
                    poissons.remove(i);
                    continue;
                }

                // Perte PV
                poisson.setPv(poisson.getPv() - 1);

                if (poisson.getPv() <= 0) {
                    rapport.addMort(poisson.getNom() + " (" + poisson.getType() + ") est mort (PV = 0)");
                    poissons.remove(i);
                    continue;
                }

                // ACTIONS (faim)
                if (poisson.getPv() <= 5) {

                    if (poisson instanceof PoissonCarnivore carnivore) {

                        if (poissons.size() > 1) {

                            Poisson proie = poissons.get(rand.nextInt(poissons.size()));

                            if (poisson != proie && poisson.getClass() != proie.getClass()) {

                                carnivore.mangerPoisson(proie);
                                rapport.addAction(poisson.getNom() + " (" + poisson.getType() + ") mange "
                                        + proie.getNom() + " (" + proie.getType() + ")");
                                if (proie.getPv() <= 0) {
                                    rapport.addMort(poisson.getNom() + " (" + poisson.getType() + " est mangé");
                                    poissons.remove(proie);
                                }
                            }
                        }

                    } else if (poisson instanceof PoissonHerbivore herbivore) {

                        if (!algues.isEmpty()) {

                            Algue algue = algues.get(rand.nextInt(algues.size()));

                            herbivore.mangerAlgue(algue);
                            rapport.addAction(poisson.getNom() + " (" + poisson.getType() + ") mange une algue");

                        }
                    }

                } else {

                    // REPRODUCTION
                    if (!aDejaReproduit && poissons.size() > 1) {

                        Poisson partenaire = poissons.get(rand.nextInt(poissons.size()));

                        if (poisson != partenaire
                                && poisson.getClass() == partenaire.getClass()
                                && poisson.getSexe() != partenaire.getSexe()) {

                            Sexe sexeBebe = rand.nextBoolean() ? Sexe.MALE : Sexe.FEMELLE;

                            Poisson bebe = creerBebeSimple(poisson, sexeBebe);

                            if (bebe != null) {
                                poissons.add(bebe);
                                rapport.addReproduction(poisson.getNom() + " (" + poisson.getType() + ") + "
                                        + partenaire.getNom() + " (" + partenaire.getType() + ") -> "
                                        + bebe.getNom() + " (" + bebe.getType() + ")");
                                aDejaReproduit = true;
                            }
                        }
                    }
                }
            }

            // Algues (simple reproduction)
            for (int i = 0; i < algues.size(); i++) {

                Algue a = algues.get(i);

                if (algues.size() < 50 && a.getPv() >= 10) {

                    int pv = a.getPv() / 2;
                    a.setPv(pv);

                    Algue bebe = new Algue();
                    bebe.setPv(pv);

                    algues.add(bebe);

                    rapport.addAction("Une algue se divise");
                }
            }

            // Affichage propre du tour
            rapport.afficher(poissons.size(), algues.size());

            // Condition fin
            if (poissons.size() == 1) {
                gagnant = poissons.get(0);
                over = true;
            }

            if (over) {
                System.out.println("Fin du jeu. Gagnant : " + gagnant.getNom());
                break;
            }
        }
    }

    private static Poisson creerBebeSimple(Poisson parent, Sexe sexe) {

        String nom = parent.getNom() + "Junior";

        if (parent instanceof Merou)
            return new Merou(nom, sexe);
        if (parent instanceof Bar)
            return new Bar(nom, sexe);
        if (parent instanceof Carpe)
            return new Carpe(nom, sexe);
        if (parent instanceof Sole)
            return new Sole(nom, sexe);
        if (parent instanceof Thon)
            return new Thon(nom, sexe);
        if (parent instanceof PoissonClown)
            return new PoissonClown(nom, sexe);
    
        return null;
    }
}