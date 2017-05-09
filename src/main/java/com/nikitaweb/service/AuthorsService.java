package com.nikitaweb.service;

import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;

import java.util.List;

public interface AuthorsService {
    AuthorsEntity findById(int id);
    AuthorsEntity findByName(String name);
    void saveAuthor(AuthorsEntity author);

    void deleteAuthor(int id);
    List<AuthorsEntity> findAllAuthors();
}
