package ru.job4j.task.servlets;

import ru.job4j.task.dao.UserRep;
import ru.job4j.task.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by gavrikov.a on 10/08/2017.
 */
public class UsersListServlet extends HttpServlet{

    /**
     * Объект управления пользователями в бд.
     */
    private final UserRep userManger = UserRep.getInstance();

    /**
     * Поле для допустимых фильтров. Данные не добавляем динамически, лишь получаем.
     */
    private static final ArrayList<Filter> FILTERS = new ArrayList<Filter>();

    @Override
    public void init() throws ServletException {
        FILTERS.add(new Filter(1, "Роль"));
        FILTERS.add(new Filter(2, "Адрес"));
        FILTERS.add(new Filter(3, "Музыкальный тип"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserTask user = userManger.getUserById((Integer)session.getAttribute("user_id"));

        ArrayList<UserTask> usersList = new ArrayList<UserTask>();

        if (user.isAdmin()) {
            ArrayList<Filter> valFilter = new ArrayList<Filter>();
            req.setAttribute("filters", FILTERS);
            if (req.getParameter("filterId") == null) {
                req.setAttribute("filterValues", userManger.getAllRoles());
                usersList = userManger.getAllUsers();
            } else if (Integer.parseInt(req.getParameter("filterId")) == 1){
                usersList = userManger.getUsersByRole(userManger.getRoleById(Integer.parseInt(req.getParameter("filter_val"))));
                req.setAttribute("filterValues", userManger.getAllRoles());
            } else if (Integer.parseInt(req.getParameter("filterId")) == 2){
                usersList.add(userManger.getUserByAddress(req.getParameter("filter_val")));
                for (Address addr : userManger.getAllAddress()) {
                    valFilter.add(new Filter(addr.getId(), addr.getAddress()));
                }
                req.setAttribute("filterValues", valFilter);
            } else if (Integer.parseInt(req.getParameter("filterId")) == 3){
                usersList = userManger.getUsersByMusicType(userManger.getMusicTypeByid((Integer.parseInt(req.getParameter("filter_val")))));
                for (MusicType type : userManger.getAllMusicTypes()) {
                    valFilter.add(new Filter(type.getId(), type.getType()));
                }
                req.setAttribute("filterValues", valFilter);
            }
        } else {
            usersList.add(user);
        }

        req.setAttribute("userIsAdmin", user.isAdmin());
        req.setAttribute("users", usersList);
        req.getRequestDispatcher("/WEB-INF/views/get_users_task.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if (req.getParameter("filter_id") != null) {
            int idFil = Integer.parseInt(req.getParameter("filter_id"));
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (idFil == 1) {
                for (Role role : userManger.getAllRoles()) {
                    sb.append(String.format("{\"id\":%s, \"name\":\"%s\"},",role.getId(), role.getName()));
                }
            } else if (idFil == 2) {
                for (Address address : userManger.getAllAddress()) {
                    sb.append(String.format("{\"id\":%s, \"name\":\"%s\"},",address.getId(), address.getAddress()));
                }
            } else if (idFil == 3) {
                for (MusicType type : userManger.getAllMusicTypes()) {
                    sb.append(String.format("{\"id\":%s, \"name\":\"%s\"},",type.getId(), type.getType()));
                }
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("]");
            PrintWriter pw = new PrintWriter(resp.getOutputStream());
            pw.append(sb.toString());
            pw.flush();
        } else {
            UserTask user = userManger.getUserById(Integer.parseInt(req.getParameter("userId")));
            userManger.deleteUser(user);
            doGet(req, resp);
        }
    }
}
