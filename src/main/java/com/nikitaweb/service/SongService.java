package com.nikitaweb.service;

import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;
import com.nikitaweb.model.UsersEntity;

import java.util.List;

public interface SongService {
    SongsEntity findById(int id);
    SongsEntity findByFullSongName(String songName, AuthorsEntity author);
    void saveSong(SongsEntity song);

    void deleteUser(int id);
    List<SongsEntity> findAllSongs();
    List<SongsEntity> findByAuthor(AuthorsEntity author);
    List<SongsEntity> findByName(String songName);
}
