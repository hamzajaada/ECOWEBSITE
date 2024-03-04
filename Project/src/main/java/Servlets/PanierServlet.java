package Servlets;

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

@WebServlet(urlPatterns = "/Panier", name = "Panier")
public class PanierServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Récupérer la session ou en créer une nouvelle
        HttpSession session = request.getSession(true);

        // Récupérer le panier depuis la session ou initialiser un nouveau panier
        List<String> panier = (List<String>) session.getAttribute("panier");
        if (panier == null) {
            panier = new ArrayList<>();
            // Ajouter des articles au panier (pour l'exemple)
            panier.add("Disque CD - AMOR TICINES 15 Euros");
            panier.add("Disque CD - AMOR TICINES 15 Euros");
            panier.add("Disque CD - AMOR TICINES 15 Euros");
            panier.add("Disque CD - AMOR TICINES 15 Euros");
        }

        // Stocker le panier mis à jour dans la session
        session.setAttribute("panier", panier);

        String Nom = (String) session.getAttribute("nom");

        // Calculer le nombre d'articles dans le panier
        int nombreArticles = panier.size();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Page de choix</title>");

        out.println("<link rel='stylesheet' type='text/css' href='View/Css/Panierstyle.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h1>Bienvenue <span id=\"nom_personne\">" + Nom +"</span>, dans ma servlet d'achat</h1>");
        out.println("<h2>(Votre panier)</h2>");

        // Table of ordered discs
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Informations du disque commandé</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        // Afficher les articles du panier
        for (String article : panier) {
            out.println("<tr>");
            out.println("<td>" + article + "</td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");

        out.println("<h2>Votre panier contient : " + nombreArticles + " disques</h2>");

        // Links for further actions

        out.println("<div class ='div'>");

        // Balise <a>
        out.println("<a class='custom-link' href='#'>Vous pouvez commander un autre disque</a>");

        // Formulaire
        out.println("<form action='Commande' method='post'>");
        out.println("   <button type='submit'>Enregistrer votre commande</button>");
        out.println("</form>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
