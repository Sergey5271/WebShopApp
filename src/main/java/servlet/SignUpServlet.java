package servlet;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SignUpServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Do nothing because at the moment this is method reduce.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = (UserService) getServletContext().getAttribute("userService");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        User user = new User();
        try {
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setAge(Integer.parseInt(age));
            user.setMobile(Integer.parseInt(mobile));
            user.setPassword(password);
            userService.add(user);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error servlet add user", e);
        }
    }
}
