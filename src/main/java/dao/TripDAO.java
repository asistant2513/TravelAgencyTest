package dao;

import entities.Route;
import entities.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class TripDAO  implements DAO<Trip, Integer>{

    Session session;

    @Override
    public Optional<Trip> getById(Integer login) {
        session = sessionFactory.openSession();
        Optional<Trip> el = Optional.of(session.get(Trip.class, login));
        session.close();
        return el;
    }

    @Override
    public List<Trip> getAll() {
        session = sessionFactory.openSession();
        List<Trip> list = session.createQuery("FROM Trip").list();
        session.close();
        return list;
    }

    @Override
    public void save(Trip entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Trip entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Trip entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }
}
