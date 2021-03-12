package dao;

import entities.Client;
import entities.Route;
import enums.Country;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class RouteDAO implements DAO<Route, Integer>{

    Session session = sessionFactory.openSession();

    @Override
    public Optional<Route> getById(Integer id) {
        session = sessionFactory.openSession();
        Optional<Route> el = Optional.of(session.get(Route.class, id));
        session.close();
        return el;
    }

    @Override
    public List<Route> getAll() {
        session = sessionFactory.openSession();
        List<Route> list = session.createQuery("FROM Route ").list();
        session.close();
        return list;
    }

    @Override
    public void save(Route entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Route entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Route entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }
}
