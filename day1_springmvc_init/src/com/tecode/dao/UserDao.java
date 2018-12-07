package com.tecode.dao;

import com.tecode.model.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/11/12.
 */
@Repository
public class UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public User getUserById(int id){
        return hibernateTemplate.load(User.class,id);
    }

    public List<User> selectAll(){
        return hibernateTemplate.loadAll(User.class);
    }

    public void insertUser(User user){
       hibernateTemplate.save(user);
    }

    public void updateUserById(User user){
        hibernateTemplate.update(user);
    }

    public void deleteUserById(User user){
        hibernateTemplate.delete(user);
    }

    public List<?> login(String username,String password){
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        return hibernateTemplate.findByCriteria(criteria.add(Restrictions.eq("username",username)).add(Restrictions.eq("password",password)));
    }

    public List<?> check(String username){
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        return hibernateTemplate.findByCriteria(criteria.add(Restrictions.eq("username",username)));
    }
}
