package com.nikitaweb.service;

import com.nikitaweb.dao.AuthorsDao;
import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AuthorsService")
@Transactional
public class AuthorsServiceImpl implements AuthorsService {
    @Autowired
    AuthorsDao authorsDao;

    @Override
    public AuthorsEntity findById(int id) {
        return authorsDao.findById(id);
    }

    @Override
    public AuthorsEntity findByName(String name) {
        return authorsDao.findByName(name);
    }

    @Override
    public void saveAuthor(AuthorsEntity author) {
        authorsDao.saveAuthor(author);
    }

    @Override
    public void deleteAuthor(int id) {
        authorsDao.deleteAuthor(id);
    }

    @Override
    public List<AuthorsEntity> findAllAuthors() {
        return authorsDao.findAllAuthors();
    }
}
