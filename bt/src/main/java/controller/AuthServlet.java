package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import dao.UserDAO;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if (user != null)
            user = user.trim();
        if (pass != null)
            pass = pass.trim();

        String errorMessage = "";
        if (user == null || user.isEmpty() || pass == null || pass.isEmpty()) {
            errorMessage = "Tài khoản và mật khẩu không được để trống!";
        } else if (pass.length() < 6) {
            errorMessage = "Mật khẩu phải có ít nhất 6 ký tự!";
        } else if (!user.matches("^[a-zA-Z0-9._-]{3,}$")) {
            errorMessage = "Tài khoản không được chứa ký tự đặc biệt!";
        }

        if (!errorMessage.isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }
        UserDAO dao = new UserDAO();

        if (dao.checkLogin(user, pass)) {
            request.getSession().setAttribute("userSession", user);
            System.out.println("Login successful for user: " + user);
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            request.setAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }
}