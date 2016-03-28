package com.rasulov.model.dao.impl;

import com.rasulov.model.User;
import com.rasulov.model.dao.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("select * from user").getResultList();
    }

    @Override
    public User findByUserName(String searchName) {
        User user = (User) entityManager.createQuery(
                "select c from User c where c.name = :searchName")
                .setParameter("searchName", searchName.trim()+"%").getSingleResult();
        return user;
    }

    @Override
    public void add(User user) {
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
