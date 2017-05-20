package com.nikitaweb.dao;

import com.nikitaweb.model.SongsEntity;
import com.nikitaweb.model.UsersEntity;
import com.sun.javafx.scene.traversal.SceneTraversalEngine;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
public interface UserDao {
    UsersEntity findById(int id);
    UsersEntity findByLogin(String login);
    void saveUser(UsersEntity user);
    void updateUser(UsersEntity user);
    void deleteUser(int id);
    List<UsersEntity> findAllUsers();
    void deleteSong(UsersEntity user, SongsEntity song);
    void addSong(UsersEntity user, SongsEntity song);
}
