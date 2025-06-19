package banking.system;

import java.sql.*;

public class Connn {
    private Connection connection;
    public Statement statement;

    public Connn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankSystem?useSSL=false&serverTimezone=UTC",
                    "root",
                    "enter your pass"
            );
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Database connection failed");
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (statement != null && !statement.isClosed()) statement.close();
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection");
        }
    }
}
