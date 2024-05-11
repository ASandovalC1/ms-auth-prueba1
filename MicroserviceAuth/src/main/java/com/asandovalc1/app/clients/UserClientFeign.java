package com.asandovalc1.app.clients;

import com.asandovalc1.app.persistence.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "micro-users")
public interface UserClientFeign {
    //https://bcrypt.online/ Encriptación de contraseñas
    @GetMapping("/users/search/find-username")
    public User findByUserName(@RequestParam String username);

}
