package com.challenge7user.challenge7user.Repository.oauth;

import com.challenge7user.challenge7user.entity.oauth.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Client findOneByClientId(String clientId);
}

