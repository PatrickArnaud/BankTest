package services;

import models.Echeance;

import java.util.ArrayList;
import java.util.List;

public class CreditServices {


    public static List<Echeance> genererTableau(double montant, double tauxAnnuel, int dureeMois) {
        double tauxMensuel = tauxAnnuel / 100 / 12;
        double mensualite = (montant * tauxMensuel) / (1 - Math.pow(1 + tauxMensuel, -dureeMois));

        List<Echeance> echeances = new ArrayList<>();
        double capitalRestant = montant;

        for (int mois = 1; mois <= dureeMois; mois++) {
            double interets = capitalRestant * tauxMensuel;
            double capital = mensualite - interets;
            capitalRestant -= capital;

            echeances.add(new Echeance(mois, mensualite, interets, capital, Math.max(capitalRestant, 0)));
        }

        return echeances;
    }

    public static void afficherTableau(List<Echeance> echeances) {
        System.out.printf("%-5s %-12s %-12s %-12s %-12s%n", "Mois", "Mensualité", "Intérêts", "Capital", "Restant");
        for (Echeance e : echeances) {
            System.out.printf("%-5d %-12.2f %-12.2f %-12.2f %-12.2f%n",
                    e.getNumero(), e.getMensualite(), e.getInterets(), e.getCapital(), e.getRestant());
        }
    }

}
