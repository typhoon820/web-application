package com.nikitaweb.dao;

import com.nikitaweb.model.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDAO<Integer,UsersEntity> implements UserDao {
    @Override
    public UsersEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(UsersEntity.class);
        criteria.add(Restrictions.eq("idUser",id));
        return (UsersEntity)criteria.uniqueResult();
    }
    public void updateUser (UsersEntity user){
        getSession().update(user);
    }
    @Override
    public void saveUser(UsersEntity user) {
        persist(user);

    }

    @Override
    public void deleteUser(int id) {
        Query query = getSession().createSQLQuery("delete from users where ID_user = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public List<UsersEntity> findAllUsers() {
        Criteria criteria = getSession().createCriteria(UsersEntity.class);
        return (List<UsersEntity>) criteria.list();
    }
}