<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="EmployeeType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Details</title>
</head>
<body>
    <h2>Employee Details</h2>

    <%
        Employee employee = (Employee) request.getAttribute("employee");
        EmployeeType employeeType = (EmployeeType) request.getAttribute("employeeType");
        int vacationLeave = (int) request.getAttribute("vacationLeave");
        int sickLeave = (int) request.getAttribute("sickLeave");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    %>

    <p>ID: <%= employee.getId() %></p>
    <p>Name: <%= employee.getName() %></p>
    <p>Date of Birth: <%= dateFormat.format(employee.getDateOfBirth()) %></p>
    <p>Email: <%= employee.getEmail() %></p>
    <p>Joining Date: <%= dateFormat.format(employee.getJoiningDate()) %></p>
    <p>Employee Type: <%= employeeType.name() %></p>
    <p>Vacation Leave: <%= vacationLeave %></p>
    <p>Sick Leave: <%= sickLeave %></p>
</body>
</html>
