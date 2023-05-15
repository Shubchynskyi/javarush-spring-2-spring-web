package com.example.javarushspring2springweb.repository;

import com.example.javarushspring2springweb.config.SessionCreator;
import com.example.javarushspring2springweb.entity.Product;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class ProductRepo implements AbstractRepo<Product> {

    private final SessionCreator sessionCreator;


    public Product getById(Long id) {
        Session session = sessionCreator.getSession();
        return session.get(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        String hql = """
                SELECT p FROM Product p
                ORDER BY p.id
                """;
        Session session = sessionCreator.getSession();
        return session.createQuery(hql, Product.class).list();
    }

    @Override
    public void create(Product type) {
        Session session = sessionCreator.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(type);
        transaction.commit();
    }
}
