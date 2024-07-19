package com.challenge7user.challenge7user.Repository.oauth;

import com.challenge7user.challenge7user.entity.oauth.Role;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findOneByName(String name);
    List<Role> findByNameIn(String[] names);
}