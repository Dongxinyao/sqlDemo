package com.example.sqldemo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class KingbaseConnection extends ConnectFactory {

    Connection connection;

    @Override
    Connection getConnection() {
        if (connection == null) {
            synchronized (this) {
                if (connection == null) {
                    try {
                        Class.forName(driverName);
                        connection = DriverManager.getConnection(url, userName, password);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }


}
