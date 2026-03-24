<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Chào mừng, ${userSession}!</h1>
    
    <p>Bạn đã đăng nhập thành công vào hệ thống quản trị.</p>
    <hr>

    <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
</body>
</html>