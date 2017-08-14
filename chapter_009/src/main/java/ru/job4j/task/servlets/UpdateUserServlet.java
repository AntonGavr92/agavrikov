package ru.job4j.task.servlets;

import ru.job4j.task.dao.UserRep;
import ru.job4j.task.entity.Address;
import ru.job4j.task.entity.MusicType;
import ru.job4j.task.entity.Role;
import ru.job4j.task.entity.UserTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gavrikov.a on 10/08/2017.
 */
public class UpdateUserServlet extends HttpServlet {

    /**
     * Объект управления пользователями в бд.
     */
    private final UserRep userManger = UserRep.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserTask currentUser = userManger.getUserById((Integer)session.getAttribute("user_id"));
        if (req.getParameter("id") != null) {
            UserTask user = userManger.getUserById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", user);
        } else if (!currentUser.isAdmin() && (Integer)session.getAttribute("user_id") != Integer.parseInt(req.getParameter("id"))) {
            resp.sendRedirect(String.format("%s/update_user?id=%s", req.getContextPath(), session.getAttribute("id")));
            return;
        }

        if (currentUser.isAdmin()) {
            req.setAttribute("roles", userManger.getAllRoles());
            req.setAttribute("isAdmin", true);
        }
        req.setAttribute("musicTypes", userManger.getAllMusicTypes());
        req.getRequestDispatcher("/WEB-INF/views/update_user_task.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            userManger.updateUser(getInitUser(req));
            userManger.getUserById(Integer.parseInt(req.getParameter("id")));
        } else {
            userManger.addUser(getInitUser(req));
        }
        doGet(req, resp);
    }

    private UserTask getInitUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        UserTask currentUser = userManger.getUserById((Integer)session.getAttribute("user_id"));
        Role role = null;
        if(req.getParameter("role") != null) {
            role = userManger.getRoleById(Integer.parseInt(req.getParameter("role")));
        } else {
            role = currentUser.getRole();
        }
        Address address = userManger.addAddress(req.getParameter("addressUser"));
        ArrayList<MusicType> listMusicTypes = new ArrayList<MusicType>();
        if (req.getParameterValues("musicType") != null){
            for (String s : req.getParameterValues("musicType")) {
                listMusicTypes.add(userManger.getMusicTypeByid(Integer.parseInt(s)));
            }
        }
        UserTask res = new UserTask(req.getParameter("nameUser"), req.getParameter("emailUser"), req.getParameter("loginUser"), req.getParameter("userPassword"), role, address, listMusicTypes);
        if (req.getParameter("id") != null) {
            res.setId(Integer.parseInt(req.getParameter("id")));
        }
        return res;
    }
}
