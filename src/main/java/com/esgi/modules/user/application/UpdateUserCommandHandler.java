package com.esgi.modules.user.application;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.file.application.DeleteImageCommand;
import com.esgi.modules.file.application.ResizeImageCommand;
import com.esgi.modules.file.application.SaveImageCommand;
import com.esgi.modules.security.application.EncryptPasswordCommand;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public class UpdateUserCommandHandler implements CommandHandler<UpdateUser, UserId> {
    private final static int DEFAULT_IMAGE_SIZE = 200;
    private final static List<String> IMAGE_EXTENSIONS = List.of("image/jpeg", "image/png");

    private final UserRepository userRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final CommandBus commandBus;
    @Value("${image.thumbnail.suffix}")
    private String thumbnailSuffix;

    public UpdateUserCommandHandler(UserRepository userRepository, EventDispatcher<Event> eventEventDispatcher, CommandBus commandBus) {
        this.userRepository = userRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.commandBus = commandBus;
    }

    public UserId handle(UpdateUser updateUser) {
        var userId = new UserId(updateUser.userId);
        var user = userRepository.findById(userId);
        if (updateUser.email.getEmail() != null &&
                !updateUser.email.getEmail().equals("") &&
                !user.getEmail().getEmail().equals(updateUser.email.getEmail())) {
            user.changeEmail(updateUser.email);
        }
        if (updateUser.password != null &&
                !updateUser.password.equals("") &&
                !user.getPassword().equals(updateUser.password)) {
            String encryptedPassword = (String) commandBus.send(new EncryptPasswordCommand(updateUser.password));
            user.changePassword(encryptedPassword);
        }
        String imageName;
        try {
            if (updateUser.image != null) {
                if(user.getImage() != null) {
                    DeleteImageCommand deleteImage = new DeleteImageCommand(user.getImage());
                    commandBus.send(deleteImage);
                }
                if (!IMAGE_EXTENSIONS.contains(updateUser.image.getContentType()))
                    throw new InvalidImageException("Invalid image type " + updateUser.image.getContentType());

                imageName = UUID.randomUUID().toString();
                byte[] resizedImage = (byte[]) commandBus.send(new ResizeImageCommand(
                        updateUser.image.getBytes(),
                        DEFAULT_IMAGE_SIZE,
                        DEFAULT_IMAGE_SIZE));
                commandBus.send(new SaveImageCommand(updateUser.image.getBytes(), imageName));
                commandBus.send(new SaveImageCommand(resizedImage, imageName + thumbnailSuffix));
                user.changeImage(imageName);
            } else {
                if(user.getImage() != null) {
                    DeleteImageCommand deleteImage = new DeleteImageCommand(user.getImage());
                    commandBus.send(deleteImage);
                }
                user.changeImage(null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userRepository.add(user);
        eventEventDispatcher.dispatch(new UpdateUserEvent(userId));
        return userId;
    }
}
