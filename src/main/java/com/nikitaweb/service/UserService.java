package com.nikitaweb.service;

import com.nikitaweb.model.UsersEntity;

import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
public interface UserService {
    UsersEntity findById(int id);
    UsersEntity findByLogin(String login);

    void saveUser(UsersEntity user);

    void deleteUser(int id);
    List<UsersEntity> findAllUsers();
}
