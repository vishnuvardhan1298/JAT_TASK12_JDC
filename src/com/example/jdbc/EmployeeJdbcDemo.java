package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeJdbcDemo {
    public static void main(String[] args) {
    	String url = "jdbc:mysql://localhost:3306/your_database";
    	String user = "root";
    	String password = "MyNewPass123!";
        String insertQuery = "INSERT INTO emp_jdbc (empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";
        String selectQuery = "SELECT * FROM emp_jdbc";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // Step 1: Insert values
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);

            Object[][] employees = {
                {101, "Jenny", 25, 10000},
                {102, "Jacky", 30, 20000},
                {103, "Joe", 20, 40000},
                {104, "John", 40, 80000},
                {105, "Shameer", 25, 90000}
            };

            for (Object[] emp : employees) {
                insertStmt.setInt(1, (Integer) emp[0]);
                insertStmt.setString(2, (String) emp[1]);
                insertStmt.setInt(3, (Integer) emp[2]);
                insertStmt.setInt(4, (Integer) emp[3]);
                try {
                    insertStmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Skipping duplicate: " + emp[1]);
                }
            }

            // Step 2: Fetch and print
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            ResultSet rs = selectStmt.executeQuery();

            System.out.println("EmpCode | EmpName | Age | Salary");
            System.out.println("-----------------------------------");

            while (rs.next()) {
                System.out.printf("%d | %s | %d | %d%n",
                        rs.getInt("empcode"),
                        rs.getString("empname"),
                        rs.getInt("empage"),
                        rs.getInt("esalary"));
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}

