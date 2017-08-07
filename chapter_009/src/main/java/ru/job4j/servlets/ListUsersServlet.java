package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Class описывающий список пользователей и взаимодействия с ними.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class ListUsersServlet extends HttpServlet {

    /**
     * Объект управления пользователями в бд.
     */
    private final SimpleUserManager userManger = SimpleUserManager.getManager();

    /**
     * Метод для получения пользователей.
     * @param req запрос
     * @param resp ответ
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = userManger.getUserById((Integer) session.getAttribute("id"));
        if (user.getIdRole() == userManger.getIdAdminRole()) {
            req.setAttribute("users", userManger.getAllUsers());
        } else {
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/WEB-INF/views/get_users.jsp").forward(req, resp);
    }

    /**
     * Метод для удаления пользователя.
     * @param req запрос
     * @param resp ответ
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userManger.deleteUserById(Integer.parseInt(req.getParameter("userId")));
        req.setAttribute("users", userManger.getAllUsers());
        req.getRequestDispatcher("/WEB-INF/views/get_users.jsp").forward(req, resp);
    }
}
