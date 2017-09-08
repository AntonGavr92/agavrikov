package ru.job4j.lambda;

import java.io.Serializable;

/**
 * Created by gavrikov.a on 08/09/2017.
 */
public class HibernateTemplate {

    <R> Function<Session, R> usingTransaction(Function<Session, R> actions){
        return (session) -> {
            Transaction tx = session.beginTransaction();
            R result = null;
            try {
                result = actions.apply(session);
                tx.commit();
            } catch (Exception e){
                tx.rollback();
            } finally {
                tx,close();
                session.close();
            }
            return result;
        };
    }

    public Function<Session, Serializable> save(Object entity) {
        return this.usingTransaction(session -> session.save(entity));
    }
}
