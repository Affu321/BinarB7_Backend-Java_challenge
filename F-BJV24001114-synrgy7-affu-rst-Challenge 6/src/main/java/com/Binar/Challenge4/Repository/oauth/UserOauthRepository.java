package com.Binar.Challenge4.Repository.oauth;

import com.Binar.Challenge4.Entity.oauth.UserOauth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserOauthRepository extends PagingAndSortingRepository<UserOauth, Long> {
    @Query("FROM UserOauth u WHERE LOWER(u.username) = LOWER(?1)")
    UserOauth findOneByUsername(String username);

    @Query("FROM UserOauth u WHERE u.otp = ?1")
    UserOauth findOneByOTP(String otp);

    @Query("FROM UserOauth u WHERE LOWER(u.username) = LOWER(:username)")
    UserOauth checkExistingEmail(String username);
}
