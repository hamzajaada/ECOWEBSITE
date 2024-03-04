package Servlets;

import DAO.User;
import DAO.UserDao;
import DAO.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
        Cookie usernameCookie = new Cookie("username", username);
        resp.addCookie(usernameCookie);
        Cookie passwordCookie = new Cookie("password", password);
        resp.addCookie(passwordCookie);
        resp.sendRedirect(req.getContextPath() + "/");

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
