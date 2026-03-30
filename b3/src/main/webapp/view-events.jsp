<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Event List</title>
            <style>
                body {
                    font-family: sans-serif;
                    padding: 20px;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }

                th,
                td {
                    border: 1px solid #ddd;
                    padding: 12px;
                    text-align: left;
                }

                th {
                    background-color: #007bff;
                    color: white;
                }

                tr:nth-child(even) {
                    background-color: #f2f2f2;
                }

                .btn-del {
                    color: #dc3545;
                    text-decoration: none;
                    font-weight: bold;
                }

                .btn-add {
                    display: inline-block;
                    padding: 10px 15px;
                    background: #007bff;
                    color: white;
                    text-decoration: none;
                    border-radius: 5px;
                }
            </style>
        </head>

        <body>
            <h2>Event List</h2>
            <a href="add-event.jsp" class="btn-add">+ Add New Event</a>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Venue</th>
                        <th>Seats</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="event" items="${eventList}">
                        <tr>
                            <td>${event.id}</td>
                            <td><a href="attendees?action=viewByEvent&eventId=${event.id}">${event.name}</a></td>
                            <td>${event.date}</td>
                            <td>${event.venue}</td>
                            <td>${event.seatsAvailable}</td>
                            <td>
                                <a href="events?action=delete&id=${event.id}" class="btn-del"
                                    onclick="return confirm('Are you sure?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <a href="attendees?action=prepareAdd">Go to Attendee Management</a>
        </body>

        </html>