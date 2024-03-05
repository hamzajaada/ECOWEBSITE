package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDaoImpl implements  CommandeDAO{

    @Override
    public void AddCommande(Commande c)  {
        Connection con = DbConnection.getConnexion();

        if (con != null) {
            String query = "INSERT INTO Commande (Nom_utilisateur, Nom_produits,Prix_total) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, c.getNom_user());
                preparedStatement.setString(2,c.getNom_prod() );
                preparedStatement.setInt(3,c.getPrix_total() );
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Commande added successfully!");
                } else {
                    System.out.println("Failed to add Commande.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed.");
        }
    }
    @Override
    public List<Commande> getCommandesByNomUtilisateur(String nomUtilisateur) {
        List<Commande> commandes = new ArrayList<>();
        Connection con = DbConnection.getConnexion();

        if (con != null) {
            String query = "SELECT * FROM Commande WHERE Nom_utilisateur = ?";

            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, nomUtilisateur);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nomUser = resultSet.getString("Nom_utilisateur");
                    String nomProd = resultSet.getString("Nom_produits");
                    int prixTotal = resultSet.getInt("Prix_total");

                    Commande commande = new Commande(id, nomUser, nomProd, prixTotal);
                    commandes.add(commande);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection to database failed.");
        }

        return commandes;
    }
}
