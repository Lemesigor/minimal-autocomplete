package org.autocomplete.infrastructure.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection createConnection(String address, String user, String password, String database, String port)
            throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + address + ":" + port + "/" + database, user, password);
    }
}
