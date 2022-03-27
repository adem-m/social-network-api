package com.esgi.modules.user.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserRepository;

import java.util.List;

public class RetrieveUsersHandler implements QueryHandler<RetrieveUsers, List<User>> {

    private final UserRepository userRepository;

    public RetrieveUsersHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(RetrieveUsers query) {
        return userRepository.findAll();
    }
}

