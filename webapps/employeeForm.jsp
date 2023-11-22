<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Employee</title>
</head>
<body>
    <h2>Add Employee</h2>

    <form action="/EmployeeManagement/EmployeeServlet" method="post">
        <label for="id">ID:</label>
        <input type="text" name="id" required>
        <br>

        <label for="name">Name:</label>
        <input type="text" name="name" required>
        <br>

        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" name="dateOfBirth" required>
        <br>

        <label for="email">Email:</label>
        <input type="text" name="email" required>
        <br>

        <label for="joiningDate">Joining Date:</label>
        <input type="date" name="joiningDate" required>
        <br>

        <label for="employeeType">Employee Type:</label>
        <select name="employeeType" required>
            <option value="S">Staff</option>
            <option value="O">Officer</option>
        </select>
        <br>

        <button type="submit">Add Employee</button>
    </form>
</body>
</html>
