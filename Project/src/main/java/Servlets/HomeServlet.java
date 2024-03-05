package Servlets;

import DAO.Produit;
import DAO.ProduitDaoImlp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/Home" , name = "Home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);

        List<Produit> panier = (List<Produit>) session.getAttribute("panier");

        String Nom = (String) session.getAttribute("nom");

        // Get products from the database
        List<Produit> produits = new ProduitDaoImlp().getAllProduit();
        System.out.println("liste de produit : " + produits.toString());

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Page de produits</title>");
        out.println("<link rel='stylesheet' type='text/css' href='View/Css/Homestyle.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h1>Bienvenue <span id=\"nom_personne\">" + Nom +"</span>, dans ma servlet d'achat</h1>");
        out.println("<a href=\"/Project_war_exploded/Panier\">Voir votre carte</a>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Nom Disque</th>");
        out.println("<th>Prix</th>");
        out.println("<th>Commander</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        // Iterate over products and generate table rows
        for (Produit produit : produits) {
            if(panier != null && isInPanier(produit, panier)) {
                // Produit déjà dans le panier
                out.println("<tr>");
                out.println("<td>" + produit.getNom_produit() + "</td>");
                out.println("<td>" + produit.getPrix() + " Euros</td>");
                out.println("<td>");
                out.println("<form action=\"\" method=\"delete\">");
                out.println("<input type=\"hidden\" name=\"id_prod\" value=\"" + produit.getId() + "\">");
                out.println("<input type=\"hidden\" name=\"nom_prod\" value=\"" + produit.getNom_produit() + "\">");
                out.println("<input type=\"hidden\" name=\"prix_prod\" value=\"" + produit.getPrix() + "\">");
                out.println("<input type=\"submit\" value=\"Enlever du panier\">");
                out.println("</form></td>");
                out.println("</tr>");
            } else {
                // Produit non encore ajouté au panier
                out.println("<tr>");
                out.println("<td>" + produit.getNom_produit() + "</td>");
                out.println("<td>" + produit.getPrix() + " Euros</td>");
                out.println("<td>");
                out.println("<form action=\"\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"id_prod\" value=\"" + produit.getId() + "\">");
                out.println("<input type=\"hidden\" name=\"nom_prod\" value=\"" + produit.getNom_produit() + "\">");
                out.println("<input type=\"hidden\" name=\"prix_prod\" value=\"" + produit.getPrix() + "\">");
                out.println("<input type=\"submit\" value=\"Ajouter au panier\">");
                out.println("</form></td>");
                out.println("</tr>");
            }
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id_prod");
        int productId = (idString != null && !idString.isEmpty()) ? Integer.parseInt(idString) : 0;
        String productNom = request.getParameter("nom_prod");
        String prixString = request.getParameter("prix_prod");
        float productPrix = (prixString != null && !prixString.isEmpty()) ? Float.parseFloat(prixString) : 0.0f;

        Produit p = new Produit(productId, productNom, productPrix);
        HttpSession session = request.getSession(true);

        List<Produit> panier = (List<Produit>) session.getAttribute("panier");
        if (panier == null) {
            panier = new ArrayList<>();
        }
        panier.add(p);

        session.setAttribute("panier", panier);
        System.out.println("Le panier : "+panier.toString());

        response.sendRedirect(request.getContextPath() + "/Home");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id_prod");
        int Id = (idString != null && !idString.isEmpty()) ? Integer.parseInt(idString) : 0;
        String Nom = req.getParameter("nom_prod");
        String prixString = req.getParameter("prix_prod");
        float Prix = (prixString != null && !prixString.isEmpty()) ? Float.parseFloat(prixString) : 0.0f;

        Produit p = new Produit(Id, Nom, Prix);

        HttpSession session = req.getSession(true);

        List<Produit> panier = (List<Produit>) session.getAttribute("panier");
        if (panier == null) {
            panier = new ArrayList<>();
        }

        panier.remove(p);

        session.setAttribute("panier", panier);
        System.out.println("Le panier : "+panier.toString());

        resp.sendRedirect(req.getContextPath() + "/Home");
    }


    private boolean isInPanier(Produit produit, List<Produit> panier) {
        if (panier != null && !panier.isEmpty()) {
            for (Produit p : panier) {
                if (p.getId() == produit.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    // test to hamza
}