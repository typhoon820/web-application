package com.nikitaweb.dao;

import com.nikitaweb.model.UserStatusEntity;
import com.nikitaweb.model.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userStatusDao")
public class UserStatusDaoImpl extends AbstractDAO<Integer,UserStatusEntity> implements UserStatusDao {

    private static final Logger logger = LoggerFactory.getLogger(UserStatusDaoImpl.class);
    @Override
    public UserStatusEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(UserStatusEntity.class);
        criteria.add(Restrictions.eq("id",id));
        return (UserStatusEntity)criteria.uniqueResult();
    }


    @Override
    public void saveStatus(UserStatusEntity status) {

        logger.info("status DAO------");
        getSession().save(status);
    }

    @Override
    public void deleteStatus(int id) {
        Query query = getSession().createSQLQuery("delete from user_status where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public List<UserStatusEntity> findAllStatuses() {
        Criteria criteria = getSession().createCriteria(UserStatusEntity.class);
        return (List<UserStatusEntity>) criteria.list();
    }
}
