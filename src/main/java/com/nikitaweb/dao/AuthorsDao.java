package com.nikitaweb.dao;


import com.nikitaweb.model.AuthorsEntity;


import java.util.List;

public interface AuthorsDao {
    AuthorsEntity findById(int id);
    AuthorsEntity findByName(String name);
    void saveAuthor (AuthorsEntity author);
    void deleteAuthor(int id);
    List<AuthorsEntity> findAllAuthors();
}
