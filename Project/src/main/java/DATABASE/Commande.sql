CREATE TABLE commande (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_utilisateur INT,
    id_produit INT,
    quantite INT,
    FOREIGN KEY (id_utilisateur) REFERENCES user(id),
    FOREIGN KEY (id_produit) REFERENCES produit(id)
);