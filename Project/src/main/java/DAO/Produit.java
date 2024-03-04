package DAO;

public class Produit {
    private int id;
    private String nom_produit;
    private float prix;

    public Produit(int id, String nom_produit, float prix) {
        this.id = id;
        this.nom_produit = nom_produit;
        this.prix = prix;
    }

    public Produit() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom_produit='" + nom_produit + '\'' +
                ", prix=" + prix +
                '}';
    }
}
