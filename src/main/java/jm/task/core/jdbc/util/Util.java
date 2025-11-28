package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/users_db";
        String user = "root";
        String pass = "Purple8star#";
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT NOW()")) {

                if (rs.next()) {
                    System.out.println("🕒 Текущее время в БД: " + rs.getString(1));
                }
            }
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД", e);
        }
    }
}
