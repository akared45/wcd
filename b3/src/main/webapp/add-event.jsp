<%@ page contentType="text/html; charset=UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Add New Event</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                padding: 20px;
                background-color: #f4f7f6;
            }

            .container {
                width: 400px;
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                margin: auto;
            }

            h2 {
                text-align: center;
                color: #333;
            }

            label {
                display: block;
                margin-top: 10px;
                font-weight: bold;
            }

            input {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
            }

            button {
                width: 100%;
                padding: 12px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                margin-top: 20px;
                cursor: pointer;
                font-size: 16px;
            }

            button:hover {
                background-color: #218838;
            }

            .error {
                color: red;
                font-size: 14px;
                text-align: center;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <h2>Add New Event</h2>
            <% if(request.getParameter("error") !=null) { %>
                <p class="error">Invalid input! Name >= 5 chars, Seats > 0.</p>
                <% } %>
                    <form action="events" method="POST">
                        <label>Name:</label>
                        <input type="text" name="name" minlength="5" required placeholder="Event Name">

                        <label>Date:</label>
                        <input type="date" name="date" required>

                        <label>Venue:</label>
                        <input type="text" name="venue" required placeholder="Location">

                        <label>Seats Available:</label>
                        <input type="number" name="seats_available" min="1" required>

                        <button type="submit">Add Event</button>
                    </form>
                    <p style="text-align: center;"><a href="events">View Event List</a></p>
        </div>
    </body>

    </html>