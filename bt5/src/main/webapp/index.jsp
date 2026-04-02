<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>

<c:set var="lang" value="${not empty param.lang ? param.lang : (not empty sessionScope.lang ? sessionScope.lang : 'vi')}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

<html>
<head>
    <title>Homepage</title>
</head>
<body>
    <p>
        <a href="?lang=en">English</a> | <a href="?lang=vi">Tiếng Việt</a>
    </p>
    
    <h1><fmt:message key="homepage.welcome" /> ${sessionScope.user.username}</h1>
    
    <ul>
        <li><a href="books?action=list"><fmt:message key="menu.list" /></a></li>
        <li><a href="books?action=prepareInsert"><fmt:message key="menu.insert" /></a></li>
        <li><a href="logout"><fmt:message key="menu.logout" /></a></li>
    </ul>
</body>
</html>