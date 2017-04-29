package com.nikitaweb.service;

import com.nikitaweb.model.UserStatusEntity;

import java.util.List;

/**
 * Created by Никита on 27.04.2017.
 */
public interface UserStatusService {
    UserStatusEntity findById(int id);

    void saveStatus(UserStatusEntity user);

    void deleteStatus(int id);
    List<UserStatusEntity> findAllStatuses();
}
