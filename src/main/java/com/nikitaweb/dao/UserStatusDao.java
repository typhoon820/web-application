package com.nikitaweb.dao;

import com.nikitaweb.model.UserStatusEntity;
import com.nikitaweb.model.UsersEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserStatusDao {
    UserStatusEntity findById(@Param("id")int id);

    void saveStatus(UserStatusEntity user);

    void deleteStatus(int id);
    List<UserStatusEntity> findAllStatuses();
}
