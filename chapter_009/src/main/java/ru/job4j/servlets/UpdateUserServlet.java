package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private final SimpleUserManager userManger = SimpleUserManager.getManager();

    /**
     * Объект управления городами в бд.
     */
    private final SimpleCityManager cityManager = SimpleCityManager.getManager();

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
        req.setAttribute("cities", cityManager.getAllCities());
        if (req.getParameter("id") != null) {
            User userForUpdate = userManger.getUserById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", userForUpdate);
        } else if (!isAdmin(req)) {
            resp.sendRedirect(String.format("%s/update_user?id=%s", req.getContextPath(), session.getAttribute("id")));
            return;
        }
        if (isAdmin(req)) {
            req.setAttribute("roles", userManger.getAllRoles());
            req.setAttribute("isAdmin", true);
            req.getRequestDispatcher("/WEB-INF/views/update_user.jsp").forward(req, resp);
        } else if ((Integer) session.getAttribute("id") != Integer.parseInt(req.getParameter("id"))) {
            resp.sendRedirect(String.format("%s/update_user?id=%s", req.getContextPath(), session.getAttribute("id")));
        } else {
            req.getRequestDispatcher("/WEB-INF/views/update_user.jsp").forward(req, resp);
        }
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
        req.setAttribute("cities", cityManager.getAllCities());
        if (req.getParameter("id") != null) {
            User user = userManger.getUserById(Integer.parseInt(req.getParameter("id")));

            User updateUser = new User(req.getParameter("nameUser"), req.getParameter("emailUser"), req.getParameter("loginUser"), req.getParameter("userPassword"), user.getIdRole());
            updateUser.setCity(cityManager.getCityById((Integer.parseInt(req.getParameter("active_city")))));
            userManger.updateUserById(Integer.parseInt(req.getParameter("userId")), updateUser);
            user = userManger.getUserById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", user);
        } else {
            User user = new User(req.getParameter("nameUser"), req.getParameter("emailUser"), req.getParameter("loginUser"), req.getParameter("userPassword"), Integer.parseInt(req.getParameter("role")));
            user.setCity(cityManager.getCityById((Integer.parseInt(req.getParameter("active_city")))));
            userManger.addUser(user);
        }
        if (isAdmin(req)) {
            req.setAttribute("isAdmin", true);
            req.setAttribute("roles", userManger.getAllRoles());
        }

        req.getRequestDispatcher("/WEB-INF/views/update_user.jsp").forward(req, resp);
    }

    /**
     * Метод для проверки на администратора.
     * @param req объект запроса
     * @return true  - если пользователь - администратор, иначе false.
     */
    private boolean isAdmin(HttpServletRequest req) {
        boolean result = false;
        HttpSession session = req.getSession();
        User user = userManger.getUserById((Integer) session.getAttribute("id"));
        if (user.getIdRole() == userManger.getIdAdminRole()) {
            result = true;
        }
        return result;
    }
}
