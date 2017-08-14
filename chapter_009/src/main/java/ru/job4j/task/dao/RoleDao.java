package ru.job4j.task.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.task.entity.Role;
import ru.job4j.task.utils.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Класс, реализующий работу с ролями на уровне бд.
 * @author agavrikov
 * @since 10.08.2017
 * @version 1
 */
public class RoleDao implements EntityDao<Role>{

    /**
     * Поле для хранения объекта логгирования.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AddressDao.class);

    /**
     * Поле для хранения менеджера соединений с бд.
     */
    private final ConnectionPoolManager connManager = ConnectionPoolManager.getInstance();

    /**
     * Метод для получения роли из бд по идентификатору.
     * @param id идентификатор
     * @return роль
     */
    public Role getById(int id) {
        Role result = null;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT id, name FROM roles_task WHERE roles_task.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new Role(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить роль по id.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для добавления роли в бд.
     * @param role роль
     * @return идентификатор роли в бд
     */
    public int add(Role role) {
        int result = 0;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO roles_task (name) VALUES (?) ");
             PreparedStatement psId = conn.prepareStatement("SELECT MAX(id) FROM roles_task WHERE roles_task.name = ?")) {
            ps.setString(1, role.getName());
            ps.execute();

            psId.setString(1, role.getName());
            ResultSet rs = psId.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке добавления роли.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для получения всех ролей из бд.
     * @return список ролей.
     */
    public ArrayList<Role> getAllEntities() {
        ArrayList<Role> result = new ArrayList<Role>();
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT id, name FROM roles_task")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Role(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить всех ролей.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для удаления роли из бд.
     * @param role роль для удаления
     */
    public void deleteEntity(Role role) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM roles_task WHERE roles_task.id = ?")) {
            ps.setInt(1, role.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке удалить роль.", e);
        }
        connManager.closeConnection(conn);
    }

    /**
     * Метод для изменения роли в бд.
     * @param role ролm
     */
    public void updateEntity(Role role) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("UPDATE music_types_task SET name_type = ? WHERE id = ?")) {
            ps.setString(1, role.getName());
            ps.setInt(2, role.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке изменить роль.", e);
        }
        connManager.closeConnection(conn);
    }
}