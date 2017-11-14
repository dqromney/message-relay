package com.dqr.messagerelay.dao;

import com.dqr.messagerelay.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserDao extends PagingAndSortingRepository<User, Long> {

    @Override
    @RestResource(exported = false)
    <S extends User> S save(S arg0);

    /**
     * Retrieves a user by their email
     *
     * @param email User's {@link String} email
     * @return User the {@link User} object
     */
    public User findByEmail(@Param("email") String email);
}
