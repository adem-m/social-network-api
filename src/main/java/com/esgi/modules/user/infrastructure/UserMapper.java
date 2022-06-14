package com.esgi.modules.user.infrastructure;

import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId().getValue(),
                user.getEmail().getEmail(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getImage());
    }

    public User toModel(UserEntity userEntity) {
        return new User(
                new UserId(userEntity.getId()),
                userEntity.getLastname(),
                userEntity.getFirstname(),
                new Email(userEntity.getEmail()),
                userEntity.getPassword(),
                userEntity.getImage(),
                false);
    }
}
