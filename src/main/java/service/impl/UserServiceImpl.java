package service.impl;

import dao.UserDAO;
import db.MySqlDataSourceUtil;
import entity.User;
import service.UserService;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    public static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    private UserDAO userDAO;


    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean add(User user) {
        Connection connection = null;
        try {
            connection = MySqlDataSourceUtil.getConnection();
            return userDAO.add(connection, user);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection add service failed");
        } finally {
            MySqlDataSourceUtil.close(connection);
        }
        return false;
    }

    @Override
    public User findById(int key) {
        Connection connection = null;
        try {
            connection = MySqlDataSourceUtil.getConnection();
            return userDAO.findById(connection, key);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection findById service failed");
        } finally {
            MySqlDataSourceUtil.close(connection);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        Connection connection = null;
        try {
            connection = MySqlDataSourceUtil.getConnection();
            return userDAO.findByEmail(connection, email);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection find by email service failed");
        } finally {
            MySqlDataSourceUtil.close(connection);
        }
        return null;
    }
}
