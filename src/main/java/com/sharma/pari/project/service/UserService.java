package com.sharma.pari.project.service;

import com.sharma.pari.project.model.User;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}
