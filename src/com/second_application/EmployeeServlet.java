// EmployeeServlet.java
package com.second_application;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;

@WebServlet("/Employee")
public class EmployeeServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date dateOfBirth = parseDate(request.getParameter("dateOfBirth"));
        String email = request.getParameter("email");
        LocalDate joiningDate = LocalDate.parse(request.getParameter("joiningDate"));

        EmployeeType employeeType = getEmployeeType(request.getParameter("employeeType"));

        Employee employee = new Employee(id, name, dateOfBirth, email, joiningDate);
        calculateAndDisplayLeaveDetails(response.getWriter(), employee, employeeType);
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private EmployeeType getEmployeeType(String typeStr) {
        if ("s".equals(typeStr)) {
            return EmployeeType.STAFF;
        } else if ("o".equals(typeStr)) {
            return EmployeeType.OFFICER;
        } else {
            return EmployeeType.STAFF;
        }
    }

    private void calculateAndDisplayLeaveDetails(PrintWriter out, Employee employee, EmployeeType employeeType) {
        int joiningYear = employee.getJoiningDate().getYear();
        int totalDaysInYear = Year.of(joiningYear).length();

        LocalDate endDateOfYear = LocalDate.of(joiningYear, 12, 31);
        long totalDays = employee.getJoiningDate().datesUntil(endDateOfYear.plusDays(1)).count();

        int vacationLeave = 0;
        int sickLeave = 0;

        if (employeeType == EmployeeType.STAFF) {
            vacationLeave = (int) Math.ceil((totalDays * 10.0) / totalDaysInYear);
            sickLeave = (int) Math.ceil((totalDays * 7.0) / totalDaysInYear);
        } else if (employeeType == EmployeeType.OFFICER) {
            vacationLeave = (int) Math.ceil((totalDays * 15.0) / totalDaysInYear);
            sickLeave = (int) Math.ceil((totalDays * 10.0) / totalDaysInYear);
        }

        out.println("\nDetails for Employee ID: " + employee.getId());
        out.println("Name: " + escapeHtml(employee.getName()));
        out.println("Date of Birth: " + new SimpleDateFormat("dd/MM/yyyy").format(employee.getDateOfBirth()));
        out.println("Email: " + escapeHtml(employee.getEmail()));
        out.println("Joining Date: " + employee.getJoiningDate());
        out.println("Employee Type: " + employeeType);
        out.println("Vacation Leave: " + vacationLeave);
        out.println("Sick Leave: " + sickLeave);
        out.println("------------------------");
    }

    // HTML escape method (a simple version)
    private String escapeHtml(String input) {
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
