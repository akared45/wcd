<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

    <!DOCTYPE html>
    <html>

    <body>
        <form action="${pageContext.request.contextPath}/auth" method="POST">
            <input type="text" name="username" placeholder="Username" required minlength="3" maxlength="20">
            <input type="password" name="password" placeholder="Password" required minlength="6">
            <button type="submit">Login</button>
        </form>
        <p style="color:red">${errorMessage}</p>
    </body>

    </html>