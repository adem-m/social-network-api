package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.modules.challenge.application.AddChallengeEntryCommand;
import com.esgi.modules.challenge.application.AddChallengeEntryCommandHandler;
import com.esgi.modules.challenge.domain.ChallengeEntryRepository;
import com.esgi.modules.challenge.infastructure.SpringDataChallengeEntryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChallengeConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public ChallengeConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public ChallengeEntryRepository challengeEntryRepository() {
        return new SpringDataChallengeEntryRepository();
    }

    @Bean
    public AddChallengeEntryCommandHandler addChallengeEntryCommandHandler() {
        return new AddChallengeEntryCommandHandler(kernelConfiguration.commandBus(), challengeEntryRepository());
    }

    @Bean
    public CommandBus challengeCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(AddChallengeEntryCommand.class, addChallengeEntryCommandHandler());
        return commandBus;
    }
}
