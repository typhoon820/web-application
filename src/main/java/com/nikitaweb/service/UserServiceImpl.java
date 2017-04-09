package com.nikitaweb.service;

import com.nikitaweb.dao.UserDao;
import com.nikitaweb.model.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
@Service ("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public UsersEntity findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void saveUser(UsersEntity user) {
        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<UsersEntity> findAllUsers() {
        return userDao.findAllUsers();
    }
}
