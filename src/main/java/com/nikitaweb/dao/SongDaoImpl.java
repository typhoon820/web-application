package com.nikitaweb.dao;

import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Никита on 15.04.2017.
 */
@Repository("songDao")
public class SongDaoImpl extends AbstractDAO<Integer,SongsEntity> implements SongDao {

    @Override
    public SongsEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(SongsEntity.class);
        criteria.add(Restrictions.eq("idSong",id));
        return (SongsEntity) criteria.uniqueResult();
    }
    @Override
    public SongsEntity findByFullSongName(String songName, AuthorsEntity author){
        Criteria criteria = getSession().createCriteria(SongsEntity.class);
        criteria.add(Restrictions.eq("songName",songName));
        criteria.add(Restrictions.eq("author", author));
        return (SongsEntity) criteria.uniqueResult();
    }
    @Override
    public void saveSong(SongsEntity song) {
        persist(song);
    }

    @Override
    public void deleteSong(int id) {
        Query query = getSession().createSQLQuery("delete from songs where ID_song = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }
    public void updateSong (SongsEntity song){
        getSession().update(song);
    }
    @Override
    public List<SongsEntity> findAllSongs() {
        Criteria criteria = getSession().createCriteria(SongsEntity.class);
        return (List<SongsEntity>) criteria.list();
    }

    @Override
    public List<SongsEntity> findByAuthor(AuthorsEntity author) {
        Criteria criteria = getSession().createCriteria(SongsEntity.class);
        criteria.add(Restrictions.eq("author", author));
        return (List<SongsEntity>) criteria.list();
    }

    @Override
    public List<SongsEntity> findByName(String songName) {
        Criteria criteria = getSession().createCriteria(SongsEntity.class);
        criteria.add(Restrictions.eq("songName", songName));
        return (List<SongsEntity>) criteria.list();
    }
}
