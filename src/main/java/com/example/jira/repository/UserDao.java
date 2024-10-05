package com.example.jira.repository;

import com.example.jira.models.user.User;

public interface UserDao {
    User findUserById();

    User save(User user);
}
