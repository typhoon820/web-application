package com.nikitaweb.service;

import com.nikitaweb.dao.SongDao;
import com.nikitaweb.model.SongsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SongService")
@Transactional
public class SongServiceImpl implements SongService {

    @Autowired
    SongDao songDao;
    @Override
    public SongsEntity findById(int id) {
        return songDao.findById(id);
    }

    @Override
    public void saveSong(SongsEntity song) {
        songDao.saveSong(song);
    }

    @Override
    public void deleteUser(int id) {
        songDao.deleteSong(id);
    }

    @Override
    public List<SongsEntity> findAllSongs() {
        return songDao.findAllSongs();
    }
}
