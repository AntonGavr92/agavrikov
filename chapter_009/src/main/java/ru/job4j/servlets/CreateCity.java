package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class описывающий добавление города.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class CreateCity extends HttpServlet {

    /**
     * Объект управления городами в бд.
     */
    private final SimpleCityManager cityManager = SimpleCityManager.getManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        int idCity = cityManager.addCity(new City(req.getParameter("city"), req.getParameter("country")));
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(String.format("{\"id\": %s}", idCity));
        pw.flush();
    }
}
