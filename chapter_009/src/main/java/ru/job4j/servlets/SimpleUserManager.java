package ru.job4j.servlets;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class описывающий работу с пользователями на уровне бд.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class SimpleUserManager {

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
    private static final SimpleUserManager USER_MANAGER = new SimpleUserManager();

    /**
     * поле для хранения идентификатора роли администратора.
     */
    private static final int ID_ADMIN_ROLE = 0;

    /**
     * Конструктор с модификатором privat(паттерн синглтон).
     */
    private SimpleUserManager() {
        this.dataConnection = new DataConnection("localhost", "5432", "postgres", "12345678", "task", "postgresql");
        this.pool = new ConnectionPool(this.dataConnection, "org.postgresql.Driver", 2);
    }

    /**
     * Геттер идентификатора роли администратора.
     * @return идентификатор административной роли
     */
    public int getIdAdminRole() {
        return ID_ADMIN_ROLE;
    }

    /**
     * Метод для получения экземпляра класса.
     * @return объект SimpleUserManager
     */
    public static SimpleUserManager getManager() {
        return USER_MANAGER;
    }

    /**
     * Метод для добавления пользователя в бд.
     * @param user объект - пользователь.
     */
    public void addUser(User user) {
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO users_s (name, email, login, date_created, password, role) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getLogin());
            ps.setLong(4, System.currentTimeMillis());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getIdRole());
            ps.execute();

        } catch (SQLException e) {
            e.getStackTrace();
        }
        pool.closeConnection(conn);
    }

    /**
     * Метод для удаления пользователя.
     * @param user пользователь
     */
    public void deleteUser(User user) {
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM users_s WHERE users_s.name = ?")) {
            ps.setString(1, user.getName());
            ps.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
        pool.closeConnection(conn);
    }

    /**
     * Метод для изменения пользователя.
     * @param user пользователь
     */
    public void updateUser(User user) {
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("UPDATE users_s SET users_s.email = ?, users_s.login = ? WHERE users_s.name = ?")) {
            ps.setString(3, user.getName());
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getLogin());
            ps.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
        pool.closeConnection(conn);
    }

    /**
     * Метод для поиск пользователя по имени.
     * @param name имя пользователя
     * @return  пользователь
     */
    public User getUserByName(String name) {
        User user = null;
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users_s WHERE users_s.name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(name, rs.getString(3), rs.getString(4), rs.getLong(5));
                break;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        pool.closeConnection(conn);
        return user;
    }


    /******************************************************************************************************/

    /**
     * Метод для получения списка всех пользователей.
     * @return список пользователей.
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<User>();
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users_s")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getInt(7)));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
        pool.closeConnection(conn);
        return list;
    }

    /**
     * Метод для удаления пользователя по идентификатору в бд.
     * @param id идентификатор пользователя
     */
    public void deleteUserById(int id) {
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM users_s WHERE users_s.id = ?")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {

        }
        pool.closeConnection(conn);
    }

    /**
     * Метод для получения пользователя из бд по идентификатору.
     * @param id идентификатор пользователя
     * @return пользователь
     */
    public User getUserById(int id) {
        User user = null;
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users_s WHERE users_s.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getInt(7));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
        pool.closeConnection(conn);
        return user;
    }

    /**
     * Метод для обновления пользователя по идентификатору.
     * @param id идентификатор пользователя
     * @param user объект пользователя с полями на которые нужно зпроизвести замену.
     * @return в случае успешного обновления - true иначе - false
     */
    public boolean updateUserById(int id, User user) {
        boolean result = false;
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("UPDATE users_s SET name = ?, email = ?, login = ?, password = ?, role = ? WHERE id = ?")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getIdRole());
            ps.setInt(6, id);
            ps.execute();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.closeConnection(conn);
        return result;
    }

    /**
     * Метод для поиска пользователя по логину и паролю.
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return в случае успешного поиска возвратит пользователя иначе null.
     */
    public User getUserByLoginPass(String login, String password) {
        User result = null;
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users_s WHERE users_s.login = ? AND users_s.password = ?")) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getInt(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.closeConnection(conn);
        return result;
    }

    /**
     * метод для получения карты всех ролей.
     * @return карта ролей
     */
    public HashMap<Integer, String> getAllRoles() {
        HashMap<Integer, String> result = new HashMap<Integer, String>();
        Connection conn = pool.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM roles_s")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getInt(1), rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.closeConnection(conn);
        return result;
    }
}
