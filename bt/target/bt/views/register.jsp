<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Đăng ký tài khoản</title>
    </head>

    <body>
        <h2>Đăng ký thành viên</h2>

        <form action="${pageContext.request.contextPath}/register" method="POST">
            <input type="text" name="username" placeholder="Tên đăng nhập" required minlength="3"><br><br>
            <input type="password" name="password" placeholder="Mật khẩu" required minlength="6"><br><br>
            <input type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu" required><br><br>

            <button type="submit">Đăng ký</button>
        </form>

        <p style="color:red">${errorMessage}</p>
        <p style="color:green">${successMessage}</p>

        <p>Đã có tài khoản? <a href="login.jsp">Đăng nhập ngay</a></p>
    </body>

    </html>