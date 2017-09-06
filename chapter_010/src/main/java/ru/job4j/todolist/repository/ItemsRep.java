package ru.job4j.todolist.repository;

import org.hibernate.Session;
import ru.job4j.todolist.models.Item;
import ru.job4j.todolist.utils.ConnectionPool;
import java.util.List;

/**
 * Class for working with db.
 * @author agavrikov
 * @since 29.08.2017
 * @version 1
 */
public class ItemsRep {

    /**
     * Instance of.
     */
    private static final ItemsRep INSTANCE_OF = new ItemsRep();

    /**
     * Pool of connections with db.
     */
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.instanceOf();

    /**
     * Method for getting instance of.
     * @return instance of
     */
    public static ItemsRep instanceOf() {
        return INSTANCE_OF;
    }

    /**
     * Method for adding item.
     * @param item item
     * @throws Exception exception
     */
    public void addItem(Item item) throws Exception {
        Session session = CONNECTION_POOL.getConnection();
        boolean result = false;
        try {
            session.save(item);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        CONNECTION_POOL.closeConnection(session);
    }

    /**
     * Method for getting list of all items.
     * @return list of all items
     */
    public List<Item> getAllItems() {
        Session session = CONNECTION_POOL.getConnection();
        List<Item> list = session.createQuery("from Item").list();
        CONNECTION_POOL.closeConnection(session);
        return list;
    }

    /**
     * Method for getting list of items with some filter.
     * @param nameFields name field.
     * @param val value.
     * @param condition condition.
     * @return list of filtered items
     */
    public List<Item> getItemsWithFilter(String nameFields, String val, String condition) {
        Session session = CONNECTION_POOL.getConnection();
        String hql = String.format("from Item where %s %s :val", nameFields, condition);
        List<Item> list = session.createQuery(hql).setParameter("val", val).list();
        CONNECTION_POOL.closeConnection(session);
        return list;
    }
}
