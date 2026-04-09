<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Ticket Management</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f2f2f2; }
        .pagination { margin-top: 20px; }
        .pagination a { padding: 8px 16px; border: 1px solid #ccc; text-decoration: none; color: black; }
        .pagination b { padding: 8px 16px; background: #007bff; color: white; }
        .search-box { margin-bottom: 20px; }
    </style>
</head>
<body>
    <h2>Ticket List</h2>

    <div class="search-box">
        <form action="list-tickets" method="GET">
            <input type="text" name="search" placeholder="Event or Customer name..." value="${search}">
            <button type="submit">Search</button>
            <a href="list-tickets">Clear</a>
        </form>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Event Name</th>
                <th>Customer</th>
                <th>Seat</th>
                <th>Date</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ticket" items="${ticketList}">
                <tr>
                    <td>${ticket.ticketId}</td>
                    <td>${ticket.eventName}</td>
                    <td>${ticket.customerName}</td>
                    <td>${ticket.seatNumber}</td>
                    <td>${ticket.date}</td>
                    <td>
                        <fmt:formatNumber value="${ticket.price}" type="currency" currencySymbol="$"/>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty ticketList}">
                <tr>
                    <td colspan="6" style="text-align:center;">No tickets found.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

    <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <b>${i}</b>
                </c:when>
                <c:otherwise>
                    <a href="?page=${i}&search=${search}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>

    <br>
    <a href="${pageContext.request.contextPath}/tickets">Add New Ticket</a>
</body>
</html>