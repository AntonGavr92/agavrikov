package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class реализующий тестирование сервлета по удалению пользователей.
 * @author agavrikov
 * @since 08.08.2017
 * @version 1
 */
public class ListUsersServletTest {

    /**
     * Метод для тестирования удаления.
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Test
    public void whenDeleteUserThenUserWithSameIdNotExistInDb() throws ServletException, IOException {
        ListUsersServlet listUsersServlet = new ListUsersServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        User user = new User("TestDelete", "TestDelete", "TestDelete", "TestDelete", 1);
        SimpleUserManager userManager = SimpleUserManager.getManager();
        userManager.addUser(user);

        User searchUser = userManager.getUserByLoginPass("TestDelete", "TestDelete");

        when(request.getParameter("userId")).thenReturn(((Integer) searchUser.getId()).toString());
        when(request.getRequestDispatcher("/WEB-INF/views/get_users.jsp")).thenReturn(rd);

        listUsersServlet.doPost(request, response);

        User expectedUser = null;

        assertThat(userManager.getUserByLoginPass("TestDelete", "TestDelete"), is(expectedUser));
    }

}