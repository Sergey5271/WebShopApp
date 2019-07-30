package util;

public class QueryUtil {

    private QueryUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ADD_USER_QUERY = "INSERT INTO user(name,surname,email,age,mobile,password) VALUES (?,?,?,?,?,?)";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM user WHERE id=?";
    public static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";
}
