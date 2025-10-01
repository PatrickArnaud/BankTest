package dao;

import models.Client;
import utils.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void ajouter(Client client) {
        String sql = "INSERT INTO clients(nom) VALUES (?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client trouverParNom(String nom) {
        String sql = "SELECT * FROM clients WHERE LOWER(nom) = LOWER(?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(rs.getInt("id"), rs.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> lister() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clients.add(new Client(rs.getInt("id"), rs.getString("nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
