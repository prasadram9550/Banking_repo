package com.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static Connection con;
    private static Statement stmt;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (con == null || con.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapp", "root", "chintu");
            System.out.println("Connection established.");
        }
        return con;
    }

    public static Statement getStatement() throws SQLException, ClassNotFoundException {
        if (stmt == null || stmt.isClosed()) {
            stmt = getConnection().createStatement();
        }
        return stmt;
    }

    public static void closeConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public static void closeStatement() throws SQLException {
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
    }
}
