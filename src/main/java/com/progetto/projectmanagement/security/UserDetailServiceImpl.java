package com.progetto.projectmanagement.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailServiceImpl implements UserDetailsService  {


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException{

        List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
        gas.add(new GrantedAuthorityImpl("ROLE_USER"));
        UserDetails user = new org.springframework.security.core.userdetails.User(
                username, "1522", true, true, true, true, gas);
       // System.out.println("UserDetails "+ user.getAuthorities());
        return user;
    }
}