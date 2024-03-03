package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
