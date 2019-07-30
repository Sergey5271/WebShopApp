package dao;

import entity.User;

import java.sql.Connection;

public interface UserDAO {
    boolean add(Connection connection, User user);

    User findById(Connection connection, int key);

    User findByEmail(Connection connection, String email);
}
