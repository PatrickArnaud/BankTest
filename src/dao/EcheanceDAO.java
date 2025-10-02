package dao;

import models.Echeance;
import utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EcheanceDAO {

    public void ajouterEcheances(List<Echeance> echeances, int creditId) {
        String sql = "INSERT INTO echeances(credit_id, numero, mensualite, interets, capital, restant) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Echeance e : echeances) {
                stmt.setInt(1, creditId);
                stmt.setInt(2, e.getNumero());
                stmt.setDouble(3, e.getMensualite());
                stmt.setDouble(4, e.getInterets());
                stmt.setDouble(5, e.getCapital());
                stmt.setDouble(6, e.getRestant());
                stmt.addBatch();
            }

            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
