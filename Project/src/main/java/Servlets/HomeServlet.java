package Servlets;

import DAO.Produit;
import DAO.ProduitDao;
import DAO.ProduitDaoImlp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Home" , name = "Home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Produit> produits = ProduitDaoImpl.getAllProduit();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Page de produits</title>");
        out.println("<style>");
        out.println("body {");
        out.println("font-family: Arial, sans-serif;");
        out.println("margin: 0;");
        out.println("padding: 0;");
        out.println("background-color: #f4f4f4;");
        out.println("}");
        out.println(".container {");
        out.println("max-width: 1000px;");
        out.println("margin: 10px auto;");
        out.println("padding: 20px;");
        out.println("background-color: #fff;");
        out.println("border-radius: 10px;");
        out.println("box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
        out.println("}");
        out.println("h1, h3 {");
        out.println("text-align: center;");
        out.println("color: #333;");
        out.println("}");
        out.println("table {");
        out.println("width: 100%;");
        out.println("border-collapse: collapse;");
        out.println("margin-top: 20px;");
        out.println("}");
        out.println("th, td {");
        out.println("padding: 10px;");
        out.println("text-align: left;");
        out.println("border-bottom: 1px solid #ddd;");
        out.println("}");
        out.println("th {");
        out.println("background-color: #f2f2f2;");
        out.println("}");
        out.println("button {");
        out.println("padding: 8px 12px;");
        out.println("background-color: #838386;");
        out.println("color: #fff;");
        out.println("border: none;");
        out.println("border-radius: 5px;");
        out.println("cursor: pointer;");
        out.println("transition: background-color 0.3s ease;");
        out.println("}");
        out.println("button:hover {");
        out.println("background-color: #b7b9b9;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h1>Bienvenue <span id=\"nom_personne\">[Nom Personne]</span>, dans ma servlet d'achat</h1>");
        out.println("<h3>(Liste de disques disponibles à l'achat)</h3>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Nom Disque</th>");
        out.println("<th>Commander</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        out.println("<tr>");
        out.println("<td>Disque CD - AMOR TICINES</td>");
        out.println("<td><button>Commander (15 Euros)</button></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Disque CD - AMOR TICINES</td>");
        out.println("<td><button>Commander (15 Euros)</button></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Disque CD - AMOR TICINES</td>");
        out.println("<td><button>Commander (15 Euros)</button></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Disque CD - AMOR TICINES</td>");
        out.println("<td><button>Commander (15 Euros)</button></td>");
        out.println("</tr>");
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
