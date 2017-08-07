package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Class описывающий взаимодействия с пользователями.
 * @author agavrikov
 * @since 04.08.2017
 * @version 1
 */
public class UsersServlet extends HttpServlet {

    /**
     * Объект управления пользователями в бд.
     */
    private final SimpleUserManager userManager = SimpleUserManager.getManager();

    /**
     * Метод для получения пользователя по имени.
     * @param req запрос
     * @param resp ответ
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userManager.getUserByName(req.getParameter("name"));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (user != null) {
            writer.append(String.format("User with name %s email %s login %s date created %s", user.getName(), user.getEmail(), user.getLogin(), user.getCreateDate()));
        } else {
            writer.append("Пользователь не найден!");
        }
        writer.flush();
    }

    /**
     * Метод для изменения пользователя по имени.
     * @param req запрос
     * @param resp ответ
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userManager.updateUser(new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("login")));
    }

    /**
     * Метод для добавления пользователя.
     * @param req запрос
     * @param resp ответ
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userManager.addUser(new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("login")));
    }

    /**
     * Метод для удаления пользователя по имени.
     * @param req запрос
     * @param resp ответ
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userManager.deleteUser(new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("login")));
    }


}
