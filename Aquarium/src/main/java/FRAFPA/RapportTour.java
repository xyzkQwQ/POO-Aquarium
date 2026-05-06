package FRAFPA;

import java.util.ArrayList;
import java.util.List;

public class RapportTour {

    private final int numeroTour;

    private final List<String> evolutions = new ArrayList<>();
    private final List<String> actions = new ArrayList<>();
    private final List<String> reproductions = new ArrayList<>();
    private final List<String> morts = new ArrayList<>();

    public RapportTour(int numeroTour) {
        this.numeroTour = numeroTour;
    }

    public void addEvolution(String msg) {
        evolutions.add(msg);
    }

    public void addAction(String msg) {
        actions.add(msg);
    }

    public void addReproduction(String msg) {
        reproductions.add(msg);
    }

    public void addMort(String msg) {
        morts.add(msg);
    }

    public void afficher(int nbPoissons, int nbAlgues) {

        System.out.println("======================");
        System.out.println("TOUR " + numeroTour);
        System.out.println("======================");

        System.out.println("\n[EVOLUTION]");
        evolutions.forEach(System.out::println);

        System.out.println("\n[ALIMENTATION]");
        actions.forEach(System.out::println);

        System.out.println("\n[REPRODUCTION]");
        reproductions.forEach(System.out::println);

        System.out.println("\n[MORTS]");
        morts.forEach(System.out::println);

        System.out.println("\n[ETAT FINAL]");
        System.out.println("Poissons : " + nbPoissons);
        System.out.println("Algues : " + nbAlgues);
        System.out.println();
    }
}