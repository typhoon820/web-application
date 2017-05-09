package com.nikitaweb.dao;

import com.nikitaweb.model.AuthorsEntity;
import com.nikitaweb.model.SongsEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AuthorsDao")
public class AuthorsDaoImpl extends AbstractDAO<Integer,AuthorsEntity> implements AuthorsDao{
    @Override
    public AuthorsEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(AuthorsEntity.class);
        criteria.add(Restrictions.eq("idAuthor",id));
        return (AuthorsEntity) criteria.uniqueResult();
    }

    @Override
    public AuthorsEntity findByName(String name) {
        Criteria criteria = getSession().createCriteria(AuthorsEntity.class);
        criteria.add(Restrictions.eq("name",name));
        return (AuthorsEntity) criteria.uniqueResult();
    }

    @Override
    public void saveAuthor(AuthorsEntity author) {
        persist(author);
    }

    @Override
    public void deleteAuthor(int id) {
        Query query = getSession().createSQLQuery("delete from songs where ID_author = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public List<AuthorsEntity> findAllAuthors() {
        Criteria criteria = getSession().createCriteria(AuthorsEntity.class);
        return (List<AuthorsEntity>) criteria.list();
    }
}
