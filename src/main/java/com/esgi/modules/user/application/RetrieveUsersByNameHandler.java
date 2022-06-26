package com.esgi.modules.user.application;

import com.esgi.kernel.QueryBus;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.file.application.RetrieveImageQuery;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Slf4j
public class RetrieveUsersByNameHandler implements QueryHandler<RetrieveUsersByName, List<User>> {
    private final UserRepository userRepository;
    private final QueryBus queryBus;
    @Value("${image.thumbnail.suffix}")
    private String thumbnailSuffix;

    public RetrieveUsersByNameHandler(UserRepository userRepository, QueryBus queryBus) {
        this.userRepository = userRepository;
        this.queryBus = queryBus;
    }

    @Override
    public List<User> handle(RetrieveUsersByName query) {
        log.info("Retrieving users by name {}", query.name);
        List<User> users = userRepository.findByName(query.name);
        for (User user : users) {
            if (user.getImage() != null && !user.getImage().equals("")) {
                String imageName = query.thumbnail ? user.getImage() + thumbnailSuffix : user.getImage();
                String image = (String) queryBus.send(new RetrieveImageQuery(imageName));
                user.setImage(image);
            }
        }
        return users;
    }
}
