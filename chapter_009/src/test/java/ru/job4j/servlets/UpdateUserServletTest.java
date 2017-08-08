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
 * Class реализующий тестирование сервлета по добавлению/редактированию пользователей.
 * @author agavrikov
 * @since 08.08.2017
 * @version 1
 */
public class UpdateUserServletTest {

    /**
     * Метод для тестирования добавления пользователя.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test
    public void whenAddUserThenUserWithSameLoginAndPasswordExistInDb() throws ServletException, IOException {
        UpdateUserServlet updateUser = new UpdateUserServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        SimpleUserManager userManager = SimpleUserManager.getManager();

        when(request.getParameter("nameUser")).thenReturn("TestCreate");
        when(request.getParameter("emailUser")).thenReturn("TestCreate");
        when(request.getParameter("loginUser")).thenReturn("TestCreate");
        when(request.getParameter("userPassword")).thenReturn("TestCreate");
        when(request.getParameter("role")).thenReturn("1");
        when(request.getRequestDispatcher("/WEB-INF/views/update_user.jsp")).thenReturn(rd);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(20);

        updateUser.doPost(request, response);

        String expected = "TestCreate";

        assertThat(userManager.getUserByLoginPass("TestCreate", "TestCreate").getName(), is(expected));
    }

    /**
     * Метод для тестирования редактирования пользователя.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test
    public void whenUpdateUserFieldsThenUserWithSameLoginAndPasswordExistInDbWithUpdateFields() throws ServletException, IOException {
        UpdateUserServlet updateUser = new UpdateUserServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        SimpleUserManager userManager = SimpleUserManager.getManager();
        when(request.getParameter("userId")).thenReturn("37");
        when(request.getParameter("nameUser")).thenReturn("TestUpdate");
        when(request.getParameter("emailUser")).thenReturn("TestUpdate");
        when(request.getParameter("loginUser")).thenReturn("TestUpdate");
        when(request.getParameter("userPassword")).thenReturn("TestUpdate");
        when(request.getParameter("role")).thenReturn("1");
        when(request.getRequestDispatcher("/WEB-INF/views/update_user.jsp")).thenReturn(rd);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(20);
        when(request.getParameter("id")).thenReturn("37");

        updateUser.doPost(request, response);

        String expected = "TestUpdate";

        assertThat(userManager.getUserByLoginPass("TestUpdate", "TestUpdate").getName(), is(expected));
    }
}