package ru.job4j.task.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.task.entity.Address;
import ru.job4j.task.utils.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Класс, реализующий работу с адресами на уровне бд.
 * @author agavrikov
 * @since 10.08.2017
 * @version 1
 */

public class AddressDao implements EntityDao<Address>{

    /**
     * Поле для хранения объекта логгирования.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AddressDao.class);

    /**
     * Поле для хранения менеджера соединений с бд.
     */
    private final ConnectionPoolManager connManager = ConnectionPoolManager.getInstance();

    /**
     * Метод для получения адреса из бд по идентификатору.
     * @param id идентификатор
     * @return адрес
     */
    public Address getById(int id) {
        Address result = null;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT id, address FROM addresses_task WHERE addresses_task.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new Address(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить адреса по id.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для добавления адреса в бд.
     * @param address адрес
     * @return идентификатор адреса в бд
     */
    public int add(Address address) {
        int result = 0;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO addresses_task (address) VALUES (?) ");
              PreparedStatement psId = conn.prepareStatement("SELECT MAX(id) FROM addresses_task WHERE addresses_task.address = ?")) {
            ps.setString(1, address.getAddress());
            ps.execute();

            psId.setString(1, address.getAddress());
            ResultSet rs = psId.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке добавления адреса.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для получения всех адресов из бд.
     * @return список сущностей.
     */
    public ArrayList<Address> getAllEntities() {
        ArrayList<Address> result = new ArrayList<Address>();
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT id, address FROM addresses_task")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Address(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить все адреса.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для удаление адреса из бд.
     * @param address адрес
     */
    public void deleteEntity(Address address) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM addresses_task WHERE addresses_task.id = ?")) {
            ps.setInt(1, address.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке удалить адрес.", e);
        }
        connManager.closeConnection(conn);
    }

    /**
     * Метод для изменения адреса в бд.
     * @param address адрес
     */
    public void updateEntity(Address address) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("UPDATE addresses_task SET address = ? WHERE id = ?")) {
            ps.setString(1, address.getAddress());
            ps.setInt(2, address.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке изменить адрес.", e);
        }
        connManager.closeConnection(conn);
    }
}
