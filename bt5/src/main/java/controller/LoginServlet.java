package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.User;
import util.JPAUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String u = request.getParameter("username");
        String p = request.getParameter("password");

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            User user = em.createQuery("SELECT u FROM User u WHERE u.username = :u AND u.password = :p", User.class)
                    .setParameter("u", u)
                    .setParameter("p", p)
                    .getSingleResult();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("index.jsp");

        } catch (NoResultException e) {
            request.setAttribute("error", "Please enter correct username and password to login");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }
}