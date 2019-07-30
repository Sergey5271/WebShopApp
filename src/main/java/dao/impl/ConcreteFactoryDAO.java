package dao.impl;

import dao.FactoryDAO;
import dao.UserDAO;

public class ConcreteFactoryDAO  implements FactoryDAO {

    private static final ConcreteFactoryDAO FACTORY_DAO = new ConcreteFactoryDAO();

    public static ConcreteFactoryDAO getInstance(){
        return FACTORY_DAO;
    }

    @Override
    public UserDAO getUserDAO() {
        return ConcreteUserDAO.getInstance();
    }
}
