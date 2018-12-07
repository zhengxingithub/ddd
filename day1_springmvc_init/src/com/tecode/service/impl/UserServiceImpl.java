package com.tecode.service.impl;

import com.tecode.dao.UserDao;
import com.tecode.model.User;
import com.tecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/11/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.selectAll();
    }

    @Override
    public void addUser(User user) {
       userDao.insertUser(user);
    }

    @Override
    public void updateUserById(User user) {
        userDao.updateUserById(user);
    }

    @Override
    public void deleteUserById(User user) {
        userDao.deleteUserById(user);
    }

    @Override
    public User login(String username, String password) {
        List<User> users = (List<User>) userDao.login(username,password);
        User user = null;
        if(users.size()>0){
            user = users.get(0);
        }
        return user;
    }

    @Override
    public boolean check(String username) {
        List<User> users =  (List<User>) userDao.check(username);
        if(users.size()>0){
            return true;
        }
        return false;
    }
}
