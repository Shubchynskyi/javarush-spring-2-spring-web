package com.example.javarushspring2springweb.lessons1_7.repository;

import com.example.javarushspring2springweb.lessons1_7.config.SessionCreator;
import com.example.javarushspring2springweb.lessons1_7.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class UserRepo implements AbstractRepo<User> {

//    @Autowired
//    public void setSessionCreator(SessionCreator sessionCreator) {
//        this.sessionCreator = sessionCreator;
//    }

    private final SessionCreator sessionCreator;

//    private final Map<Long, User> userMap = Map.of(
//            1L, new User(1L,"User1", "qwerty1"),
//            2L, new User(2L,"User2", "qwerty2"),
//            3L, new User(3L,"User3", "qwerty3"),
//            4L, new User(4L,"User4", "qwerty4")
//    );

    public User getById(Long id) {
        Session session = sessionCreator.getSession();
        return session.get(User.class, id);
    }

    @Override
    public List<User> getAll() {
        String hql = """
                SELECT u FROM User u
                ORDER BY u.id
                """;
        Session session = sessionCreator.getSession();
        return session.createQuery(hql, User.class).list();
    }

    @Override
    public void create(User type) {
        Session session = sessionCreator.getSession();
        session.persist(type);
    }
}
