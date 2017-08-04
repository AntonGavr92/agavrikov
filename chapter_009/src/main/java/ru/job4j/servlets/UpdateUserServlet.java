package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


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
        resp.setContentType("text/html");
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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (req.getParameter("userId") != null) {
            if (userManger.updateUserById(Integer.parseInt(req.getParameter("userId")), new User(req.getParameter("nameUser"), req.getParameter("emailUser"), req.getParameter("loginUser")))) {
                writer.append(getUsersHtml(req) + "</br>Данные успешно сохранены. <a href='" + req.getContextPath() + "/users'>Назад</a>.");
            } else {
                writer.append(getUsersHtml(req) + "</br>При сохранении данных возникла ошибка. <a href='" + req.getContextPath() + "/users'>Назад</a>.");
            }
        } else {
            userManger.addUser(new User(req.getParameter("nameUser"), req.getParameter("emailUser"), req.getParameter("loginUser")));
            writer.append(getUsersHtml(req) + "</br>Данные успешно сохранены. <a href='" + req.getContextPath() + "/users'>Назад</a>.");
        }
        writer.flush();
    }

    /**
     * Вспомогательный метод для создания html строки.
     * @param req запрос.
     * @return html строку
     */
    private String getUsersHtml(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        if (req.getParameter("id") != null) {
            User user = userManger.getUserById(Integer.parseInt(req.getParameter("id")));
            sb.append("Форма редактирования пользователя</br></br>");
            sb.append("<form style='margin-bottom: 0;' action='" + req.getContextPath() + "/update_user?id=" + user.getId() + "' method='post'>"
                    + "Имя : <input type='text' name='nameUser' value='" + user.getName() + "'></input></br></br>"
                    + "Почта : <input type='text' name='emailUser' value='" + user.getEmail() + "'></input></br></br>"
                    + "Логин : <input type='text' name='loginUser' value='" + user.getLogin() + "'></input></br></br>"
                    + "<input type='submit' value='Сохранить'></input>"
                    + "<input type='hidden' name='userId' value='" + user.getId() + "'></input>"
                    + "</form>");
        } else { //если нужно создать пользователя
            sb.append("Форма создания пользователя</br></br>");
            sb.append("<form style='margin-bottom: 0;' action='" + req.getContextPath() + "/update_user' method='post'>"
                    + "Имя : <input type='text' name='nameUser'></input></br></br>"
                    + "Почта : <input type='text' name='emailUser'></input></br></br>"
                    + "Логин : <input type='text' name='loginUser'></input></br></br>"
                    + "<input type='submit' value='Сохранить'></input>"
                    + "</form>");
        }
        return sb.toString();
    }

}
