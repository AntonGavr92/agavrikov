package ru.job4j.task.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.task.entity.Address;
import ru.job4j.task.entity.MusicType;
import ru.job4j.task.entity.Role;
import ru.job4j.task.entity.UserTask;
import ru.job4j.task.utils.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Класс, реализующий работу с пользователями на уровне бд.
 * @author agavrikov
 * @since 10.08.2017
 * @version 1
 */
public class UserTaskDao implements EntityDao<UserTask>{

    /**
     * Поле для хранения объекта логгирования.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AddressDao.class);

    /**
     * Поле для хранения менеджера соединений с бд.
     */
    private final ConnectionPoolManager connManager = ConnectionPoolManager.getInstance();

    /**
     * Метод для получения пользователя из бд по идентификатору.
     * @param id идентификатор
     * @return пользователь
     */
    public UserTask getById(int id) {
        UserTask result = null;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT u.id, u.name, u.email, u.login, u.password, u.date_created, u.role_id, u.adress_id, a.address, r.name "
                                                            + "FROM users_task as u LEFT JOIN addresses_task AS a ON u.adress_id = a.id LEFT JOIN roles_task AS r ON u.role_id = r.id WHERE u.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Role role = new Role(rs.getInt(7), rs.getString(10));
                Address address = new Address(rs.getInt(8), rs.getString(9));
                result = new UserTask(rs.getString(2), rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), role, address);
                addMusicTypes(result);
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить пользователя по id.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для добавления нового пользователя в бд.
     * @param user адрес
     * @return идентификатор нового пользователя в бд
     */
    public int add(UserTask user) {
        int result = 0;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO users_task (name, email, login, date_created, password, role_id, adress_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
             PreparedStatement psResId = conn.prepareStatement("SELECT id FROM users_task WHERE users_task.name = ? AND users_task.email = ? AND users_task.login = ?")) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getLogin());
            ps.setLong(4, System.currentTimeMillis());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole().getId());
            ps.setInt(7, user.getAdress().getId());
            ps.execute();

            psResId.setString(1, user.getName());
            psResId.setString(2, user.getEmail());
            psResId.setString(3, user.getLogin());

            ResultSet rs = psResId.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке добавления пользователя.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для получения всех пользователей бд.
     * @return список пользователей типов.
     */
    public ArrayList<UserTask> getAllEntities() {
        ArrayList<UserTask> result = new ArrayList<UserTask>();
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT u.id, u.name, u.email, u.login, u.password, u.date_created, u.role_id, u.adress_id, a.address, r.name "
                + "FROM users_task as u LEFT JOIN addresses_task AS a ON u.adress_id = a.id LEFT JOIN roles_task AS r ON u.role_id = r.id")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(7), rs.getString(10));
                Address address = new Address(rs.getInt(8), rs.getString(9));
                UserTask user = new UserTask(rs.getString(2), rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), role, address);
                addMusicTypes(user);
                result.add(user);
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить список пользователей.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для удаления пользователя из бд.
     * @param user пользователь
     */
    public void deleteEntity(UserTask user) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM users_task WHERE users_task.id = ?")) {
            ps.setInt(1, user.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке удалить пользователя.", e);
        }
        connManager.closeConnection(conn);
    }

    /**
     * Метод для изменения пользователя в бд.
     * @param user пользователь
     */
    public void updateEntity(UserTask user) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("UPDATE users_task SET name = ?, email = ?, login = ?, password = ?, role_id = ?, adress_id = ? WHERE id = ?")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getId());
            ps.setInt(6, user.getAdress().getId());
            ps.setInt(7, user.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке изменить пользователя.", e);
        }
        connManager.closeConnection(conn);
    }

    /**
     * Вспомогательный метод для добавления пользователю списка типов музыки.
     * @param user пользователь
     */
    public void addMusicTypes(UserTask user) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT u.music_type_id, m.name_type FROM user_music AS u LEFT JOIN music_types_task AS m ON u.music_type_id = m.id WHERE u.user_id = ?")) {
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<MusicType> list = new ArrayList<MusicType>();
            while (rs.next()) {
                list.add(new MusicType(rs.getInt(1), rs.getString(2)));
            }
            user.setMusicTypes(list);
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить список музыкальных типов.", e);
        }
        connManager.closeConnection(conn);
    }
}