package context;

import dao.UserDAO;
import dao.impl.ConcreteFactoryDAO;
import service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        UserDAO userDAO = ConcreteFactoryDAO.getInstance().getUserDAO();
        servletContext.setAttribute("userService", new UserServiceImpl(userDAO));


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent){
        //do nothing because this is method reduce
    }
}
