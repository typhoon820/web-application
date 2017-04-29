package com.nikitaweb.service;

import com.nikitaweb.dao.UserStatusDao;
import com.nikitaweb.model.UserStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userStatusService")
@Transactional
public class UserStatusServiceImpl implements UserStatusService {
    @Autowired
    UserStatusDao userStatusDao;

    @Override
    public UserStatusEntity findById(int id) {
        return userStatusDao.findById(id);
    }


    @Override
    public void saveStatus(UserStatusEntity status) {
        userStatusDao.saveStatus(status);
    }

    @Override
    public void deleteStatus(int id) {
        userStatusDao.deleteStatus(id);
    }

    @Override
    public List<UserStatusEntity> findAllStatuses() {
        return userStatusDao.findAllStatuses();
    }
}
