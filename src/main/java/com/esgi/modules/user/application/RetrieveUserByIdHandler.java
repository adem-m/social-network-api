package com.esgi.modules.user.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserRepository;

public class RetrieveUserByIdHandler implements QueryHandler<RetrieveUserById, User> {

    private final UserRepository userRepository;

    public RetrieveUserByIdHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(RetrieveUserById query) {
        return userRepository.findById(query.id);
    }
}
