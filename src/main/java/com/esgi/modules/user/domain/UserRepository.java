package com.esgi.modules.user.domain;

import com.esgi.kernel.Repository;

import java.util.List;

public interface UserRepository extends Repository<UserId, User> {
    List<User> findAll();
    List<User> findByName(String name);
}
