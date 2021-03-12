package utils;

import entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private SessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                Configuration config = new Configuration().configure();
                config.addAnnotatedClass(User.class);
                config.addAnnotatedClass(Client.class);
                config.addAnnotatedClass(Route.class);
                config.addAnnotatedClass(Trip.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
                sessionFactory = config.buildSessionFactory(builder.build());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
