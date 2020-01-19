package com.sharma.pari.project.service;

import com.sharma.pari.project.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
}
