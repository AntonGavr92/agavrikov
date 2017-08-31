package ru.job4j.carShop.servlets;

import ru.job4j.carShop.repository.CarsRep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by gavrikov.a on 30/08/2017.
 */
public class CarsList extends HttpServlet {

    private final CarsRep carsRep = CarsRep.instanceOf();

    private final HashMap<String, String> filtersConditions = new HashMap<>();

    @Override
    public void init() throws ServletException {
        this.filtersConditions.put("filter_name", "name like '%");
        this.filtersConditions.put("filter_engine", "engine.id = ");
        this.filtersConditions.put("filter_gear_shift", "gearShift.id = ");
        this.filtersConditions.put("filter_transmission","transmission.id = ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("transmissions", carsRep.getAllTransmissions());
        req.setAttribute("engines", carsRep.getAllEngines());
        req.setAttribute("gearShifts", carsRep.getAllGearShift());
        if (req.getAttribute("cars") == null){
            req.setAttribute("cars", carsRep.getAllCars());
        }
        req.getRequestDispatcher("/WEB-INF/views/cars.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        for (String key : this.filtersConditions.keySet()) {
            if(req.getParameter(key) != null && !req.getParameter(key).equals("")){
                sb.append(String.format("%s%s", this.filtersConditions.get(key), req.getParameter(key)));
                if (this.filtersConditions.get(key).contains("%")){
                    sb.append("%'");
                }
                sb.append(" and ");
            }
        }
        sb.delete(sb.length() - 5, sb.length());
        req.setAttribute("cars", carsRep.getCarsByFilters(sb.toString()));
        doGet(req, resp);
    }



}
