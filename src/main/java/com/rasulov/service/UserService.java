package com.rasulov.service;

import com.rasulov.model.User;

import java.util.List;

public interface UserService {

    User findByUserName(String userName);

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    boolean isUserNameExist(String userName);

}
