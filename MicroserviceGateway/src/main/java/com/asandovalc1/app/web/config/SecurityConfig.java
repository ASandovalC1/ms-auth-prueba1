package com.asandovalc1.app.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
//    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig() {
//        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http ,JwtFilter jwtFilter) throws Exception{
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().permitAll()
                )
//                .authorizeExchange(exanges -> exanges
//                        .pathMatchers("/micro-auth/api/**").permitAll()
////                        .pathMatchers("/micro-users/users/search/find-username").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .authorizeExchange(exanges -> exanges
////                                .pathMatchers("/micro-auth/api/**").permitAll()
////                        .pathMatchers("/micro-users/users/search/find-username").permitAll()
//                                .anyExchange().permitAll()
//                )
                .addFilterAfter(jwtFilter,SecurityWebFiltersOrder.FIRST)
                .cors(cors->cors.disable());
//                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
