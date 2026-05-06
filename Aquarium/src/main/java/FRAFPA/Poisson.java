package FRAFPA;

import java.util.Random;
import FRAFPA.enums.Sexe;
import FRAFPA.reproduction.ReproductionStrategie;

public abstract class Poisson extends EtreVivant {

    private String nom;
    private Sexe sexe;
    private ReproductionStrategie reproductionStrategie;

    public Poisson(String nom, Sexe sexe, ReproductionStrategie strategie) {
        super(10, 0);
        this.nom = nom;
        this.sexe = sexe;
        this.reproductionStrategie = strategie;

        Random rand = new Random();
        this.setAge(rand.nextInt(10));
    }

    public String getNom() {
        return nom;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public ReproductionStrategie getReproductionStrategie() {
        return reproductionStrategie;
    }

    public void setReproductionStrategie(ReproductionStrategie reproductionStrategie) {
        this.reproductionStrategie = reproductionStrategie;
    }

    public boolean peutSeReproduireAvec(Poisson autre) {
        return reproductionStrategie.peutSeReproduireAvec(this, autre);
    }

    public void appliquerReglesAge() {
        reproductionStrategie.majSexeSelonAge(this);
    }

    @Override
    public String toString() {
        return "Poisson [nom=" + nom + ", sexe=" + sexe + "]";
    }
}