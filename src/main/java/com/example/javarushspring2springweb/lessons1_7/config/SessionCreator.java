package com.example.javarushspring2springweb.lessons1_7.config;


import com.example.javarushspring2springweb.lessons1_7.entity.Order;
import com.example.javarushspring2springweb.lessons1_7.entity.Product;
import com.example.javarushspring2springweb.lessons1_7.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SessionCreator implements AutoCloseable {
    private final SessionFactory sessionFactory;
    private Session session;

    public SessionCreator(
            @Value("${hibernate.connection.url}") String url,
            @Value("${hibernate.connection.driver_class}") String driver,
            @Value("${hibernate.connection.username}") String userName,
            @Value("${hibernate.connection.password}") String password,
            @Value("${hibernate.connection.default_schema}") String schema,
            @Value("${hibernate.show_sql}") String showSql,
            @Value("${hibernate.format_sql}") String formatSql,
            @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl
    ) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(Product.class);
        Properties properties = new Properties();
        properties.setProperty(Environment.URL, url);
        properties.setProperty(Environment.DRIVER, driver);
        properties.setProperty(Environment.USER, userName);
        properties.setProperty(Environment.PASS, password);
        properties.setProperty(Environment.DEFAULT_SCHEMA, schema);
        properties.setProperty(Environment.SHOW_SQL, showSql);
        properties.setProperty(Environment.FORMAT_SQL, formatSql);
        properties.setProperty(Environment.HBM2DDL_AUTO, hbm2ddl);
        configuration.setProperties(properties);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        this.sessionFactory = configuration.buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

    public Session getSession() {
        if (!session.isOpen()) {
            System.out.println("open new session");
            session = sessionFactory.openSession();
            return sessionFactory.openSession();
        } else {
            System.out.println("return existing session");
            return session;
        }
    }

    @Override
    public void close() {
        sessionFactory.close();
    }

    public void beginTransactional() {
        getSession().beginTransaction();
    }

    public void endTransactional() {
        try {
            getSession().getTransaction().commit();
        } catch (RuntimeException e) {
            getSession().getTransaction().rollback();
            throw e;
        }
    }

}
