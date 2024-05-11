package com.asandovalc1.app.clients;

import com.asandovalc1.app.persistence.models.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;


@FeignClient(name = "micro-users")
public interface UserClientFeign {
    @GetMapping("/users/search/find-username")
    public Mono<UserModel> findByUserName(@RequestParam String username);
}
