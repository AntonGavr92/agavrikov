package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class реализующий тестирование сервлета авторизации.
 * @author agavrikov
 * @since 08.08.2017
 * @version 1
 */
public class SigninControllerTest {
    /**
     * Метод для тестирования авторизации.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test
    public void whenDeleteUserThenUserWithSameIdNotExistInDb() throws ServletException, IOException {
        SigninController signinController = new SigninController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        User user = new User("TestAuth", "TestAuth", "TestAuth", "TestAuth", 1);
        SimpleUserManager userManager = SimpleUserManager.getManager();
        userManager.addUser(user);

        when(request.getParameter("login")).thenReturn("TestAuth");
        when(request.getParameter("password")).thenReturn("TestAuth");
        when(request.getRequestDispatcher("/WEB-INF/views/login_view.jsp")).thenReturn(rd);
        when(request.getSession()).thenReturn(session);

        signinController.doPost(request, response);
        User expectedUser = null;

        assertThat(userManager.getUserByLoginPass("TestAuths", "TestDelete"), is(expectedUser));
    }
}