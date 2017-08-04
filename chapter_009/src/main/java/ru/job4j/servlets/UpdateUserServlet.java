package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class описывающий изменение пользователя.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class UpdateUserServlet extends HttpServlet {

    /**
     * Объект управления пользователями в бд.
     */
    private final SimpleUserManager userManger = new SimpleUserManager();

    /**
     * Метод для получения пользователей.
     * @param req запрос
     * @param resp ответ
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            User user = userManger.getUserById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/WEB-INF/views/update_user.jsp").forward(req, resp);
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
        if (req.getParameter("id") != null) {
            userManger.updateUserById(Integer.parseInt(req.getParameter("userId")), new User(req.getParameter("nameUser"), req.getParameter("emailUser"), req.getParameter("loginUser")));
            User user = userManger.getUserById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", user);
        } else {
            userManger.addUser(new User(req.getParameter("nameUser"), req.getParameter("emailUser"), req.getParameter("loginUser")));
        }
        req.getRequestDispatcher("/WEB-INF/views/update_user.jsp").forward(req, resp);
    }
}
