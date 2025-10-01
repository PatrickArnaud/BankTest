package models;

public class Credit {
    private double montant;
    private double tauxAnnuel;
    private int dureeMois;

    public Credit(double montant, double tauxAnnuel, int dureeMois) {
        this.montant = montant;
        this.tauxAnnuel = tauxAnnuel;
        this.dureeMois = dureeMois;
    }

    public double getMontant() { return montant; }
    public double getTauxAnnuel() { return tauxAnnuel; }
    public int getDureeMois() { return dureeMois; }

    public double calculerMensualite() {
        double tauxMensuel = (tauxAnnuel / 100) / 12;
        return (montant * tauxMensuel) /
                (1 - Math.pow(1 + tauxMensuel, -dureeMois));
    }

    @Override
    public String toString() {
        return String.format("Crédit %.2f €, %.2f%% sur %d mois -> Mensualité %.2f €",
                montant, tauxAnnuel, dureeMois, calculerMensualite());
    }
}