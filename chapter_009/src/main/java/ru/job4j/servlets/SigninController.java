package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class реализующий авторизацию пользователя.
 * @author agavrikov
 * @since 07.08.2017
 * @version 1
 */
public class SigninController extends HttpServlet {

    /**
     * Объект управления пользователями в бд.
     */
    private final SimpleUserManager userManger = SimpleUserManager.getManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login_view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userManger.getUserByLoginPass(req.getParameter("login"), req.getParameter("password"));
        if (user != null) {
            HttpSession session = req.getSession();
                synchronized (session) {
                session.setAttribute("login", user.getLogin());
                session.setAttribute("id", user.getId());
                resp.sendRedirect(String.format("%s/users", req.getContextPath()));
            }
        } else {
            req.setAttribute("error", "User not found.");
            doGet(req, resp);
        }
    }
}
