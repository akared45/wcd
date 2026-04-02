<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
    <style>
        .error { color: red; font-weight: bold; }
        .login-box { width: 300px; margin: 50px auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; }
    </style>
</head>
<body>
    <div class="login-box">
        <h2>Login</h2>
        <p class="error">${error}</p>
        
        <form action="login" method="POST">
            Username: <input type="text" name="username" required /><br><br>
            Password: <input type="password" name="password" required /><br><br>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>