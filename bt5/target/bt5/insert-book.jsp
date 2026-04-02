<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
        <html>

        <head>
            <title>Insert New Book</title>
        </head>

        <body>
            <h2>Insert New Book</h2>
            <form action="books" method="POST">
                Title: <input type="text" name="title" required /><br><br>

                Category:
                <select name="category_id" required>
                    <option value="">-- Select Category --</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.id}">${cat.name}</option>
                    </c:forEach>
                </select><br><br>

                Price: <input type="number" step="0.01" name="price" required /><br><br>

                <button type="submit">Insert</button>
                <button type="reset">Clear</button>
            </form>
            <br>
            <a href="books?action=list">Back to List</a>
        </body>

        </html>