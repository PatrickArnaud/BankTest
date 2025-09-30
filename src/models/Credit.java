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

    public double calculerMensualite() {
        double tauxMensuel = (tauxAnnuel / 100) / 12;
        return (montant * tauxMensuel) / (1 - Math.pow(1 + tauxMensuel, -dureeMois));
    }
}
