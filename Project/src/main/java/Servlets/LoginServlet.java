package Servlets;

import DAO.User;
import DAO.UserDao;
import DAO.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet( urlPatterns = "/Login" , name = "Login")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        User newUser = new User();
        boolean result = userDao.login(username,password);
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String storedUsername = cookie.getValue();
                    if (storedUsername.equals(username)) {
                        HttpSession session = req.getSession(true);
                        session.setAttribute("nom", username);
                        resp.sendRedirect(req.getContextPath() + "/Home");
                    }
                } else if (cookie.getName().equals("password")) {
                    String storedPassword = cookie.getValue();
                    if (storedPassword.equals(password)) {
                        out.println("Mot de passe correct");

                    }
                }
            }
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
