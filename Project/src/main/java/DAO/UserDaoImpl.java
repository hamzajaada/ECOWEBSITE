package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) {
        Connection con = DbConnection.getConnexion();

        if (con != null) {
            String query = "INSERT INTO user (nom, password) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, user.getNom());
                preparedStatement.setString(2, user.getPassword());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User added successfully!");
                } else {
                    System.out.println("Failed to add user.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed.");
        }
    }

    @Override
    public boolean login(String username, String password) {
        Connection con = DbConnection.getConnexion();
        if (con != null) {
            System.out.println("connected!!!");
        } else {
            System.out.println("erreur de connection");

            return false;
        }

        String req = "SELECT * FROM USER WHERE nom = ? AND password = ?";
        try (PreparedStatement stm = con.prepareStatement(req)) {
            stm.setString(1, username);
            stm.setString(2, password);
            try (ResultSet rs = stm.executeQuery()) {
                System.out.println("test");
                return rs.next(); // Si le résultat de la requête n'est pas vide, les informations de connexion sont correctes.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
