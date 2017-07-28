package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by gavrikov.a on 28/07/2017.
 */
public class DbIterator {

    /**
     * Поле для хранения объекта логгирования.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbIterator.class);

    private DataConnection dataConnection;

    public DbIterator() {
        this.dataConnection = new DataConnection("localhost", "5432", "postgres", "12345678", "task", "postgresql");
    }

    public boolean tableIsNotExist(String tableName) {
        boolean result = true;
        try {
            Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
            PreparedStatement ps = conn.prepareStatement("select table_name from information_schema.tables where table_name = ?;");
            ps.setString(1, tableName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = false;
            }
            ps.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public void createTable(String tableName) {
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword())) {
            PreparedStatement ps = conn.prepareStatement("CREATE TABLE ?");
            ps.setString(1, tableName);
            ResultSet rs = ps.executeQuery();
            ps.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public boolean tableEmpty (String tableName) {
        boolean result = false;
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword())) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ?");
            ps.setString(1, tableName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = true;
                break;
            }
            ps.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public void clearTable (String tableName) {
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword())) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ?");
            ps.setString(1, tableName);
            ResultSet rs = ps.executeQuery();
            ps.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void createEntryInDb(int count, String tableName) {
        if(tableIsNotExist(tableName)) {
            createTable(tableName);
        } else if(!tableEmpty(tableName)) {
            clearTable(tableName);
        }
    }

    public static void main(String[] args) {
        DbIterator it = new DbIterator();
        it.createEntryInDb(10, "tesst");
    }

}
