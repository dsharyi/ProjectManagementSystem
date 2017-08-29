package com.sharyi_dmytro.practice.module02.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    public Connection getConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost:3306/dev_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        ;
        final String USER = "root";
        final String PASSWORD = "root";


        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

}