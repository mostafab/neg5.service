package org.neg5.jwt;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.neg5.jwt.module.JwtSigningModule;

import java.util.HashMap;

public class SigningKeyBackedJwtManagerImpl implements JwtManager {

    private final String signingKey;
    private final JwtParser jwtParser;

    @Inject
    protected SigningKeyBackedJwtManagerImpl(@Named(JwtSigningModule.JWT_SECRET_PROP_NAME) String signingKey) {
        this.signingKey = signingKey;
        jwtParser = new JwtParserProvider(signingKey).get();
    }

    @Override
    public String buildJwt(JwtData data) {
        JwtBuilder builder = Jwts.builder();
        data.getClaims().forEach(builder::claim);
        return null;
    }

    @Override
    public JwtData readJwt(String token) {
        Jws<Claims> data = jwtParser.parseClaimsJws(token);
        return new JwtData(new HashMap<>(data.getBody()));
    }
}
