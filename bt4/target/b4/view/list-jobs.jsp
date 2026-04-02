<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Job Listings</title>
    <style>
        body { font-family: Arial; padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 10px; }
        th { background: #2c3e50; color: white; }
        .btn { padding: 5px 10px; text-decoration: none; border-radius: 3px; }
        .btn-add { background: #27ae60; color: white; }
        .btn-del { background: #c0392b; color: white; }
    </style>
</head>
<body>
    <h2>Job Postings List</h2>
    <a href="add-job.jsp" class="btn btn-add">+ Add New Job</a>
    <table>
        <tr>
            <th>ID</th><th>Title</th><th>Company</th><th>Location</th><th>Date</th><th>Actions</th>
        </tr>
        <c:forEach var="job" items="${jobList}">
            <tr>
                <td>${job.id}</td>
                <td><a href="applicants?action=viewByJob&jobId=${job.id}">${job.title}</a></td>
                <td>${job.company}</td>
                <td>${job.location}</td>
                <td>${job.datePosted}</td>
                <td>
                    <a href="jobs?action=delete&id=${job.id}" class="btn btn-del" onclick="return confirm('Delete this job?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="applicants?action=prepareAdd">Register an Applicant</a></p>
</body>
</html>