package ru.job4j.carShop.servlets;

import ru.job4j.carShop.repository.CarsRep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by gavrikov.a on 30/08/2017.
 */
public class CarsList extends HttpServlet {

    private final CarsRep carsRep = CarsRep.instanceOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carsRep.getAllCars());
        req.getRequestDispatcher("/WEB-INF/views/cars.jsp").forward(req, resp);
    }

}
