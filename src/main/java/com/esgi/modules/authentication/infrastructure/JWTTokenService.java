package com.esgi.modules.authentication.infrastructure;

import com.esgi.modules.authentication.domain.Token;
import com.esgi.kernel.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


public class JWTTokenService implements TokenService {
    private static final String SECRET_KEY = "SECRET";
    private static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    @Override
    public Token generateToken(String email, String userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(email)
                .setIssuer(userId)
                .signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + EXPIRATION_TIME;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);


        return new Token(builder.compact());
    }

    @Override
    public String getUserId(Token token) {
        Claims body = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token.value()).getBody();
        return body.getIssuer();
    }
}
