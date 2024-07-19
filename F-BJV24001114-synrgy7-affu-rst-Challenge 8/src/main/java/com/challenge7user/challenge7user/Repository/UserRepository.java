package com.challenge7user.challenge7user.Repository;

import com.challenge7user.challenge7user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    @Query(value = "select u from User u")
    public List<User> getAllPage();

    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User getByIdUser(@Param("id") UUID id);
    @Query("FROM User u")
    public Page<User> getAllDataPage(Pageable pageable);
}
