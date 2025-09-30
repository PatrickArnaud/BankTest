package views;
import models.Credit;
import java.util.Scanner;

public class ConsoleUi {
    private Scanner scanner = new Scanner(System.in);

    public void demarrer() {
        boolean quitter = false;
        while (!quitter) {
            System.out.println("=== Banque App ===");
            System.out.println("1. Calculer un crédit");
            System.out.println("2. Quitter");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // consommer le retour à la ligne

            switch (choix) {
                case 1:
                    calculerCredit();
                    break;
                case 2:
                    quitter = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
    private void calculerCredit() {
        System.out.print("Montant du crédit (€) : ");
        double montant = scanner.nextDouble();

        System.out.print("Taux annuel (%) : ");
        double taux = scanner.nextDouble();

        System.out.print("Durée (mois) : ");
        int duree = scanner.nextInt();

        Credit credit = new Credit(montant, taux, duree);
        System.out.printf("Mensualité : %.2f €%n", credit.calculerMensualite());
    }

}
