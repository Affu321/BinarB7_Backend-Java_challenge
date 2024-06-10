package com.Binar.Challenge4.Repository.oauth;

import com.Binar.Challenge4.Entity.oauth.Role;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findOneByName(String name);
    List<Role> findByNameIn(String[] names);
}

