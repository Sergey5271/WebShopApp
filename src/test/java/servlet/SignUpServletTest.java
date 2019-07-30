package servlet;

import dao.UserDAO;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SignUpServletTest {

    @Mock
    private ServletConfig servletConfig;

    @Mock
    ServletContext servletContext;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    UserService userService;

    private SignUpServlet signUpServlet;

    @Before
    public void beforeTest() throws ServletException {
        signUpServlet = new SignUpServlet();
        when(servletContext.getAttribute("userService")).thenReturn(servletContext);
        signUpServlet.init(servletConfig);
    }


    @Test
    public void shouldAddUserToDataBase() throws ServletException, IOException {
        addUserParameterToRequest(request);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        signUpServlet.doPost(request, response);
        verify(userService).add(userArgumentCaptor.capture());

    }

    private void addUserParameterToRequest(HttpServletRequest httpServletRequest) {
        when(httpServletRequest.getParameter("name")).thenReturn("Serhii");
        when(httpServletRequest.getParameter("surname")).thenReturn("Makarenko");
        when(httpServletRequest.getParameter("email")).thenReturn("sergey@gameil.com");
        when(httpServletRequest.getParameter("age")).thenReturn("12");
        when(httpServletRequest.getParameter("mobile")).thenReturn("380979491599");
        when(httpServletRequest.getParameter("password")).thenReturn("fdsgsDSF234");
    }

}