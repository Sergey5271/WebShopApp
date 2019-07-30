package db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySqlDataSourceUtil {

    private static final Logger LOGGER = Logger.getLogger(MySqlDataSourceUtil.class.getName());

    private MySqlDataSourceUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/webshop_db");
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, "No database connection", e);
        }
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(connection, statement);
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Closed failed with statement and resultSet", e);
        }
    }

    public static void close(Connection connection, Statement statement) {
        close(connection);
        try {
            if (statement != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Closed failed with statement", e);
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Closed failed");
        }
    }
}
