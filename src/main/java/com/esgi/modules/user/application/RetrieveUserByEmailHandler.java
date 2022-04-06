package com.esgi.modules.user.application;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserRepository;

public class RetrieveUserByEmailHandler implements QueryHandler<RetrieveUserByEmail, User> {

    private final UserRepository userRepository;

    public RetrieveUserByEmailHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(RetrieveUserByEmail query) {
        if (userRepository.findByEmail(query.email) == null)
            throw NoSuchEntityException.withId(query.email);
        return userRepository.findByEmail(query.email);
    }
}
