package views;
import dao.ClientDAO;
import dao.CreditDAO;
import models.Client;
import models.Credit;

import java.util.List;
import java.util.Scanner;

public class ConsoleUi {
    private Scanner scanner = new Scanner(System.in);
    private ClientDAO clientDAO = new ClientDAO();
    private CreditDAO creditDAO = new CreditDAO();

    public void demarrer() {
        boolean quitter = false;
        while (!quitter) {
            System.out.println("\n=== Banque App ===");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Ajouter un crédit à un client");
            System.out.println("3. Afficher les crédits d’un client");
            System.out.println("4. Lister tous les clients");
            System.out.println("5. Quitter");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterClient();
                case 2 -> ajouterCredit();
                case 3 -> afficherCredits();
                case 4 -> listerClients();
                case 5 -> {
                    quitter = true;
                    System.out.println("Au revoir !");
                }
                default -> System.out.println("Choix invalide !");
            }
        }
    }

    private void ajouterClient() {
        System.out.print("Nom du client : ");
        String nom = scanner.nextLine();
        clientDAO.ajouter(new Client(nom));
        System.out.println("Client ajouté !");
    }

    private void ajouterCredit() {
        System.out.print("Nom du client : ");
        String nom = scanner.nextLine();
        Client client = clientDAO.trouverParNom(nom);

        if (client == null) {
            System.out.println("Client non trouvé !");
            return;
        }

        System.out.print("Montant du crédit (€) : ");
        double montant = scanner.nextDouble();
        System.out.print("Taux annuel (%) : ");
        double taux = scanner.nextDouble();
        System.out.print("Durée (mois) : ");
        int duree = scanner.nextInt();
        scanner.nextLine();

        Credit credit = new Credit(montant, taux, duree);
        creditDAO.ajouter(credit, client.getId());
        System.out.println("Crédit ajouté !");
    }

    private void afficherCredits() {
        System.out.print("Nom du client : ");
        String nom = scanner.nextLine();
        Client client = clientDAO.trouverParNom(nom);

        if (client == null) {
            System.out.println("Client non trouvé !");
            return;
        }

        List<Credit> credits = creditDAO.listerParClient(client.getId());
        if (credits.isEmpty()) {
            System.out.println("Aucun crédit pour ce client.");
            return;
        }

        System.out.println("Crédits de " + client.getNom() + " :");
        for (Credit c : credits) {
            System.out.println(" - " + c);
        }
    }

    private void listerClients() {
        List<Client> clients = clientDAO.lister();
        if (clients.isEmpty()) {
            System.out.println("Aucun client.");
            return;
        }
        System.out.println("Liste des clients :");
        for (Client c : clients) {
            System.out.println(" - " + c);
        }}
}
