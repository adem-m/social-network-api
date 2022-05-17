package com.esgi.kernel;

import com.esgi.modules.user.domain.User;

public class CoreUserMapper {
    public static CoreUserResponse map(User user) {
        return new CoreUserResponse(
                user.id().getValue(),
                String.format("%s %s", user.getFirstname(), user.getLastname()));
    }
}
