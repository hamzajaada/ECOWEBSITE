package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/Commande", name = "Commande")
public class CommandeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Récupérer la session ou en créer une nouvelle
        HttpSession session = request.getSession(true);

        // Récupérer le panier depuis la session
        List<String> panier = (List<String>) session.getAttribute("panier");

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Page de commande</title>");
        out.println("<style>");
        // CSS styles here
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h1>Bienvenue dans ma servlet d'achat</h1>");
        out.println("<h2>(Voici ta commande complète)</h2>");

        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Voici les disques commandés</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        // Afficher les articles du panier
        if (panier != null) {
            for (String article : panier) {
                out.println("<tr>");
                out.println("<td>" + article + "</td>");
                out.println("</tr>");
            }
        }
        out.println("</tbody>");
        out.println("</table>");

        // Lien pour effectuer une autre achat
        out.println("<a href=\"Panier\">Effectuer un autre achat</a>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Traitement de la requête POST
    }
}
