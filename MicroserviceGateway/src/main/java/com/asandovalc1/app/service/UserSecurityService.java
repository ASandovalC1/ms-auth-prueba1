package com.asandovalc1.app.service;

import com.asandovalc1.app.clients.UserClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements ReactiveUserDetailsService {
    @Autowired
    private UserClientFeign userClientFeign;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userClientFeign.findByUserName(username)
                .map(userResult ->{
                    List<GrantedAuthority> authorities = userResult.getRoles().stream().map(roleModel -> new SimpleGrantedAuthority(roleModel.getName())).collect(Collectors.toList());
                    UserDetails userDetails = new User(userResult.getUsername(), userResult.getPassword(), userResult.getEnabled(), true, true ,true , authorities);
                    return userDetails;
                })
                .switchIfEmpty(Mono.defer(()->{
                    throw new UsernameNotFoundException("User not found");
                }));
    }
}
