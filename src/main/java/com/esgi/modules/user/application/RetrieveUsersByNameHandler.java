package com.esgi.modules.user.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserRepository;

import java.util.List;

public class RetrieveUsersByNameHandler implements QueryHandler<RetrieveUsersByName, List<User>> {

    private final UserRepository userRepository;

    public RetrieveUsersByNameHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(RetrieveUsersByName query) {
        return userRepository.findByName(query.name);
    }
}
