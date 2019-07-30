package row;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserRow {

    User createUserFromResult(ResultSet resultSet) throws SQLException;
}
