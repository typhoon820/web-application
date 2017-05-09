package com.nikitaweb.service;

import com.nikitaweb.dao.AuthorsDao;
import com.nikitaweb.dao.SongDao;
import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SongService")
@Transactional
public class SongServiceImpl implements SongService {
    private static final Logger logger = LoggerFactory.getLogger(SongServiceImpl.class);
    @Autowired
    SongDao songDao;
    @Autowired
    AuthorsDao authorsDao;
    @Override
    public SongsEntity findById(int id) {
        return songDao.findById(id);
    }
    @Override
    public SongsEntity findByFullSongName(String songName, AuthorsEntity author){
        return songDao.findByFullSongName(songName, author);
    }

    @Override
    public void saveSong(SongsEntity song) {
        AuthorsEntity author = authorsDao.findByName(song.getAuthor().getName());
        if(author != null)
            song.setAuthor(author);
        else
        {
            AuthorsEntity newAuthor = new AuthorsEntity();
            newAuthor.setName(song.getAuthor().getName());
            newAuthor.setSongs(null);
            authorsDao.saveAuthor(newAuthor);
            song.setAuthor(authorsDao.findByName(newAuthor.getName()));
        }
        song.setDownloadCount(0);
        song.setUsers(null);
        logger.info("INFO SONG" + song.getAuthor().getName()
                + song.getSongName() +"_"+
                + song.getDownloadCount()+"_");
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
