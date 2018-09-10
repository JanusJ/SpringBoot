package com.jane.dao;

import com.jane.model.User;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Janus on 2018/9/10.
 */
public class UserRepositoryImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserById(Integer id) {
        User user = entityManager.find(User.class, id);
        System.out.println("UserRepositoryImpl.getUserById()");
        return user;

    }
}
