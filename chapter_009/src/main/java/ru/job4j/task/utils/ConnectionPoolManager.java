package ru.job4j.task.utils;

import java.sql.Connection;

/**
 * Created by gavrikov.a on 10/08/2017.
 */
public class ConnectionPoolManager {

    private static final ConnectionPoolManager POOL_MANAGER = new ConnectionPoolManager();

    private final ConnectionPool connPool;

    private ConnectionPoolManager() {
        DataConnection dataConnection = new DataConnection("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/task", "postgres", "12345678");
        this.connPool = new ConnectionPool(dataConnection,10);
    }

    public static ConnectionPoolManager getInstance() {
        return POOL_MANAGER;
    }

    public Connection getConnection() {
        return this.connPool.getConnection();
    }

    public void closeConnection(Connection conn) {
        this.connPool.closeConnection(conn);
    }
}
