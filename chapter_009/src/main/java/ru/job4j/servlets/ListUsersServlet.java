package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
        resp.sendRedirect(String.format("%s/users/", req.getContextPath()));
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
        resp.sendRedirect(String.format("%s/users/", req.getContextPath()));
    }
}
