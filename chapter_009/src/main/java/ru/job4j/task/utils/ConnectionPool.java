package ru.job4j.task.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class реализующий пул соединений с бд.
 * @author agavrikov
 * @since 10.08.2017
 * @version 1
 */
public class ConnectionPool {
    /**
     * Очередь, в которой хранятся доступные соединения.
     */
    private ConcurrentLinkedQueue<Connection> availableConns = new ConcurrentLinkedQueue<Connection>();

    /**
     * Очередь, в которой хранятся занятые соединения.
     */
    private ConcurrentLinkedQueue<Connection> usedConns = new ConcurrentLinkedQueue<Connection>();


    /**
     * Поле для хранения данных соединения.
     */
    private DataConnection dataConn;


    /**
     * Конструктор для инициализации.
     * @param initConnCnt количество соединений на старте
     */
    public ConnectionPool(DataConnection dataConnection, int initConnCnt) {
        this.dataConn = dataConnection;
        try {
            Class.forName(this.dataConn.getDbDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < initConnCnt; i++) {
            availableConns.add(createConnection());
        }
    }

    /**
     * Вспомогательный метод, для создание соединений.
     * @return соединение
     */
    private Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.dataConn.getDbUrl(), this.dataConn.getDbUserName(), this.dataConn.getDbPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Метод для получения свободного соединения. Убирает соединение из очереди availableConns и помещает в очередь  usedConns. Если нет свободных соединений - создаем новое.
     * @return  соединение
     */
    public synchronized Connection getConnection() {
        Connection newConn;
        if (availableConns.size() == 0) {
            newConn = createConnection();
        } else {
            newConn = availableConns.poll();
        }
        usedConns.add(newConn);
        return newConn;
    }

    /**
     * Метод для сигнал изации о том что соединение должно быть свободным. Помещает соединение в очередь availableConns.
     * @param conn соединение.
     */
    public synchronized void closeConnection(Connection conn) {
        if (conn != null) {
            if (usedConns.remove(conn)) {
                availableConns.add(conn);
            }
        }
    }
}
