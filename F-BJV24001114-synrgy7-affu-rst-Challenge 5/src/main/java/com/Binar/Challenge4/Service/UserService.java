package com.Binar.Challenge4.Service;


import com.Binar.Challenge4.Entity.RegisterModel.RegisterModel;
import com.Binar.Challenge4.Entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Map addUser(User user);
    Map updateUser(UUID userId, User user);
    Map deleteUser(UUID userId);
    Map getUserById(UUID id);
    Map pagination(int page, int size);
    Optional<List<User>> getAllUser();

}