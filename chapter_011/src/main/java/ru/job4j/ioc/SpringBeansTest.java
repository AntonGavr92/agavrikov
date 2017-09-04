package ru.job4j.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by gavrikov.a on 31/08/2017.
 */
@Component
public class SpringBeansTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        SpringBeansTest sbt = context.getBean(SpringBeansTest.class);
    }
}
