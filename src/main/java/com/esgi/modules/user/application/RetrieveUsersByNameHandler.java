package com.esgi.modules.user.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RetrieveUsersByNameHandler implements QueryHandler<RetrieveUsersByName, List<User>> {

    private final UserRepository userRepository;

    public RetrieveUsersByNameHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(RetrieveUsersByName query) {
        log.info("Retrieving users by name {}", query.name);
        return userRepository.findByName(query.name);
    }
}
