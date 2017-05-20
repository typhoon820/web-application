package com.nikitaweb.service;

import com.nikitaweb.model.SongsEntity;
import com.nikitaweb.model.UserStatusEntity;
import com.nikitaweb.model.UsersEntity;

import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
public interface UserService {
    UsersEntity findById(int id);
    UsersEntity findByLogin(String login);

    void saveUser(UsersEntity user);
    void deleteSong(UsersEntity user, SongsEntity song);
    void saveUser(UsersEntity user, boolean flag);
    void addSong(UsersEntity user, SongsEntity song);
    void deleteUser(int id);
    public void deleteUserByLogin(String login);
    List<UsersEntity> findAllUsers();
}
