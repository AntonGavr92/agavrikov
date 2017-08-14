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
import java.util.List;

/**
 * Класс, реализующий работу с данными на уровне бд и их представление в объекты.
 * @author agavrikov
 * @since 10.08.2017
 * @version 1
 */
public class UserRep {

    /**
     * Поле для хранения dao по пользователям.
     */
    private final UserTaskDao userDao;

    /**
     * Поле для хранения единственного экземпляра класса.
     */
    private final static UserRep USER_REP = new UserRep();

    /**
     * Поле для хранения объекта логгирования.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AddressDao.class);

    /**
     * Поле для хранения менеджера соединений с бд.
     */
    private final ConnectionPoolManager connManager = ConnectionPoolManager.getInstance();

    /**
     * Конструктор синглтона.
     */
    private UserRep() {
        this.userDao = new UserTaskDao();
    }

    /**
     * Метод для получения единственного экземляра класса.
     * @return экземпляр UserRep
     */
    public static UserRep getInstance() {
        return USER_REP;
    }

    /**
     * Метод для получения пользователя по идентификатору.
     * @param id идентификатор пользователя
     * @return объект пользователь
     */
    public UserTask getUserById(int id) {
        return userDao.getById(id);
    }

    /**
     * Метод для получения всех пользователей из бд.
     * @return список пользователей
     */
    public ArrayList<UserTask> getAllUsers() {
        return userDao.getAllEntities();
    }

    /**
     * Метод добавляющий пользователя в бд и дополняющий таблицы со связями пользователя.
     * @param user пользователь
     * @return индекс пользователя в бд.
     */
    public int addUser(UserTask user) {
        int result = userDao.add(user);
        addRelationsUserMusic(result, user.getMusicTypes()); // здесь нам нужен пользователь с идентификатором, при создании UserTask еще не обладает таковым. Поэтому обратимся к бд за цельным объектом.
        return result;
    }

    /**
     * Метод удаляющий пользователя из бд и очищающий таблицы со связями пользователя.
     * @param user пользователь
     */
    public void deleteUser(UserTask user) {
        deleteRelationUserMusicByUserId(user.getId());
        userDao.deleteEntity(user);
    }

    /**
     * Метод обновляющий пользователя из бд и обновляющий таблицы со связями пользователя.
     * @param user пользователь
     */
    public void updateUser(UserTask user) {
        deleteRelationUserMusicByUserId(user.getId());
        userDao.updateEntity(user);
        addRelationsUserMusic(user.getId(), user.getMusicTypes());
    }

    /**
     * Получение списка пользователей по адресу.
     * @param address адрес
     * @return список пользователей
     */
    public UserTask getUserByAddress(String address) {
        UserTask result = null;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT users_task.id FROM users_task LEFT JOIN addresses_task on users_task.adress_id = addresses_task.id WHERE addresses_task.address = ?")) {
            ps.setString(1, address);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = getUserById(rs.getInt(1));
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить пользователя по адресу.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Получение списка пользователей по роли.
     * @param role роль
     * @return список пользователей
     */
    public ArrayList<UserTask> getUsersByRole(Role role) {
        ArrayList<UserTask> result = new ArrayList<UserTask>();
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT u.id, u.name, u.email, u.login, u.password, u.date_created, u.role_id, u.adress_id, a.address, r.name "
                + "FROM users_task as u LEFT JOIN addresses_task AS a ON u.adress_id = a.id LEFT JOIN roles_task AS r ON u.role_id = r.id WHERE u.role_id = ?")) {
            ps.setInt(1, role.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Address address = new Address(rs.getInt(8), rs.getString(9));
                UserTask userTask = new UserTask(rs.getString(2), rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), role, address);
                this.userDao.addMusicTypes(userTask);
                result.add(userTask);
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить пользователей по роли.", e);
        }
        return result;
    }

    /**
     * Получение списка пользователей по типу музыки.
     * @param musicType тип музыки
     * @return список пользователей
     */
    public ArrayList<UserTask> getUsersByMusicType(MusicType musicType) {
        ArrayList<UserTask> result = new ArrayList<UserTask>();
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT u.id, u.name, u.email, u.login, u.password, u.date_created, u.role_id, u.adress_id, a.address, r.name "
                + "FROM users_task as u LEFT JOIN addresses_task AS a ON u.adress_id = a.id LEFT JOIN roles_task AS r ON u.role_id = a.id LEFT JOIN user_music as m ON u.id = m.user_id WHERE m.music_type_id = ?")) {
            ps.setInt(1, musicType.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Address address = new Address(rs.getInt(8), rs.getString(9));
                Role role = new Role(rs.getInt(7), rs.getString(10));
                UserTask userTask = new UserTask(rs.getString(2), rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), role, address);
                this.userDao.addMusicTypes(userTask);
                result.add(userTask);
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить пользователей по типу музки.", e);
        }
        return result;
    }

    /**
     * Метод для поиска пользователя по логину и паролю.
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return в случае успешного поиска возвратит пользователя иначе null.
     */
    public UserTask getUserByLoginPass(String login, String password) {
        UserTask result = null;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM users_task WHERE users_task.login = ? AND users_task.password = ?")) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = getUserById(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для получения всех ролей.
     * @return список ролей
     */
    public ArrayList<Role> getAllRoles() {
        return new RoleDao().getAllEntities();
    }

    /**
     * Метод для получения всех типов музыки.
     * @return список типов музыки
     */
    public ArrayList<MusicType> getAllMusicTypes() {
        return new MusicTypeDao().getAllEntities();
    }

    /**
     * Метод для получения всех адресов.
     * @return список адресов
     */
    public ArrayList<Address> getAllAddress() {
        return new AddressDao().getAllEntities();
    }

    /**
     * Метод для получения роли из бд по идентификатору.
     * @param id идентификатор роли
     * @return роль
     */
    public Role getRoleById(int id) {
        EntityDao<Role> roleDao = new RoleDao();
        return roleDao.getById(id);
    }

    /**
     * Получение адреса из бд по уникальному идентификатору.
     * @param address адрес
     * @return объект адрес
     */
    public Address addAddress(String address) {
        EntityDao<Address> addressDao = new AddressDao();
        return addressDao.getById(addressDao.add(new Address(address)));
    }

    /**
     * Получение музыкального типа из бд по уникальному идентификатору.
     * @param id идентификатор
     * @return объект музыкальный тип
     */
    public MusicType getMusicTypeByid(int id) {
        EntityDao<MusicType> musicTypeDao = new MusicTypeDao();
        return musicTypeDao.getById(id);
    }

    /**
     * Вспомогательный метод для удаления связей пользователя с отношением М:М.
     * @param id идентификатор пользователя
     */
    private void deleteRelationUserMusicByUserId(int id) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM user_music WHERE user_music.user_id = ?")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке удаления связей типов музыки и пользователей.", e);
        }
        connManager.closeConnection(conn);
    }

    /**
     * Вспомогательный метод для добавления связей пользователя с отношением М:М.
     * @param idUser идентификатор пользователя
     * @param musicTypes музыкальные типы пользователя
     */
    private void addRelationsUserMusic(int idUser, List<MusicType> musicTypes) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO user_music (user_id, music_type_id) VALUES (?, ?)")) {
            ps.setInt(1, idUser);
            for (MusicType type : musicTypes){
                ps.setInt(2, type.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке добавления связей типов музыки и пользователей.", e);
        }
        connManager.closeConnection(conn);
    }

}
