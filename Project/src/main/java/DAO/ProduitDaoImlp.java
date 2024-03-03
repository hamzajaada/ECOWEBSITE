package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImlp implements ProduitDao {

    private static Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    static {
        con = DbConnection.getConnexion();
    }

    @Override
    public List<Produit> getAllProduit() {
        List<Produit> produits = new ArrayList<Produit>();
        String req = "select * from produit";
        try {
            stm = con.prepareStatement(req);
            rs = stm.executeQuery();
            while(rs.next()) {
                Produit p = new Produit(rs.getInt(1),rs.getString(2),rs.getFloat(3));
                produits.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produits;
    }
}
