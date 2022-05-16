package com.esgi.modules.authentication.domain;

public interface TokenService {
    Token generateToken(String username, String userId);

    String getUserId(Token token);
}
