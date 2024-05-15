package com.asandovalc1.app.service;

import com.asandovalc1.app.persistence.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements ReactiveUserDetailsService {

    @Autowired
    private final WebClient webClient;

    public UserSecurityService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return webClient.get()
                .uri("http://micro-users/users/search/find-username?username={username}", username)
                .retrieve()
                .bodyToMono(UserModel.class)
                .map(userResult -> {
                    List<GrantedAuthority> authorities = userResult.getRoles().stream()
                            .map(roleModel -> new SimpleGrantedAuthority(roleModel.getName()))
                            .collect(Collectors.toList());
                    UserDetails userDetails = new User(userResult.getUsername(), userResult.getPassword(), userResult.getEnabled(), true, true ,true , authorities);
                    return userDetails;
                })
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")));
    }


}
