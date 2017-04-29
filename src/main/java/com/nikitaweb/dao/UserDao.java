package com.nikitaweb.dao;

import com.nikitaweb.model.UsersEntity;
import com.sun.javafx.scene.traversal.SceneTraversalEngine;

import java.util.List;

/**
 * Created by Никита on 05.04.2017.
 */
public interface UserDao {
    UsersEntity findById(int id);
    UsersEntity findByLogin(String login);

    void saveUser(UsersEntity user);

    void deleteUser(int id);
    List<UsersEntity> findAllUsers();
}
