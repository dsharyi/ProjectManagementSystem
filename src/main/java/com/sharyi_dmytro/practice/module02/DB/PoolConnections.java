package com.sharyi_dmytro.practice.module02.DB;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class PoolConnections {
    private DataSource dataSource;

    public PoolConnections(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
