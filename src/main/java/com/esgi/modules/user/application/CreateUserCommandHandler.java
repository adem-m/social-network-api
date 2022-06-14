package com.esgi.modules.user.application;

import com.esgi.kernel.*;
import com.esgi.modules.file.application.ResizeImageCommand;
import com.esgi.modules.file.application.SaveImageCommand;
import com.esgi.modules.security.application.EncryptPasswordCommand;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public final class CreateUserCommandHandler implements CommandHandler<CreateUser, UserId> {
    private final static int DEFAULT_IMAGE_SIZE = 200;
    private final static List<String> IMAGE_EXTENSIONS = List.of("image/jpeg", "image/png");

    private final UserRepository userRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final CommandBus commandBus;
    @Value("${image.thumbnail.suffix}")
    private String thumbnailSuffix;

    public CreateUserCommandHandler(UserRepository userRepository, EventDispatcher<Event> eventEventDispatcher, CommandBus commandBus) {
        this.userRepository = userRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.commandBus = commandBus;
    }

    public UserId handle(CreateUser createUser) {
        if (userRepository.findByEmail(createUser.email.getEmail()) != null)
            throw AlreadyExistsException.withEmail(createUser.email);

        String imageName = null;
        try {
            if (createUser.image != null) {
                if (!IMAGE_EXTENSIONS.contains(createUser.image.getContentType()))
                    throw new InvalidImageException("Invalid image type " + createUser.image.getContentType());

                imageName = UUID.randomUUID().toString();
                byte[] resizedImage = (byte[]) commandBus.send(new ResizeImageCommand(
                        createUser.image.getBytes(),
                        DEFAULT_IMAGE_SIZE,
                        DEFAULT_IMAGE_SIZE));
                commandBus.send(new SaveImageCommand(createUser.image.getBytes(), imageName));
                commandBus.send(new SaveImageCommand(resizedImage, imageName + thumbnailSuffix));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final UserId userId = userRepository.nextIdentity();
        String encryptedPassword = (String) commandBus.send(new EncryptPasswordCommand(createUser.password));
        User user = new User(
                userId,
                createUser.lastname,
                createUser.firstname,
                new Email(createUser.email.getEmail()),
                encryptedPassword,
                imageName,
                false);
        userRepository.add(user);
        eventEventDispatcher.dispatch(new CreateUserEvent(userId));
        return userId;
    }
}
