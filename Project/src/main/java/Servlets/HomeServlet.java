package Servlets;

import DAO.Produit;
import DAO.ProduitDao;
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

        String Nom = (String) session.getAttribute("nom");

        // Get products from the database
        List<Produit> produits = new ProduitDaoImlp().getAllProduit();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Page de produits</title>");
        out.println("<style>");
        // CSS styles here
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h1>Bienvenue <span id=\"nom_personne\">" + Nom +"</span>, dans ma servlet d'achat</h1>");
        out.println("<h3>(Liste de disques disponibles à l'achat)</h3>");
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
            out.println("<tr>");
            out.println("<td>" + produit.getNom_produit() + "</td>");
            out.println("<td>" + produit.getPrix() + " Euros</td>");
            out.println("<td><button>Commander (" + produit.getPrix() + " Euros)</button></td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer le paramètre identifiant le produit à commander
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Récupérer la session ou en créer une nouvelle
        HttpSession session = request.getSession(true);

        // Récupérer le panier depuis la session ou initialiser un nouveau panier
        List<Integer> panier = (List<Integer>) session.getAttribute("panier");
        if (panier == null) {
            panier = new ArrayList<>();
        }

        // Ajouter le produit au panier
        panier.add(productId);

        // Mettre à jour la session avec le panier mis à jour
        session.setAttribute("panier", panier);

        // Rediriger l'utilisateur vers la servlet PanierServlet
        response.sendRedirect(request.getContextPath() + "/Panier");
    }
}
