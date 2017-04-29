package com.nikitaweb.service;


import com.nikitaweb.dao.UserDao;
import com.nikitaweb.model.UserStatusEntity;
import com.nikitaweb.model.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component(value = "userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        UsersEntity user = userDao.findByLogin(login);
        if (user == null) throw new UsernameNotFoundException("Invalid username or password");

        return null;

    }
    public Collection<? extends GrantedAuthority> getAuthirities(Integer status){
        List<GrantedAuthority> authList = getGrantedAuthorities(getStatus(status));
        return authList;
    }
    public List<String> getStatus(Integer status){
        List<String> statuses = new ArrayList<>();
        if (status.intValue() == 1){
            statuses.add("ROLE_ADMIN");
        }
        else if (status.intValue() == 2){
            statuses.add("ROLE_USER");
        }
        return statuses;
    }
    private List<GrantedAuthority> getGrantedAuthorities(List<String> statuses){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(String role: statuses){
            grantedAuthorities.add(new SimpleGrantedAuthority(role));

        }
        return grantedAuthorities;
    }

}
