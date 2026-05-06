package FRAFPA;

import java.util.ArrayList;
import java.util.Random;

import FRAFPA.enums.Sexe;
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
        for (int tour = 1; tour < 11; tour++) {

            System.out.println("---------------");
            System.out.println("TOUR NUMERO " + tour);

            for (int i = poissons.size() - 1; i >= 0; i--) {

                Poisson poisson = poissons.get(i);
                boolean aDejaReproduit = false;

                // Vieillissement
                poisson.setAge(poisson.getAge() + 1);
                System.out.println(poisson.getNom() + " vieillit -> âge : " + poisson.getAge());

                if (poisson.getAge() >= 20) {

                    System.out.println(poisson.getNom() + " meurt de vieillesse");
                    poissons.remove(i);

                } else {

                    // Perte PV
                    poisson.setPv(poisson.getPv() - 1);
                    System.out.println(poisson.getNom() + " perd 1 PV -> PV : " + poisson.getPv());

                    if (poisson.getPv() <= 0) {

                        System.out.println(poisson.getNom() + " meurt (plus de PV)");
                        poissons.remove(i);

                    } else {

                        // ACTION
                        if (poisson.getPv() <= 5) {

                            System.out.println(poisson.getNom() + " a faim");

                            if (poisson instanceof PoissonCarnivore carnivore) {

                                if (poissons.size() > 1) {

                                    Poisson proie = poissons.get(rand.nextInt(poissons.size()));

                                    if (poisson == proie) {

                                        System.out.println("Action impossible : lui-même");

                                    } else if (poisson.getClass() == proie.getClass()) {

                                        System.out.println("Même espèce, il ne mange pas " + proie.getNom());

                                    } else {

                                        System.out.println(poisson.getNom() + " attaque " + proie.getNom());

                                        carnivore.mangerPoisson(proie);

                                        System.out.println(proie.getNom() + " PV : " + proie.getPv());

                                        if (proie.getPv() <= 0) {
                                            System.out.println(proie.getNom() + " est mort");
                                            poissons.remove(proie);
                                        }
                                    }
                                }

                            } else if (poisson instanceof PoissonHerbivore herbivore) {

                                if (!algues.isEmpty()) {

                                    Algue algue = algues.get(rand.nextInt(algues.size()));

                                    System.out.println(poisson.getNom() + " mange une algue");

                                    herbivore.mangerAlgue(algue);

                                    System.out.println("Algue PV : " + algue.getPv());
                                }
                            }

                        } else {

                            // REPRODUCTION
                            if (!aDejaReproduit && poissons.size() > 1) {

                                Poisson partenaire = poissons.get(rand.nextInt(poissons.size()));

                                if (poisson == partenaire) {

                                    System.out.println("Reproduction impossible : seul");

                                } else if (poisson.getClass() != partenaire.getClass()) {

                                    System.out.println("Pas même espèce avec " + partenaire.getNom());

                                } else if (poisson.getSexe() == partenaire.getSexe()) {

                                    System.out.println("Même sexe avec " + partenaire.getNom());

                                } else {

                                    System.out.println(poisson.getNom() + " s'accouple avec " + partenaire.getNom());

                                    Sexe sexeBebe = rand.nextBoolean() ? Sexe.MALE : Sexe.FEMELLE;

                                    Poisson bebe = creerBebeSimple(poisson, sexeBebe);

                                    if (bebe != null) {
                                        poissons.add(bebe);
                                        System.out.println("Naissance de " + bebe.getNom());
                                        aDejaReproduit = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Reproduction algues
            for (int i = 0; i < algues.size(); i++) {

                Algue a = algues.get(i);

                if (algues.size() < 50 && a.getPv() >= 10) {

                    int pv = a.getPv() / 2;
                    a.setPv(pv);

                    Algue bebe = new Algue();
                    bebe.setPv(pv);

                    algues.add(bebe);

                    System.out.println("Une algue se divise");
                }
            }

            // Résumé du tour
            System.out.println("Résumé : " + poissons.size() + " poissons / " + algues.size() + " algues");

            // Fin
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

        if (parent instanceof Merou) return new Merou(nom, sexe);
        if (parent instanceof Bar) return new Bar(nom, sexe);
        if (parent instanceof Carpe) return new Carpe(nom, sexe);
        if (parent instanceof Sole) return new Sole(nom, sexe);
        if (parent instanceof Thon) return new Thon(nom, sexe);
        if (parent instanceof PoissonClown) return new PoissonClown(nom, sexe);

        return null;
    }
}