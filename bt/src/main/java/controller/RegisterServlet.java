package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username").trim();
        String pass = request.getParameter("password").trim();
        String confirm = request.getParameter("confirmPassword").trim();

        if (!pass.equals(confirm)) {
            request.setAttribute("errorMessage", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }

        UserDAO dao = new UserDAO();
        if (dao.registerUser(user, pass)) {
            request.setAttribute("successMessage", "Đăng ký thành công! Mời bạn đăng nhập.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Tên đăng nhập đã tồn tại hoặc lỗi hệ thống!");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        }
    }
}