package com.example.sqldemo.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlConnection extends ConnectFactory {

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
