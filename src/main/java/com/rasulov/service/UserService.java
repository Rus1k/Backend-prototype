package com.rasulov.service;

import com.rasulov.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    Boolean add(User user);

    void delete(User user);

    void update(User user);

}
