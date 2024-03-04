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
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background-color: #f4f4f4;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("}");

        out.println(".container {");
        out.println("    width: 80%;");
        out.println("    margin: 0 auto;");
        out.println("    padding: 20px;");
        out.println("    background-color: #fff;");
        out.println("    border-radius: 5px;");
        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
        out.println("}");

        out.println("h1, h3 {");
        out.println("    color: #333;");
        out.println("}");

        out.println("table {");
        out.println("    width: 100%;");
        out.println("    border-collapse: collapse;");
        out.println("    margin-top: 20px;");
        out.println("}");

        out.println("th, td {");
        out.println("    padding: 10px;");
        out.println("    text-align: left;");
        out.println("    border-bottom: 1px solid #ddd;");
        out.println("}");

        out.println("th {");
        out.println("    background-color: #f2f2f2;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println("input[type=\"submit\"] {");
        out.println("    padding: 8px 15px;");
        out.println("    background-color: #4CAF50;");
        out.println("    color: white;");
        out.println("    border: none;");
        out.println("    border-radius: 4px;");
        out.println("    cursor: pointer;");
        out.println("    transition: background-color 0.3s;");
        out.println("}");

        out.println("input[type=\"submit\"]:hover {");
        out.println("    background-color: #45a049;");
        out.println("}");

        out.println("a {");
        out.println("    display: inline-block;");
        out.println("    margin-top: 10px;");
        out.println("    text-decoration: none;");
        out.println("    color: #007bff;");
        out.println("}");

        out.println("a:hover {");
        out.println("    text-decoration: underline;");
        out.println("}");
        out.println("</style>");
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
            out.println("<tr>");
            out.println("<td>" + produit.getNom_produit() + "</td>");
            out.println("<td>" + produit.getPrix() + " Euros</td>");
            out.println("<td>");
            out.println("<form action=\"CommandeProduit\" method=\"post\">"); // <-- Action updated here
            out.println("<input type=\"hidden\" name=\"id_prod\" value=\"" + produit.getId() + "\">");
            out.println("<input type=\"submit\" value=\"Ajouter au panier\">");
            out.println("</form></td>");

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
        int productId = Integer.parseInt(request.getParameter("id_prod"));
        System.out.println("produit id : " + productId);

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
        System.out.println("Le panier : "+panier.toString());

        // Rediriger l'utilisateur vers la servlet PanierServlet
        response.sendRedirect(request.getContextPath() + "/Home");
    }
}
