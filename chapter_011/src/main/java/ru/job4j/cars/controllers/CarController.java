package ru.job4j.cars.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.models.*;
import ru.job4j.cars.repositories.CarDataRepository;
import ru.job4j.cars.repositories.CarsRep;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gavrikov.a on 01/09/2017.
 */
@Controller
public class CarController {

    //Тут спорный вариант, но если от него отказываться, тогда делать сборку без файла контекста, насколько я понимаю, все через аннотации.
    private final CarsRep repository = new ClassPathXmlApplicationContext("spring-context.xml").getBean(CarsRep.class);

    private final CarDataRepository repositoryData = new ClassPathXmlApplicationContext("spring-context.xml").getBean(CarDataRepository.class);

    private static final String UPLOAD_DIRECTORY = "upload";

    @Autowired
    ServletContext servletContext;

    private final HashMap<String, String> filtersConditions = new HashMap<String, String>();

    public CarController() {
        //this.repositoryData = new ClassPathXmlApplicationContext("spring-context.xml").getBean(CarDataRepository.class);
        this.filtersConditions.put("filter_name", "name like '%");
        this.filtersConditions.put("filter_engine", "engine.id = ");
        this.filtersConditions.put("filter_gear_shift", "gearShift.id = ");
        this.filtersConditions.put("filter_transmission","transmission.id = ");
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String showCars(ModelMap model) {

        this.repository.getAllCars();
        if (!model.containsAttribute("cars")) {
            model.addAttribute("cars", repositoryData.findAll());
        }
        model.addAttribute("transmissions", repository.getAllTransmissions());
        model.addAttribute("engines", repository.getAllEngines());
        model.addAttribute("gearShifts", repository.getAllGearShift());
        return "cars";
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public void showCarsWithFilters(ModelMap model, HttpServletRequest request) {
        model.addAttribute("cars", repository.getCarsByFilters(createConditionPartOfQuery(request)));
        showCars(model);
    }

    @RequestMapping(value = "/create_car", method = RequestMethod.GET)
    public String createCar(ModelMap model) {
        model.addAttribute("transmissions", repository.getAllTransmissions());
        model.addAttribute("engines", repository.getAllEngines());
        model.addAttribute("gearShifts", repository.getAllGearShift());
        return "createCar";
    }

    //Пробовал пойти через @RequestParam("picture") MultipartFile, но вылетало исключение
    //Unable to process parts as no multi-part configuration has been provided - хотя все подключил в конф файлах...
    //Так что оставлю вариант, который реализуется без спринга.
    @RequestMapping(value = "/create_car", method = RequestMethod.POST)
    public String createNewCar(@RequestParam("picture") MultipartFile file, @RequestParam Map<String,String> allRequestParams) {
        Car car = new Car();
        car.setUser(new User(Integer.parseInt(allRequestParams.get("user"))));
        car.setName(allRequestParams.get("name"));
        car.setEngine(new Engine(Integer.parseInt(allRequestParams.get("engine"))));
        car.setTransmission(new Transmission(Integer.parseInt(allRequestParams.get("transmission"))));
        car.setGearShift(new GearShift(Integer.parseInt(allRequestParams.get("gear-shift"))));
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String uploadPath = String.format("%s/%s", servletContext.getContextPath(), UPLOAD_DIRECTORY);
        String fullPath = String.format("%s%s", servletContext.getRealPath(""), UPLOAD_DIRECTORY);
        File uploadDir = new File(fullPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        //without spring boot
        /*try {
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
        }*/
        if (!file.isEmpty()) {
            FileOutputStream out = null;
            try {
                String fileName = new File(file.getName()).getName();
                String filePath = uploadPath + File.separator + fileName;
                File storeFile = new File(fullPath + File.separator + fileName);
                out = new FileOutputStream(storeFile);
                byte[] bytes = file.getBytes();
                out.write(bytes);
                out.flush();
                car.setPicturePath(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            car.setPicturePath("none");
        }
        repositoryData.save(car);
        return "createCar";
    }

    public String createConditionPartOfQuery(HttpServletRequest req) {
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
        return sb.toString();
    }
}
