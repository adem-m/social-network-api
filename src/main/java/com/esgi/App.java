package com.esgi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Hello Spring!");
        SpringApplication.run(App.class, args);
    }
}
