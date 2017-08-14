package ru.job4j.task.servlets;

import ru.job4j.task.dao.UserRep;
import ru.job4j.task.entity.UserTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет, отвечающий за авторизацию пользователей.
 * @author agavrikov
 * @since 10.08.2017
 * @version 1
 */
public class AuthServlet extends HttpServlet{
    /**
     * Объект управления пользователями в бд.
     */
    private final UserRep userManger = UserRep.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login_task.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserTask user = userManger.getUserByLoginPass(req.getParameter("login"), req.getParameter("password"));
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user_id", user.getId());
            resp.sendRedirect(String.format("%s/task/list", req.getContextPath()));
        } else {
            req.setAttribute("error", "User not found.");
            doGet(req, resp);
        }
    }
}
