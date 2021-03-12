package dao;

import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User, String> {

    Session session = sessionFactory.openSession();

    @Override
    public Optional<User> getById(String login) {
        session = sessionFactory.openSession();
        Optional<User> el = Optional.ofNullable(session.get(User.class, login));
        session.close();
        return el;
    }

    @Override
    public List<User> getAll() {
        session = sessionFactory.openSession();
        List<User> list = session.createQuery("FROM User").list();
        session.close();
        return list;
    }

    @Override
    public void save(User entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(User entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(User entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }
}
