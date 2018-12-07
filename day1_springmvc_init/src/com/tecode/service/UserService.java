package com.tecode.service;

import com.tecode.model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/11/12.
 */
public interface UserService {

    User getUserById(int id);

    List<User> getAll();

    void addUser(User user);

    void updateUserById(User user);

    void deleteUserById(User user);

    User login(String username,String password);

    boolean check(String username);
}
