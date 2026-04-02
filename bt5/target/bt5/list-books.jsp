<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
        <html>

        <head>
            <title>Book List</title>
            <style>
                table {
                    width: 100%;
                    border-collapse: collapse;
                }

                th,
                td {
                    border: 1px solid #ddd;
                    padding: 10px;
                    text-align: left;
                }

                th {
                    background-color: #007bff;
                    color: white;
                }
            </style>
            <script>
                function confirmDelete(title) {
                    return confirm("Do you really want to delete the book: " + title + "?");
                }
            </script>
        </head>

        <body>
            <h2>Book List</h2>
            <a href="index.jsp">Back to Home</a> | <a href="books?action=prepareInsert">Add New Book</a>
            <br><br>
            <table>
                <tr>
                    <th>Book Id</th>
                    <th>Title</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="b" items="${books}">
                    <tr>
                        <td>${b.id}</td>
                        <td>${b.title}</td>
                        <td>${b.category.name}</td>
                        <td>${b.price}</td>
                        <td>
                            <a href="books?action=delete&id=${b.id}" onclick="return confirmDelete('${b.title}')"
                                style="color: red;">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </body>

        </html>