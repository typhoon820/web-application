package com.nikitaweb.service;

import com.nikitaweb.model.SongsEntity;
import com.nikitaweb.model.UsersEntity;

import java.util.List;

public interface SongService {
    SongsEntity findById(int id);

    void saveSong(SongsEntity song);

    void deleteUser(int id);
    List<SongsEntity> findAllSongs();
}
