package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.file.application.*;
import com.esgi.modules.file.domain.FileRepository;
import com.esgi.modules.file.domain.ImageResizeService;
import com.esgi.modules.file.infrastructure.S3FileRepository;
import com.esgi.modules.file.infrastructure.ThumbnailatorImageResizeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfiguration {
    private final KernelConfiguration kernelConfiguration;
    private final S3ClientConfiguration s3ClientConfiguration;

    public FileConfiguration(KernelConfiguration kernelConfiguration, S3ClientConfiguration s3ClientConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
        this.s3ClientConfiguration = s3ClientConfiguration;
    }

    @Bean
    public CreateFileEventListener createFileEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        CreateFileEventListener listener = new CreateFileEventListener();
        dispatcher.addListener(CreateFileEvent.class, listener);
        return listener;
    }

    @Bean
    public ImageResizeService imageResizeService() {
        return new ThumbnailatorImageResizeService();
    }

    @Bean
    public FileRepository fileRepository() {
        return new S3FileRepository(s3ClientConfiguration.s3Client());
    }

    @Bean
    public CommandBus fileCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreateFile.class, new CreateFileCommandHandler(kernelConfiguration.eventDispatcher()));
        commandBus.addHandler(ResizeImageCommand.class, new ResizeImageCommandHandler(imageResizeService()));
        commandBus.addHandler(SaveImageCommand.class, new SaveImageCommandHandler(fileRepository()));
        commandBus.addHandler(DeleteImageCommand.class, new DeleteImageCommandHandler(fileRepository()));
        return commandBus;
    }

    @Bean
    public QueryBus fileQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveImageQuery.class, new RetrieveImageQueryHandler(fileRepository()));
        return queryBus;
    }
}
