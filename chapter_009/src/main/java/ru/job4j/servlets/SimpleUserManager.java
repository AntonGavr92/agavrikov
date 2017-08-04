package ru.job4j.servlets;

import java.sql.*;
import java.util.ArrayList;

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
    private final DataConnection dataConnection = new DataConnection("localhost", "5432", "postgres", "12345678", "task", "postgresql");

    /**
     * Метод для добавления пользователя в бд.
     * @param user объект - пользователь.
     */
    public void addUser(User user) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("INSERT INTO users_s (name, email, login, date_created) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getLogin());
            ps.setLong(4, System.currentTimeMillis());
            ps.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    /**
     * Метод для удаления пользователя.
     * @param user пользователь
     */
    public void deleteUser(User user) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(this.dataConnection.urlConnection(), this.dataConnection.getUser(), this.dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("DELETE FROM users_s WHERE users_s.name = ?")) {
            ps.setString(1, user.getName());
            ps.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    /**
     * Метод для изменения пользователя.
     * @param user пользователь
     */
    public void updateUser(User user) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("UPDATE users_s SET users_s.email = ?, users_s.login = ? WHERE users_s.name = ?")) {
            ps.setString(3, user.getName());
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getLogin());
            ps.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    /**
     * Метод для поиск пользователя по имени.
     * @param name имя пользователя
     * @return  пользователь
     */
    public User getUserByName(String name) {
        User user = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(this.dataConnection.urlConnection(), this.dataConnection.getUser(), this.dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users_s WHERE users_s.name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(name, rs.getString(3), rs.getString(4), rs.getLong(5));
                break;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return user;
    }


    /******************************************************************************************************/

    /**
     * Метод для получения списка всех пользователей.
     * @return список пользователей.
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<User>();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(this.dataConnection.urlConnection(), this.dataConnection.getUser(), this.dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users_s")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
        return list;
    }

    /**
     * Метод для удаления пользователя по идентификатору в бд.
     * @param id идентификатор пользователя
     */
    public void deleteUserById(int id) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(this.dataConnection.urlConnection(), this.dataConnection.getUser(), this.dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("DELETE FROM users_s WHERE users_s.id = ?")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {

        }
    }

    /**
     * Метод для получения пользователя из бд по идентификатору.
     * @param id идентификатор пользователя
     * @return пользователь
     */
    public User getUserById(int id) {
        User user = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(this.dataConnection.urlConnection(), this.dataConnection.getUser(), this.dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users_s WHERE users_s.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
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
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(this.dataConnection.urlConnection(), this.dataConnection.getUser(), this.dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("UPDATE users_s SET name = ?, email = ?, login = ? WHERE id = ?")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getLogin());
            ps.setInt(4, id);
            ps.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
