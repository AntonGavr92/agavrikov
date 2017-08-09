package ru.job4j.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class описывающий работу с городами на уровне бд.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class SimpleCityManager {
    /**
     * Информация о соединении с бд.
     */
    private final DataConnection dataConnection;

    /**
     * Поле для хранения пула соединений с бд.
     */
    private final ConnectionPool pool;

    /**
     * поле для хранения единственного экземпляра класса.
     */
    private static final SimpleCityManager CITY_MANAGER = new SimpleCityManager();

    /**
     * Метод для получения экземпляра класса.
     * @return объект SimpleCityManager
     */
    public static SimpleCityManager getManager() {
        return CITY_MANAGER;
    }


    /**
     * Конструктор для инициализации.
     */
    private SimpleCityManager() {
        this.dataConnection = new DataConnection("localhost", "5432", "postgres", "12345678", "task", "postgresql");
        this.pool = new ConnectionPool(this.dataConnection, "org.postgresql.Driver", 2);
    }

    /**
     * Метод для доблавления города в базу данных и получения его идентификатора.
     * @param city город
     * @return идентификатор добавленного города
     */
    public int addCity(City city) {
        int result = -1;
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO cities_s (city, country) VALUES (?, ?)");
             PreparedStatement psResId = conn.prepareStatement("SELECT id FROM cities_s WHERE cities_s.city = ? AND cities_s.country = ?")) {
            ps.setString(1, city.getName());
            ps.setString(2, city.getCountry());
            ps.execute();

            psResId.setString(1, city.getName());
            psResId.setString(2, city.getCountry());

            ResultSet rs = psResId.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);

            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
        pool.closeConnection(conn);
        return result;
    }

    /**
     * Метод для получения всех городов.
     * @return список городов
     */
    public ArrayList<City> getAllCities() {
        ArrayList<City> list = new ArrayList<City>();
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM cities_s")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new City(rs.getString(2), rs.getString(3), rs.getInt(1)));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
        pool.closeConnection(conn);
        return list;
    }

    /**
     * Метод для получения города по идентификатору города.
     * @param id идентификатор города
     * @return город
     */
    public City getCityById(int id) {
        City result = null;
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM cities_s WHERE cities_s.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new City(rs.getString(2), rs.getString(3), rs.getInt(1));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        pool.closeConnection(conn);
        return result;
    }

}
