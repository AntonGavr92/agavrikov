package ru.job4j.ios;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by gavrikov.a on 31/08/2017.
 */
public class ImportUserTest {
    @Test
    public void whenUserAddInStructureThenStructureHasSameUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ImportUser importUser = context.getBean(ImportUser.class);
        User user = context.getBean(User.class);
        user.setName("ss");
        importUser.addUser(user);
    }

}