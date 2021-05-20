package org.hillel.service.notInUse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DB_USER_NAME = "postgres";
    private static final String DB_USER_PASS = "1234";
    private static final String DB_HOST = "127.0.0.1:5432";
    private static final String DB_DATABASE = "Ticket";

    static {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getMySQlConnection() throws SQLException {
        String url = "jdbc:postgresql://" + DB_HOST + "/" + DB_DATABASE;
        return DriverManager.getConnection(url, DB_USER_NAME, DB_USER_PASS);
    }
}
