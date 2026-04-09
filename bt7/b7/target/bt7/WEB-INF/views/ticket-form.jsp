<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <html>

        <head>
            <title>Book New Ticket</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 50px;
                }

                .form-group {
                    margin-bottom: 15px;
                }

                label {
                    display: block;
                    font-weight: bold;
                }

                input {
                    width: 300px;
                    padding: 8px;
                    margin-top: 5px;
                }

                .btn {
                    padding: 10px 20px;
                    background: #28a745;
                    color: white;
                    border: none;
                    cursor: pointer;
                }

                .error {
                    color: red;
                    margin-bottom: 15px;
                }
            </style>
        </head>

        <body>
            <h2>Event Ticket Booking</h2>

            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>

            <form action="${pageContext.request.contextPath}/tickets" method="POST">
                <div class="form-group">
                    <label>Event Name:</label>
                    <input type="text" name="eventName" required>
                </div>
                <div class="form-group">
                    <label>Customer Name:</label>
                    <input type="text" name="customerName" required>
                </div>
                <div class="form-group">
                    <label>Seat Number:</label>
                    <input type="text" name="seatNumber" required>
                </div>
                <div class="form-group">
                    <label>Date (YYYY-MM-DD):</label>
                    <input type="date" name="date" required>
                </div>
                <div class="form-group">
                    <label>Price:</label>
                    <input type="number" step="0.01" name="price" required>
                </div>
                <button type="submit" class="btn">Save Ticket</button>
            </form>

            <br>
            <a href="${pageContext.request.contextPath}/list-tickets">View All Tickets</a>
        </body>

        </html>