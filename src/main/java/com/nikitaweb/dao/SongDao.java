package com.nikitaweb.dao;


import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;

import java.util.List;

public interface SongDao {
    SongsEntity findById(int id);
    SongsEntity findByFullSongName(String songName, AuthorsEntity author);
    void saveSong (SongsEntity song);
    void deleteSong(int id);
    List<SongsEntity> findAllSongs();
    List<SongsEntity> findByAuthor(AuthorsEntity author);
    List<SongsEntity> findByName(String songName);
}
