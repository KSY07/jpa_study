package com.example.demo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.demo.connection.ConnectionConst.*;

public class DBConnectionUtil {

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USERNAME, Password);

    }
}
