package dao;

import entities.Client;
import enums.Country;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ClientDAO implements DAO<Client, Integer>{

    private Session session;

    @Override
    public Optional<Client> getById(Integer id) {
        session = sessionFactory.openSession();
        Optional<Client> el = Optional.of(session.get(Client.class, id));
        session.close();
        return el;
    }

    @Override
    public List<Client> getAll() {
        session = sessionFactory.openSession();
        List<Client> list = session.createQuery("FROM Client ").list();
        session.close();
        return list;
    }

    @Override
    public void save(Client entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Client entity) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Client entity) {
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }
}
