package com.nikitaweb.service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.nikitaweb.dao.UserDao;
import com.nikitaweb.dao.UserStatusDao;
import com.nikitaweb.model.UserStatusEntity;
import com.nikitaweb.model.UsersEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.datatransfer.StringSelection;
import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
@Service ("userService")
@Transactional
public class UserServiceImpl implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserStatusDao userStatusDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UsersEntity findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public UsersEntity findByLogin(String login){return userDao.findByLogin(login);}

    @Override
    public void saveUser(UsersEntity user) {

        UserStatusEntity status = userStatusDao.findById(2);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserStatus(status);
        logger.info("Password is  " +user.getPassword());
        userDao.saveUser(user);
    }
    @Override
    public void saveUser(UsersEntity user, boolean flag){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setUserStatus(status);
        userDao.saveUser(user);
    }
    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void deleteUserByLogin(String login){
        userDao.deleteUser(userDao.findByLogin(login).getIdUser());
    }

    @Override
    public List<UsersEntity> findAllUsers() {
        return userDao.findAllUsers();
    }
}
