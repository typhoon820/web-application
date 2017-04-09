package com.nikitaweb.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Никита on 05.04.2017.
 */
public abstract class AbstractDAO <K,E>{

    private final Class<K> persisatnceClass;
    @Autowired
    SessionFactory _sessionFactory ;




    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.persisatnceClass = (Class<K>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }


    protected Session getSession(){
        return _sessionFactory.getCurrentSession();

    }
    public void persist(E entity){
        getSession().persist(entity);
    }

    public void delete (E entity){
        getSession().delete(entity);

    }
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persisatnceClass);
    }
}
