package com.example.javarushspring2springweb.repository;

import com.example.javarushspring2springweb.config.SessionCreator;
import com.example.javarushspring2springweb.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class OrderRepo implements AbstractRepo<Order> {

    private final SessionCreator sessionCreator;


    public Order getById(Long id) {
        Session session = sessionCreator.getSession();
        return session.get(Order.class, id);
    }

    @Override
    public List<Order> getAll() {
        String hql = """
                SELECT o FROM Order o
                ORDER BY o.id
                """;
        Session session = sessionCreator.getSession();
        return session.createQuery(hql, Order.class).list();
    }

    @Override
    public void create(Order type) {
        Session session = sessionCreator.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(type);
        transaction.commit();
    }
}
