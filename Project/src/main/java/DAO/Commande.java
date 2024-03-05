package DAO;

public class Commande {
    private  int id;
    private String nom_user;
    private  String nom_prod;
    private int prix_total;

    public Commande(int id, String nom_user, String nom_prod, int prix_total) {
        this.id = id;
        this.nom_user = nom_user;
        this.nom_prod = nom_prod;
        this.prix_total = prix_total;
    }

    public Commande() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public int getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(int prix_total) {
        this.prix_total = prix_total;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", nom_user='" + nom_user + '\'' +
                ", nom_prod='" + nom_prod + '\'' +
                ", prix_total=" + prix_total +
                '}';
    }
}
