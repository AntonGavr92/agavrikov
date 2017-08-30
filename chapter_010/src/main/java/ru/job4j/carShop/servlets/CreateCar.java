package ru.job4j.carShop.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.carShop.models.*;
import ru.job4j.carShop.repository.CarsRep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by gavrikov.a on 30/08/2017.
 */
public class CreateCar extends HttpServlet {

    private static final CarsRep carsRep = CarsRep.instanceOf();
    private static final String UPLOAD_DIRECTORY = "upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("transmissions", carsRep.getAllTransmissions());
        req.setAttribute("engines", carsRep.getAllEngines());
        req.setAttribute("gearShifts", carsRep.getAllGearShift());
        req.getRequestDispatcher("/WEB-INF/views/createCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = new Car();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String uploadPath = String.format("%s/%s", getServletContext().getContextPath(), UPLOAD_DIRECTORY);
        String fullPath = String.format("%s%s", getServletContext().getRealPath(""), UPLOAD_DIRECTORY);
        File uploadDir = new File(fullPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(fullPath + File.separator + fileName);
                        item.write(storeFile);
                        car.setPicturePath(filePath);
                    } else {
                        req.setAttribute(item.getFieldName(), item.getString());
                    }
                }
            }
            car.setUser(new User(Integer.parseInt(req.getAttribute("user").toString())));
            car.setName(req.getAttribute("name").toString());
            car.setEngine(new Engine(Integer.parseInt(req.getAttribute("engine").toString())));
            car.setTransmission(new Transmission(Integer.parseInt(req.getAttribute("transmission").toString())));
            car.setGearShift(new GearShift(Integer.parseInt(req.getAttribute("gear-shift").toString())));
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        carsRep.addCar(car);
        resp.sendRedirect(String.format("%s/cars/", req.getContextPath()));
    }
}
