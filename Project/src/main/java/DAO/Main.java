package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = DbConnection.getConnexion();

        if (connection != null) {
            System.out.println("Connexion à la base de données établie avec succès!");
            try {
                // Vous pouvez ajouter ici le code pour effectuer des opérations sur la base de données
                // ...
                // Fermer la connexion lorsque vous avez terminé
                connection.close();
                System.out.println("Connexion fermée avec succès!");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion");
                e.printStackTrace();
            }
        } else {
            System.out.println("La connexion à la base de données a échoué.");
        }
    }
}
