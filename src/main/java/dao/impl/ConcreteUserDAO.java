package dao.impl;

import dao.UserDAO;
import db.MySqlDataSourceUtil;
import entity.User;
import row.UserRow;


import static util.QueryUtil.*;
import static util.UserUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcreteUserDAO implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(ConcreteUserDAO.class.getName());
    private UserRow userRow;
    private static ConcreteUserDAO userDAO = new ConcreteUserDAO();

    public static ConcreteUserDAO getInstance(){
        return userDAO;
    }

    @Override
    public boolean add(Connection connection, User user) {
        PreparedStatement preparedStatement = null;
        try {
            connection = MySqlDataSourceUtil.getConnection();
            preparedStatement = connection.prepareStatement(ADD_USER_QUERY);
            preparedStatement.setString(COLUMN_NAME - 1, user.getName());
            preparedStatement.setString(COLUMN_SURNAME - 1, user.getSurname());
            preparedStatement.setString(COLUMN_EMAIL - 1, user.getEmail());
            preparedStatement.setInt(COLUMN_AGE - 1, user.getAge());
            preparedStatement.setInt(COLUMN_MOBILE - 1, user.getMobile());
            preparedStatement.setString(COLUMN_PASSWORD - 1, user.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Add user is failed");
        } finally {
            MySqlDataSourceUtil.close(connection, preparedStatement);
        }
        return false;
    }

    @Override
    public User findById(Connection connection, int key) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MySqlDataSourceUtil.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            return userRow.createUserFromResult(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Find by Id user is failed");
        } finally {
            MySqlDataSourceUtil.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User findByEmail(Connection connection, String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MySqlDataSourceUtil.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            return userRow.createUserFromResult(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Find by email is failed");
        } finally {
            MySqlDataSourceUtil.close(connection, preparedStatement, resultSet);
        }
        return null;
    }
}
