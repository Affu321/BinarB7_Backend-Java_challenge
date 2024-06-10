package com.Binar.Challenge4.Repository.oauth;

import com.Binar.Challenge4.Entity.oauth.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Client findOneByClientId(String clientId);
}

