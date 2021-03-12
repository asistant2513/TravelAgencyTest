package dao;

import org.hibernate.SessionFactory;
import utils.SessionFactoryUtil;

import java.util.List;
import java.util.Optional;

public interface DAO<T1, T2> {

    SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();

    Optional<T1> getById(T2 id);

    List<T1> getAll();

    void save(T1 entity);

    void update(T1 entity);

    void delete(T1 entity);
}
