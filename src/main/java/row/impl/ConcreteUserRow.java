package row.impl;

import entity.User;
import row.UserRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import static util.UserUtil.*;

public class ConcreteUserRow implements UserRow {

    @Override
    public User createUserFromResult(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);
        String surname = resultSet.getString(COLUMN_SURNAME);
        String email = resultSet.getString(COLUMN_EMAIL);
        int age = resultSet.getInt(COLUMN_AGE);
        int mobile = resultSet.getInt(COLUMN_MOBILE);
        String password = resultSet.getString(COLUMN_PASSWORD);
        return new User(id, name, surname, email, age, mobile, password);
    }
}
