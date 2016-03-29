package com.rasulov.model.dao.impl;

import com.rasulov.model.User;
import com.rasulov.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("select c from user c");
        return (List<User>) query.getResultList();
    }

    @Override
    public User findByUserName(String searchName) {
        User user = (User) entityManager.createQuery(
                "select c from user c where c.name = :searchName")
                .setParameter("searchName", searchName.trim() + "%").getSingleResult();
        return user;
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
