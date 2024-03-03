package Servlets;

import DAO.User;
import DAO.UserDao;
import DAO.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( urlPatterns = "/Register" , name = "Register")
public class RegisterServlet extends HttpServlet {
    public RegisterServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confpass = req.getParameter("confirmPassword");
        out.println("le username est ="+username);
        out.println("le password est ="+password);
        out.println("le confpass est ="+confpass);
        UserDao userDao = new UserDaoImpl();
        User newUser = new User();
        newUser.setNom(username);
        newUser.setPassword(password);

        // Vérifier si les mots de passe correspondent
        if (password.equals(confpass)) {
            userDao.addUser(newUser);
            resp.getWriter().println("Utilisateur ajouté avec succès!");
        } else {
            resp.getWriter().println("Les mots de passe ne correspondent pas.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
