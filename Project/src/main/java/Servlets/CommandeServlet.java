package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import DAO.Commande;
import DAO.CommandeDaoImpl;
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
        String Nom = (String) session.getAttribute("nom");
        System.out.println("Le nom de session ="+Nom);


        // Récupérer les commandes depuis la base de données
        List<Commande> commandes = new CommandeDaoImpl().getCommandesByNomUtilisateur(Nom);
        //System.out.println("la commande :"+commandes.toString());
        //System.out.println(commandes);
        for (Commande c : commandes){
            System.out.println(c.getNom_prod());
            String produitsString =c.getNom_prod();
            String[] produitsArray = produitsString
                    .replaceAll("[\\[\\]]", "")
                    .split(", (?=Produit)");
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Page de choix</title>");

            out.println("<link rel='stylesheet' type='text/css' href='View/Css/Commandestyle.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Nom du produit</th>");
            out.println("<th>Prix du produit</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            for (String produit : produitsArray) {
                String nomProduit = produit.split("nom_produit='")[1].split("'")[0];
                double prixProduit = Double.parseDouble(produit.split("prix=")[1].split("}")[0]);

                out.println("<tr>");
                out.println("<td>" + nomProduit + "</td>");
                out.println("<td>" + prixProduit + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
