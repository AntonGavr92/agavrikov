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
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(getUsersHtml(req));
        writer.flush();
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
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(getUsersHtml(req));
        writer.flush();
    }

    /**
     * Вспомогательный метод для создания html строки.
     * @param req запрос.
     * @return html строку
     */
    private String getUsersHtml(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        ArrayList<User> list = userManger.getAllUsers();
        for (User user : list) {
            sb.append("<tr style='margin-bottom: 10px;'>"
                    + "<td>" + user.getName() + "</td>"
                    + "<td>" + user.getEmail() + "</td>"
                    + "<td>" + user.getLogin() + "</td>"
                    + "<td><a href='" + req.getContextPath() + "/update_user?id=" + user.getId() + "'>Изменить пользователя</a></td>"
                    + "<td>"
                    + "<form style='margin-bottom: 0;' action='" + req.getContextPath() + "/users' method='post'>"
                    + "<input type='submit' value='Удалить пользователя'></input>"
                    + "<input type='hidden' name='userId' value='" + user.getId() + "'></input>"
                    + "</form>"
                    + "</td>"
                    + "</tr>");
        }
        sb.append("</table></br><a href='" + req.getContextPath() + "/update_user'>Создать пользователя</a>");

        return sb.toString();
    }
}
