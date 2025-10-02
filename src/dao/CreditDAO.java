package dao;

import models.Credit;
import models.Echeance;
import services.CreditServices;
import utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditDAO {


    public void ajouter(Credit credit, int clientId) {
        String sql = "INSERT INTO credits(montant, taux_annuel, duree_mois, client_id) VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, credit.getMontant());
            stmt.setDouble(2, credit.getTauxAnnuel());
            stmt.setInt(3, credit.getDureeMois());
            stmt.setInt(4, clientId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int creditId = rs.getInt("id");

                List<Echeance> echeances = CreditServices.genererTableau(
                        credit.getMontant(),
                        credit.getTauxAnnuel(),
                        credit.getDureeMois()
                );
                new EcheanceDAO().ajouterEcheances(echeances, creditId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Credit> listerParClient(int clientId) {
        List<Credit> credits = new ArrayList<>();
        String sql = "SELECT * FROM credits WHERE client_id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                credits.add(new Credit(
                        rs.getDouble("montant"),
                        rs.getDouble("taux_annuel"),
                        rs.getInt("duree_mois")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credits;
    }
}
