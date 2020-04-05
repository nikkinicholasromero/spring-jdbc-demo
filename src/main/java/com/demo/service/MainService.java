package com.demo.service;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class MainService {
    private static final String URL = "jdbc:mysql://localhost/demo";
    private static final String USER = "admin";
    private static final String PASS = "pass123";

    public String run() {
        StringBuilder stringBuilder = new StringBuilder();

        try (Connection connection = getConnectionFromDataSource();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES")) {
            while (resultSet.next()) {
                String row =
                        resultSet.getInt("ID") + " - " +
                                resultSet.getString("FIRST_NAME") + " - " +
                                resultSet.getString("MIDDLE_NAME") + " - " +
                                resultSet.getString("LAST_NAME") + " - " +
                                resultSet.getDouble("SALARY") + " - " +
                                resultSet.getDate("SOME_DATE").toLocalDate() + " - " +
                                resultSet.getTime("SOME_TIME").toLocalTime() + " - " +
                                resultSet.getTimestamp("SOME_DATETIME").toLocalDateTime() + " - " +
                                resultSet.getBoolean("ACTIVE")  + "\n";
                stringBuilder.append(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private Connection getConnectionFromDriverManager() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    private Connection getConnectionFromDataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);
        return dataSource.getConnection();
    }

}
