package ru.job4j.todolist.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by gavrikov.a on 29/08/2017.
 */
public class ConnectionPool {
    /**
     * Очередь, в которой хранятся доступные соединения.
     */
    private ConcurrentLinkedQueue<Session> availableConns = new ConcurrentLinkedQueue<Session>();

    /**
     * Очередь, в которой хранятся занятые соединения.
     */
    private ConcurrentLinkedQueue<Session> usedConns = new ConcurrentLinkedQueue<Session>();

    /**
     * Объект для создания сессий соединений с бд.
     */
    private  SessionFactory factory;

    /**
     * Поле для хранения единственного экземпляра.
     */
    private static final ConnectionPool INSTANCE_OF = new ConnectionPool(10);

    /**
     * Получение единственного экземпляра пула соединений с бд.
     * @return экземпляр.
     */
    public static ConnectionPool instanceOf() {
        return INSTANCE_OF;
    }

    /**
     * Конструктор для инициализации.
     * @param initConnCnt количество соединений на старте
     */
    public ConnectionPool(int initConnCnt) {
        try {
            this.factory = new Configuration().configure().buildSessionFactory();
            for (int i = 0; i < initConnCnt; i++) {
                availableConns.add(this.factory.openSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для получения свободного соединения. Убирает соединение из очереди availableConns и помещает в очередь  usedConns. Если нет свободных соединений - создаем новое.
     * @return  соединение
     */
    public synchronized Session getConnection() {
        Session newSession;
        if (availableConns.size() == 0) {
            newSession = factory.openSession();
        } else {
            newSession = availableConns.poll();
        }
        usedConns.add(newSession);
        newSession.beginTransaction();
        return newSession;
    }

    /**
     * Метод для сигнал изации о том что соединение должно быть свободным. Помещает соединение в очередь availableConns.
     * @param session соединение.
     */
    public synchronized void closeConnection(Session session) {
        if (session != null) {
            session.getTransaction().commit();
            if (usedConns.remove(session)) {
                availableConns.add(session);
            }
        }
    }
}
