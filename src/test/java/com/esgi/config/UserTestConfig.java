package com.esgi.config;

import com.esgi.KernelConfiguration;
import com.esgi.UserConfiguration;
import com.esgi.modules.user.domain.UserRepository;
import com.esgi.modules.user.infrastructure.InMemoryUserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@ActiveProfiles("test")
public class UserTestConfig extends UserConfiguration {

    public UserTestConfig(KernelConfiguration kernelConfiguration) {
        super(kernelConfiguration);
        //TODO Auto-generated constructor stub
    }
    
    @Profile("test")
    @Bean
    @Override
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }
}
