package ru.job4j.cars.controllers;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.job4j.cars.models.*;
import ru.job4j.cars.repositories.CarDataRepository;
import ru.job4j.cars.repositories.CarsRep;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by gavrikov.a on 06/09/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarDataRepository rep;

    private Car createCar(String name) {
        User user = new User();
        user.setLogin("test");
        user.setName(name);
        user.setPassword("test");

        Engine engine = new Engine();
        engine.setName("test Engine");

        Transmission transmission = new Transmission();
        transmission.setName("test Transmission");

        GearShift gs = new GearShift();
        gs.setName("test Gear Shift");

        Car car = new Car();
        car.setName(name);
        car.setPicturePath("test/path");
        car.setGearShift(gs);
        car.setTransmission(transmission);
        car.setUser(user);
        car.setEngine(engine);
        return car;
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void whenGetAllCarsThenPageCars() throws Exception{
        given(
                this.rep.findAll()
        ).willReturn(
                new ArrayList<Car>(Lists.newArrayList(createCar("test")))
        );
        this.mvc.perform(
                get("/cars").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("cars")
        );
    }


    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void whenGetAllCarsWithFilterThenPageCarsWithFilter() throws Exception{
        this.mvc.perform(
                post("/cars").param("filter_name", "test")
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void whenCreateCarGetThenCreateCarPage() throws Exception{
        this.mvc.perform(
                get("/create_car").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("createCar")
        );
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void whenCreateCarPostThenCreateCar() throws Exception{
        MediaType mediaType = new MediaType("multipart", "form-data");

        FileInputStream fis = new FileInputStream("C:\\projects\\log.txt");
        MockMultipartFile multipartFile = new MockMultipartFile("picture", fis);

        this.mvc.perform(
                post("/create_car")
                .param("user", "1")
                .param("name", "JunitTest")
                .param("engine", "1")
                .param("transmission", "1")
                .param("gear-shift", "1")
                .content(multipartFile.getBytes())
                .contentType(mediaType)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("createCar")
        );
    }


}