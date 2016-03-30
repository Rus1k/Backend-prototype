package com.rasulov.model.dao.impl;

import com.rasulov.model.User;
import com.rasulov.model.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
@Slf4j
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("select c from User c");
        return (List<User>) query.getResultList();
    }

    @Override
    public User findByUserName(String searchName) {
        List<User> listUsers = entityManager.createQuery(
                "select c from User c where c.name = :searchName")
                .setParameter("searchName", searchName.trim() + "%").getResultList();
        if (listUsers.isEmpty()) {
            log.info("user with name {} not found",searchName);
            return null;
        }
        log.info("found user");
        return listUsers.get(0);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.getReference(User.class, user.getId()));
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }
}
