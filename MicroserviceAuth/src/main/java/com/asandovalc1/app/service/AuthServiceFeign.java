package com.asandovalc1.app.service;

import com.asandovalc1.app.clients.UserClientFeign;
import com.asandovalc1.app.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class AuthServiceFeign implements AuthService {

    @Autowired
    private UserClientFeign userClientFeign;

    public AuthServiceFeign(UserClientFeign userClientFeign){
        this.userClientFeign = userClientFeign;
    }


}
