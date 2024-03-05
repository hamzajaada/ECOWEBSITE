package DAO;

import java.util.List;

public interface CommandeDAO {
    public void AddCommande(Commande c);
    public List<Commande> getCommandesByNomUtilisateur(String nomUtilisateur);
}
