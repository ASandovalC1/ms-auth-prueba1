package com.asandovalc1.app.models.repository;

import com.asandovalc1.app.models.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

//Esto hace que se haca un crud auto (incluyendo service and controller)
@RepositoryRestResource(path = "users")
public interface UserDao extends ListCrudRepository<User, Long> {
    @RestResource(path = "find-username")
    public User findByUsername(@Param("username") String username);
}
