package com.nikitaweb.service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.nikitaweb.dao.UserDao;
import com.nikitaweb.dao.UserStatusDao;
import com.nikitaweb.model.SongsEntity;
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
@Service ("UserService")
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
    public void addSong(UsersEntity user,SongsEntity song){

        userDao.addSong(user,song);
        logger.info("NEW SONG INFO===================: "+ song.getSongName());
        userDao.updateUser(user);
    }
    @Override
    public UsersEntity findByLogin(String login){return userDao.findByLogin(login);}

    @Override
    public void saveUser(UsersEntity user) {

        UserStatusEntity status = userStatusDao.findById(4);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserStatus(status);

        logger.info("Password is  " +user.getPassword());
        userDao.saveUser(user);
    }

    @Override
    public void deleteSong(UsersEntity user, SongsEntity song) {
        userDao.deleteSong(user, song);
        userDao.updateUser(user);
    }

    @Override
    public void saveUser(UsersEntity user, boolean flag){
        user.setPassword(passwordEncoder.encode(user.getPassword())); //TODO: раскомментить это все, с преобрпзователем работает плохо.
                                                                      //TODO: в Thymeleaf изменить выбор роли.
        logger.info("Status is "+ user.getUserStatus().getStatus());
        logger.info("status_id is "+ user.getUserStatus().getId());
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
