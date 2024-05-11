package com.asandovalc1.app.service;

import com.asandovalc1.app.clients.UserClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserClientFeign userClientFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.asandovalc1.app.persistence.models.User user = userClientFeign.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("Login Error, the user doest not exists");
        }

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true ,authorities);
    }
}
