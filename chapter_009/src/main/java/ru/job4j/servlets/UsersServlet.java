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
 * @since 08.07.2017
 * @version 1
 */
public class UsersServlet extends HttpServlet {

    /**
     * Информация о соединении с бд.
     */
    private DataConnection dataConnection = new DataConnection("localhost", "5432", "postgres", "12345678", "task", "postgresql");

    /**
     * Метод для получения пользователя по имени.
     * @param req запрос
     * @param resp ответ
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String name = req.getParameter("name");
        try (Connection conn = DriverManager.getConnection(this.dataConnection.urlConnection(), this.dataConnection.getUser(), this.dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users_s WHERE users_s.name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(name, rs.getString(3), rs.getString(4), rs.getLong(5));
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append(String.format("User with name %s email %s login %s date created %s", user.getName(), user.getEmail(), user.getLogin(), user.getCreateDate()));
                writer.flush();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
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
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String name = req.getParameter("name");
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
              PreparedStatement ps = conn.prepareStatement("UPDATE users_s SET users_s.email = ?, users_s.login = ? WHERE users_s.name = ?")) {
            ps.setString(3, name);
            ps.setString(1, req.getParameter("email"));
            ps.setString(2, req.getParameter("login"));
            ps.execute();
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(String.format("User with name %s was updated.", name));
        } catch (SQLException e) {
            e.getStackTrace();
        }
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
        String name = req.getParameter("name");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("INSERT INTO users_s (name, email, login, date_created) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, req.getParameter("email"));
            ps.setString(3, req.getParameter("login"));
            ps.setLong(4, System.currentTimeMillis());
            ps.execute();
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(String.format("User with name %s was created.", name));
        } catch (SQLException e) {
            e.getStackTrace();
        }
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
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String name = req.getParameter("name");
        try (Connection conn = DriverManager.getConnection(this.dataConnection.urlConnection(), this.dataConnection.getUser(), this.dataConnection.getPassword());
              PreparedStatement ps = conn.prepareStatement("DELETE FROM users_s WHERE users_s.name = ?")) {
            ps.setString(1, name);
            ps.execute();
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(String.format("User with name %s was deleted.", name));
            writer.flush();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }


}
