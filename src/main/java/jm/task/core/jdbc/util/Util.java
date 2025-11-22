package jm.task.core.jdbc.util;

import java.sql.Connection;

public class Util {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {}
        return null;
    }
}
