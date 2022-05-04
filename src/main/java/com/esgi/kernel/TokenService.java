package com.esgi.kernel;

import com.esgi.modules.authentication.domain.Token;

public interface TokenService {
    Token generateToken(String username, String userId);

    String getUserId(Token token);
}
