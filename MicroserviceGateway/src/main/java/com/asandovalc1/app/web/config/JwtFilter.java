package com.asandovalc1.app.web.config;

//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import com.asandovalc1.app.service.UserSecurityService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
public class JwtFilter implements WebFilter {
    private final JwtUtils jwtUtils;
    private final UserSecurityService userSecurityService;


    public JwtFilter(JwtUtils jwtUtils, UserSecurityService userSecurityService) {
        this.jwtUtils = jwtUtils;
        this.userSecurityService = userSecurityService;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();

        if(path.contains("auth"))
            return chain.filter(exchange);

        String auth = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(auth == null)
            return unauthorized(exchange, "No token was found");

        if(!auth.startsWith("Bearer "))
            return unauthorized(exchange, "Invalid auth");

        String jwt = auth.replace("Bearer ", "");

        if (!this.jwtUtils.isValid(jwt)) {
            return unauthorized(exchange, "Invalid JWT token");
        }

        String username = this.jwtUtils.getUsername(jwt);

        return userSecurityService.findByUsername(username)
                .flatMap(userDetails -> {
                    System.out.println("---------Entro a return----------");
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    return chain.filter(exchange);
                })
                .onErrorResume(e-> unauthorized(exchange, e.getMessage()));

    }
    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
