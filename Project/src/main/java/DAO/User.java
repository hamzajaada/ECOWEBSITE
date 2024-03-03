package DAO;

public class User {
    private int id;
    private String nom;
    private String password;

    public User(int id, String nom, String password) {
        this.id = id;
        this.nom = nom;
        this.password = password;
    }

    public User() {
       super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPassword() {
        return password;
    }
}
