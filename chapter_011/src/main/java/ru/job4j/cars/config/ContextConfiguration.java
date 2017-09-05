package ru.job4j.cars.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by gavrikov.a on 04/09/2017.
 */
@Configuration
public class ContextConfiguration {
    @Bean
    public DataSource dataSource() {
        final BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/car_garage");
        ds.setUsername("postgres");
        ds.setPassword("12345678");
        return ds;
    }
}
