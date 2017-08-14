package ru.job4j.task.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.task.entity.MusicType;
import ru.job4j.task.utils.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Класс, реализующий работу с музыкальными типами на уровне бд.
 * @author agavrikov
 * @since 10.08.2017
 * @version 1
 */
public class MusicTypeDao implements EntityDao<MusicType>{

    /**
     * Поле для хранения объекта логгирования.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AddressDao.class);

    /**
     * Поле для хранения менеджера соединений с бд.
     */
    private final ConnectionPoolManager connManager = ConnectionPoolManager.getInstance();

    /**
     * Метод для получения музыкального типа из бд по идентификатору.
     * @param id идентификатор
     * @return музыкальный тип
     */
    public MusicType getById(int id) {
        MusicType result = null;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT id, name_type FROM music_types_task WHERE music_types_task.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new MusicType(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить музыкальный тип по id.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для добавления музыкального типа в бд.
     * @param musicType адрес
     * @return идентификатор музыкального типа в бд
     */
    public int add(MusicType musicType) {
        int result = 0;
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO music_types_task (name_type) VALUES (?) ");
             PreparedStatement psId = conn.prepareStatement("SELECT MAX(id) FROM music_types_task WHERE music_types_task.name_type = ?")) {
            ps.setString(1, musicType.getType());
            ps.execute();

            psId.setString(1, musicType.getType());
            ResultSet rs = psId.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке добавления музыкального типа.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для получения всех музыкальных типов из бд.
     * @return список музыкальных типов.
     */
    public ArrayList<MusicType> getAllEntities() {
        ArrayList<MusicType> result = new ArrayList<MusicType>();
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT id, name_type FROM music_types_task")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new MusicType(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке получить всех музыкальные типы.", e);
        }
        connManager.closeConnection(conn);
        return result;
    }

    /**
     * Метод для типа музыки из бд.
     * @param musicType тип музыки
     */
    public void deleteEntity(MusicType musicType) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM music_types_task WHERE music_types_task.id = ?")) {
            ps.setInt(1, musicType.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке удалить музыкальный тип.", e);
        }
        connManager.closeConnection(conn);
    }

    /**
     * Метод для изменения типа музыки в бд.
     * @param musicType тип музыки
     */
    public void updateEntity(MusicType musicType) {
        Connection conn = connManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("UPDATE music_types_task SET name_type = ? WHERE id = ?")) {
            ps.setString(1, musicType.getType());
            ps.setInt(2, musicType.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд, при попытке изменить музыкальный тип.", e);
        }
        connManager.closeConnection(conn);
    }
}
