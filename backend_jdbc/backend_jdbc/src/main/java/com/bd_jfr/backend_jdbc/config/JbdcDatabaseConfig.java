package com.bd_jfr.backend_jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class JbdcDatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${DB_JRF_USERNAME}")
    private String dbUsername;

    @Value("${DB_JRF_PASSWORD}")
    private String dbPassword;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}




